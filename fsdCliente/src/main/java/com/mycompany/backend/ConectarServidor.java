/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;
import java.net.*;
import java.io.*;

public class ConectarServidor {
    static final int DEFAULT_PORT=2000;
    static final String DEFAULT_HOST="127.0.0.1"; 

    public void conectar() throws IOException {
        String servidor = DEFAULT_HOST;
        int porta = DEFAULT_PORT;
                
        InetAddress servidorConectar = InetAddress.getByName(servidor);
		
        Socket ligacao = new Socket(servidorConectar, porta);
        try {
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            PrintWriter printOut = new PrintWriter(ligacao.getOutputStream(), true);
            String request = "get" + " " + "teste 2";//args[0]
            String pedido = "Pedido ao servidor...";

            printOut.println(pedido);

            String resposta = bufferIn.readLine();

            System.out.println(resposta);

            ligacao.close();
            System.out.println("Terminou a ligacao!");
        } catch (IOException e) {
                System.out.println("Erro ao comunicar com o servidor: "+e);
                System.exit(1);
        }
    }
}
