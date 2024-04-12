package com.multimedia.chatsocket;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import com.multimedia.dto.AgendamientoDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import sgm.org.gui.LlamadaConsultaForm;

public class AgendaServerSocket implements Flow.Publisher<AgendamientoDTO>, Runnable {

    private ServerSocket servidor = null;
    private boolean servidorConectado = false;
    private final int puerto;

    private List<Socket> clientes;
    //LlamadaConsultaForm formularioSubscriber;
    AgendamientoDTO mensaje;

    public AgendaServerSocket(int puerto) {
        this.puerto = puerto;
        this.clientes = new ArrayList<>();
        //formularioSubscriber = new LlamadaConsultaForm();
        //subscriber.setVisible(true);

    }

    @Override
    public void run() {
        definirLookAndFeel();
        servidor = null;
        Socket sc = null;
        servidorConectado = false;
        ObjectInputStream objIn;

        try {
            servidor = new ServerSocket(puerto);
            servidorConectado = true;
            System.out.println("Servidor iniciado");
            //JOptionPane.showMessageDialog(null, "Servidor iniciado", "Conexión servidor", JOptionPane.INFORMATION_MESSAGE);
            showFormSaludo();

            while(servidorConectado) {
                sc = servidor.accept();
                objIn = new ObjectInputStream(sc.getInputStream());

                mensaje = (AgendamientoDTO) objIn.readObject();
                //subscribe(formularioSubscriber);
                System.out.println("Cliente conectado: ");

            }

        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Conexión servidor", JOptionPane.ERROR_MESSAGE);
            servidor = null;
            servidorConectado = false;
        }
    }

    public boolean desconectar() {
        try {
            if(servidor != null) {

                servidor.close();
                servidorConectado = false;
                System.out.println("Servidor desconectado");

                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super AgendamientoDTO> subscriber) {
        if(mensaje != null) {
            AgendamientoDTO[] mensajeArray = new AgendamientoDTO[1];
            mensajeArray[0] = mensaje;
            subscriber.onSubscribe(new AgendaSubscription(subscriber, mensajeArray));
        }
    }

    private void definirLookAndFeel() {
        try {
            //javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        }  catch (Exception ex) {
            //java.util.logging.Logger.getLogger(AgendaServerSocket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /*
    public static void main(String[] args) {
        AgendaServerSocket s = new AgendaServerSocket(6000);
        Thread hilo = new Thread(s);
        hilo.start();
    }
    */

    public void showFormularioSubcriber() {
//        if(formularioSubscriber == null) {
//            formularioSubscriber = new LlamadaConsultaForm();
//        }
//        formularioSubscriber.setVisible(true);
    }

    private void showFormSaludo() {
        try {

            JFrame mainFrame= new JFrame("Llamador de turnos");
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // Crear el JLabel
            JLabel label = new JLabel("Servicio Iniciado");


            // Crear el layout y configurar el JFrame
            GridBagLayout layout = new GridBagLayout();
            mainFrame.setLayout(layout);

            // Configurar las restricciones para el JLabel
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.CENTER;
            //Agregar el JLabel al JFrame
            mainFrame.add(label, constraints);
            mainFrame.setSize(300, 150);

            mainFrame.setLocationRelativeTo(null);

            /*
            try {
                javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(LlamadaConsultaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            */

            mainFrame.addWindowStateListener(new WindowStateListener() {

                @Override
                public void windowStateChanged(WindowEvent arg0) {
                    if (arg0.getNewState() == Frame.ICONIFIED) {
                        mainFrame.dispose();
                    }

                }
            });

            mainFrame.setVisible(true);
            Thread.sleep(2000);
            mainFrame.dispose();
        } catch(Exception ex) {

        }

    }
}
