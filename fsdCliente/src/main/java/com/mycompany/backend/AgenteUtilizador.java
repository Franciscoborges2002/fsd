/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.backend;

/*
 * Classe para ter o objeto cliente que vai ser aidiocnado ao repositório clientes
*/

public class AgenteUtilizador {
    String nomeCliente;
    int segundosUltimaMensagem = 0;
    
    public AgenteUtilizador(String nick){
        this.nomeCliente = nick;
    }

    public void setNickAgenteUtilziador(String nick){
        this.nomeCliente = nick;
    }

    public String getNickAgenteUtilizador(){
        return this.nomeCliente;
    }
}
