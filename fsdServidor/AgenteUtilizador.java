/*
 * Classe para ter o objeto cliente que vai ser aidiocnado ao repositório clientes
*/

public class AgenteUtilizador {
    String nomeUtilziador;
    String ipUtilizador;
    Boolean recebeMensagensPrivadas;
    String protocoloMensagemPrivada;

    public AgenteUtilizador(String nomeUtilziador, String ipUtilizador, Boolean recebeMensagensPrivadas, String tipoMensagemPrivada) {
        this.nomeUtilziador = nomeUtilziador;
        this.ipUtilizador = ipUtilizador;
        this.recebeMensagensPrivadas = recebeMensagensPrivadas;
        this.protocoloMensagemPrivada = tipoMensagemPrivada;
    }

    public void setProtocoloMensagemPrivada(String protocolo){
        this.protocoloMensagemPrivada = protocolo;
    }

    public String getProtocoloMensagemPrivada(){
        return this.protocoloMensagemPrivada;
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
