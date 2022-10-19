/*
 * Repositorio para guardar todos os clientes
 */ 
import java.util.*;

public class RepositorioClientes {
    private Hashtable<Integer, Cliente> clientes = new Hashtable<>();
    private int id;

    public RepositorioClientes(){
        this.id = 0;
    }

    public void adicionarCliente(Cliente cliente){
        if(!clientes.containsValue(cliente)){
            clientes.put(id, cliente);
            id++;
        }else{
            throw new IllegalArgumentException("O cliente já está na lista.");
        }
    }

    public void removerCliente(Cliente cliente){
        int keyRemover = -1;

        for(Map.Entry<Integer, Cliente> entry: clientes.entrySet()){
            if(cliente.equals(entry.getValue())){
                keyRemover = (int) entry.getKey();
                break;
            }
        }

        if(clientes.containsValue(cliente) && keyRemover != -1){
            clientes.remove(keyRemover);
        }else{
            throw new IllegalArgumentException("O cliente não está na lista.");
        }
    }

    public void removerUtilizador(Cliente cliente){
        clientes.put(id, cliente);
    }

    public void setClientes(Hashtable<Integer, Cliente> tabelaClientes){
        this.clientes = tabelaClientes;
    }

    public Hashtable<Integer, Cliente> getClientes(){
        return this.clientes;
    }

    public void setId(int novoId){
        this.id = novoId;
    }

    public int getId(){
        return this.id;
    }
}
