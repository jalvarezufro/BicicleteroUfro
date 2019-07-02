package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.ListSelectionModel.*;
import javax.swing.table.*;

public class IdentifyUserBike extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton accept;
    private JButton cancel;

    public IdentifyUserBike() {
        this.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(480, 270);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

        Object[] columnNames = {"Marca", "Color", ""};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

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
    
        Object[] row1 = {"bianchi", "rojo", true};
        model.addRow(row1);
        model.addRow(row1);
        model.addRow(row1);
        System.out.println(model.getValueAt(0, 2));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 20, 400, 120);
        getContentPane().add(scrollPane);

        accept = new JButton("Aceptar");
        accept.setBounds(45, 180, 192, 27);
        this.getContentPane().add(accept);
        accept.addActionListener(this);
        
        cancel = new JButton("Cancelar");
        cancel.setBounds(245,180,192,27);
        this.getContentPane().add(cancel);
        cancel.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getSource() == cancel) {
            IdentifyUser iUser = new IdentifyUser("Estacionar bicicleta");
            dispose();
        }
    }
}
