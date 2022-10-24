/*
 * Classe para ter todas as informações da sessão atual,
 * como pessoas online
 * tratar de enviar as mensagens a todas as pessoas online 
 */

public class SessaoAtual {
    private RepositorioAgenteUtilizador repAgenteUtilizador;
    private RepositorioPosts repPosts;

    public SessaoAtual(){
        repAgenteUtilizador = new RepositorioAgenteUtilizador();
        repPosts = new RepositorioPosts();
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
