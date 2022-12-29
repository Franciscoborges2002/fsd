/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

/*
 * Repositorio para guardar todos os agentes utilizadores
 */ 
import java.util.*;

public class RepositorioAgenteUtilizador {
    private ArrayList<AgenteUtilizador> agentesUtilizadores;

    public RepositorioAgenteUtilizador(){
        agentesUtilizadores = new ArrayList<>();
    }
    
    public ArrayList<AgenteUtilizador> getRepositorioAgenteUtilizador(){
        return this.agentesUtilizadores;
    }
    
    public void setRepositorioAgenteUtilizador(ArrayList<AgenteUtilizador> agentesUtilizadores){
        this.agentesUtilizadores = agentesUtilizadores;
    }
    
    public void adicionarAgenteUtilizador(AgenteUtilizador agenteUtilizador){
        agentesUtilizadores.add(agenteUtilizador);
    }
    
    public ArrayList<String> getNomesAgentesUtilizadores(){
        ArrayList<String> arrayList = new ArrayList<>();
        
        for(AgenteUtilizador agenteUtilizador: agentesUtilizadores){
            arrayList.add(agenteUtilizador.getNomeUtilizadorAgenteUtilizador());
        }
        
        return arrayList;
    }
    
    public AgenteUtilizador getAgenteByNome(String nomeUtilizador){
        AgenteUtilizador agenteRetornar = null;
       
        for(AgenteUtilizador agenteUtilizador: agentesUtilizadores){
            if(nomeUtilizador.equals(agenteUtilizador.getNomeUtilizadorAgenteUtilizador())){
                agenteRetornar = agenteUtilizador;
                break;
            }
        }
        
        return agenteRetornar;
    }
    
    public void limparLista(){
        agentesUtilizadores.clear();
    }
    
    public void listar(){
        System.out.println("Listar utilizadores:");
        for(AgenteUtilizador agentUtilizador: agentesUtilizadores){
            System.out.println(agentUtilizador.getNomeUtilizadorAgenteUtilizador() + "(" + agentUtilizador.getIpUtilizador() + ") ->" + agentUtilizador.getChavePublica());
        }
    }

}
