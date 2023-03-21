package teste.medidores;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;

public class Controlador {

    public static final String FILENAME = "src/teste/clientes.json";
    public static LinkedList<Cliente> listaclientes = new LinkedList<>();

    public synchronized LinkedList<Cliente> getClientes() {
        return listaclientes;
    }

    public synchronized void setClientes(LinkedList<Cliente> listaclientes) {
        this.listaclientes = listaclientes;
    }
    
    public static void handleRequest(String requestBody) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Converte o corpo da requisição para um objeto Cliente
        Cliente cliente = gson.fromJson(requestBody, Cliente.class);
        
        // Lê a lista existente de listaclientes do arquivo JSON
        //listaclientes = new ArrayList<>();
        try {
            File file = new File(FILENAME);
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                Type clienteListType = new TypeToken<ArrayList<Cliente>>(){}.getType();
                listaclientes = gson.fromJson(reader, clienteListType);
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------passa-------");
        // Adiciona o novo cliente à lista
        listaclientes.add(cliente);
    
        // Escreve a lista atualizada de volta para o arquivo JSON
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            gson.toJson(listaclientes, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // public static void handleRequest(String requestBody) throws IOException {
    //     Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    //     // Lê a lista existente de listaclientes do arquivo JSON
    //     List<Cliente> listaclientes = new ArrayList<>();
    //     try {
    //         File file = new File(FILENAME);
    //         if (file.exists()) {
    //             FileReader reader = new FileReader(file);
    //             Type clienteListType = new TypeToken<ArrayList<Cliente>>(){}.getType();
    //             listaclientes = gson.fromJson(reader, clienteListType);
    //             reader.close();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println("------------passa-------");
    //     // Converte o corpo da requisição para um objeto Cliente
    //     Cliente cliente = gson.fromJson(requestBody, Cliente.class);
    
    //     // Adiciona o novo cliente à lista
    //     listaclientes.add(cliente);
    
    //     // Escreve a lista atualizada de volta para o arquivo JSON
    //     try {
    //         File file = new File(FILENAME);
    //         if (!file.exists()) {
    //             file.createNewFile();
    //         }
    //         FileWriter writer = new FileWriter(file);
    //         gson.toJson(listaclientes, writer);
    //         writer.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    // public static void handleRequest(String requestBody) throws IOException {
        
    //     Gson gson = new GsonBuilder().setPrettyPrinting().create();
    //     // Lê a lista existente de listaclientes do arquivo JSON
    //     List<Cliente> listaclientes = new ArrayList<>();
    //     try {
    //         File file = new File(FILENAME);
    //         if (file.exists()) {
    //             FileReader reader = new FileReader(file);
    //             Type clienteListType = new TypeToken<ArrayList<Cliente>>(){}.getType();
    //             listaclientes = gson.fromJson(reader, clienteListType);
    //             reader.close();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println("------------passa-------");
    //     if (requestBody == null) {
    //         System.err.println("Corpo da requisição é nulo.");
    //         return;
    //     }
    //     // Converte o corpo da requisição para um objeto Cliente
    //     System.out.println("Conteúdo da requisição: " + requestBody);
    //     Cliente cliente = gson.fromJson(requestBody, Cliente.class);

    //     if (cliente == null) {
    //         System.err.println("Não foi possível converter o corpo da requisição para um objeto Cliente.");
    //         return;
    //     }
    //     // Adiciona o novo cliente à lista
    //     listaclientes.add(cliente);
    
    //     // Escreve a lista atualizada de volta para o arquivo JSON
    //     try {
    //         File file = new File(FILENAME);
    //         if (!file.exists()) {
    //             file.createNewFile();
    //         }
    //         FileWriter writer = new FileWriter(file);
    //         gson.toJson(listaclientes, writer);
    //         writer.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
    

    public static void VerConsumo()
    {   
        
        
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            

            try {
                
                File file = new File(FILENAME);
                Scanner scanner = new Scanner(file);

                StringBuilder jsonBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    jsonBuilder.append(scanner.nextLine());
                }

                Type clienteListType = new TypeToken<ArrayList<Cliente>>(){}.getType();
                listaclientes = gson.fromJson(jsonBuilder.toString(), clienteListType);

                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String responseBody = gson.toJson(listaclientes);
            System.out.println(responseBody);
        
    }


}
