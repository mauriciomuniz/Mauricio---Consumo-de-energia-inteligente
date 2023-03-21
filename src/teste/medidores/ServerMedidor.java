package teste.medidores;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import teste.servidores.Cadastro;

public class ServerMedidor extends Thread{
    
    private final int PORT = 7776;
    //private final String JSON_FILE = "src/teste/clientes.json";

    
    @Override
    public void run(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Medidor iniciado na porta " + PORT);

             while (true) {
                Socket medidorSocket = serverSocket.accept() ;
                System.out.println("socket medidor funcionando");

                new Cadastro(medidorSocket).start();
                

             }

        } catch (IOException e) {
            System.err.println("Erro ao iniciar o medidor na porta " + PORT + ": " + e.getMessage());
        }
        
    }
}




