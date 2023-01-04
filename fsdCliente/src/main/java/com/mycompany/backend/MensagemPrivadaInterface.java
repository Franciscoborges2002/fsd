/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.backend;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MensagemPrivadaInterface extends Remote {
    void enviarMensagem(String nome, String mensagem) throws RemoteException;
    void enviarMensagemSegura(String nome, String mensagem, String assinatura) throws RemoteException;
    void abrirJanela(String teste)throws RemoteException;
}