
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import controller.Funcionario;


public class FuncionarioDAO {
    private Connection conexao;
    
    public FuncionarioDAO(){
        try{
            conexao=BDconexao.getConnection();
        }catch(SQLException|ClassNotFoundException ex){
            System.out.println("Erro na conexao: "+ex.getMessage());
        }
        
    }
    
    public void inserirFunc(Funcionario f){
        String query="INSERT INTO funcionario(nome,bi,dataNasc,nrNuit,contacto,endereco,categoria,sexo) VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getBi());
            stmt.setDate(3, f.getDataNasc());
            stmt.setInt(4, f.getNrNuit());
            stmt.setInt(5, f.getContacto());
            stmt.setString(6, f.getEndereco());
            stmt.setString(7, f.getCategoria());
            stmt.setString(8, f.getSexo());
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Erro na insercao: "+ex.getMessage());
        }
    }
    
    public void actulizarFunc(Funcionario f, int id){
        String query="UPDATE funcionario SET nome=?,bi=?,dataNasc=?,nrNuit=?,contacto=?,endereco=?,categoria=?,sexo=? "
                + "WHERE idfuncionario=?";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getBi());
            stmt.setDate(3, f.getDataNasc());
            stmt.setInt(4, f.getNrNuit());
            stmt.setInt(5, f.getContacto());
            stmt.setString(6, f.getEndereco());
            stmt.setString(7, f.getCategoria());
            stmt.setString(8, f.getSexo());
            stmt.setInt(9, id);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Erro na actualizacao: "+ex.getMessage());
        }
    }
    
    public void apagar(int id){
        String query=" DELETE FROM funcionario WHERE idfuncionario=?";
        try{
            PreparedStatement st=conexao.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        }catch(SQLException ex){
            System.out.println("Erro ao apagar: "+ex.getMessage());
        }
    }
    
    public List<Funcionario> listarFuncionario(){
        String query="SELECT *FROM funcionario";
        try{
            PreparedStatement st=conexao.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            List<Funcionario> lista=new ArrayList<>();
            while(rs.next()){
                Funcionario f=new Funcionario();
                f.setIdFunc(rs.getInt("idfuncionario"));
                f.setNome(rs.getString("nome"));
                f.setBi(rs.getString("bi"));
                f.setCategoria(rs.getString("categoria"));
                f.setContacto(rs.getInt("contacto"));
                f.setDataNasc(rs.getDate("dataNasc"));
                f.setEndereco(rs.getString("endereco"));
                f.setNrNuit(rs.getInt("nrNuit"));
                f.setSexo(rs.getString("sexo"));
                lista.add(f);
            }
            return lista;
        }catch(SQLException ex){
            System.out.println("Erro ao apagar: "+ex.getMessage());
        return new ArrayList<>();
        }
    }
    
}
