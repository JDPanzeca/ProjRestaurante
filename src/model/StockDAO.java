
package model;

import controller.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class StockDAO {
    
    private Connection conexao;
    public StockDAO(){
        
        try{
            conexao = BDconexao.getConnection();
        }catch(SQLException|ClassNotFoundException ex){
            System.out.println("Erro de conexao: "+ex.getMessage());            
        }
    }
    
    
    public void inserir(Stock s){
        String query="INSERT INTO stock(idProd,quantidade) VALUES(?,?)";
        
        try {
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setInt(1, s.getIdProd());
            stmt.setFloat(2, s.getQuant());                        
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println("Erro de insercao da base de dados: "+ex.getMessage());
        }
        
    }
     public void carregarStock(Stock stk){
        Stock s=encontrarStock(stk.getIdProd());
        s.setQuant(s.getQuant()+stk.getQuant());
        String sql="UPDATE stock SET quantidade=? WHERE idProd=?";
        try{                                  
            PreparedStatement stmt=conexao.prepareStatement(sql);
            stmt.setDouble(1, s.getQuant());
            stmt.setInt(2, stk.getIdProd());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Carregamento feito com exito");
            
            
        }catch(SQLException ex){
            System.out.println("Erro de insercao da base de dados: "+ex.getMessage());
        }
    }
    public Stock encontrarStock(int id){
        Stock fou=new Stock();
        boolean contr=false;
        String query="SELECT * FROM stock";
        try{
            PreparedStatement st=conexao.prepareStatement(query);            
            ResultSet rs=st.executeQuery();
            //st.setInt(1, id);
            while(rs.next()&&!contr){
                if(id==rs.getInt("idProd")){
                    fou.setIdProd(rs.getInt("idProd"));
                    fou.setQuant(rs.getFloat("quantidade"));                    
                    contr=true;
                }                   
            }         
        }catch(SQLException ex){
            System.out.println("--Erro de insercao da base de dados: "+ex.getMessage());
        }
        return fou;
    }
    
   
    
    /*public void actualizar(Stock s, int id){
        String query="UPDATE stock SET nome=?,especialidade=? WHERE idfornecedor=?";
        
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
    }*/
    
    public List<Stock> listarStock(){
        String query="SELECT * FROM stock";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            List<Stock> lista=new ArrayList<>();
            while(rs.next()){
                Stock ss=new Stock();
                ss.setIdProd(rs.getInt("idProd"));
                //ss.setNome(rs.getString("nome"));
                ss.setQuant(rs.getFloat("quantidade"));                
                lista.add(ss);
            }
            return lista;
        }catch(SQLException ex){
            System.out.println("Erro de insercao da base de dados: "+ex.getMessage());
        
    return new ArrayList<>();
    }
    }
    
}
