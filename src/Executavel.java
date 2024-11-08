import java.sql.SQLException;
import java.util.Scanner;

public class Executavel {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

    }

    public static Plano inputSalvarPlano () {

        System.out.println("Insira a operadora que oferece o plano:");
        String operadora = sc.next();

        System.out.println("Insira o nome do plano:");
        String nome = sc.next();

        System.out.println("Insira a quantidade de dados do plano:");
        double quantidadeDados = sc.nextDouble();

        System.out.println("Insira a quantidade de dados bônus do plano:");
        double quantidadeBonus = sc.nextDouble();

        System.out.println("Insira os benefícios do plano:");
        String benefecios = sc.next();

        System.out.println("Insira o valor do plano:");
        double valor = sc.nextDouble();

        return new Plano(operadora, nome, benefecios, quantidadeBonus, quantidadeDados, valor);

    }

    public static void removerPlano () {

        System.out.println("Insira o ID do plano que deseja remover:");
        int id = sc.nextInt();

        PlanoCRUD.deletarPlano(id);

    }

    public static int buscarPlanoPorId () {

        System.out.println("Insira o ID do plano que deseja buscar:");
        int id = sc.nextInt();
        PlanoCRUD.buscarPorId(id);
        return id;

    }

    private static void atualizarPlanoPorId () throws SQLException {

        System.out.println("Insira a nova operadora do plano:");
        String operadora = sc.next();

        System.out.println("Insira o novo nome do plano:");
        String nome = sc.next();

        System.out.println("Insira a nova quantidade dados:");
        double quantidadeDados = sc.nextDouble();

        System.out.println("Insira a nova quantidade de bônus:");
        double quantidadeBonus = sc.nextDouble();

        System.out.println("Insira os novos benefícios do plano:");
        String benefecios = sc.next();

        System.out.println("Insira o novo valor do plano:");
        double valor = sc.nextDouble();

        System.out.println("Insira o ID do plano que deseja atualizar");
        int id = sc.nextInt();

        Plano plano = new Plano( operadora, nome, benefecios, quantidadeDados, quantidadeBonus, valor);
        PlanoCRUD.atualizarPlano(plano, id);

    }
}
