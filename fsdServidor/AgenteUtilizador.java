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
