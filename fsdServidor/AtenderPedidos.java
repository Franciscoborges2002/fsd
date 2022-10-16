import java.io.*;
import java.net.*;

public class AtenderPedidos extends Thread{
    Socket ligacao;
    private BufferedReader bufferIn;
    private PrintWriter printOut;

    public AtenderPedidos(Socket ligacao) throws IOException{
        this.ligacao = ligacao;
        try{
            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);    
        }catch(IOException err){
            System.out.println("Erro na execucao do servidor: " + err);
			System.exit(1);
        }
    }

    public void run(){
        String pedido, resposta;

        System.out.println("Pedido recebido;");
        try{
            pedido = bufferIn.readLine();
            System.out.println(pedido);
            resposta = pedido.toUpperCase();
            printOut.println(resposta);
        }catch(IOException e){
            System.out.println("Erro na execucao do servidor: "+e);
            System.exit(1);
        }
    }
    
}
