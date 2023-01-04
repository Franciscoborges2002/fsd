/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;    

/*
 * Classe para ter todas as informações da sessão atual,
 * por parte do cliente
 */
public class SessaoConectada {

    private RepositorioAgenteUtilizador repAgenteUtilizador;
    private RepositorioPosts repPosts;

    public SessaoConectada() {
        repAgenteUtilizador = new RepositorioAgenteUtilizador();
        repPosts = new RepositorioPosts();
    }

    public void mudarUtilizadores(String utilizadores) throws NoSuchAlgorithmException, InvalidKeySpecException {
        boolean temNomes = true, recebeMensagens;
        String nickname, ipUtilizador, utilizador, chavePublica;

        //Limpar lista de utilizadores
        repAgenteUtilizador.limparLista();

        //Passar a string para arraylist
        ArrayList<AgenteUtilizador> arrayList = new ArrayList<>();

        while (temNomes) {//Enquanto existem nomes na string

            if (utilizadores.contains(",")) {//Se ainda tiver virgulas, quer dizer que ainda tem nomes
                utilizador = utilizadores.substring(0, utilizadores.indexOf(","));

                ipUtilizador = utilizador.substring(utilizador.indexOf("(") + 1, utilizador.indexOf(")"));

                recebeMensagens = !ipUtilizador.equals("null"); //Verificar se recebe ou não mensagens privadas

                nickname = utilizador.substring(0, utilizador.indexOf("("));//Dar set ao nickname do utilizador
                chavePublica = utilizador.substring(utilizador.indexOf(")") + 1);//Dar set à chave pública do utilizador
                
                byte[] chavePublicaBytes = Base64.getDecoder().decode(chavePublica);
                
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(chavePublicaBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                arrayList.add(new AgenteUtilizador(nickname, ipUtilizador, recebeMensagens, "RMI", keyFactory.generatePublic(keySpec)));

                utilizadores = utilizadores.substring(utilizadores.indexOf(",") + 2);
            } else {//Se não tiver virgulas, quer dizer que só tem um nome
                utilizador = utilizadores;
                System.out.println(utilizador);
                ipUtilizador = utilizador.substring(utilizador.indexOf("(") + 1, utilizador.indexOf(")"));

                recebeMensagens = !ipUtilizador.equals("null"); //Verificar se recebe ou não mensagens privadas

                nickname = utilizador.substring(0, utilizador.indexOf("("));  //Dar set ao nickname do utilizador
                chavePublica = utilizador.substring(utilizador.indexOf(")") + 1);//Dar set à chave pública do utilizador
                
                byte[] chavePublicaBytes = Base64.getDecoder().decode(chavePublica);
                
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(chavePublicaBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                System.out.println(keySpec);
                System.out.println(keyFactory);
                System.out.println(keyFactory.generatePublic(keySpec));

                arrayList.add(new AgenteUtilizador(nickname, ipUtilizador, recebeMensagens, "RMI", keyFactory.generatePublic(keySpec)));
                utilizadores = "";
            }

            if (utilizadores.length() < 1) {
                temNomes = false;
            }
        }

        //mudar arraylist
        repAgenteUtilizador.setRepositorioAgenteUtilizador(arrayList);

        repAgenteUtilizador.listar();
    }

    public void adicionarPosts(String posts) {
        //Passar a string par aarraylist
        ArrayList<String> arrayList = new ArrayList<>();
        boolean temMensagens = true, temMaisVirgula;
        String post;
        int indexVirgula = 0, indexAdicionarPost = 0;

        while (temMensagens) {

            if (posts.contains(",")) {//Caso tenha virgula
                if (numeroCharEmString(posts, ":") == 1) {//Se tiver apenas 1 vez :
                    arrayList.add(indexAdicionarPost, posts);
                    posts = "";
                } else {//Se tiver mais do que 1 vez
                    //Ir adicionando posts
                    post = posts.substring(0, posts.indexOf(":", posts.indexOf(":") + 1));//Começar a string até à segunda vez que aparecam os :

                    indexVirgula = ultimaVirgula(post);//Ter index da última virgula

                    post = post.substring(0, indexVirgula);//Meter a mensagem desde o inicio até a última vez que ve uma Virgula(,)

                    arrayList.add(indexAdicionarPost, post);
                    posts = posts.substring(indexVirgula + 2);
                    indexAdicionarPost++;
                }

            } else {//Caso não tenha virgula
                if (posts.length() != 0) {//Caso tenha mais que 1 car, quer dizer que tem 1 mensagem
                    //ADICIONAR O ÚNICO POST 
                    arrayList.add(indexAdicionarPost, posts);
                    posts = "";
                }
            }

            if (posts.length() < 2) {
                temMensagens = false;
            }
        }

        repPosts.setRepositorioMensagens(arrayList);
    }

    public int numeroCharEmString(String mensagem, String charProcurar) {
        int numeroRepeticoes = 0;

        for (int i = 0; i < mensagem.length(); i++) {
            if (mensagem.charAt(i) == charProcurar.charAt(0)) {
                numeroRepeticoes++;
            }
        }

        return numeroRepeticoes;
    }

    public int ultimaVirgula(String mensagem) {
        int index = 0;

        for (int i = 0; i < mensagem.length(); i++) {
            if (mensagem.charAt(i) == ',') {
                index = i;
            }
        }

        return index;
    }

    public RepositorioAgenteUtilizador getRepAgenteUtilizador() {
        return this.repAgenteUtilizador;
    }

    public void setRepAgenteUtilizador(RepositorioAgenteUtilizador novoRepAgenteUtilizador) {
        this.repAgenteUtilizador = novoRepAgenteUtilizador;
    }

    public RepositorioPosts getRepositorioPosts() {
        return this.repPosts;
    }

    public void setRepPosts(RepositorioPosts novoRepPosts) {
        this.repPosts = novoRepPosts;
    }
}
