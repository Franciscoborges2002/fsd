import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * Classe para ter todas as informações da sessão atual,
 * como pessoas online
 * tratar de enviar as mensagens a todas as pessoas online 
 */

public class SessaoAtual {
    private RepositorioAgenteUtilizador repAgenteUtilizador;
    private RepositorioPosts repPosts;
    private ArrayList<AtenderPedidos> repThreads;

    public SessaoAtual(){
        this.repAgenteUtilizador = new RepositorioAgenteUtilizador();
        this.repPosts = new RepositorioPosts();
        this.repThreads = new ArrayList<AtenderPedidos>();
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

    public ArrayList<AtenderPedidos> getAtenderPedidos(){
        return this.repThreads;
    }

    public void setAtenderPedidos(ArrayList<AtenderPedidos> repThreads){
        this.repThreads = repThreads;
    }

    public void removerUtilizador(Thread threadUtilizador, String nickUtilizador){//Remover thread e utilizador do repositorio
        repThreads.remove(threadUtilizador);
        repAgenteUtilizador.removerClienteNome(nickUtilizador);
    }

    public String getInfoSession2Send(){
        return "SESSION_UPDATE," + repAgenteUtilizador.getAgentesUtilizadores().toString()+ "," + repPosts.getRepInString().toString();
    }
}
