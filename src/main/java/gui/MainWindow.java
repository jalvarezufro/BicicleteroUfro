 package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
 import domProblema.Parking;

public class MainWindow extends JFrame implements ActionListener {

    private JButton parkBike;
    private JButton unparkBike;
    private JButton manageUsers;
    private JTable tabla;
    private JTextField rutTexto;
    private DefaultTableModel modeloTabla;

    /**
     * Constructor of this window.
     *
     * @param title
     * @throws HeadlessException
     */
    public MainWindow() throws HeadlessException {
        super("Ufrocleta");
        this.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(848, 369);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

        rutTexto = new JTextField("Rut");
        rutTexto.setBounds(16, 9, 192, 27);
        add(rutTexto);

        parkBike = new JButton("Estacionar bicicleta");
        parkBike.setBounds(640, 139, 192, 27);
        parkBike.addActionListener(this);
        add(parkBike);

        unparkBike = new JButton("Quitar bicicleta");
        unparkBike.setBounds(640, 184, 192, 27);
        unparkBike.addActionListener(this);
        add(unparkBike);

        manageUsers = new JButton("Gestionar usuarios");
        manageUsers.setBounds(640, 229, 192, 27);
        manageUsers.addActionListener(this);
        add(manageUsers);

        //crear tabla
        //crear modelo
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Rut");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Espacio");

        String[] p1 = {"203656947", "10:30", "25"};
        String[] p11 = {"203557132", "8:30", "24"};


        modeloTabla.addRow(p1);
        modeloTabla.addRow(p11);

        //creacion tabla
        tabla = new JTable(modeloTabla);

        //row sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        //crear scroll
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(16, 45, 594, 279);

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
        
        //cargar datos pantalla
       String [][] screen = Parking.loadModel();
       for(int i = 0; i<screen.length;i++){
           String [] temp = {screen[i][0],screen[i][1],screen[i][2]};
           modeloTabla.addRow(temp);
       }
        
    }

    private void filtros(String query) {
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter2);
        sorter2.setRowFilter(RowFilter.regexFilter(query, 0));

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
        if (e.getSource() == parkBike) {
            IdentifyUser identifyUser = new IdentifyUser("Ufrocleta: Estacionar bicicleta");
            dispose();
        }
        
        if(e.getSource() == unparkBike){
            IdentifyUser identifyUser = new IdentifyUser("Ufrocleta: Quitar bicicleta");
            dispose();
        }

        if (e.getSource() == manageUsers) {
           UserManagement userMan = new UserManagement();
            dispose();
        }

    }
}
