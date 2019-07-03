/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domProblema.User;
import domProblema.UserList;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author JAVIER
 */
public class EditUser extends JFrame implements ActionListener {

    private String title;
    private JTextField nombre;
    private JTextField rut;
    private JTextField correo;
    private JTextField numero;
    private JButton bike;
    private JButton accept;
    private JButton cancel;
    private String rutUser;
   

    /**
     * Constructor of this window.
     *
     * @param title
     * @param rutUser
     * @throws HeadlessException
     */
    public EditUser(String title, String rutUser) throws HeadlessException {
        super(title);
        this.setLayout(null);

        this.title = title;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(480, 270);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        crearText(rutUser);
        crearButtons();
        // rut.setBounds(16, 9, 192, 27);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.rutUser=rutUser;
    }

    public void crearText(String rutUser) {
        //text nombre
        nombre = new JTextField(UserList.findUserByRut(rutUser).getName());
        nombre.setBounds(16, 27, 192, 27);
        nombre.setEditable(false);
        this.add(nombre);
        //text rut
        rut = new JTextField(UserList.findUserByRut(rutUser).getRut());
        rut.setBounds(16, 77, 192, 27);
        rut.setEditable(false);
        this.add(rut);
        //text correo
        correo = new JTextField(UserList.findUserByRut(rutUser).geteMail());
        correo.setBounds(16, 127, 192, 27);
        this.add(correo);
        //text numero
        numero = new JTextField(UserList.findUserByRut(rutUser).getPhone());
        numero.setBounds(16, 177, 192, 27);
        this.add(numero);
    }

    public void crearButtons() {
        //boton editar bicis
        bike = new JButton("Bicicletas");
        bike.setBounds(240, 27, 192, 27);
        this.add(bike);
        bike.addActionListener(this);
        //boton aceptar usuario
        accept = new JButton("Aceptar");
        accept.setBounds(240, 90, 192, 27);
        this.add(accept);
        accept.addActionListener(this);
        //boton cancelar
        cancel = new JButton("Cancelar");
        cancel.setBounds(240, 150, 192, 27);
        this.add(cancel);
        cancel.addActionListener(this);

    }

    /**
     * Detects user actions on this window and executes accordingly.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bike) {
            BikeManage bManage = new BikeManage(rutUser);
            dispose();
        }
        if (e.getSource() == accept) {
            UserList.findUserByRut(rutUser).seteMail(correo.getText());
            UserList.findUserByRut(rutUser).setPhone(numero.getText());
            UserList.writeUsers();
            UserManagement userMan = new UserManagement();
            dispose();
        }
        if (e.getSource() == cancel) {
            UserManagement userMan = new UserManagement();
            dispose();

        }

    }

}
