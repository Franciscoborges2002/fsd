import java.io.*;
import java.net.*;

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
        String pedido, resposta;

        try{
            pedido = bufferIn.readLine();
            System.out.println(pedido);
            
            funcMensagem(tipoMensagem(pedido), pedido);
            
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

    //Função para receber ver quais os dados recebido
    public void funcMensagem(String tipoMensagem, String mensagem){
        /* String[] dadosMensagemEstruturados;
        dadosMensagemEstruturados. */
        switch(tipoMensagem){
            case "SESSION_UPDATE_REQUEST":
                String nick;
                int indexVirgula = mensagem.indexOf(",") +1;//index da virgula
                nick = mensagem.substring(indexVirgula);//separa o nick

                AgenteUtilizador novoCliente = new AgenteUtilizador(nick);//Criar AgenteUtilziador
                sessaoAtual.getRepAgenteUtilizador().adicionarCliente(novoCliente);//Adicionar repositorio
                
                //enviar a mensagem
                printOut.println(getInfoSession(sessaoAtual.getRepAgenteUtilizador(), sessaoAtual.getRepositorioPosts()));
            break;
        }
    }

    public String getInfoSession(RepositorioAgenteUtilizador repAgenteUtilizador, RepositorioPosts repPosts){
        /* System.out.println(repAgenteUtilizador.getNickAgentesUtilizador().toString());
        System.out.println(repPosts.getRepositorioMensagens().toString()); */
        return "SESSION_UPDATE," + repAgenteUtilizador.getNickAgentesUtilizador().toString()+ "," +repPosts.getRepositorioMensagens().toString();
    }
    
}
