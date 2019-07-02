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
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JTextField;

/**
 *
 * @author JAVIER
 */
    public class AddNewUser extends JFrame implements ActionListener {

        private String title;
        private JTextField nombre;
        private JTextField rut;
        private JTextField correo;
        private JTextField numero;

        private JButton add;
        private JButton cancel;

        /**
         * Constructor of this window.
         *
         * @param title
         * @throws HeadlessException
         */
        public AddNewUser(String title) throws HeadlessException {
            super(title);
            this.setLayout(null);

            this.title = title;

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(480, 270);
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
            nombre = new JTextField("Nombre");
            nombre.setBounds(16, 27, 192, 27);
            this.add(nombre);
            //text rut
            rut = new JTextField("Rut");
            rut.setBounds(16, 77, 192, 27);
            this.add(rut);
            //text correo
            correo = new JTextField("Correo");
            correo.setBounds(16, 127, 192, 27);
            this.add(correo);
            //text numero
            numero = new JTextField("Numero");
            numero.setBounds(16, 177, 192, 27);
            this.add(numero);
        }

        public void crearButtons() {

            //boton aceptar usuario
            add = new JButton("Aceptar");
            add.setBounds(240, 90, 192, 27);
            this.add(add);
            add.addActionListener(this);
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
            if (e.getSource() == cancel) {
                UserManagement userMan = new UserManagement();
                dispose();

            }

        }

    }

