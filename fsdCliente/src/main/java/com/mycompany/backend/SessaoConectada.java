/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Classe para ter todas as informações da sessão atual,
 * por parte do cliente
 */

public class SessaoConectada {
    private RepositorioAgenteUtilizador repAgenteUtilizador;
    private RepositorioPosts repPosts;

    public SessaoConectada(){
        repAgenteUtilizador = new RepositorioAgenteUtilizador();
        repPosts = new RepositorioPosts();
    }
    
    public void mudarUtilizadores(String utilizadores){
        //Passar a string par aarraylist
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(utilizadores.split(", ")));
        //mudar arraylist
        repAgenteUtilizador.setRepositorioAgenteUtilizador(arrayList);
    }
    
    //PARA TESTAR DEPOIS
    public void adicionarPosts(String mensagens){
        //Passar para arraylist
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(mensagens.split(", ")));
        
        System.out.println("Adicioanr posts");
        System.out.println(mensagens);
        System.out.println("------------");
        //mudar arraylist
        repPosts.adicionarPost(mensagens);
    }

    public RepositorioAgenteUtilizador getRepAgenteUtilizador(){
        return this.repAgenteUtilizador;
    }

    public void setRepAgenteUtilizador(RepositorioAgenteUtilizador novoRepAgenteUtilizador){
        this.repAgenteUtilizador = novoRepAgenteUtilizador;
    }

    public RepositorioPosts getRepositorioPosts(){
        return this.repPosts;
    }

    public void setRepPosts(RepositorioPosts novoRepPosts){
        this.repPosts = novoRepPosts;
    }
}
