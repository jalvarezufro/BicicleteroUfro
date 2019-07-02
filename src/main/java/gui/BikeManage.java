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

    /**
     * Constructor of this window.
     *
     * @param title
     * @throws HeadlessException
     */
    public BikeManage() throws HeadlessException {
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

        //crear tabla
        //crear modelo
        Object[] columnNames = {"Marca", "Color", ""};

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(columnNames);
        table = new JTable(modeloTabla) {

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

        Object[] row1 = {"bianchi", "rojo", true};
        modeloTabla.addRow(row1);
        modeloTabla.addRow(row1);
        modeloTabla.addRow(row1);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(16, 20, 543, 120);
        getContentPane().add(scrollPane);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
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
            AddNewBike nBike = new AddNewBike("Ufrocleta: Agregar bicicleta");
            dispose();
        }

        if (e.getSource() == edit) {
            EditBike eBike = new EditBike("Ufrocleta: Editar bicicleta");
            dispose();
        }

        if (e.getSource() == delete) {
            BikeManage bManage = new BikeManage();
            dispose();

        }

        if (e.getSource() == back) {
            EditUser eUser = new EditUser("Ufrocleta: Editar usuario");
            dispose();
        }

    }
}
