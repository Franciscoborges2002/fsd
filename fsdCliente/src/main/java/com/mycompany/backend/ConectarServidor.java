/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectarServidor extends Thread{
    static final int DEFAULT_PORT=2001;
    static final String DEFAULT_HOST="127.0.0.1"; 
    static final int SESSION_TIMEOUT = 120;
    BufferedReader bufferIn;
    PrintWriter printOut;
    SessaoConectada sessaoConectada;
    InetAddress servidorConectar;
    Socket ligacao;
    String nick;
    
    Thread threadEnviarMensagem = new Thread(){
        public void run(){
          String mensagemAEnviar;
          System.out.println("threadReceberInfo Running");
          Scanner ler = new Scanner(System.in);
          
          while(true){
            //Pedir nova post para enviar
            System.out.printf("O que quer escrever no chat:");
            mensagemAEnviar = ler.nextLine();
            
            //Enviar o AGENT_POST
            printOut.println("AGENT_POST," + nick + ":" + mensagemAEnviar);
          }
          
        }
    };
    
    Thread threadEnviarSessionRequest = new Thread(){
        public void run(){
          String mensagemAEnviar;
          System.out.println("threadReceberInfo Running");
          Scanner ler = new Scanner(System.in);
            try {
                while(true){
                    //CODIGO PARA MANDAR REQUEST
                    this.sleep(5000);
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ConectarServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
          
          while(true){
            //Pedir nova post para enviar
            System.out.printf("O que quer escrever no chat:");
            mensagemAEnviar = ler.nextLine();
            
            //Enviar o AGENT_POST
            printOut.println("AGENT_POST," + nick + ":" + mensagemAEnviar);
          }
          
        }
    };
    
    Thread threadReceberInfo = new Thread(){
        public void run(){
            String infoSessao;
            String resposta;
            
            try {
                while(true){
                    System.out.println("threadEnviarMensagens Running");
                    resposta = bufferIn.readLine();
                    System.out.println(resposta);
                    sessaoConectada.getRepositorioPosts().listar();
                    sessaoConectada.getRepAgenteUtilizador().listarRep();

                    //Receber informação do chat, se houver
                    int indexVirgula = resposta.indexOf(",") +1;
                    infoSessao = resposta.substring(indexVirgula);

                    setInfoSessao(resposta);
                }
            } catch (IOException e) {
                Logger.getLogger(ConectarServidor.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    };

    public void conectar() throws IOException {
        String servidor = DEFAULT_HOST;
        this.sessaoConectada = new SessaoConectada();
        int porta = DEFAULT_PORT;
                
        this.servidorConectar = InetAddress.getByName(servidor);
		
        this.ligacao = new Socket(servidorConectar, porta);
        try {
            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);
            nick = "joao25";
            int segundosTimeout = SESSION_TIMEOUT;
            String request = "SESSION_UPDATE_REQUEST," + nick + "," + segundosTimeout;
            

            printOut.println(request);

            String resposta = bufferIn.readLine();
            
            System.out.println(resposta);
            
            /*switch(tipoMensagem(resposta)){
                case "SESSION_UPDATE":
                    String infoSessao;
                    int indexVirgula = resposta.indexOf(",") +1;
                    infoSessao = resposta.substring(indexVirgula);

                    setInfoSessao(resposta);
                break;
            }*/
            
            //Thread para receber sempre as informações
            threadReceberInfo.start();
            
            //Thread para enviar mensagens
            threadEnviarMensagem.start();
            
            /*while(true){
                System.out.println("Inicio while");
                
               
                System.out.println("Fim while");
            }*/

            //ligacao.close();
            //System.out.println("Terminou a ligacao!");
        } catch (IOException e) {
            System.out.println("Erro ao comunicar com o servidor: "+e);
            System.exit(1);
        }
    }
    
    public String tipoMensagem(String mensagem){
        int indexVirgula = mensagem.indexOf(",");

        String tipoMensagem = mensagem.substring(0, indexVirgula);

        return tipoMensagem;
    }
    
    public void setInfoSessao(String mensagem){
        System.out.println("Adicionar: " + mensagem);//
        this.sessaoConectada.mudarUtilizadores(mensagem.substring(mensagem.indexOf("[") + 1, mensagem.indexOf("]")));
        mensagem = mensagem.substring(mensagem.indexOf("]")  + 3);//Tirar os utilizadores já adicionados acima
        this.sessaoConectada.adicionarPosts(mensagem.substring(0, mensagem.indexOf("]")));
    
        this.sessaoConectada.getRepAgenteUtilizador().listarRep();
        this.sessaoConectada.getRepositorioPosts().listar();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
