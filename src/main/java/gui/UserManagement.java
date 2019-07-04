/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import domProblema.UserList;

/**
 *
 * @author JAVIER
 */
public class UserManagement extends JFrame implements ActionListener {

    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton warning;
    private JButton cancel;
    private JTable tabla;
    private JTextField rutTexto;
    private DefaultTableModel modeloTabla;

    /**
     * Constructor of this window.
     *
     * @param title
     * @throws HeadlessException
     */
    public UserManagement() throws HeadlessException {
        super("Ufrocleta: Gestion de usuarios");
        this.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(716, 404);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

        rutTexto = new JTextField("Buscar por rut");
        rutTexto.setBounds(16, 9, 192, 27);
        add(rutTexto);

        add = new JButton("Agregar");
        add.setBounds(16, 334, 120, 27);
        add.addActionListener(this);
        add(add);

        edit = new JButton("Editar");
        edit.setBounds(157, 334, 120, 27);
        edit.addActionListener(this);
        add(edit);

        delete = new JButton("Borrar");
        delete.setBounds(298, 334, 120, 27);
        delete.addActionListener(this);
        add(delete);

        warning = new JButton("Advertir");
        warning.setBounds(439, 334, 120, 27);
        warning.addActionListener(this);
        add(warning);

        cancel = new JButton("Volver");
        cancel.setBounds(580, 334, 120, 27);
        cancel.addActionListener(this);
        add(cancel);

        //crear tabla
        //crear modelo
        modeloTabla = new DefaultTableModel();
        Object[] columNames = {"Nombre", "Rut", "Advertencias", ""};
        modeloTabla.setColumnIdentifiers(columNames);

        //creacion tabla
        tabla = new JTable(modeloTabla) {

            //private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        //row sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        //crear scroll
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(16, 45, 684, 279);

        add(sp);

        //KeyListener
        // agregar keyListener
        KeyListener KeyList = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    String query = rutTexto.getText();
                    rutTexto.setText(query.substring(0, query.length()));
                }

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String query = rutTexto.getText();
                filtros(query);

            }
        };

        this.rutTexto.addKeyListener(KeyList);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //mostrar Usuarios
        for (int i = 0; i < UserList.getUsers().size(); i++) {
            String nombre = UserList.getUsers().get(i).getName();
            String rut = UserList.getUsers().get(i).getRut();
            int advertencias = UserList.getUsers().get(i).getWarnings();
            Object[] rowTemp = {nombre, rut, advertencias};
            modeloTabla.addRow(rowTemp);
        }

    }

    private void filtros(String query) {
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter2);
        sorter2.setRowFilter(RowFilter.regexFilter(query, 1));

    }



    /**
     * Detects user actions on this window and executes accordingly.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            AddNewUser eUser = new AddNewUser("Ufrocleta: Nuevo usuario");
            dispose();
        }

        if (e.getSource() == edit) {
            int cont = 0;
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Object bTemp = modeloTabla.getValueAt(i, 3);
                try {
                    if (bTemp.equals(true)) {
                        cont++;
                    }
                } catch (NullPointerException s) {
                }
            }
            if (cont == 1) {
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    Object bTemp = modeloTabla.getValueAt(i, 3);
                    try {
                        //if para saber si esta el checkbox en true
                        if (bTemp.equals(true)) {
                            String rut = "" + modeloTabla.getValueAt(i, 1);
                            EditUser eUser = new EditUser("Ufrocleta: Editar usuario", rut);
                            dispose();
                        }
                    } catch (NullPointerException s) {

                    }
                }

            } else if (cont > 1) {
                JOptionPane.showMessageDialog(null, "Hay mas de un usuario seleccionado");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario");
            }
        }

        if (e.getSource() == warning) {
            int cont = 0;
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Object bTemp = modeloTabla.getValueAt(i, 3);
                try {
                    if (bTemp.equals(true)) {
                        cont++;
                    }
                } catch (NullPointerException s) {
                }
            }
            if (cont == 1) {
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    Object bTemp = modeloTabla.getValueAt(i, 3);
                    boolean tru = false;
                    try {
                        if (bTemp.equals(true)) {
                            tru = true;
                        }
                    } catch (NullPointerException s) {
                    }
                    if (tru) {

                        Object tablaTemp = modeloTabla.getValueAt(i, 2);
                        int s = (int) tablaTemp + 1;
                        modeloTabla.setValueAt(s, i, 2);
                        UserList.getUsers().get(i).setWarnings(s);
                    } else {

                    }

                }
            } else if (cont > 1) {
                JOptionPane.showMessageDialog(null, "Hay mas de un usuario seleccionado");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario");
            }

        }

        if (e.getSource() == delete) {
            int cont = 0;
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Object bTemp = modeloTabla.getValueAt(i, 3);
                try {
                    if (bTemp.equals(true)) {
                        cont++;
                    }
                } catch (NullPointerException s) {
                }
            }
            if (cont == 1) {
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    Object bTemp = modeloTabla.getValueAt(i, 3);
                    try {
                        //if para saber si esta el checkbox en true
                        if (bTemp.equals(true)) {
                            String rut = "" + modeloTabla.getValueAt(i, 1);
                            UserList.deleteUserByRut(rut);
                            UserList.writeUsers();
                            UserManagement uMan = new UserManagement();
                            dispose();
                            

                        }
                    } catch (NullPointerException s) {

                    }
                }

            } else if (cont > 1) {
                JOptionPane.showMessageDialog(null, "Hay mas de un usuario seleccionado");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario");
            }
        }
        if (e.getSource() == cancel) {
            MainWindow mWindow = new MainWindow();
            dispose();
        }

    }
}
