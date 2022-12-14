/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexoes extends Thread{
    private String ipServidor;
    private int portaServidor;
    private int taxaAtualizacao;
    private BufferedReader bufferIn;
    private PrintWriter printOut;
    private SessaoConectada sessaoConectada;
    private InetAddress servidorConectar;
    private Socket ligacao;
    private AgenteUtilizador dadosCliente;
    private ArrayList<AgenteUtilizador> chatsPrivadosAbertos;
    private final String SERVICE_NAME = "/mensagemPrivada";
    MensagemPrivada mensagemPrivada;

    public MensagemPrivada getMensagemPrivada() {
        return mensagemPrivada;
    }

    public void setMensagemPrivada(MensagemPrivada mensagemPrivada) {
        this.mensagemPrivada = mensagemPrivada;
    }
    
    public boolean chatComUtilizador(AgenteUtilizador agenteUtilizadorEnviarMensagem){
       return chatsPrivadosAbertos.contains(agenteUtilizadorEnviarMensagem);
    }
    
    public void removerAgenteChatPrivado(AgenteUtilizador agenteAdicionar){
        chatsPrivadosAbertos.remove(agenteAdicionar);
    }
    
    public void adicionarAgenteChatPrivado(AgenteUtilizador agenteAdicionar){
        chatsPrivadosAbertos.add(agenteAdicionar);
    }
    
    public int numChatsPrivadosAbertos(){
        return this.chatsPrivadosAbertos.size();
    }
    
    public ArrayList<AgenteUtilizador> getChatsPrivadosAbertos(){
        return this.chatsPrivadosAbertos;
    }
    
    public void setChatsPrivadosAbertos(ArrayList<AgenteUtilizador> chatsPrivadosAbertos){
        this.chatsPrivadosAbertos = chatsPrivadosAbertos;
    }
    
    public void setDadosCliente(AgenteUtilizador dadosCliente){
        this.dadosCliente = dadosCliente;
    }
    
    public AgenteUtilizador getDadosCliente(){
        return this.dadosCliente;
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
    public void conectar() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            //Definir todas as variaveis da classe
            this.sessaoConectada = new SessaoConectada();
            this.chatsPrivadosAbertos = new ArrayList<AgenteUtilizador>();

            this.servidorConectar = InetAddress.getByName(ipServidor);

            this.ligacao = new Socket(servidorConectar, portaServidor);

            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);
            
            //Passar chave publica para string
            String chavePublicaEncoded = Base64.getEncoder().encodeToString(dadosCliente.getChavePublica().getEncoded());
            
            //Enviar a primeira request e receber a resposta
            printOut.println("SESSION_UPDATE_REQUEST," + dadosCliente.getNomeUtilizadorAgenteUtilizador() + "," + dadosCliente.recebeMensagensPrivadas() + "," + dadosCliente.getTipoMensagemPrivada() + "," + chavePublicaEncoded);

            //Ler resposta do servidor
            String resposta = bufferIn.readLine();
            
            System.out.println(resposta);
            
            //Meter informa????es na sessao atual
            setInfoSessao(resposta.substring(resposta.indexOf(",") +1));

            //iniciar a thread receber info
            threadReceberInfo.start();

            //iniciar a thread para enviar SESSION_UPDATE_REQUEST
            threadEnviarSessionRequest.start();
        } catch (IOException ex) {
            Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException();
        }
    }
    
    //Metodo para enviar mensagem
    public void enviarMensagem(String mensagemEnviar){
        //System.out.println("EnviarMensagem Running");   
        //System.out.println("                                      <- enviar mensagem");
        
        //Enviar o AGENT_POST
        printOut.println("AGENT_POST," + mensagemEnviar);

    }
    
    public void sessionRequestForced(){
        //System.out.println("Atualizar Forcer");   
        //System.out.println("                                                 <- pedria atualizacao for??ada");
    
        printOut.println("SESSION_UPDATE_REQUEST");
    }
    
    //Thread para receber info
    Thread threadReceberInfo = new Thread(){
        public void run(){
            String resposta;
            //System.out.println("threadReceberInfo Running");
                
            try {
                while(true){
                    //System.out.println("                           <- receber info");
                    resposta = bufferIn.readLine();
                    //System.out.println(resposta);
                    sessaoConectada.getRepositorioPosts().listar();//PARA REMOVER
                    
                    if("SESSION_TIMEOUT".equals(resposta)){
                        //System.out.println("Fechar ligacao");
                        bufferIn.close();
                        printOut.close();
                        ligacao.close();
                        
                    }else{
                        try {
                            setInfoSessao(resposta.substring(resposta.indexOf(",") + 1));
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvalidKeySpecException ex) {
                            Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch (IOException e) {
                Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("ERRORORORORO " + e);
            }
        }
    };
    
    //Thread para enviar SESSION_UPDATE_REQUEST
    Thread threadEnviarSessionRequest = new Thread(){
        public void run(){
          //System.out.println("threadReceberInfo Running");

            try {
                while(true){
                    //System.out.println("               <- pedir updates");
                    //CODIGO PARA MANDAR REQUEST
                    printOut.println("SESSION_UPDATE_REQUEST");

                    this.sleep(taxaAtualizacao * 1000);
                }
            } catch (InterruptedException /*| IOException*/ e) {
                Logger.getLogger(Conexoes.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    };
    
    //Fun????o para ter o tipo de mensagem
    public String tipoMensagem(String mensagem){//Retornar o tipo de mensagem
        return mensagem.substring(0, mensagem.indexOf(","));
    }
    
    //Fun????o para registar a informa????o
    public void setInfoSessao(String mensagem) throws NoSuchAlgorithmException, InvalidKeySpecException{//Registar tudo o recebido da nova request
        //System.out.println("Adicionar: " + mensagem);//PARA REMOVER
        this.sessaoConectada.mudarUtilizadores(mensagem.substring(mensagem.indexOf("[") + 1, mensagem.indexOf("]")));
        
        this.sessaoConectada.getRepAgenteUtilizador().listar();
        
        mensagem = mensagem.substring(mensagem.indexOf("]")  + 3);//Tirar os utilizadores j?? adicionados acima
        this.sessaoConectada.adicionarPosts(mensagem.substring(0, mensagem.indexOf("]")));
    
        this.sessaoConectada.getRepositorioPosts().listar();//PARA REMOVER
    }
    
    public SessaoConectada getSessaoConectada(){
        return this.sessaoConectada;
    }
    
    //Fun????o para retornar arraylist com o repositorio de utilizadores
    public ArrayList<String> getNomesAgentesUtilizadores(){
        return sessaoConectada.getRepAgenteUtilizador().getNomesAgentesUtilizadores();
    }
    
    //Fun????o para retornar arraylist com o repositorio de posts
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
            this.ligacao.close();//Fechar liga????o
            this.bufferIn.close();//Fechar buffer para receber mensagens na liga????o
            this.printOut.close();//Fechar buffer para enviar mensagens na liga????o
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
    
    private void bindRMI(MensagemPrivada mensagemPrivada) throws RemoteException {
	try { 
            LocateRegistry.createRegistry(1099);
	} catch( RemoteException e) {
			
	}
	try {
            LocateRegistry.getRegistry("127.0.0.1",1099).rebind(SERVICE_NAME, mensagemPrivada);
	} catch( RemoteException e) {
            System.out.println("Registry not found");
	}
    }
    
    public void iniciarServidorRMI(){//Iniciar o servidor de RMI
        
        try {
            mensagemPrivada = new MensagemPrivada(this);
	} catch (RemoteException e1) {
            System.err.println("unexpected error...");
            e1.printStackTrace();
        }
		
	try {
            bindRMI(mensagemPrivada);
	} catch (RemoteException e1) {
            System.err.println("erro ao registar o stub...");
            e1.printStackTrace();
	}
    }
}