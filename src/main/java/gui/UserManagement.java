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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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

        rutTexto = new JTextField("Rut");
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

        cancel = new JButton("Cancelar");
        cancel.setBounds(580, 334, 120, 27);
        cancel.addActionListener(this);
        add(cancel);

        //crear tabla
        //crear modelo
        modeloTabla = new DefaultTableModel();
        Object[] columNames = {"Nombre", "Rut", "Advertencias", ""};
        modeloTabla.setColumnIdentifiers(columNames);

        Object[] p1 = {"1", "2", 1};
        Object[] p11 = {"3", "2", 2};
        Object[] p12 = {"20", "15", 3};
        Object[] p13 = {"26", "3", 2};

        modeloTabla.addRow(p1);
        modeloTabla.addRow(p11);

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
    }

    private void filtros(String query) {
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter2);
        sorter2.setRowFilter(RowFilter.regexFilter(query, 1));

    }

    private void cargarTabla() {

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
            EditUser eUser = new EditUser("Ufrocleta: Editar usuario");
            dispose();
        }

        if (e.getSource() == warning) {
            System.out.println(modeloTabla.getRowCount());

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Object bTemp = modeloTabla.getValueAt(i, 3);
                Object tru = true;
                if (bTemp.equals(tru)) {
                    /*
                    Object tablaTemp =  modeloTabla.getValueAt(i, 2);
                    System.out.println(tablaTemp);
                    String s = (String) tablaTemp;
                    int t = Integer.parseInt(s)+1;
                    System.out.println(s);
                    modeloTabla.setValueAt(t, i, 2);
                     */
                    Object tablaTemp = modeloTabla.getValueAt(i, 2);
                    int s = (int) tablaTemp + 1;
                    modeloTabla.setValueAt(s, i, 2);
                }
            }

        }

        if (e.getSource() == cancel) {
            MainWindow mWindow = new MainWindow();
            dispose();
        }

    }
}
