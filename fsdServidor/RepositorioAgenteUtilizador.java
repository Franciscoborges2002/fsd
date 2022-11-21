/*
 * Repositorio para guardar todos os agentes utilizadores
 */ 
import java.util.*;

public class RepositorioAgenteUtilizador {
    private Hashtable<Integer, AgenteUtilizador> agentesUtilizador = new Hashtable<>();
    private int id;

    public RepositorioAgenteUtilizador(){
        this.id = 0;
    }

    public void adicionarCliente(AgenteUtilizador cliente){
        for (Map.Entry<Integer, AgenteUtilizador> entry : agentesUtilizador.entrySet()) {
            AgenteUtilizador agenteUtilizador = entry.getValue();
        
            if(agenteUtilizador.getNomeUtilizadorAgenteUtilizador().equals(cliente.getNomeUtilizadorAgenteUtilizador())){
                throw new IllegalArgumentException("O cliente já está na lista.");
            }
        }
        
        if(!agentesUtilizador.containsValue(cliente)){
            agentesUtilizador.put(id, cliente);
            id++;
        }else{
            throw new IllegalArgumentException("O cliente já está na lista.");
        }
    }

    public void removerCliente(AgenteUtilizador cliente){
        int keyRemover = -1;

        for(Map.Entry<Integer, AgenteUtilizador> entry: agentesUtilizador.entrySet()){
            if(cliente.equals(entry.getValue())){
                keyRemover = (int) entry.getKey();
                break;
            }
        }

        if(agentesUtilizador.containsValue(cliente) && keyRemover != -1){
            agentesUtilizador.remove(keyRemover);
        }else{
            throw new IllegalArgumentException("O cliente não está na lista.");
        }
    }

    public void removerClienteNome(String nomeUtilizador){
        int keyRemover = -1;

        for(Map.Entry<Integer, AgenteUtilizador> entry: agentesUtilizador.entrySet()){
            if(nomeUtilizador.equals(entry.getValue().getNomeUtilizadorAgenteUtilizador())){
                keyRemover = (int) entry.getKey();
                break;
            }
        }

        if(keyRemover != -1){
            agentesUtilizador.remove(keyRemover);
        }else{
            throw new IllegalArgumentException("O cliente não está na lista.");
        }
    }

    public AgenteUtilizador getAgenteUtilizador(String nomeUtilizador){
        AgenteUtilizador agenteUtilizador = null;
        for (Map.Entry<Integer, AgenteUtilizador> entry : agentesUtilizador.entrySet()) {
            if(nomeUtilizador.equals(entry.getValue().getNomeUtilizadorAgenteUtilizador())){
                agenteUtilizador = entry.getValue();
            }
            
        }

        return agenteUtilizador;
    }

    public ArrayList<String> getAgentesUtilizadores(){
        ArrayList<String> nomesUtilizadoresAgentesUtilizadores = new ArrayList<String>();

        //Passar por todos os valores da hash table
        for (Map.Entry<Integer, AgenteUtilizador> entry : agentesUtilizador.entrySet()) {
            AgenteUtilizador agenteUtilizador = entry.getValue();

            if(agenteUtilizador.recebeMensagensPrivadas()){
                nomesUtilizadoresAgentesUtilizadores.add(agenteUtilizador.getNomeUtilizadorAgenteUtilizador() + "(" + agenteUtilizador.getIpUtilizador() + ")");
            }else{
                nomesUtilizadoresAgentesUtilizadores.add(agenteUtilizador.getNomeUtilizadorAgenteUtilizador());
            }
        }

        return nomesUtilizadoresAgentesUtilizadores;
    } 

    public void setClientes(Hashtable<Integer, AgenteUtilizador> tabelaClientes){
        this.agentesUtilizador = tabelaClientes;
    }

    public Hashtable<Integer, AgenteUtilizador> getClientes(){
        return this.agentesUtilizador;
    }

    public void setId(int novoId){
        this.id = novoId;
    }

    public int getId(){
        return this.id;
    }
}
