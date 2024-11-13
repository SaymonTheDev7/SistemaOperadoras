public class Contrato {

    private int id;
    private Plano id_plano;
    private String termos;
    private String data_inicio;
    private String data_fim;

    public Contrato(int id, Plano plano, String termos, String data_inicio, String data_fim) {
        this.id = id;
        this.id_plano = plano;
        this.termos = termos;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Contrato( Plano plano, String termos, String data_inicio, String data_fim) {
        this.id_plano = plano;
        this.termos = termos;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plano getId_plano() {
        return id_plano;
    }

    public void setId_plano(Plano id_plano) {
        this.id_plano = id_plano;
    }

    public String getTermos() {
        return termos;
    }

    public void setTermos(String termos) {
        this.termos = termos;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public String toString() {

        return "\nID do contrato: " + getId() +
               "\nID do plano: " + id_plano.getId() +
               "\nTermos do contrato: " + getTermos() +
               "\nData de início: " + getData_inicio() +
               "\nData de fim: " + getData_fim();
    }
}
