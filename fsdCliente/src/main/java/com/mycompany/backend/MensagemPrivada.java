/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MensagemPrivada extends UnicastRemoteObject implements MensagemPrivadaInterface {

    Conexoes conectarServidor;
    PaginaMensagemPrivada paginaMensagemPrivada;

    public MensagemPrivada(Conexoes conectarServidor) throws RemoteException {
        super();
        this.conectarServidor = conectarServidor;
    }

    @Override
    public void enviarMensagem(String nome, String mensagem) throws RemoteException {
        paginaMensagemPrivada.adicionarMensagemNaLista(mensagem);
    }

    @Override
    public void abrirJanela(String nomeEmissor) throws RemoteException {
        System.out.println("ABRIR JANELA");
        AgenteUtilizador agenteEmissor = conectarServidor.getSessaoConectada().getRepAgenteUtilizador().getAgenteByNome(nomeEmissor);

        //Criar página
        PaginaMensagemPrivada mensagemPrivadaChatPagina = new PaginaMensagemPrivada(conectarServidor, agenteEmissor, 1);
        mensagemPrivadaChatPagina.setVisible(true);//Mostrar página
    }

    public void enviarMensagemSegura(String nome, String mensagem, String assinatura) throws RemoteException {
        //Converte para bytes
        byte[] decodedBytes = Base64.getDecoder().decode(assinatura);
        
        System.out.println("Metodo enviarMensagemSegura");
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, conectarServidor.getDadosCliente().getChavePublica());
            byte[] decriptedDigest = cipher.doFinal(decodedBytes);
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(mensagem.getBytes());
            byte[] digest = md.digest();
            
            if(Arrays.equals(decriptedDigest, digest)){
                System.out.println("Mensagem segura123 : " + mensagem);
                paginaMensagemPrivada.adicionarMensagemNaLista(mensagem);
            }else{
                System.out.println("Mensagem sofreu alterações");
                paginaMensagemPrivada.adicionarMensagemNaLista("Mensagem recebida, mas foi alterada. " + mensagem);
            }
            
        } catch (NoSuchAlgorithmException ex) {//Por causa de iniciar o cipher
            Logger.getLogger(MensagemPrivada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {//Por causa de iniciar o cipher
            Logger.getLogger(MensagemPrivada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {//Por causa do cipher.init
            Logger.getLogger(MensagemPrivada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {//Por causa do decriptedDigest
            Logger.getLogger(MensagemPrivada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {//Por causa do decriptedDigest
            Logger.getLogger(MensagemPrivada.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PaginaMensagemPrivada getPaginaMensagemPrivada() {
        return paginaMensagemPrivada;
    }

    public void setPaginaMensagemPrivada(PaginaMensagemPrivada paginaMensagemPrivada) {
        this.paginaMensagemPrivada = paginaMensagemPrivada;
    }
    
    
}
