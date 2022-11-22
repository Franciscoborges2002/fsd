/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.util.ArrayList;
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
        boolean temNomes = true, recebeMensagens;
        String nickname, ipUtilizador, utilizador;
        //Limpar lista
        repAgenteUtilizador.limparLista();
        
        //Passar a string para arraylist
        ArrayList<AgenteUtilizador> arrayList = new ArrayList<>();
        
        while(temNomes){
            System.out.println("TEM NICKS" + temNomes);
            
            if(utilizadores.contains(",")){//Se ainda tiver virgulas, quer dizer que ainda tem nomes
                System.out.println("Utilizadores " + utilizadores);
                System.out.println("VARIOS UTILIZADOR");
                utilizador = utilizadores.substring(0, utilizadores.indexOf(","));
                //System.out.println("asdasd");
                
                if(utilizador.contains("(") && utilizador.contains(")")){//Se contiver, quer dizer que recebe mensagens
                    System.out.println("asdasd");
                    recebeMensagens = true;
                    ipUtilizador = utilizador.substring(utilizador.indexOf("(") + 1, utilizador.indexOf(")"));
                    System.out.println("Ip Utilizador1" + ipUtilizador);
                    nickname = utilizador.substring(0, utilizador.indexOf("("));
                }else{//Não recebe mensagens
                    System.out.println("asdawojp");
                    recebeMensagens = false;
                    ipUtilizador = null;
                    System.out.println("çççççççççç pp:" + utilizador.indexOf(","));
                    nickname = utilizador;
                }
                
                System.out.println("ddddddddddd");
                
                System.out.println("NICKNAME1 " + nickname);
                
                arrayList.add(new AgenteUtilizador(nickname, ipUtilizador, recebeMensagens));
                System.out.println("oioi");
                
                utilizadores = utilizadores.substring(utilizadores.indexOf(",") + 2);
                System.out.println("npooa");
            }else{//Se não tiver virgulas, quer dizer que só tem um nome
                utilizador = utilizadores;
                System.out.println("UM UTILIZADOR");
                
                //verificar se o utilizador recebe mensagens
                if(utilizador.contains("(") && utilizador.contains(")")){//Se contiver, quer dizer que recebe mensagens
                    recebeMensagens = true;
                    ipUtilizador = utilizador.substring(utilizador.indexOf("(") + 1, utilizador.indexOf(")"));
                    System.out.println("Ip Utilizador2" + ipUtilizador);
                    nickname = utilizador.substring(0, utilizador.indexOf("("));
                }else{//Não recebe mensagens
                    recebeMensagens = false;
                    ipUtilizador = null;
                    nickname = utilizador;
                }
                
                System.out.println("ip ja feito");
                
                System.out.println("NICKNAME2 " + nickname);
                
                arrayList.add(new AgenteUtilizador(nickname, ipUtilizador, recebeMensagens));
                utilizadores = "";
            }
            
            if(utilizadores.length() < 1){
                System.out.println("Não tem utilizadores");
                temNomes = false;
            }
        }
        
        //mudar arraylist
        repAgenteUtilizador.setRepositorioAgenteUtilizador(arrayList);
        
        repAgenteUtilizador.listar();
    }
    
    public void adicionarPosts(String posts){
        //Passar a string par aarraylist
        ArrayList<String> arrayList = new ArrayList<>();
        boolean temMensagens = true, temMaisVirgula;
        String post;
        int indexVirgula = 0, indexAdicionarPost = 0;
        
        System.out.println("Iniciar divisao posts");
        System.out.println("Posts " + posts);
        
        while(temMensagens){
            
            if(posts.contains(",")){//Caso tenha virgula
                if(numeroCharEmString(posts, ":") == 1){//Se tiver apenas 1 vez :
                    arrayList.add(indexAdicionarPost, posts);
                    posts = "";
                }else{//Se tiver mais do que 1 vez
                    //Ir adicionando posts
                    post = posts.substring(0, posts.indexOf(":", posts.indexOf(":") + 1));//Começar a string até à segunda vez que aparecam os :
                    
                    indexVirgula = ultimaVirgula(post);//Ter index da última virgula
                    
                    post = post.substring(0, indexVirgula);//Meter a mensagem desde o inicio até a última vez que ve uma Virgula(,)
                    
                    arrayList.add(indexAdicionarPost, post);
                    posts = posts.substring(indexVirgula + 2);
                    indexAdicionarPost++;
                }
                
            }else{//Caso não tenha virgula
                if(posts.length() != 0){//Caso tenha mais que 1 car, quer dizer que tem 1 mensagem
                    //ADICIONAR O ÚNICO POST 
                    arrayList.add(indexAdicionarPost, posts);
                    posts = "";
                }
            }
            
            if(posts.length() < 2){
                temMensagens = false;
            }
        }
        
        repPosts.setRepositorioMensagens(arrayList);
    }
    
    public int numeroCharEmString(String mensagem, String charProcurar){
        int numeroRepeticoes = 0;
        
        for(int i = 0; i < mensagem.length(); i++){
            if(mensagem.charAt(i) == charProcurar.charAt(0)){
                numeroRepeticoes++;
            }
        }
        
        return numeroRepeticoes;
    }
    
    public int ultimaVirgula(String mensagem){
        int index = 0;
        
        for(int i = 0; i < mensagem.length(); i++){            
            if(mensagem.charAt(i) == ','){
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
