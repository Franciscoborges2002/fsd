/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

/**
 *
 * @author francisco
 */
public class AgenteUtilizador {
    String nomeUtilziador;
    String ipUtilizador;
    Boolean recebeMensagensPrivadas;
    String tipoMensagemPrivada;
    String chavePublica, chavePrivada;

    public AgenteUtilizador(String nomeUtilziador, String ipUtilizador, Boolean recebeMensagensPrivadas, String tipoMensagemPrivada, String chavePublica) {
        this.nomeUtilziador = nomeUtilziador;
        this.ipUtilizador = ipUtilizador;
        this.recebeMensagensPrivadas = recebeMensagensPrivadas;
        this.tipoMensagemPrivada = tipoMensagemPrivada;
        this.chavePublica = chavePublica;
    }
    
    
    
    public AgenteUtilizador(String nomeUtilziador, Boolean recebeMensagensPrivadas, String tipoMensagemPrivada) {
        this.nomeUtilziador = nomeUtilziador;
        this.recebeMensagensPrivadas = recebeMensagensPrivadas;
        this.tipoMensagemPrivada = tipoMensagemPrivada;
    }

    public boolean recebeMensagensPrivadas(){
        return this.recebeMensagensPrivadas;
    }

    public void setIpUtilizador(String ipUtilizador){
        this.ipUtilizador = ipUtilizador;
    }

    public String getIpUtilizador(){
        return this.ipUtilizador;
    }

    public void setNomeUtilizadorAgentesUtilizador(String nomeUtilziador){
        this.nomeUtilziador = nomeUtilziador;
    }

    public String getNomeUtilizadorAgenteUtilizador(){
        return this.nomeUtilziador;
    }

    public String getNomeUtilziador() {
        return nomeUtilziador;
    }

    public void setNomeUtilziador(String nomeUtilziador) {
        this.nomeUtilziador = nomeUtilziador;
    }

    public Boolean getRecebeMensagensPrivadas() {
        return recebeMensagensPrivadas;
    }

    public void setRecebeMensagensPrivadas(Boolean recebeMensagensPrivadas) {
        this.recebeMensagensPrivadas = recebeMensagensPrivadas;
    }

    public String getTipoMensagemPrivada() {
        return tipoMensagemPrivada;
    }

    public void setTipoMensagemPrivada(String tipoMensagemPrivada) {
        this.tipoMensagemPrivada = tipoMensagemPrivada;
    }

    public String getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(String chavePublica) {
        this.chavePublica = chavePublica;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(String chavePrivada) {
        this.chavePrivada = chavePrivada;
    }
    
    
}
