/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.backend;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        System.out.println("Cliente iniciado!");
        
        //ConectarServidor conexaoServidor = new ConectarServidor();
        PaginaInicial paginaInicial = new PaginaInicial();
        paginaInicial.setVisible(true);
          
        //conexaoServidor.conectar();
    }
}
