package Visao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class CadastrarFornecedor  extends JFrame implements ActionListener{
    //Atributos swings a serem usados na janela para cadastro dos clientes.
    private final JButton bCadastrar,bApagar;
    private final  JTextField tNome, tEspecialidade;
    private final JLabel lNome,lEspecialidade;
    private final JPanel panelDad,panelBut;   
    
    public CadastrarFornecedor(){
        //Setando o nome da janela
        super("Cadastrar Fornecedor");
        //inicializando paineis
        panelDad=new JPanel();
        panelBut=new JPanel();
        //inicializando caixas de textos
        tNome=new JTextField(20);
        tEspecialidade=new JTextField(20);
        //inicializando labels
        lNome=new JLabel("Nome: ");
        lEspecialidade=new JLabel("Especialidade:");
        //inicializando botoes
        bCadastrar=new JButton("Cadastrar");
        bApagar=new JButton("Apagar");        
        
    }
    
    public void visualizarFornecedor(){
        janelaFornecedor();
    }
    
    public void janelaFornecedor(){
        panelDad.add(lNome);
        panelDad.add(tNome);
        panelDad.add(lEspecialidade);
        panelDad.add(tEspecialidade);
        
        panelBut.add(bCadastrar);
        panelBut.add(bApagar);
        panelBut.setBorder(BorderFactory.createTitledBorder("  "));
        panelDad.setBorder(BorderFactory.createTitledBorder("Dados do fornecedor"));
        
        bCadastrar.addActionListener(this);
        bApagar.addActionListener(this);
        
        this.add(panelDad,BorderLayout.CENTER);
        this.add(panelBut,BorderLayout.SOUTH);
        this.setSize(700, 200);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        /*As operacoes que estao a ser efectuadas aqui de base de dados devem estar 
        noutro pacote especifico com base no MVC*/
        if(e.getSource()==bApagar){
            tEspecialidade.setText("");
            tNome.setText("");
        }
        if(e.getSource()==bCadastrar){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con;
                con=DriverManager.getConnection("jdbc:mysql://localhost/pbl","root","");
                String query="INSERT INTO fornecedor(nome,especialidade) VALUES(?,?)";
                PreparedStatement stmt=con.prepareStatement(query);
                
                stmt.setString(1,tNome.getText());
                stmt.setString(2, tEspecialidade.getText());
                stmt.executeUpdate();
                stmt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                dispose();
                FornecedorTab f=new FornecedorTab();
               // setCadControl(1);
            }
            catch(ClassNotFoundException cnf){
                System.out.println("Erro, a classe nao foi encontrada: "+cnf.getMessage());
            }catch(SQLException sq){
                System.out.println("Erro :"+sq.getMessage());
            }
        }
        
    }
    
}
