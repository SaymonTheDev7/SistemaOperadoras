import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlanoCRUD {

    private static Scanner sc = new Scanner(System.in);

    public static Plano salvarPlano (Plano plano) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_plano (operadora, nome, quantidade_dados, quantidade_bonus, beneficios, valor) VALUES " + " (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, plano.getOperadora());
            ps.setString(2, plano.getNome());
            ps.setDouble(3, plano.getQuantidade_dados());
            ps.setDouble(4, plano.getQuantidade_bonus());
            ps.setString(5, plano.getBeneficios());
            ps.setDouble(6, plano.getValor());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                plano.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plano;

    }

    public static void deletarPlano (int id) {

        try (Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_plano WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Plano buscarPorId (int id) {

        try (Connection connection = ConexaoBanco.getConnections()){

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_plano WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Plano(rs.getInt("id"),
                                 rs.getString("operadora"),
                                 rs.getString("nome"),
                                 rs.getString("beneficios"),
                                 rs.getDouble("quantidade_bonus"),
                                 rs.getDouble("quantidade_dados"),
                                 rs.getDouble("valor"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Plano não encontrado.");

    }

    public static void atualizarPlano (Plano plano, int id) throws SQLException {

        try (Connection connection = ConexaoBanco.getConnections()) {

            String sql = "UPDATE tb_plano SET operadora = ?, nome = ?, quantidade_dados = ?, quantidade_bonus = ?, beneficios = ?, valor = ?  WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, plano.getOperadora());
            ps.setString(2, plano.getNome());
            ps.setDouble(3, plano.getQuantidade_dados());
            ps.setDouble(4, plano.getQuantidade_bonus());
            ps.setString(5, plano.getBeneficios());
            ps.setDouble(6, plano.getValor());
            ps.setInt(7, id);

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

    public static String listarTodosPlanos () {

        String planoss = "";

        ArrayList <Plano> planos = new ArrayList<>();

        try ( Connection connection = ConexaoBanco.getConnections()) {

            PreparedStatement ps =  connection.prepareStatement("SELECT id, operadora, nome, quantidade_dados, quantidade_bonus, beneficios, valor FROM tb_plano");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String operadora = rs.getString("operadora");
                String nome = rs.getString("nome");
                double quantidade_dados = rs.getDouble("quantidade_dados");
                double quantidade_bonus = rs.getDouble("quantidade_bonus");
                String benefecios = rs.getString("beneficios");
                int valor = rs.getInt("valor");

                Plano plano = new Plano(id, operadora, nome, benefecios, quantidade_dados, quantidade_bonus,  valor);
                planos.add(plano);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Plano plano : planos) {
            planoss += plano;
        }

        return planoss + "\n" + "----------------------------";

    }
}
