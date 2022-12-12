import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class AtenderPedidos extends Thread{
    private Socket ligacao;
    private BufferedReader bufferIn;
    private PrintWriter printOut;
    private SessaoAtual sessaoAtual;
    private String nomeUtilizador;
    private int ultimaInteracao, sessionTimeout;

    public AtenderPedidos(Socket ligacao, SessaoAtual sessaoAtual) throws IOException{
        this.ligacao = ligacao;
        this.sessaoAtual = sessaoAtual;
        this.ultimaInteracao = 0;

        try{
            this.bufferIn = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
            this.printOut = new PrintWriter(ligacao.getOutputStream(), true);    
        }catch(IOException err){
            System.out.println("Erro na execucao do servidor: " + err);
			System.exit(1);
        }
    }

    public void run(){//Mudar, para quando aceitar uma ligação criar o cliente e adicioanr ao repositorio do servidor
        String pedido, mensagem, protocoloMensagensPrivadas;
        Boolean recebeMensagens;
        verificarSessionTimeout(this);

        try{ 
            while(true){
                pedido = bufferIn.readLine();
                System.out.println(pedido);

                if(pedido == null){
                    System.out.println("Pedido null, fechar a ligacao fechada");
                    sessaoAtual.removerUtilizador(this, this.nomeUtilizador);
                    enviarParaTodos(sessaoAtual.getAtenderPedidos());
                    break;
                }
    
                switch(tipoMensagem(pedido)){
                    case "SESSION_UPDATE_REQUEST":
                        if(!(pedido.length() == 22)){//Se tiver diferente de 22 caracteres adicionar nome, ip utilizador e se quer receber mensagens ou não
                            System.out.println(pedido);
                            //Remover tipo mensagem
                            pedido = pedido.substring(pedido.indexOf(",") +1);

                            //Adicionar nome utilizador
                            nomeUtilizador = pedido.substring(0, pedido.indexOf(","));//separa o nick

                            //Remover nomeUtilizador
                            pedido = pedido.substring(pedido.indexOf(",") +1);

                            //Receber se o cliente vai querer receber mensagens privadas
                            recebeMensagens = Boolean.parseBoolean(pedido.substring(0, pedido.indexOf(",")));

                            //Remover se o cliente vai querer receber mensagens privadas
                            pedido = pedido.substring(pedido.indexOf(",") +1);

                            //protocoloMensagensPrivadas
                            protocoloMensagensPrivadas = pedido.substring(0);//separa o nick

                            AgenteUtilizador novoCliente = new AgenteUtilizador(nomeUtilizador, ligacao.getRemoteSocketAddress().toString(), recebeMensagens, protocoloMensagensPrivadas);//Criar AgenteUtilziador
                            sessaoAtual.getRepAgenteUtilizador().adicionarCliente(novoCliente);//Adicionar repositorio
                            
                            /* TODO: remover*/sessaoAtual.getRepAgenteUtilizador().listar();

                            //Enviar para todos os utilizadores
                            enviarParaTodos(sessaoAtual.getAtenderPedidos());
                        }else{
                            //Enviar só para o cliente que pediu o request
                            printOut.println(sessaoAtual.getInfoSession2Send());
                        }
                    break; 
                    case "AGENT_POST":
                        //Remover tipo mensagem
                        mensagem = pedido.substring(pedido.indexOf(",") +1);

                        //adicionar às mensagens
                        sessaoAtual.getRepositorioPosts().adicionarMensagem(nomeUtilizador, mensagem, sessaoAtual.getRepAgenteUtilizador());

                        //Enviar para todos os utilziadores
                        enviarParaTodos(sessaoAtual.getAtenderPedidos());
                    break;
                    default:
                        System.out.println("Mensagem sem tipo");
                    break;
                }
            }

            ligacao.close();
        }catch(IOException e){
            System.out.println("Erro na execucao do servidor: "+e);
            System.exit(1);
        }
    }

    public String tipoMensagem(String mensagem){
        if(mensagem != null && mensagem.contains(",")){
            return mensagem.substring(0, mensagem.indexOf(","));
        }else{
            return mensagem;
        }
    }

    //Thread para verificar os tempos
    public boolean verificarSessionTimeout(Thread threadParaRemover){
        boolean paraTerminar = false;

        Thread threadVerificarSessionTimeout = new Thread(){
            public void run(){
                try {
                    while(true){
                        if(ultimaInteracao >= sessionTimeout){
                            printOut.println("SESSION_TIMEOUT");

                            if(bufferIn.readLine() == null){
                                sessaoAtual.removerUtilizador(threadParaRemover, nomeUtilizador);//remover das threads ativas
                                enviarParaTodos(sessaoAtual.getAtenderPedidos());//Enviar para todos os agentes
                               
                                //fechar tudo
                                bufferIn.close();
                                printOut.close();
                                ligacao.close();

                                return;
                            }  
                        }
    
                        this.sleep(1000);

                        ultimaInteracao++;//Adicionar 1 seg à interação
                    }
                } catch (InterruptedException | IOException e) {
                    System.out.println(e);
                }
            }
        };

        threadVerificarSessionTimeout.start();
        return paraTerminar;
    }
    

    public void setSessionTimeout(int sessionTimeout){
        this.sessionTimeout = sessionTimeout;
    }

    public void enviarParaTodos(ArrayList<AtenderPedidos> repThreads){
        System.out.println(sessaoAtual.getInfoSession2Send());
        for(AtenderPedidos thread: repThreads){
            /* TODO: Remover */System.out.println("A enviar para thread " + thread);
            thread.getPrintOut().println(sessaoAtual.getInfoSession2Send());
        }
    }

    public PrintWriter getPrintOut(){
        return this.printOut;
    }
}