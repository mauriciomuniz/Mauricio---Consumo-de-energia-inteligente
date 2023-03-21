package teste.medidores;

import static teste.servidores.Main.controlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChamadaCliente  extends Thread{
    private Socket connectionMedidor;
    
    
    public ChamadaCliente(Socket connectionMedidor, LinkedList<Cliente> cliente){
        this.connectionMedidor = connectionMedidor;
    }

    @Override
    public void run(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(connectionMedidor.getInputStream());
            BufferedReader leitor = new BufferedReader(inputStreamReader);
            StringBuilder requestBody = new StringBuilder();
            while(leitor.ready()){
                requestBody.append((char) leitor.read());
            }

            System.out.println(requestBody.toString());
            if(!requestBody.toString().isEmpty()){
                System.out.println("PASSAAAAAA......");  

                String req = requestBody.toString();
                     
                String[] parts = req.split(" "); // separa a requisição em partes
                String method = parts[0]; // método HTTP
                String path = parts[1]; // caminho da rota

                String[] parts2 = path.split("=");
                String MesmaRota = parts2[0];
                String identificador = parts2[1];
                System.out.println("método:"+ method);
                System.out.println("caminho: "+path);
                System.out.println("a rota agr: "+MesmaRota);
                System.out.println("identificador: "+identificador);
                System.out.println("PASSAAAAAA......");
                switch (MesmaRota){

                    case "/teste":
                    if (method.equals("GET")){
                        System.out.println("Rota '/teste' chamada pelo cliente " + connectionMedidor.getInetAddress().getHostAddress());
                        String resposta = "{\"mensagem\": \"Bem-vindo ao servidor!\"}";
                        enviarRespostaget(connectionMedidor, resposta);

                    }
                    break;

                    case "/historico":
                    if (method.equals("GET")){
                        System.out.println("Rota '/historico' chamada pelo cliente " + connectionMedidor.getInetAddress().getHostAddress());
                        
                        String resposta = clienteHistoricoId(identificador); //"{\"mensagem\": \"Bem-vindo ao servidor!\"}";
                        enviarRespostaget(connectionMedidor, resposta);
                        
                    }
                    break;

                    case "/consumo":
                    if (method.equals("GET")){
                        System.out.println("Rota '/consumo' chamada pelo cliente " + connectionMedidor.getInetAddress().getHostAddress());
                        
                        String resposta = clienteConsumoId(identificador); //"{\"mensagem\": \"Bem-vindo ao servidor!\"}";
                        enviarRespostaget(connectionMedidor, resposta);
                        
                    }
                    break;

                    case "/fatura":
                    if (method.equals("GET")){
                        System.out.println("Rota '/fatura' chamada pelo cliente " + connectionMedidor.getInetAddress().getHostAddress());
                        
                        String resposta = clienteFaturaId(identificador); //"{\"mensagem\": \"Bem-vindo ao servidor!\"}";
                        enviarRespostaget(connectionMedidor, resposta);
                        
                    }
                    break;

                    case "/alerta":
                    if (method.equals("GET")){
                        System.out.println("Rota '/alerta' chamada pelo cliente " + connectionMedidor.getInetAddress().getHostAddress());
                        
                        String resposta = clienteAlertaId(identificador); //"{\"mensagem\": \"Bem-vindo ao servidor!\"}";
                        enviarRespostaget(connectionMedidor, resposta);
                        
                    }
                    break;
                }


            }

            connectionMedidor.close();

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




    private Cliente buscaClienteId(String id){ 
        LinkedList<Cliente> listaClientes = controlador.getClientes();
        if(listaClientes.size() == 0){
            return null;
        }
        else{
            for(Cliente cliente : listaClientes){
                if(cliente.getId().equals(id)){
                    return cliente;
                }
            }
            return null;
            
        }
    }
    

    public String clienteHistoricoId(String id) {
        Cliente cliente = buscaClienteId(id);
        if (cliente == null) {
            return "Nao existe um cliente registrado com esse id";
        } else {
            String historicoId = "Historico de medicoes do cliente id: "+id+ "\n" +
                    "Consumo/ Dia/ Horario\n";
            Medidor[] listaMedidor = cliente.getHistorico();
            for (Medidor medidor : listaMedidor) {
                if (medidor != null) {
                    historicoId += medidor.getconsumo() + " / " + medidor.getdataMedida() + " / " + medidor.getHorario() + "\n";
                }
            }
            return historicoId;   
        }
    }
    

    public String clienteConsumoId(String id){
        Cliente cliente = buscaClienteId(id);
        if(cliente == null){
            return "Não existe um cliente registrado com esse id";

        }
        String consumo = "Esse foi o consumo total do cliente com identificador "
        +id+": "+ cliente.getConsumoTotal()+"kW/h";
        return consumo;
            
    }



    public String clienteAlertaId(String id){
        Cliente cliente = buscaClienteId(id);
        if(cliente == null) {
            return "Nao existe um cliente registrado com esse id";
        } else {
            String alerta = cliente.alertaConsumoAlto() + cliente.alertaVariacao();
            LinkedList<Cliente> listaClientes = controlador.getClientes();
            for (Cliente c : listaClientes) {
                if (c.getId().equals(cliente.getId())) {
                    controlador.getClientes().set(listaClientes.indexOf(c), cliente);
                }
            }
            return alerta;
            
        }
    }
    

    public String clienteFaturaId(String id){
        Cliente cliente = buscaClienteId(id);
        if(cliente == null) {
            return "Nao existe um cliente registrado com esse id";
        }
            float fatura = cliente.getConsumoTotal()* 0.06f;
            String dataVencimento = dataPagar(cliente.getfaturaDia());
            String msgfatura = "Fatura\nCliente com id: " + cliente.getId() + 
            "\nConsumo Total: " + cliente.getConsumoTotal()+ "kW/h"+
            "\nData de vencimento da fatura: " + dataVencimento+ 
            "\nValor a pagar: R$" + fatura;
            cliente.setConsumoTotal(0);
            atualizaDiaFatura(cliente, dataVencimento);
            return msgfatura;
            
        
    }

    private void atualizaDiaFatura(Cliente cliente, String date){
        LinkedList<Cliente> listaClientes = controlador.getClientes();
        for(Cliente c : listaClientes){
            if(c.getId().equals(cliente.getId())){
                c.setfaturaDia(date);
                controlador.setClientes(listaClientes);
            }
        }
    }
    
    public static String dataPagar(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.parse(data, formatter);

        LocalDate dataPagamento;
        if (dataAtual.getMonthValue() < 12) {
            dataPagamento = dataAtual.plusMonths(1);
        } else {
            dataPagamento = dataAtual.plusMonths(1).withMonth(1).withYear(dataAtual.getYear() + 1);
        }

        return formatter.format(dataPagamento);
    }

}
    

