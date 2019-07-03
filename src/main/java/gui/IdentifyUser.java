package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domProblema.Parking;
import domProblema.UserList;

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
        rut.setBounds(144, 63, 192, 27);
        rut.addActionListener(this);
        add(rut);

        accept = new JButton("Aceptar");
        accept.setBounds(32, 108, 192, 27);
        accept.addActionListener(this);
        add(accept);

        cancel = new JButton("Cancelar");
        cancel.setBounds(256, 108, 192, 27);
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
            if (title.equals("Ufrocleta: Estacionar bicicleta")) {
                try {
                    IdentifyUserBike userBike = new IdentifyUserBike("Ufrocleta: Seleccionar bicicleta", UserList.findUserByRut(rut.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar el usuario con ese rut.");
                    ex.printStackTrace();
                }
            }
            if (title.equals("Ufrocleta: Quitar bicicleta")) {
                Parking.unparkBike(rut.getText());
                MainWindow mWindow = new MainWindow();
                dispose();

            }
        }
        if (e.getSource() == cancel) {
            MainWindow main = new MainWindow();
            dispose();
        }
    }
}
