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
    private ArrayList<String> agentesUtilizador;

    public RepositorioAgenteUtilizador(){
        agentesUtilizador = new ArrayList<>();
    }
    
    public ArrayList<String> getRepositorioAgenteUtilizador(){
        return this.agentesUtilizador;
    }
    
    public void setRepositorioAgenteUtilizador(ArrayList<String> agentesUtilizadores){
        this.agentesUtilizador = agentesUtilizadores;
    }
    
    public void listar(){
        System.out.println("Listar utilizadores:");
        for(String agentUtilizador: agentesUtilizador){
            System.out.println(agentUtilizador);
        }
    }

}
