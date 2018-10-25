package Visao;


import controller.Fornecedor;
import controller.Stock;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.FornecedorDAO;
import model.StockDAO;

public class StockTab extends JFrame implements ActionListener {
	
    private final JLabel lSearch,lID,lQuant;
    private final JTextField tProd,tID,tQuant;
    private final JPanel panelTable,panelSearch,panelButton,pGeral;
    private final JButton bSearch,bDepositar,bCancelar;
    private final JMenuBar barraMenu;
    private final JMenu menu;
    private JMenuItem itemCarregar,item;
    private DefaultTableModel modelo;
    private JRadioButton rCarr,rIntro;

    public StockTab(){
        super("Stock");
        lSearch=new JLabel("Procurar (Produto):");
        lID=new JLabel("ID:");
        lQuant=new JLabel("Quantidade:");

        tProd=new JTextField(7);
        tID=new JTextField(5);
        tQuant=new JTextField(10);
        
        pGeral=new JPanel(new BorderLayout());
        pGeral.setPreferredSize(new Dimension(720,420));
        panelTable=new JPanel(new GridLayout(1,1));
        panelSearch=new JPanel();
        panelButton=new JPanel();
        
        //menu
        barraMenu=new JMenuBar();
        this.setJMenuBar(barraMenu);
        menu=new JMenu("Operacoes");
        barraMenu.add(menu);
        item=new JMenuItem("Total.Quant");
        menu.add(item);
        itemCarregar=new JMenuItem("Carregar Stock");
        menu.add(itemCarregar);
        item=new JMenuItem("Avaliar Stock");
        menu.add(item);

        bSearch=new JButton("Procurar");
        bDepositar=new JButton("Depositar");
        bCancelar=new JButton("Cancelar");
        itemCarregar.addActionListener(this);
        stock();
    }
    public void carregarStock(){
            
            JFrame f=new JFrame();
            JPanel panel1,panel2,panel3;
            panel1=new JPanel();
            panel2=new JPanel();
            panel3=new JPanel();
            
            panel1.add(lID);
            panel1.add(tID);
            panel1.add(lQuant);
            panel1.add(tQuant);
            //rCarr=new JRadioButton("Carregar");
            //rIntro=new JRadioButton("Inserir");
            //panel3.add(rCarr);
            //panel3.add(rIntro);
            
            
            panel2.add(bDepositar);
            panel2.add(bCancelar);
            
            bDepositar.addActionListener(this);
            bCancelar.addActionListener(this);
            f.add(panel1,BorderLayout.NORTH);
            //f.add(panel3,BorderLayout.CENTER);
            f.add(panel2,BorderLayout.SOUTH);
            f.setVisible(true);
            f.pack();
            f.setLocationRelativeTo(null);
    }

     public void stock(){ 
            StockDAO d=new StockDAO();
            modelo=new DefaultTableModel();
            JTable tabStock=new JTable(modelo);
            modelo.addColumn("ID");
            modelo.addColumn("Quantidade");           
            tabStock.getColumnModel().getColumn(0).setPreferredWidth(30);
            List <Stock> st=d.listarStock();
            actualizarTabela(st);
            JScrollPane scroll = new JScrollPane(tabStock);
            JPanel p=new JPanel();
            p.setLayout(null);
            p.setPreferredSize(new Dimension(720, 350));
            p.add(scroll);
            scroll.setBounds(5, 0, 710, 350);
            
            JPanel panelAux=new JPanel();
            panelSearch.add(lSearch);
            panelSearch.add(tProd);
            
            panelAux.add(panelSearch, BorderLayout.NORTH);
            

            panelButton.add(bSearch);
            getpGeral().add(p, BorderLayout.NORTH);
            getpGeral().add(panelAux, BorderLayout.CENTER);
            getpGeral().add(panelButton, BorderLayout.SOUTH);
            this.add(getpGeral());
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }
     
        public void actualizarTabela(List<Stock> st) {
            for (Stock e : st) {
                modelo.addRow(new Object[]{String.valueOf(e.getIdProd()),/* e.getNome(), */e.getQuant()});
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemCarregar)
            carregarStock();
        if(e.getSource()==bDepositar){
            int idCli;
            float qt;
            if(tID.getText().isEmpty()||tQuant.getText().isEmpty())
                JOptionPane.showMessageDialog(null,"Nao deixe campos vazios");
            else{
                idCli=Integer.parseInt(tID.getText());
                qt=Float.parseFloat(tQuant.getText());
                StockDAO cl=new StockDAO(); 
                Stock s=new Stock();
                s.setIdProd(idCli);
                s.setQuant(qt);
                cl.carregarStock(s);
               
            }
        }
        if(e.getSource()==bCancelar){
            tID.setText("");
            tQuant.setText("");
        }
        
    }

    /**
     * @return the pGeral
     */
    public JPanel getpGeral() {
        return pGeral;
    }

}
