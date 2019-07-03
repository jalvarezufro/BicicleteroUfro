/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import datos.FileManager;
import domProblema.Bike;
import domProblema.User;
import domProblema.UserList;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JTextField;

/**
 * @author JAVIER
 */
public class AddNewBike extends JFrame implements ActionListener {

    private String title;

    private String nombre;
    private String rut;
    private String correo;
    private String numero;

    private JTextField brand;
    private JTextField color;

    private JButton add;
    private JButton cancel;

    /**
     * Constructor of this window.
     *
     * @param title
     * @throws HeadlessException
     */
    public AddNewBike(String title, String nombre, String rut, String correo, String numero) throws HeadlessException {
        super(title);
        this.setLayout(null);

        this.title = title;
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.numero = numero;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(450, 270);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        crearText();
        crearButtons();
        // rut.setBounds(16, 9, 192, 27);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void crearText() {
        //text nombre
        brand = new JTextField("Marca");
        brand.setBounds(16, 72, 192, 27);
        this.add(brand);
        //text rut
        color = new JTextField("Color");
        color.setBounds(16, 132, 192, 27);
        this.add(color);

    }

    public void crearButtons() {

        //boton aceptar usuario
        add = new JButton("Agregar");
        add.setBounds(240, 72, 192, 27);
        this.add(add);
        add.addActionListener(this);
        //boton cancelar
        cancel = new JButton("Cancelar");
        cancel.setBounds(240, 132, 192, 27);
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

        if (e.getSource() == add) {
            if (title.equals("Ufrocleta: Primera bicicleta")) {
                ArrayList<Bike> bikes = new ArrayList<>();
                bikes.add(new Bike(color.getText(), brand.getText()));
                UserList.addUser(bikes, correo, nombre, rut, numero, 0);
                UserList.writeUsers();
                UserManagement uMan = new UserManagement();
                dispose();
               
            } else {
                UserList.findUserByRut(rut).getBikes().add(new Bike(color.getText(),brand.getText()));
                UserList.writeUsers();
                BikeManage bManage = new BikeManage(rut);
                dispose();
            }
        }

        if (e.getSource() == cancel) {
            if (title.equals("Ufrocleta: Primera bicicleta")) {
                AddNewUser nUser = new AddNewUser("Ufrocleta: Nuevo usuario");
                dispose();
            } else {
                BikeManage bManage = new BikeManage(rut);
                dispose();
            }

        }

    }

}
