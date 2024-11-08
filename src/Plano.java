public class Plano {

    private int id;
    private String operadora;
    private String nome;
    private String beneficios;
    private double quantidade_dados;
    private double quantidade_bonus;
    private double valor;

    public Plano(int id, String operadora, String nome, String beneficios, double quantidade_dados, double quantidade_bonus, double valor) {
        this.id = id;
        this.operadora = operadora;
        this.nome = nome;
        this.quantidade_dados = quantidade_dados;
        this.quantidade_bonus = quantidade_bonus;
        this.beneficios = beneficios;
        this.valor = valor;
    }

    public Plano( String operadora, String nome, String beneficios, double quantidade_dados, double quantidade_bonus, double valor) {
        this.operadora = operadora;
        this.nome = nome;
        this.quantidade_dados = quantidade_dados;
        this.quantidade_bonus = quantidade_bonus;
        this.beneficios = beneficios;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public double getQuantidade_dados() {
        return quantidade_dados;
    }

    public void setQuantidade_dados(double quantidade_dados) {
        this.quantidade_dados = quantidade_dados;
    }

    public double getQuantidade_bonus() {
        return quantidade_bonus;
    }

    public void setQuantidade_bonus(double quantidade_bonus) {
        this.quantidade_bonus = quantidade_bonus;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString () {

        return "\nID do plano: " + getId() +
               "\nOperadora: " + getOperadora() +
               "\nNome do plano: " + getNome() +
               "\nQuantidade de dados: " + getQuantidade_dados() +
               "\nQuantidade de dados bônus: " + getQuantidade_bonus() +
               "\nBenefícios: " + getBeneficios() +
               "\nValor do plano: " + getValor();
    }
}