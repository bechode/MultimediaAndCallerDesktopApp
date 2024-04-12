package com.multimedia.chatsocket;

import com.multimedia.dto.AgendamientoDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ClienteSocket implements Runnable {

    int puerto;
    private String host;
    AgendamientoDTO agenda;

    public ClienteSocket(String host, int puerto, AgendamientoDTO agenda) {
        this.puerto = puerto;
        this.host = host;
        this.agenda = agenda;
    }

    @Override
    public void run() {
        Socket sc = null;
        ObjectInputStream in;
        ObjectOutputStream out;

        try {
            sc = new Socket(host, puerto);
            out = new ObjectOutputStream(sc.getOutputStream());

            System.out.println("Cliente iniciado");


            out.writeObject(agenda);

            System.out.println("mensje enviado");

            sc.close();

        }catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /*
    public static void main(String[] args) {
        AgendamientosDTO ag = new AgendamientosDTO();
        ag.setEspecialidad("CLINICA MEDICA");
        ag.setEstado(Short.valueOf("1"));
        ag.setFechaHoraConsulta(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        ag.setId(1L);
        ag.setMedico("Juan Pérez");
        ag.setOrdenConsulta("1");
        ag.setTurno("Mañana");
        ag.setPaciente("Tatiana Peralta");
        ag.setSala("Sala 5");

        ClienteSocket cli = new ClienteSocket("localhost", 6000, ag);
        Thread hilo = new Thread(cli);
        hilo.start();
    }
    */
}
