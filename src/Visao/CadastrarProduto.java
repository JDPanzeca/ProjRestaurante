package Visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class CadastrarProduto extends JFrame implements ActionListener  {
    //Atributos a serem usados na definicao da janela 
    private final JButton bCadastrar,bApagar;
    private final JLabel lNome,lDataValid,lPreco;
    private final JTextField tNome,tDataValid,tPreco;
    private final JPanel aux,panelG,panelBut ;
    private final JRadioButton rPer,rNPer;
    
    public CadastrarProduto(){
        //inicializando Nome da janela
        super("Novo produto");
        //inicializando os radio button
        rPer=new JRadioButton("Perecivel");
        rNPer=new JRadioButton("Nao Perecivel");
        //inicializando os botoes
        bCadastrar=new JButton("Cadastar");
        bApagar=new JButton("Apagar");
        //inicializando os labals
        lNome=new JLabel("Nome: ");
        lDataValid=new JLabel("Validade:");
        lPreco=new JLabel("Preco:");
        //inicializando as caixas de texto        
        tDataValid=new JTextField(10);
        tNome=new JTextField(15);
        tPreco=new JTextField(10);
        //inicializando os paineis
        aux=new JPanel();
        panelG=new JPanel(new GridLayout(4,2));
        panelBut=new JPanel();
        
    }
    
    public void vizualizarCadastroProd(){
        montarGUI();
    }
    
    public void montarGUI(){        
        //Alocando componentes
        aux.add(rPer);
        aux.add(rNPer);
        aux.setOpaque(rootPaneCheckingEnabled);
        panelG.setBorder(BorderFactory.createTitledBorder("Dados do Produto"));
        panelG.add(lNome);
        panelG.add(tNome);       
        panelG.add(lPreco);
        panelG.add(tPreco);
        panelG.add(lDataValid);
        panelG.add(tDataValid);
        //panelG.add(lTipo);
        panelG.add(aux);
        
        panelBut.add(bCadastrar);
        panelBut.add(bApagar);
        panelBut.setBorder(BorderFactory.createTitledBorder("  "));
        
        this.add(panelG,BorderLayout.CENTER);
        this.add(panelBut,BorderLayout.SOUTH);
        //accoes nos componentes
        bCadastrar.addActionListener(this);
        bApagar.addActionListener(this);
        //comportamento da janela
        this.setSize(500,210);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*Modelar as accoes como DAO rever a estrutura do codigo*/
        String validade;
        java.text.SimpleDateFormat sdf;
        java.util.Date data;
        java.sql.Date sqlDate;
        double preco;
        
        if(e.getSource()==bCadastrar){
            String tipo="";
            if(rNPer.isSelected())
                tipo="Nao perecivel";
            if(rPer.isSelected())
                tipo="Perecivel";
                
            if(tDataValid.getText().isEmpty()||gettNome().getText().isEmpty()||tPreco.getText().isEmpty()||tipo.isEmpty())
                JOptionPane.showMessageDialog(null,"Nao pode deixar campos vazios");
            else{
                preco=Double.parseDouble(tPreco.getText());                
                sdf=new SimpleDateFormat("dd/MM/yyyy");
                validade=tDataValid.getText();
                 
                try {
                    data=sdf.parse(validade);
                    sqlDate=new java.sql.Date(data.getTime());                    
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con;
                    con=DriverManager.getConnection("jdbc:mysql://localhost/jp_vendas_bd","root","");
                    String query="INSERT INTO produto(nome,tipo,data,preco) VALUES(?,?,?,?)";
                    PreparedStatement stmt=con.prepareStatement(query);
                    
                    stmt.setString(1, gettNome().getText());
                    stmt.setString(2,tipo);
                    stmt.setDate(3,sqlDate);
                    stmt.setDouble(4, preco);
                    
                    stmt.executeUpdate();
                    stmt.close();
                    con.close();                    
                    JOptionPane.showMessageDialog(null, "Cadastro Feito com sucesso!");
                    
                }catch (ParseException ex) {
                    System.out.println(ex.getMessage()+"Erro de excepcao na conversao do dado Data");
                } 
                catch (ClassNotFoundException ex) {
                    System.out.println("O driver nao  foi encontrado");
                }
                catch(SQLException sq){
                    System.out.println("Erro de cadastro: "+sq.getMessage());
                    sq.getErrorCode();
                }              
           }
                
        }
        if(e.getSource()==bApagar){
            tDataValid.setText("");
            gettNome().setText("");
            tPreco.setText("");            
        }      
    }
    public JTextField gettNome() {
        return tNome;
    }
    
}
