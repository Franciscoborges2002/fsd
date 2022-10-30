import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class AtenderPedidos extends Thread{
    Socket ligacao;
    private BufferedReader bufferIn;
    private PrintWriter printOut;
    SessaoAtual sessaoAtual;

    public AtenderPedidos(Socket ligacao, SessaoAtual sessaoAtual) throws IOException{
        this.ligacao = ligacao;
        this.sessaoAtual = sessaoAtual;

        try{
            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);    
        }catch(IOException err){
            System.out.println("Erro na execucao do servidor: " + err);
			System.exit(1);
        }
    }

    public void run(){//Mudar, para quando aceitar uma ligação criar o cliente e adicioanr ao repositorio do servidor
        String pedido, mensagem;
        int indexVirgula;
        try{
            
            while(true){
                pedido = bufferIn.readLine();
                System.out.println(pedido);
    
                switch(tipoMensagem(pedido)){
                    case "SESSION_UPDATE_REQUEST":
                        String nick;
                        int sessionTimeoutSeg;

                        //Remover tipo mensagem
                        indexVirgula = pedido.indexOf(",") +1;
                        pedido = pedido.substring(indexVirgula);

                        //fazer o que é preciso
                        nick = pedido.substring(0, pedido.indexOf(","));//separa o nick
                        //System.out.println("nick " +nick);
                        pedido = pedido.substring(pedido.indexOf(",") + 1);
                        sessionTimeoutSeg = Integer.parseInt(pedido.substring(0));
                        //System.out.println("timeout " +sessionTimeoutSeg);

                        AgenteUtilizador novoCliente = new AgenteUtilizador(nick, sessionTimeoutSeg);//Criar AgenteUtilziador
                        sessaoAtual.getRepAgenteUtilizador().adicionarCliente(novoCliente);//Adicionar repositorio
                    break;
                    case "AGENT_POST":
                        //Remover tipo mensagem
                        indexVirgula = pedido.indexOf(",") +1;//index da virgula
                        mensagem = pedido.substring(indexVirgula);

                        //adicionar às mensagens
                        sessaoAtual.getRepositorioPosts().adicionarMensagem(mensagem);
                        sessaoAtual.getRepositorioPosts().listar();
                    break;
                }

                //enviar para todos
                System.out.println("eNVIAR PARA TODOS OS UTILIZADORES");
                //sessaoAtual.enviarParaTodos(printOut);
                ArrayList<AtenderPedidos> threads = sessaoAtual.getAtenderPedidos();
                for(AtenderPedidos thread: threads){
                    System.out.println("A enviar para thread " + thread);
                    System.out.println(sessaoAtual.getInfoSession2Send());
                    printOut.println(sessaoAtual.getInfoSession2Send());
                }
            }
        }catch(IOException e){
            System.out.println("Erro na execucao do servidor: "+e);
            System.exit(1);
        }
    }

    public String tipoMensagem(String mensagem){
        int indexVirgula = mensagem.indexOf(",");

        String tipoMensagem = mensagem.substring(0, indexVirgula);

        return tipoMensagem;
    }
    
}
