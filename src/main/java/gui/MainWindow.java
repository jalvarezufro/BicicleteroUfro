package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {

    private JButton parkBike;
    private JButton unparkBike;
    private JButton manageUsers;

    /**
     * Constructor of this window.
     *
     * @param title
     * @throws HeadlessException
     */
    public MainWindow(String title) throws HeadlessException {
        super(title);
        this.setLayout(null);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(960, 540);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

        parkBike = new JButton("Estacionar bicicleta");
        parkBike.setBounds(640, 9, 192, 27);
        parkBike.addActionListener(this);
        add(parkBike);

        unparkBike = new JButton("Quitar bicicleta");
        unparkBike.setBounds(640, 45, 192, 27);
        unparkBike.addActionListener(this);
        add(unparkBike);

        manageUsers = new JButton("Gestionar usuarios");
        manageUsers.setBounds(640, 81, 192, 27);
        manageUsers.addActionListener(this);
        add(manageUsers);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Detects user actions on this window and executes accordingly.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == parkBike) {
            IdentifyUserBike identifyUser = new IdentifyUserBike("Estacionar bicicleta");
            dispose();
        }
    }
}