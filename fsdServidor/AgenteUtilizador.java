/*
 * Classe para ter o objeto cliente que vai ser aidiocnado ao repositório clientes
*/

public class AgenteUtilizador {
    String nomeUtilziador;
    String ipUtilizador;
    Boolean recebeMensagensPrivadas;

    public AgenteUtilizador(String nomeUtilziador, String ipUtilizador, Boolean recebeMensagensPrivadas){
        this.nomeUtilziador = nomeUtilziador;
        this.ipUtilizador = ipUtilizador;
        this.recebeMensagensPrivadas = recebeMensagensPrivadas;
    }

    public boolean recebeMensagensPrivadas(){
        return this.recebeMensagensPrivadas;
    }

    public void setIpUtilizador(String ipUtilizador){
        this.ipUtilizador = ipUtilizador;
    }

    public String getIpUtilizador(){
        return this.ipUtilizador;
    }

    public void setNomeUtilizadorAgentesUtilizador(String nomeUtilziador){
        this.nomeUtilziador = nomeUtilziador;
    }

    public String getNomeUtilizadorAgenteUtilizador(){
        return this.nomeUtilziador;
    }
}
