/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

public class Post {
    AgenteUtilizador emissor;
    String mensagem;

    public Post() {
    }

    public Post(AgenteUtilizador emissor, String mensagem) {
        this.emissor = emissor;
        this.mensagem = mensagem;
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
    
    
}
