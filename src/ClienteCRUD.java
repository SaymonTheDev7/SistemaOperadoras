import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteCRUD {

    private static Scanner sc = new Scanner(System.in);

    public static Cliente salvarCliente (Cliente cliente) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_cliente (nome, email, telefone, id_plano) VALUES " + " (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, cliente.getId_plano());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                cliente.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;

    }
    public static void deletarCliente (int id) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_contrato WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Cliente buscarPorId (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_contrato WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cliente(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getInt("id_plano"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Cliente não encontrado.");

    }
    public static void atualizarCliente (Cliente cliente, int id) throws SQLException {

        try (Connection connection = ConexaoBanco.getConnections()) {

            String sql = "UPDATE tb_contrato SET nome = ?, email = ?, telefone = ?  WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, id);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Plano atualizado com sucesso.");

            } else {
                System.out.println("Nenhum plano encontrado com esse número.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String listarTodosClientes () {

        String clientess = "";

        ArrayList<Cliente> clientes = new ArrayList<>();

        try ( Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps =  connection.prepareStatement("SELECT id, nome, email, telefone, id_plano FROM tb_servico_contrato");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                int id_plano = rs.getInt("id_plano");

                Cliente cliente = new Cliente(id, nome, email, telefone, id_plano);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Cliente cliente : clientes) {
            clientess += cliente;
        }

        return clientess + "\n" + "----------------------------";

    }
}
