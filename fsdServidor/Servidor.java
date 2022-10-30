import java.net.*;
import java.io.*;


public class Servidor {
    private static int DEFAULT_PORT=2001;
    private SessaoAtual sessaoAtual;

    public Servidor(){
        this.sessaoAtual = new SessaoAtual();
    }

    public void iniciar() throws IOException{
        int port = DEFAULT_PORT;

        ServerSocket servidor = new ServerSocket(port);//Criar servidor socket
        
        System.out.println("Servidor a' espera de ligacoes no porto " + port);

        while(true){
            try{
                Socket ligacao = servidor.accept();

                AtenderPedidos pedidoConexao = new AtenderPedidos(ligacao, sessaoAtual);

                sessaoAtual.getAtenderPedidos().add(pedidoConexao);
                pedidoConexao.start();
            } catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
        }
        //servidor.close();
    }
}
