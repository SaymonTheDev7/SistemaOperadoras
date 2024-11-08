public class ServicoAdicional {

    private int id;
    private String descricao;
    private double custo_mensal;

    public ServicoAdicional(int id, String descricao, double custo_mensal) {
        this.id = id;
        this.descricao = descricao;
        this.custo_mensal = custo_mensal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto_mensal() {
        return custo_mensal;
    }

    public void setCusto_mensal(double custo_mensal) {
        this.custo_mensal = custo_mensal;
    }

    public String toString() {
        return "\nID do serviço adicional: " + getId() +
               "\nDescrição do serviço: " + getDescricao() +
               "\nCusto mensal: " + getCusto_mensal();
    }
}