/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

public class Mensagem {
    private String mensagem;
    private AgenteUtilizador utilizador;

    public Mensagem(String mensagem, AgenteUtilizador utilizador){
        this.mensagem = mensagem;
        this.utilizador = utilizador;
    }

    public String getMensagem(){
        return this.mensagem;
    }

    public AgenteUtilizador getAgenteUtilizador(){
        return this.utilizador;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    public void getAgenteUtilizador(AgenteUtilizador agenteUtilizador){
        this.utilizador = agenteUtilizador;
    }
}