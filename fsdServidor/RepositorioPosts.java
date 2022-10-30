import java.util.ArrayList;

public class RepositorioPosts {
    //private ArrayList<Mensagem> mensagens;
    private ArrayList<String> posts;
    public RepositorioPosts(){
        posts = new ArrayList<String>(10);
    }

    public ArrayList<String> getRepositorioPosts(){
        return this.posts;
    }

    public void setRepositorioposts(ArrayList<String> repPosts){
        this.posts = repPosts;
    }

    public void adicionarMensagem(String mensagem){
       // Mensagem mensagemAdicionar = new Mensagem(mensagem, utilizador);
        System.out.println("Memsagem a adicionar: " + mensagem);
        if(posts.size() >= 10){
            //remover primeira mensagem
            posts.remove(0);
        }
        
        //adicionar nova mensagem na ultima posiçao
        posts.add(mensagem);
    }

    public void listar(){
        for(String post: posts){
            System.out.println(post);
        }
    }
}