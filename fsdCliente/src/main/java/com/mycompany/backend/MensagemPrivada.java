/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MensagemPrivada extends UnicastRemoteObject implements MensagemPrivadaInterface {
    ArrayList<String> mensagens;
    AgenteUtilizador emissor;
    String mensagem;

    public MensagemPrivada() throws RemoteException {
        super();
        mensagens = new ArrayList<>();
    }

    public AgenteUtilizador getEmissor() {
        return emissor;
    }

    public void setEmissor(AgenteUtilizador emissor) {
        this.emissor = emissor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public void adicionarMensagem(String mensagem){
        System.out.println("Adicioanr mensagem à arraylist "+mensagem);
    }
    
        @Override
    public void enviarMensagem(String mensagem) throws RemoteException{
        System.out.println("MENSAGEMMM "+mensagem);
    }

    @Override
    public void abrirJanela(AgenteUtilizador emissor) throws RemoteException {
        System.out.println("OLAAA COMUNICAÇÃO BEM SUCEDIDA");
    }
}
