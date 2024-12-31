
/**
 *
 * @author Mauricio
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class ProdutosDAO {

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean salvarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();
            return true;
            

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<ProdutosDTO> listarProdutos() {
    List<ProdutosDTO> listaProdutos = new ArrayList<>();
    String sql = "SELECT * FROM produtos";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql);
         ResultSet rs = prep.executeQuery()) {

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getDouble("valor"));
            produto.setStatus(rs.getString("status"));

            listaProdutos.add(produto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listaProdutos;
}
}
