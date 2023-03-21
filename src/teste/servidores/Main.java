package teste.servidores;
import java.io.IOException;
import teste.medidores.Controlador;
import teste.medidores.ServerMedidor;

public class Main {
    public static Controlador controlador = new Controlador();
    public static void main(String[] args) throws IOException {
        new ServerMedidor().start();  //7776 cadastra
        new ServerServidor().start(); //8081 faz chamadas
        System.out.println("cadastra na rota 7776");
        System.out.println("e chama na 8081");
        
    }
    
}
