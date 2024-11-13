import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicoAdicionalCRUD {
    private static Scanner sc = new Scanner(System.in);

    public static ServicoAdicional salvarServicoAdicional (ServicoAdicional servicoAdicional) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_servico_adicional (descricao, custo_mensal) VALUES " + " (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, servicoAdicional.getDescricao());
            ps.setDouble(2, servicoAdicional.getCusto_mensal());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                servicoAdicional.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicoAdicional;

    }

    public static void deletarServicoAdicional (int id) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_servico_adicional WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ServicoAdicional buscarPorId (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_servico_adicional WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ServicoAdicional(rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("custo_mensal"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Serviço adicional não encontrado.");

    }

    public static void atualizarServico (ServicoAdicional servicoAdicional, int id) throws SQLException {

        try (Connection connection = ConexaoBanco.getConnections()) {

            String sql = "UPDATE tb_servico_adicional SET descricao = ?, custo_mensal = ?  WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, servicoAdicional.getDescricao());
            ps.setDouble(2, servicoAdicional.getCusto_mensal());
            ps.setInt(3, id);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Serviço adicional atualizado com sucesso.");

            } else {
                System.out.println("Nenhum serviço encontrado com esse número.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String listarTodosServicos () {

        String servicoss = "";

        ArrayList<ServicoAdicional> servicoAdicionais = new ArrayList<>();

        try ( Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps =  connection.prepareStatement("SELECT id, descricao, custo_mensal FROM tb_servico_adicional");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                double custo_mensal = rs.getDouble("custo_mensal");

                ServicoAdicional servicoAdicional = new ServicoAdicional(id, descricao, custo_mensal);
                servicoAdicionais.add(servicoAdicional);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (ServicoAdicional servicoAdicional : servicoAdicionais) {
            servicoss += servicoAdicional;
        }

        return servicoss + "\n" + "----------------------------";

    }
}
