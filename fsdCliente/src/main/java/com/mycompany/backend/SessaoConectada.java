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
    
    public void adicionarPosts(String posts){
        //Passar a string par aarraylist
        ArrayList<String> arrayList = new ArrayList<>();
        boolean temMensagens = true, temMaisVirgula;
        String post;
        int indexVirgula = 0, indexAdicionarPost = 0;
        
        while(temMensagens){
            temMaisVirgula = posts.contains(",");
            
            if(temMaisVirgula){//Caso tenha virgula
                if(numeroCharEmString(posts, ":") == 1){//Se tiver apenas 1 vez :
                    System.out.println("Post único com virgula");
                    arrayList.add(indexAdicionarPost, posts);
                }else{//Se tiver mais do que 1 vez
                    System.out.println("Vários posts");
                    //Ir adicionando posts
                    post = posts.substring(0, posts.indexOf(":", posts.indexOf(":") + 1));//Começar a string até à segunda vez que aparecam os :
                    
                    indexVirgula = ultimaVirgula(post);//Ter index da última virgula
                    
                    post = post.substring(0, indexVirgula);//Meter a mensagem desde o inicio até a última vez que ve uma Virgula(,)
                    arrayList.add(indexAdicionarPost, posts);
                    
                    posts = posts.substring(0, indexVirgula);
                }
                
            }else{//Caso não tenha virgula
                if(posts.length() != 0){//Caso tenha mais que 1 car, quer dizer que tem 1 mensagem
                    //ADICIONAR O ÚNICO POST 
                    System.out.println("index"+ indexAdicionarPost + ", post: " + posts);
                    arrayList.add(indexAdicionarPost, posts);
                }
            }
            
            indexAdicionarPost++;
            
            
            if(posts.length() < 2){
                temMensagens = false;
            }
        }
        
        repPosts.setRepositorioMensagens(arrayList);
    }
    
    public int numeroCharEmString(String mensagem, String charProcurar){
        int numeroRepeticoes = 0;
        
        for(int i = 0; i < mensagem.length(); i++){
            if(charProcurar.equals(mensagem.charAt(i))){
                numeroRepeticoes++;
            }
        }
        
        return numeroRepeticoes;
    }
    
    public int ultimaVirgula(String mensagem){
        int index = 0;
        
        for(int i = 0; i < mensagem.length(); i++){
            if("".equals(mensagem.charAt(i))){
                index = i;
            }
        }
        
        return index;
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
