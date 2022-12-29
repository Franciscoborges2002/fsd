/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.backend;

import java.awt.Color;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PaginaInicial extends javax.swing.JFrame {
    private Conexoes conectarServidor;

    public PaginaInicial() {
        initComponents();
        this.conectarServidor = new Conexoes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoConectar = new javax.swing.JButton();
        espacoTextoNome = new javax.swing.JTextField();
        espacoTextoIp = new javax.swing.JTextField();
        espacoTextoPorta = new javax.swing.JTextField();
        espacoTextoTaxaAtualizacao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        checkBoxReceberMensagensPrivadas = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("fsdChat");

        botaoConectar.setText("Conectar");
        botaoConectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoConectarMouseClicked(evt);
            }
        });

        espacoTextoNome.setToolTipText("Inserir nick a usar no chat");
        espacoTextoNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                espacoTextoNomeFocusGained(evt);
            }
        });

        espacoTextoIp.setForeground(java.awt.Color.gray);
        espacoTextoIp.setText("127.0.0.1");
        espacoTextoIp.setToolTipText("Inserir o ip para conectar");
        espacoTextoIp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                espacoTextoIpFocusLost(evt);
            }
        });
        espacoTextoIp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                espacoTextoIpMouseClicked(evt);
            }
        });

        espacoTextoPorta.setForeground(java.awt.Color.gray);
        espacoTextoPorta.setText("2001");
        espacoTextoPorta.setToolTipText("Inserir a porta para conectar");
        espacoTextoPorta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                espacoTextoPortaFocusLost(evt);
            }
        });
        espacoTextoPorta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                espacoTextoPortaMouseClicked(evt);
            }
        });
        espacoTextoPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espacoTextoPortaActionPerformed(evt);
            }
        });

        espacoTextoTaxaAtualizacao.setForeground(java.awt.Color.gray);
        espacoTextoTaxaAtualizacao.setText("60");
        espacoTextoTaxaAtualizacao.setToolTipText("Inserir taxa de atualizacao");
        espacoTextoTaxaAtualizacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                espacoTextoTaxaAtualizacaoFocusLost(evt);
            }
        });
        espacoTextoTaxaAtualizacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                espacoTextoTaxaAtualizacaoMouseClicked(evt);
            }
        });

        jLabel1.setText("Taxa atualização:");

        jLabel2.setText("Nickname para usar:");

        jLabel3.setText("Porta a conectar:");

        jLabel4.setText("Ip servidor para conectar:");

        checkBoxReceberMensagensPrivadas.setSelected(true);
        checkBoxReceberMensagensPrivadas.setText("Pretende receber mensagens privadas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(espacoTextoIp)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(espacoTextoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(espacoTextoTaxaAtualizacao)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(espacoTextoPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBoxReceberMensagensPrivadas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(espacoTextoIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacoTextoPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(espacoTextoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(espacoTextoTaxaAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(checkBoxReceberMensagensPrivadas))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoConectar)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoConectarMouseClicked
        System.out.println("Tentar conectar");
        if("".equals(espacoTextoNome.getText())){
            JOptionPane.showMessageDialog(null, "Tens de inserir um nome para te poderes entrar no servidor!","ERROR!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Dar set a todas a variaveis no conectarServidor
        conectarServidor.setIpServidor(espacoTextoIp.getText());
        conectarServidor.setPortaServidor(Integer.parseInt(espacoTextoPorta.getText()));
        conectarServidor.setTaxaAtualizacao(Integer.parseInt(espacoTextoTaxaAtualizacao.getText()));
        if(checkBoxReceberMensagensPrivadas.isSelected()){
            conectarServidor.setDadosCliente(new AgenteUtilizador(espacoTextoNome.getText(), true, "RMI"));
        }else{
            conectarServidor.setDadosCliente(new AgenteUtilizador(espacoTextoNome.getText(), false, "RMI"));
        }
        
        /*GERARR AS CAHVES*/
        conectarServidor.getDadosCliente().setChavePrivada("oioiPrivado");
        conectarServidor.getDadosCliente().setChavePublica("oioiPublico");
        
        try {
            conectarServidor.conectar();
        } catch (IOException ex) {
            Logger.getLogger(PaginaInicial.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não conseguiste te conectar ao servidor!\n" + ex,"ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        
        try{
            conectarServidor.iniciarServidorRMI();
        }catch(Exception e){
            System.out.println("error " + e);
        }
        
        if(conectarServidor.estaConectado()){//Coneguimos nos conectar
            PaginaChat chat = new PaginaChat(conectarServidor);
            chat.setVisible(true);
            this.dispose();
        }else{//Nao esta conectado
            JOptionPane.showMessageDialog(null, "Não conseguiste te conectar ao servidor!","ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botaoConectarMouseClicked

    private void espacoTextoNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_espacoTextoNomeFocusGained
        
    }//GEN-LAST:event_espacoTextoNomeFocusGained

    private void espacoTextoIpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_espacoTextoIpMouseClicked
        if("127.0.0.1".equals(espacoTextoIp.getText())){
            espacoTextoIp.setForeground(Color.BLACK);
            espacoTextoIp.setText("");
        }
        

    }//GEN-LAST:event_espacoTextoIpMouseClicked

    private void espacoTextoPortaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_espacoTextoPortaMouseClicked
        if("2001".equals(espacoTextoPorta.getText())){
            espacoTextoPorta.setForeground(Color.BLACK);
            espacoTextoPorta.setText("");
        }
    }//GEN-LAST:event_espacoTextoPortaMouseClicked

    private void espacoTextoPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espacoTextoPortaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_espacoTextoPortaActionPerformed

    private void espacoTextoIpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_espacoTextoIpFocusLost
        if("".equals(espacoTextoIp.getText())){
            espacoTextoIp.setForeground(Color.GRAY);
            espacoTextoIp.setText("127.0.0.1");
        }
        
    }//GEN-LAST:event_espacoTextoIpFocusLost

    private void espacoTextoPortaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_espacoTextoPortaFocusLost
        if("".equals(espacoTextoPorta.getText())){
            espacoTextoPorta.setForeground(Color.GRAY);
            espacoTextoPorta.setText("2001");
        }
        
    }//GEN-LAST:event_espacoTextoPortaFocusLost

    private void espacoTextoTaxaAtualizacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_espacoTextoTaxaAtualizacaoFocusLost
        if("".equals(espacoTextoTaxaAtualizacao.getText())){
            espacoTextoTaxaAtualizacao.setForeground(Color.GRAY);
            espacoTextoTaxaAtualizacao.setText("60");
        }
    }//GEN-LAST:event_espacoTextoTaxaAtualizacaoFocusLost

    private void espacoTextoTaxaAtualizacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_espacoTextoTaxaAtualizacaoMouseClicked
        if("60".equals(espacoTextoTaxaAtualizacao.getText())){
            espacoTextoTaxaAtualizacao.setForeground(Color.BLACK);
            espacoTextoTaxaAtualizacao.setText("");
        }
    }//GEN-LAST:event_espacoTextoTaxaAtualizacaoMouseClicked

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaginaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaginaInicial().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoConectar;
    private javax.swing.JCheckBox checkBoxReceberMensagensPrivadas;
    private javax.swing.JTextField espacoTextoIp;
    private javax.swing.JTextField espacoTextoNome;
    private javax.swing.JTextField espacoTextoPorta;
    private javax.swing.JTextField espacoTextoTaxaAtualizacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
