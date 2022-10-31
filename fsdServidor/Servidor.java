import java.net.*;
import java.util.Scanner;
import java.io.*;


public class Servidor {
    private int port = 2001;
    private SessaoAtual sessaoAtual;
    private int sessionTimeout;
    Scanner scanner = new Scanner(System.in);

    public Servidor(){
        this.sessaoAtual = new SessaoAtual();
    }

    public void iniciar() throws IOException{
        System.out.println("Qual o tempo do sessionTimeout: ");
        sessionTimeout = Integer.parseInt(scanner.nextLine());

        ServerSocket servidor = new ServerSocket(port);//Criar servidor socket
        
        System.out.println("Servidor a' espera de ligacoes no porto " + port);

        while(true){
            try{
                Socket ligacao = servidor.accept();

                AtenderPedidos pedidoConexao = new AtenderPedidos(ligacao, sessaoAtual);

                sessaoAtual.getAtenderPedidos().add(pedidoConexao);
                pedidoConexao.setSessionTimeout(sessionTimeout);
                pedidoConexao.start();
            } catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
			}
        }
        //servidor.close();
    }
}
