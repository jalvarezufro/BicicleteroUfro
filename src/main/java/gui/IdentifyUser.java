package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.MainWindow;

public class IdentifyUser extends JFrame implements ActionListener {

    private String title;
    private JTextField rut;
    private JButton accept;
    private JButton cancel;

    /**
     * Constructor of this window.
     *
     * @param title
     * @throws HeadlessException
     */
    public IdentifyUser(String title) throws HeadlessException {
        super(title);
        this.setLayout(null);

        this.title = title;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(480, 270);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);

        rut = new JTextField("rut");
        rut.setBounds(16, 9, 192, 27);
        rut.addActionListener(this);
        add(rut);

        accept = new JButton("Aceptar");
        accept.setBounds(16, 45, 192, 27);
        accept.addActionListener(this);
        add(accept);

        cancel = new JButton("Cancelar");
        cancel.setBounds(224, 45, 192, 27);
        cancel.addActionListener(this);
        add(cancel);

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
        if (e.getSource() == accept) {
            if (title.equals("Estacionar bicicleta")) {
                IdentifyUserBike userBike = new IdentifyUserBike();
                userBike.setVisible(true);
                dispose();

            } else if (title.equals("Quitar bicicleta")) {

            }
            dispose();
        }
        if (e.getSource() == cancel) {
            MainWindow main = new MainWindow();
            dispose();
        }
    }
}
