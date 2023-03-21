package teste.servidores;
import static teste.servidores.Main.controlador;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import teste.medidores.ChamadaCliente;


public class ServerServidor extends Thread{
    
    
    @Override
    public void run(){
        
        int port = 8081;
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(port);
            System.out.println("Servidor iniciado na porta "  + port);
            // Mantém o servidor em execução indefinidamente
            while (true) {
                Socket socketServidor = servidor.accept();
                System.out.println("Cliente " + socketServidor.getInetAddress().getHostAddress() + " adicionado.");
                new ChamadaCliente(socketServidor, controlador.getClientes()).start();
                
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor na porta " + port + ": " + e.getMessage());
        }
         finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

