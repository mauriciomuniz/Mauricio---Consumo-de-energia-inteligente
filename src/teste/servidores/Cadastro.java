package teste.servidores;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;

import teste.medidores.Cliente;

import static teste.servidores.Main.controlador;



public class Cadastro extends Thread {
    private Socket connection;
    boolean encontrado = true;
    public Cadastro(Socket connection){
        this.connection = connection;

    }
    @Override
    public void run(){
        try {
            
            BufferedReader leitor = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder requestBody = new StringBuilder();
            System.out.println("PASSAAAAAA......");     
           
            System.out.println("começa aqui: "+requestBody+ " termina aqui");
            while(leitor.ready()){
                requestBody.append((char) leitor.read());
            }   
            //System.out.println("começa aqui: "+requestBody+ " termina aqui");
            
            String req = requestBody.toString();
            //System.out.println("PASSAAAAAA......");     
            String[] parts = req.split(" "); // separa a requisição em partes
            String method = parts[0]; // método HTTP
            String path = parts[1].substring(1); // caminho da rota
            

            String[] entrada = path.split("-");
            System.out.println("id: "+ entrada[0]);
            System.out.println("consumo: "+ entrada[1]);
            System.out.println("data: "+ entrada[2]);
            System.out.println("horario: "+ entrada[3]);
            LinkedList<Cliente> clienteMedidas = controlador.getClientes();
            boolean naoExiste = true; 

            if(clienteMedidas.size() > 0){
                boolean atualizado = false;
                for(Cliente cliente : clienteMedidas){
                    if(cliente.getId().equals(entrada[0])){
                        cliente.ConsumoAtualiza(Integer.parseInt(entrada[1]), entrada[2], entrada[3]);
                        controlador.setClientes(clienteMedidas);
                        atualizado = true;
                    }
                }
                if(atualizado){
                    System.out.println("O usuario de id: " + entrada[0] + " teve os entrada atualizados");
                    System.out.println("");
                    naoExiste = false;
                } 
            }
            
            
            if(naoExiste && entrada.length>1){
                System.out.println("Cadastro Realizado com sucesso do cliente id:" +entrada[0]);
                Cliente novoCliente = new Cliente(entrada[0],entrada[2]);
                controlador.getClientes().add(novoCliente);
            }

            // for (Cliente cliente : clienteMedidas) {
            //     System.out.println("Nome do cliente: " + cliente.getId());
            //     System.out.println("Medidas do cliente: " + cliente.getConsumoTotal());
            // }

            String resposta = requestBody.toString();
            enviarRespostaget(connection, resposta);
            leitor.close();
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    
        
    }

    private void enviarRespostaget(Socket cliente, String resposta) {
        try {
            String cabecalho = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: " + resposta.length() + "\r\n" +
                    "\r\n";
    
            cliente.getOutputStream().write((cabecalho + resposta).getBytes());
        } catch (IOException e) {
            System.err.println("Erro ao enviar resposta para o cliente: " + e.getMessage());
        }
    }
}