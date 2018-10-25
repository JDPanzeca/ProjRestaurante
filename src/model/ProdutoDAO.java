
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import controller.Produto;


public class ProdutoDAO {
     private Connection conexao;
    
    public ProdutoDAO(){
        
        try{
            conexao=BDconexao.getConnection();
        }catch(SQLException|ClassNotFoundException ex){
            System.out.println("Erro... de conexao: "+ex.getMessage());            
        }
    }
    
    public void inserir(Produto pr){
        String query="INSERT INTO produto(nome,tipo,data,preco) VALUES(?,?,?,?)";
        
        try {
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setString(1, pr.getNome());
            stmt.setString(2, pr.getTipo()); 
            stmt.setDate(3, pr.getDataVali()); 
            stmt.setDouble(4, pr.getPreco()); 
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro de insercao da base de dados: "+ex.getMessage());
        }
        
    }
    
    public Produto encontrarProduto(int id){
        Produto fou=new Produto();
        boolean contr=false;
        String query="SELECT * FROM cliente";
        try{
            PreparedStatement st=conexao.prepareStatement(query);            
            ResultSet rs=st.executeQuery();
            //st.setInt(1, id);
            while(rs.next()&&!contr){
                if(id==rs.getInt("idproduto")){
                    fou.setNome(rs.getString("nome"));
                    fou.setTipo(rs.getString("tipo"));
                    fou.setDataVali(rs.getDate("data"));
                    fou.setPreco(rs.getDouble("preco"));                                       
                    contr=true;
                }                   
            }         
        }catch(SQLException ex){
            System.out.println("--Erro de insercao da base de dados: "+ex.getMessage());
        }
        return fou;
    }
    
    public void actualizar(Produto pr, int id){
        String query="UPDATE fornecedor SET nome=?,especialidade=? WHERE idfornecedor=?";
        
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);            
            stmt.setString(1, pr.getNome());
            stmt.setString(2, pr.getTipo()); 
            stmt.setDate(3, pr.getDataVali()); 
            stmt.setDouble(4, pr.getPreco());             
            stmt.setInt(5, id);            
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Erro na actualizacao da base de dados: "+ex.getMessage());
        }
        
    }
    
    public void apagar(int id){
        String query="DELETE FROM produto WHERE idproduto=?";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("Erro ao apagar dados na BD: "+ex.getMessage());
        }
    }
    
    public List<Produto> listarProdutos(){
        String query="SELECT * FROM produto";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            List<Produto> lista=new ArrayList<>();
            while(rs.next()){
                Produto pd=new Produto();
                pd.setIdProd(rs.getInt("idproduto"));
                pd.setNome(rs.getString("nome"));
                pd.setTipo(rs.getString("tipo"));  
                pd.setDataVali(rs.getDate("data"));
                pd.setPreco(rs.getDouble("preco"));
                lista.add(pd);
            }
            return lista;
        }catch(SQLException ex){
            System.out.println("Erro de insercao da base de dados: "+ex.getMessage());
        
    return new ArrayList<>();
    }
    } 
    
}
