/*
 * Classe para ter o objeto cliente que vai ser aidiocnado ao repositório clientes
*/

public class AgenteUtilizador {
    String nomeCliente;
    int segundosTimeout = 120;
    
    public AgenteUtilizador(String nick, int segundosTimeout){
        this.nomeCliente = nick;
        this.segundosTimeout = segundosTimeout;
    }

    public void setNickAgenteUtilziador(String nick){
        this.nomeCliente = nick;
    }

    public String getNickAgenteUtilizador(){
        return this.nomeCliente;
    }

    public void setSegundosTimeout(int segundosTimeout){
        this.segundosTimeout = segundosTimeout;
    }

    public int getSegundosTimeout(){
        return this.segundosTimeout;
    }
}
