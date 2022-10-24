public class RepositoriosHandler {
    private RepositorioAgenteUtilizador repClientes;
    private RepositorioPosts repMensagens;

    public RepositoriosHandler(){
        repClientes = new RepositorioAgenteUtilizador();
        repMensagens = new RepositorioPosts();
    }

    public RepositorioAgenteUtilizador getRepositorioClientes(){
        return this.repClientes;
    }

    public RepositorioPosts getRepositorioMensagens(){
        return this.repMensagens;
    }

    public void setRepositorioClientes(RepositorioAgenteUtilizador repClientes){
        this.repClientes = repClientes;
    }

    public void setRepositorioMensagens(RepositorioPosts repMensagens){
        this.repMensagens = repMensagens;
    }
}
