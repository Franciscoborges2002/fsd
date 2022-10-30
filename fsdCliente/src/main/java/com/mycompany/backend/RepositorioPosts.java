/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.util.ArrayList;

public class RepositorioPosts {
    private ArrayList<String> posts;

    public RepositorioPosts(){
        posts = new ArrayList<String>(10);
    }

    public ArrayList<String> getRepositorioPosts(){
        return this.posts;
    }

    public void setRepositorioMensagens(ArrayList<String> repPosts){
        this.posts = repPosts;
    }

    public void adicionarPost(String mensagem){
        
        if(mensagem == ""){//Se a mensagem não contiver nome nem mensagem, não adicionar
            return;
        }
        if(posts.size() >= 10){
            //remover primeira mensagem
            posts.remove(0);
        }
        
        //adicionar nova mensagem na ultima posiçao
        posts.add(mensagem);
    }
    
    public void listar(){
        System.out.println("Lista Posts:");
        for(String post: posts){
            System.out.println(post);
        }
    }
}
