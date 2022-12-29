/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.DefaultListModel;

public class MensagemPrivada extends UnicastRemoteObject implements MensagemPrivadaInterface {
    Conexoes conectarServidor;

    public MensagemPrivada(Conexoes conectarServidor) throws RemoteException {
        super();
        this.conectarServidor = conectarServidor;
    }
    
    @Override
    public void enviarMensagem(DefaultListModel mensagem) throws RemoteException{
        System.out.println("MENSAGEMMM "+mensagem);
    }

    @Override
    public void abrirJanela(String nomeEmissor) throws RemoteException {
        System.out.println("ABRIR JANELA");
        AgenteUtilizador agenteEmissor = conectarServidor.getSessaoConectada().getRepAgenteUtilizador().getAgenteByNome(nomeEmissor);
        
        //Criar página
        PaginaMensagemPrivada mensagemPrivadaChatPagina = new PaginaMensagemPrivada(conectarServidor, agenteEmissor, 1);
        mensagemPrivadaChatPagina.setVisible(true);//Mostrar página
    }
    
    public void enviarMensagemSegura(String mensagem, String assinatura) throws RemoteException{
        
    }
}
