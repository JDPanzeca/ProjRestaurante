
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import controller.Fornecedor;


public class FornecedorDAO {
    private Connection conexao;
    
    public FornecedorDAO(){
        
        try{
            conexao=BDconexao.getConnection();
        }catch(SQLException|ClassNotFoundException ex){
            System.out.println("Erro de conexao: "+ex.getMessage());            
        }
    }
    
    public void inserir(Fornecedor forn){
        String query="INSERT INTO fornecedor(nome,especialidade) VALUES(?,?)";
        
        try {
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setString(1, forn.getNome());
            stmt.setString(2, forn.getEspecialidade());                        
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro de isnercao da base de dados: "+ex.getMessage());
        }
        
    }
    
    public void actualizar(Fornecedor forn, int id){
        String query="UPDATE fornecedor SET nome=?,especialidade=? WHERE idfornecedor=?";
        
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);            
            stmt.setString(1, forn.getNome());
            stmt.setString(2, forn.getEspecialidade());             
            stmt.setInt(3, id);            
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Erro na actualizacao da base de dados: "+ex.getMessage());
        }
        
    }
    
    public void apagar(int id){
        String query="DELETE FROM fornecedor WHERE idfornecedor=?";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("Erro ao apagar dados na BD: "+ex.getMessage());
        }
    }
    
    public List<Fornecedor> listarFornecedores(){
        String query="SELECT * FROM fornecedor";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            List<Fornecedor> lista=new ArrayList<>();
            while(rs.next()){
                Fornecedor ff=new Fornecedor();
                ff.setIdForn(rs.getInt("idfornecedor"));
                ff.setNome(rs.getString("nome"));
                ff.setEspecialidade(rs.getString("especialidade"));                
                lista.add(ff);
            }
            return lista;
        }catch(SQLException ex){
            System.out.println("Erro de insercao da base de dados: "+ex.getMessage());
        
    return new ArrayList<>();
    }
    } 
    
}
