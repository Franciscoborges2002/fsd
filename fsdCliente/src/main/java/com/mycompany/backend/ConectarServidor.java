/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectarServidor extends Thread{
    private String ipServidor;
    private int portaServidor;
    private int taxaAtualizacao;
    private BufferedReader bufferIn;
    private PrintWriter printOut;
    private SessaoConectada sessaoConectada;
    private InetAddress servidorConectar;
    private Socket ligacao;
    private String nomeUtilizador;
    private Boolean mensagensPrivadas;
    private String ipAgenteUtilizador;
    
    public void run(){
        
    }
    
    public String getIpAgenteUtilizador(){
        return this.ipAgenteUtilizador;
    }
    
    public void setIpAgenteUtilizador(String ipAgenteUtilizador){
        this.ipAgenteUtilizador = ipAgenteUtilizador;
    }
    
    public Boolean getMensagensPrivadas(){
        return this.mensagensPrivadas;
    }
    
    public void setMensagensPrivadas(Boolean mensagensPrivadas){
        this.mensagensPrivadas = mensagensPrivadas;
    }
    
    public void setNomeUtilizador(String nomeUtilizador){
        this.nomeUtilizador = nomeUtilizador;
    }
    
    public void setIpServidor(String ipServidor){
        this.ipServidor = ipServidor;
    }
    
    public void setPortaServidor(int portaServidor){
        this.portaServidor = portaServidor;
    }
    
    public void setTaxaAtualizacao(int taxaAtualizacao){
        this.taxaAtualizacao = taxaAtualizacao;
    }

    //Metodo para conectar e definir todas as vars da classe
    public void conectar() throws IOException {
        try {
            //Definir todas as variaveis da classe
            this.sessaoConectada = new SessaoConectada();

            this.servidorConectar = InetAddress.getByName(ipServidor);

            this.ligacao = new Socket(servidorConectar, portaServidor);

            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);

            //Enviar a primeira request e receber a resposta
            printOut.println("SESSION_UPDATE_REQUEST," + nomeUtilizador + "," + ipAgenteUtilizador + "," + mensagensPrivadas);

            //Ler resposta do servidor
            String resposta = bufferIn.readLine();
            
            //Meter informações na sessao atual
            setInfoSessao(resposta.substring(resposta.indexOf(",") +1));

            //iniciar a thread receber info
            threadReceberInfo.start();

            //iniciar a thread para enviar SESSION_UPDATE_REQUEST
            threadEnviarSessionRequest.start();
        } catch (IOException ex) {
            Logger.getLogger(ConectarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo para enviar mensagem
    public void enviarMensagem(String mensagemEnviar){
        System.out.println("EnviarMensagem Running");   
        System.out.println("                                      <- enviar mensagem");
        
        //Enviar o AGENT_POST
        printOut.println("AGENT_POST," + mensagemEnviar);

    }
    
    public void sessionRequestForced(){
        System.out.println("Atualizar Forcer");   
        System.out.println("                                                 <- pedria atualizacao forçada");
    
        printOut.println("SESSION_UPDATE_REQUEST");
    }
    
    //Thread para receber info
    Thread threadReceberInfo = new Thread(){
        public void run(){
            String resposta;
            System.out.println("threadReceberInfo Running");
                
            try {
                while(true){
                    System.out.println("                           <- receber info");
                    resposta = bufferIn.readLine();
                    System.out.println(resposta);
                    sessaoConectada.getRepositorioPosts().listar();//PARA REMOVER
                    
                    if("SESSION_TIMEOUT".equals(resposta)){
                        System.out.println("Fechar ligacao");
                        bufferIn.close();
                        printOut.close();
                        ligacao.close();
                        
                    }else{
                        setInfoSessao(resposta.substring(resposta.indexOf(",") + 1));
                    }
                }
            } catch (IOException e) {
                Logger.getLogger(ConectarServidor.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    };
    
    //Thread para enviar SESSION_UPDATE_REQUEST
    Thread threadEnviarSessionRequest = new Thread(){
        public void run(){
          System.out.println("threadReceberInfo Running");

            try {
                while(true){
                    System.out.println("               <- pedir updates");
                    //CODIGO PARA MANDAR REQUEST
                    printOut.println("SESSION_UPDATE_REQUEST");

                    this.sleep(taxaAtualizacao * 1000);
                }
            } catch (InterruptedException /*| IOException*/ e) {
                Logger.getLogger(ConectarServidor.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    };
    
    //Função para ter o tipo de mensagem
    public String tipoMensagem(String mensagem){//Retornar o tipo de mensagem
        return mensagem.substring(0, mensagem.indexOf(","));
    }
    
    //Função para registar a informação
    public void setInfoSessao(String mensagem){//Registar tudo o recebido da nova request
        System.out.println("Adicionar: " + mensagem);//PARA REMOVER
        this.sessaoConectada.mudarUtilizadores(mensagem.substring(mensagem.indexOf("[") + 1, mensagem.indexOf("]")));
        
        mensagem = mensagem.substring(mensagem.indexOf("]")  + 3);//Tirar os utilizadores já adicionados acima
        this.sessaoConectada.adicionarPosts(mensagem.substring(0, mensagem.indexOf("]")));
    
        this.sessaoConectada.getRepositorioPosts().listar();//PARA REMOVER
    }
    
    //Função para retornar arraylist com o repositorio de utilizadores
    public ArrayList<String> getAgentesUtilizadores(){
        return sessaoConectada.getRepAgenteUtilizador().getRepositorioAgenteUtilizador();
    }
    
    //Função para retornar arraylist com o repositorio de posts
    public ArrayList<String> getPosts(){
        return sessaoConectada.getRepositorioPosts().getRepositorioPosts();
    }

    //Verificar se esta conectado
    public boolean estaConectado(){
        return ligacao.isConnected();
    }
    
    //Metodo para se desconectar do servidor
    public void desconectarServidor() throws Exception{
        try {
            this.ligacao.close();//Fechar ligação
            this.bufferIn.close();//Fechar buffer para receber mensagens na ligação
            this.printOut.close();//Fechar buffer para enviar mensagens na ligação
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}