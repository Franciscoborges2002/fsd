/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ConectarServidor {
    static final int DEFAULT_PORT=2000;
    static final String DEFAULT_HOST="127.0.0.1"; 
    BufferedReader bufferIn;
    PrintWriter printOut;

    public void conectar() throws IOException {
        String servidor = DEFAULT_HOST;
        int porta = DEFAULT_PORT;
        Scanner ler = new Scanner(System.in);
                
        InetAddress servidorConectar = InetAddress.getByName(servidor);
		
        Socket ligacao = new Socket(servidorConectar, porta);
        try {
            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);
            String request = "SESSION_UPDATE_REQUEST,joao5";

            printOut.println(request);

            String resposta = bufferIn.readLine();
            
            System.out.println(resposta);
            
            //while(true){
                
            //}

            

            //ligacao.close();
            //System.out.println("Terminou a ligacao!");
        } catch (IOException e) {
                System.out.println("Erro ao comunicar com o servidor: "+e);
                System.exit(1);
        }
    }
    
    public void funcMensagem(String tipoMensagem, String mensagem){
        /* String[] dadosMensagemEstruturados;
        dadosMensagemEstruturados. */
        switch(tipoMensagem){
            case "SESSION_UPDATE_REQUEST":
                
            break;
        }
    }
}
