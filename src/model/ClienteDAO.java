
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import controller.Cliente;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    private Connection conexao;
    
    public ClienteDAO(){
        
        try{
            conexao=BDconexao.getConnection();
        }catch(SQLException|ClassNotFoundException ex){
            System.out.println("Erro de conexao: "+ex.getMessage());           
        }
    }
    
    public void inserir(Cliente c){
        String query="INSERT INTO cliente(nomeP,nomeU,sexo,saldo,endereco,nacionalidade,nrNuit) VALUES(?,?,?,?,?,?,?)";
        String queryAux="INSERT INTO contactocli(idCli,contacto) VALUES(?,?)";
        String qSelectL="SELECT MAX(id_cli) as max_id FROM cliente";
        //String qSelectL="SELECT LAST_INSERT_ID()";
        
        try {
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setString(1, c.getNomeP());
            stmt.setString(2, c.getNomeU());
            stmt.setString(3, c.getSexo());
            stmt.setDouble(4, 0);
            stmt.setString(5, c.getEndereco());
            stmt.setString(6, c.getNacionalidade());
            stmt.setInt(7,c.getNrNuit());            
            stmt.executeUpdate();
            stmt.close();
            PreparedStatement st=conexao.prepareStatement(qSelectL);
            ResultSet rs=st.executeQuery();
            rs.next();
            //JOptionPane.showMessageDialog(null, rs.getInt("max_id"));
            //st.executeUpdate();            
            PreparedStatement stmtAux=conexao.prepareStatement(queryAux);
            stmtAux.setInt(1, rs.getInt("max_id"));
            stmtAux.setInt(2, c.getContacto());
            stmtAux.executeUpdate();
            stmtAux.close();
            st.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Erro de insercao da base de dados:: "+ex.getMessage());
        }
        
    }
    private int ultimoID(){
        int id=0;
        return id;
    }
    
    public void actualizar(Cliente c, int id){
        String query="UPDATE cliente SET nomeP=?,nomeU=?,sexo=?,endereco=?,nacionalidade=?,nrNuit=? WHERE id_cli=?";
        String queryAux="UPDATE contactocli SET contacto=? WHERE id_cli=? ";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);            
            stmt.setString(1, c.getNomeP());
            stmt.setString(2, c.getNomeU());
            stmt.setString(3, c.getSexo());
            stmt.setString(4, c.getEndereco());
            stmt.setString(5, c.getNacionalidade());            
            stmt.setInt(6,c.getNrNuit());
            stmt.setInt(7, id);
            PreparedStatement stmtAux=conexao.prepareStatement(queryAux);
            stmtAux.setInt(1, c.getContacto());
            stmtAux.setInt(2, id);
            stmtAux.executeUpdate();
            stmt.executeUpdate();
            stmtAux.close();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Erro na actualizacao da base de dados: "+ex.getMessage());
        }
        
    }
    
    public void apagar(int id){
        String query="DELETE FROM cliente WHERE idCliente=?";
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("Erro da base de dados: "+ex.getMessage());
        }
    }
    
    public Cliente encontrarCliente(int id){
        Cliente fou=new Cliente();
        boolean contr=false;
        //String query="SELECT * FROM cliente INNER JOIN contactocli ON cliente.id_cli=contactocli.idCli";
        String query="SELECT * FROM cliente WHERE id_cli=?";
        String queryAux="SELECT contacto FROM contactocli WHERE idcli=?";
        try{
            PreparedStatement st=conexao.prepareStatement(query);  
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
           
            PreparedStatement stAux=conexao.prepareStatement(queryAux);
            stAux.setInt(1,id);
            ResultSet rsAux=stAux.executeQuery();
            
             /*if(id==rs.getInt("id_cli")){
                    fou.setIdCliente(rs.getInt("id_cli"));
                    fou.setNomeP(rs.getString("nomeP"));
                    fou.setNomeU(rs.getString("nomeU"));
                    fou.setEndereco(rs.getString("endereco"));
                    fou.setSexo(rs.getString("sexo"));
                    fou.setNrNuit(rs.getInt("nrNuit"));                                        
                    fou.setSaldo(rs.getDouble("saldo"));
                    fou.setNacionalidade(rs.getString("nacionalidade"));
                    fou.setContacto(rsAux.getInt("contacto"));
                    //contr=true;
            }*/
            //st.setInt(1, id);
            /*while(rs.next()&&!contr){
                if(id==rs.getInt("idCliente")){
                    fou.setIdCliente(rs.getInt("idCliente"));
                    fou.setNomeP(rs.getString("nomeP"));
                    fou.setNomeU(rs.getString("nomeU"));
                    fou.setEndereco(rs.getString("endereco"));
                    fou.setSexo(rs.getString("sexo"));
                    fou.setNrNuit(rs.getInt("nrNuit"));                                        
                    fou.setSaldo(rs.getDouble("saldo"));
                    fou.setNacionalidade(rs.getString("nacionalidade"));
                    contr=true;
                }                   
            } */        
        }catch(SQLException ex){
            System.out.println("--Erro de insercao da base de dados: "+ex.getMessage());
        }
        return fou;
    }
    public void depositar(int id, double valor){
        Cliente cl=encontrarCliente(id);
        cl.setSaldo(cl.getSaldo()+valor);
        String sql="UPDATE cliente SET saldo=? WHERE id_cli=?";
        try{
                                  
            PreparedStatement stmt=conexao.prepareStatement(sql);
            stmt.setDouble(1, cl.getSaldo());
            stmt.setInt(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deposito feito com exito");
            
            
        }catch(SQLException ex){
            System.out.println("Erro de deposito na base de dados: "+ex.getMessage());
        }
    }
    public void debito(Cliente c, double valor){
        //Cliente cl=encontrarCliente(c.getIdCliente());
        c.setSaldo(c.getSaldo()-valor);
        String sql="UPDATE cliente SET deposito=? WHERE idCliente=?";
        try{
                                  
            PreparedStatement stmt=conexao.prepareStatement(sql);
            stmt.setDouble(1, c.getSaldo());
            stmt.setInt(2, c.getIdCliente());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra feita");
            
            
        }catch(SQLException ex){
            System.out.println("Erro de debito na base de dados: "+ex.getMessage());
        }
        
        
    }
    
    public List<Cliente> listarClientes(){
        String query="SELECT * FROM cliente INNER JOIN contactocli ON cliente.id_cli=contactocli.idCli";
        //String query="SELECT * FROM cliente";
        //String queryAux="SELECT contacto FROM contactocli WHERE idCli=?";
        List<Cliente> lista=null;
        try{
            PreparedStatement stmt=conexao.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            //PreparedStatement stmtAux=conexao.prepareStatement(queryAux);
            lista=new ArrayList<>();
            while(rs.next()){
                Cliente cc=new Cliente();
                int id=rs.getInt("id_cli");
                cc.setIdCliente(id);
                cc.setNomeP(rs.getString("nomeP"));
                cc.setNomeU(rs.getString("nomeU"));
                cc.setEndereco(rs.getString("endereco"));
                cc.setSexo(rs.getString("sexo"));
                cc.setNrNuit(rs.getInt("nrNuit"));
                cc.setNacionalidade(rs.getString("nacionalidade"));
                cc.setSaldo(rs.getDouble("saldo"));
                cc.setContacto(rs.getInt("contacto"));
                //stmtAux.setInt(1, id);
                //ResultSet rsAux=stmtAux.executeQuery();
                //cc.setContacto(rsAux.getInt("contacto"));
                lista.add(cc);
            }
            /*for(int i=0;i<lista.size();i++){
                System.out.println(lista.get(i).toString());
                System.out.println();
            }*/
            return lista;            
           
        }catch(SQLException ex){
            System.out.println("Erro de listagem da tabela da base de dados: "+ex.getMessage());
            for(int i=0;i<lista.size();i++){
                lista.get(i).toString();
            }
    return new ArrayList<>();
    }
    } 
    
}
