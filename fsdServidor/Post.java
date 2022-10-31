public class Post {
    private String mensagem;
    private AgenteUtilizador utilizador;

    public Post(String mensagem, AgenteUtilizador utilizador){
        this.mensagem = mensagem;
        this.utilizador = utilizador;
    }

    public String getMensagem(){
        return this.mensagem;
    }

    public AgenteUtilizador getAgenteUtilizador(){
        return this.utilizador;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    public void getAgenteUtilizador(AgenteUtilizador agenteUtilizador){
        this.utilizador = agenteUtilizador;
    }
}
