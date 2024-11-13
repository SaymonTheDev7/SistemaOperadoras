import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Executavel {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        while (true) {
            exibirMenu();
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuPlanos();
                    break;
                case 2:
                    menuServicosAdicionais();
                    break;
                case 3:
                    menuClientes();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Gerenciar Planos");
        System.out.println("2 - Gerenciar Serviços Adicionais");
        System.out.println("3 - Gerenciar Clientes");
        System.out.println("4 - Sair");
    }

    private static void menuPlanos() throws SQLException {
        System.out.println("\nEscolha uma opção para Planos:");
        System.out.println("1 - Cadastrar Plano");
        System.out.println("2 - Consultar Plano por ID");
        System.out.println("3 - Atualizar Plano");
        System.out.println("4 - Deletar Plano");
        System.out.println("5 - Listar Todos os Planos");
        System.out.println("6 - Voltar");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                Plano plano = inputSalvarPlano();
                PlanoCRUD.salvarPlano(plano);
                System.out.println("Plano cadastrado com sucesso.");
                break;
            case 2:
                int idPlano = buscarPlanoPorId();
                Plano planoBuscado = PlanoCRUD.buscarPorId(idPlano);
                System.out.println(planoBuscado);
                break;
            case 3:
                atualizarPlanoPorId();
                break;
            case 4:
                inputDeletePlano();
                break;
            case 5:
                System.out.println(PlanoCRUD.listarTodosPlanos());
                break;
            case 6:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuServicosAdicionais() throws SQLException {
        System.out.println("\nEscolha uma opção para Serviços Adicionais:");
        System.out.println("1 - Cadastrar Serviço Adicional");
        System.out.println("2 - Consultar Serviço Adicional por ID");
        System.out.println("3 - Atualizar Serviço Adicional");
        System.out.println("4 - Deletar Serviço Adicional");
        System.out.println("5 - Listar Todos os Serviços Adicionais");
        System.out.println("6 - Voltar");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                ServicoAdicional servico = inputSalvarServicoAdicional();
                ServicoAdicionalCRUD.salvarServicoAdicional(servico);
                System.out.println("Serviço Adicional cadastrado com sucesso.");
                break;
            case 2:
                int idServico = buscarServicoAdicionalPorId();
                ServicoAdicional servicoBuscado = ServicoAdicionalCRUD.buscarPorId(idServico);
                System.out.println(servicoBuscado);
                break;
            case 3:
                atualizarServicoAdicionalPorId();
                break;
            case 4:
                inputDeleteServicoAdicional();
                break;
            case 5:
                System.out.println(ServicoAdicionalCRUD.listarTodosServicos());
                break;
            case 6:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuClientes() throws SQLException {
        System.out.println("\nEscolha uma opção para Clientes:");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Consultar Cliente por ID");
        System.out.println("3 - Atualizar Cliente");
        System.out.println("4 - Deletar Cliente");
        System.out.println("5 - Listar Todos os Clientes");
        System.out.println("6 - Voltar");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                Cliente cliente = inputSalvarCliente();
                ClienteCRUD.salvarCliente(cliente);
                System.out.println("Cliente cadastrado com sucesso.");
                break;
            case 2:
                int idCliente = buscarClientePorId();
                Cliente clienteBuscado = ClienteCRUD.buscarPorId(idCliente);
                System.out.println(clienteBuscado);
                break;
            case 3:
                atualizarClientePorId();
                break;
            case 4:
                inputDeleteCliente();
                break;
            case 5:
                System.out.println(ClienteCRUD.listarTodosClientes());
                break;
            case 6:
                return;
            default:
                System.out.println("Opção inválida.");
        }
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

    public static ServicoAdicional inputSalvarServicoAdicional () {

        System.out.println("Insira a descrição do serviço adicional:");
        String descricao = sc.next();

        System.out.println("Insira o custo mensal do plano:");
        Double custo_mensal = sc.nextDouble();

        return new ServicoAdicional(descricao, custo_mensal);

    }

    public static Cliente inputSalvarCliente () {

        System.out.println("Insira o nome do cliente:");
        String nome = sc.next();

        System.out.println("Insira o E-mail do cliente:");
        String email = sc.next();

        System.out.println("Insira o telefone do cliente:");
        String telefone = sc.next();

        System.out.println("Insira o ID do plano que deseja salvar:");
        int id = sc.nextInt();

        PlanoCRUD.buscarPorId(id);

        return new Cliente(nome, email, telefone, id);

    }

    public static void inputDeletePlano () {

        System.out.println("Insira o ID do plano que deseja remover:");
        int id = sc.nextInt();

        PlanoCRUD.deletarPlano(id);

    }
    public static void inputDeleteServicoAdicional () {

        System.out.println("Insira o ID do servicço adcicional que deseja remover:");
        int id = sc.nextInt();

        ServicoAdicionalCRUD.deletarServicoAdicional(id);

    }

    public static void inputDeleteCliente () {

        System.out.println("Insira o ID do cliente que deseja remover:");
        int id = sc.nextInt();

        ClienteCRUD.deletarCliente(id);

    }

    public static int buscarPlanoPorId () {

        System.out.println("Insira o ID do plano que deseja buscar:");
        int id = sc.nextInt();
        PlanoCRUD.buscarPorId(id);
        return id;

    }
    public static int buscarClientePorId () {

        System.out.println("Insira o ID do cliente que deseja buscar:");
        int id = sc.nextInt();
        ClienteCRUD.buscarPorId(id);
        return id;

    }


    public static int buscarServicoAdicionalPorId () {

        System.out.println("Insira o ID do serviço que deseja buscar:");
        int id = sc.nextInt();
        ServicoAdicionalCRUD.buscarPorId(id);
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

    private static void atualizarServicoAdicionalPorId () throws SQLException {

        System.out.println("Insira a nova descrição do serviço adicional:");
        String descricao = sc.next();

        System.out.println("Insira o novo valor do serviço adicional:");
        double custo_mensal = sc.nextDouble();

        System.out.println("Insira o ID do serviço que deseja atualizar");
        int id = sc.nextInt();

        ServicoAdicional servicoAdicional = new ServicoAdicional(descricao, custo_mensal);
        ServicoAdicionalCRUD.atualizarServico(servicoAdicional, id);

    }

    private static void atualizarClientePorId () throws SQLException {

        System.out.println("Insira o novo nome do cliente:");
        String nome = sc.next();

        System.out.println("Insira o novo E-mail do cliente:");
        String email = sc.next();

        System.out.println("Insira o novo telefone do cliente:");
        String telefone = sc.next();

        System.out.println("Insira o ID do serviço que deseja atualizar");
        int id = sc.nextInt();

        Cliente cliente = new Cliente (nome, email, telefone, id);
        ClienteCRUD.atualizarCliente(cliente, id);

    }
}
