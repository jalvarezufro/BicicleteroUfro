package gui;

import domProblema.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static javax.swing.ListSelectionModel.*;

import javax.swing.table.*;

public class IdentifyUserBike extends JFrame implements ActionListener {

    //private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton accept;
    private JButton cancel;

    public IdentifyUserBike(String title, User user) {
        this.setLayout(null);
        this.setTitle(title);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(480, 270);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

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

        for (int i = 0; i < user.getBikes().size(); i++) {
            Object[] row = {user.getBikes().get(i).getBrand(), user.getBikes().get(i).getColor(), false};
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 20, 400, 120);
        getContentPane().add(scrollPane);

        accept = new JButton("Aceptar");
        accept.setBounds(45, 180, 192, 27);
        this.getContentPane().add(accept);
        accept.addActionListener(this);

        cancel = new JButton("Cancelar");
        cancel.setBounds(245, 180, 192, 27);
        this.getContentPane().add(cancel);
        cancel.addActionListener(this);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            IdentifyUser iUser = new IdentifyUser("Ufrocleta: Estacionar bicicleta");
            dispose();
        }
        if (e.getSource() == accept) {
            int cont = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                Object bTemp = table.getValueAt(i, 2);
                try {
                    if (bTemp.equals(true)) {
                        cont++;
                    }
                } catch (NullPointerException s) {
                }
            }
            if (cont == 1) {
                MainWindow uManage = new MainWindow();
                dispose();
            } else if (cont > 1) {
                JOptionPane.showMessageDialog(null, "Hay mas de una bicicleta seleccionada");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una bicicleta");
            }

        }

    }
}
