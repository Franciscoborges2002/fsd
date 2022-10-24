import java.util.ArrayList;

public class RepositorioPosts {
    private ArrayList<Mensagem> mensagens;

    public RepositorioPosts(){
        mensagens = new ArrayList<Mensagem>(10);
    }

    public ArrayList<Mensagem> getRepositorioMensagens(){
        return this.mensagens;
    }

    public void setRepositorioMensagens(ArrayList<Mensagem> repMensagens){
        this.mensagens = repMensagens;
    }

    public void adicionarMensagem(String mensagem, AgenteUtilizador utilizador){
        Mensagem mensagemAdicionar = new Mensagem(mensagem, utilizador);
        
        //verificar quantas mensagens tem na arraylsit
        int mensagensGuardadas = mensagens.size();

        if(mensagensGuardadas >= 10){
            //remover primeira mensagem
            mensagens.remove(0);
        }
        
        //adicionar nova mensagem na ultima posiçao
        mensagens.add(mensagemAdicionar);
    }
}