import java.net.*;
import java.io.*;


public class Servidor {
    private static int DEFAULT_PORT=2000;
    private RepositorioClientes repClientes;

    public Servidor(){

    }

    public void iniciar() throws IOException{
        int port = DEFAULT_PORT;

        ServerSocket servidor = new ServerSocket(port);//Criar servidor socket
        repClientes = new RepositorioClientes();
        
        System.out.println("Servidor a' espera de ligacoes no porto " + port);

        while(true){
            try{
                Socket ligacao = servidor.accept();

                AtenderPedidos pedidoConexao = new AtenderPedidos(ligacao);

                pedidoConexao.start();
                
            } catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
        }
        //servidor.close();
    }

    public RepositorioClientes getRepositorioClientes(){
        return this.repClientes;
    }

    public void setRepositorioClientes(RepositorioClientes novoRepClientes){
        this.repClientes = novoRepClientes;
    }
}
