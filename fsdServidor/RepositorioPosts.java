import java.util.ArrayList;

public class RepositorioPosts {
    private ArrayList<Post> posts;

    public RepositorioPosts(){
        posts = new ArrayList<Post>(10);
    }

    public ArrayList<Post> getRepositorioPosts(){
        return this.posts;
    }

    public void setRepositorioposts(ArrayList<Post> repPosts){
        this.posts = (ArrayList<Post>) repPosts;
    }

    public void adicionarMensagem(String emissor, String mensagem, RepositorioAgenteUtilizador repAgentesUtilizador){
        System.out.println("Memsagem a adicionar: " + mensagem + " por " + emissor);

        if(posts.size() >= 10){
            //remover primeira mensagem
            posts.remove(0);
        }

        Post postAdicionar = new Post(mensagem, repAgentesUtilizador.getAgenteUtilizador(emissor));
        
        //adicionar nova mensagem na ultima posiçao
        posts.add(postAdicionar);
    }

    public ArrayList<String> getRepInString(){
        ArrayList<String> arrayList = new ArrayList<>();

        posts.forEach((elemento) -> {
            String linhaParaAdicionar = elemento.getAgenteUtilizador().getNomeUtilizadorAgenteUtilizador() + ":" + elemento.getMensagem();
            arrayList.add(linhaParaAdicionar);
        });

        return arrayList;
    }

    //MUDAR LISTAR
    /* public void listar(){
        for(String post: posts){
            System.out.println(post);
        }
    } */
}