/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domProblema.UserList;
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
import static javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JAVIER
 */
public class BikeManage extends JFrame implements ActionListener {

    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton back;
    private JTable table;
    private DefaultTableModel modeloTabla;
    private String rut;

    /**
     * Constructor of this window.
     *
     * @throws HeadlessException
     */
    public BikeManage(String rut) throws HeadlessException {
        super("Ufrocleta: Gestion de bicicletas");
        this.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(575, 228);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

        add = new JButton("Agregar");
        add.setBounds(16, 152, 120, 27);
        add.addActionListener(this);
        add(add);

        edit = new JButton("Editar");
        edit.setBounds(157, 152, 120, 27);
        edit.addActionListener(this);
        add(edit);

        delete = new JButton("Borrar");
        delete.setBounds(298, 152, 120, 27);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Volver");
        back.setBounds(439, 152, 120, 27);
        back.addActionListener(this);
        add(back);

        this.rut = rut;

        //crear tabla
        //crear modelo
        Object[] columnNames = {"Marca", "Color", ""};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model) {

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
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        table.setSelectionMode(SINGLE_INTERVAL_SELECTION);
        for (int i = 0; i < UserList.findUserByRut(rut).getBikes().size(); i++) {
            Object[] row = {UserList.findUserByRut(rut).getBikes().get(i).getBrand(), UserList.findUserByRut(rut).getBikes().get(i).getColor(), false};
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(16, 20, 543, 120);
        getContentPane().add(scrollPane);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            String nombre = UserList.findUserByRut(rut).getName();
            String correo = UserList.findUserByRut(rut).geteMail();
            String numero = UserList.findUserByRut(rut).getPhone();
            AddNewBike nBike = new AddNewBike("Ufrocleta: Agregar bicicleta", nombre, rut, correo, numero);
            dispose();
        }

        if (e.getSource() == edit) {
            int cont = 0;
            int selected = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                Object bTemp = table.getValueAt(i, 2);
                try {
                    if (bTemp.equals(true)) {
                        cont++;
                        selected = i;
                    }
                } catch (NullPointerException s) {
                }
            }
            if (cont == 1) {
                int bikeID = selected;
                EditBike eBike = new EditBike("Ufrocleta: Editar bicicleta", rut, bikeID);
                dispose();
            } else if (cont > 1) {
                JOptionPane.showMessageDialog(null, "Hay mas de una bicicleta seleccionada");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una bicicleta");
            }

        }

        if (e.getSource() == delete) {
            int cont = 0;
            int selected = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                Object bTemp = table.getValueAt(i, 2);
                try {
                    if (bTemp.equals(true)) {
                        cont++;
                        selected = i;
                    }
                } catch (NullPointerException s) {
                }
            }
            if (cont == 1) {
                int bikeID = selected;
                UserList.findUserByRut(rut).getBikes().remove(bikeID);
                UserList.writeUsers();
                BikeManage bManage = new BikeManage(rut);
                dispose();
            } else if (cont > 1) {
                JOptionPane.showMessageDialog(null, "Hay mas de una bicicleta seleccionada");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una bicicleta");
            }

        }

        if (e.getSource() == back) {
            EditUser eUser = new EditUser("Ufrocleta: Editar usuario", rut);
            dispose();
        }

    }
}
