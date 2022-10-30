/*
 * Repositorio para guardar todos os agentes utilizadores
 */ 
import java.util.*;

public class RepositorioAgenteUtilizador {
    private Hashtable<Integer, AgenteUtilizador> agenteUtilizador = new Hashtable<>();
    private int id;

    public RepositorioAgenteUtilizador(){
        this.id = 0;
    }

    public void adicionarCliente(AgenteUtilizador cliente){
        for (Map.Entry<Integer, AgenteUtilizador> entry : agenteUtilizador.entrySet()) {
            AgenteUtilizador agenteUtilizador = entry.getValue();
        
            if(agenteUtilizador.getNickAgenteUtilizador().equals(cliente.getNickAgenteUtilizador())){
                throw new IllegalArgumentException("O cliente já está na lista.");
            }
        }
        
        if(!agenteUtilizador.containsValue(cliente)){
            agenteUtilizador.put(id, cliente);
            id++;
        }else{
            throw new IllegalArgumentException("O cliente já está na lista.");
        }
    }

    public void removerCliente(AgenteUtilizador cliente){
        int keyRemover = -1;

        for(Map.Entry<Integer, AgenteUtilizador> entry: agenteUtilizador.entrySet()){
            if(cliente.equals(entry.getValue())){
                keyRemover = (int) entry.getKey();
                break;
            }
        }

        if(agenteUtilizador.containsValue(cliente) && keyRemover != -1){
            agenteUtilizador.remove(keyRemover);
        }else{
            throw new IllegalArgumentException("O cliente não está na lista.");
        }
    }

    public void listarTudo(){
        //Passar por todos os valores doa hash table
        
    }

    public ArrayList<String> getNickAgentesUtilizador(){
        ArrayList<String> nicksAgentesUtilizadores = new ArrayList<String>();

        //Passar por todos os valores doa hash table
        for (Map.Entry<Integer, AgenteUtilizador> entry : agenteUtilizador.entrySet()) {
            AgenteUtilizador agenteUtilizador = entry.getValue();

            nicksAgentesUtilizadores.add(agenteUtilizador.getNickAgenteUtilizador());
        }

        return nicksAgentesUtilizadores;
    } 

    public void setClientes(Hashtable<Integer, AgenteUtilizador> tabelaClientes){
        this.agenteUtilizador = tabelaClientes;
    }

    public Hashtable<Integer, AgenteUtilizador> getClientes(){
        return this.agenteUtilizador;
    }

    public void setId(int novoId){
        this.id = novoId;
    }

    public int getId(){
        return this.id;
    }
}
