package Visao;

import controller.Cliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.FornecedorDAO;
import controller.Fornecedor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class FornecedorTab extends JFrame implements ActionListener {
	
	private final JLabel lSearch;
	private final JTextField tProc;
	private final JPanel panelTable,panelSearch,panelButtonBottom,panelTop,panelRadio,panelAux,pGeral;
	private final JButton bSearch,bRemove,bAdd;
	private final JRadioButton rID,rNome;	
	private final JMenuBar barraMenu;
	private final JMenu menu;
	private JMenuItem  itemForn;
        private DefaultTableModel modelo;
	
	public FornecedorTab(){		
            super("Lista de fornecedores");
            //Inicializando os paineis 
            
            panelTable=new JPanel(new GridLayout(1,1));
            panelSearch=new JPanel();
            panelButtonBottom=new JPanel(new GridBagLayout());
            panelTop=new JPanel(new GridBagLayout());
            panelRadio=new JPanel();
            panelAux=new JPanel(new BorderLayout());
            pGeral=new JPanel(new BorderLayout());
            pGeral.setPreferredSize(new Dimension(720,440));
		
            lSearch=new JLabel("Procurar:");
		
            tProc=new JTextField(7);
		
            rID=new JRadioButton("ID",false);
            rNome=new JRadioButton("Nome",false);
		
            barraMenu=new JMenuBar();
            this.setJMenuBar(barraMenu);
            menu=new JMenu("Operacoes");	
            barraMenu.add(menu);
            itemForn=new JMenuItem("Novo Fornecedor");
            menu.add(itemForn);
                
            itemForn.addActionListener(this);
		
            bSearch=new JButton("Procurar");
            bRemove=new JButton("Remover");
            bAdd=new JButton("Adicionar novo");
            bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("new-icon_1.png")));
            
            listarFornece();
	}
	
        public void listarFornece(){
            FornecedorDAO d=new FornecedorDAO();
            modelo=new DefaultTableModel();
            JTable tabClie=new JTable(modelo);
            modelo.addColumn("ID");
            modelo.addColumn("nome");
            modelo.addColumn("especialidade");           
            tabClie.getColumnModel().getColumn(0).setPreferredWidth(30);
            List <Fornecedor> fornec=d.listarFornecedores();
            actualizarTabela(fornec);
            JScrollPane scroll = new JScrollPane(tabClie);
            JPanel p=new JPanel();
            p.setLayout(null);
            p.setPreferredSize(new Dimension(720, 320));            
            p.add(scroll);
            scroll.setBounds(5, 0, 710, 320);
            
            panelRadio.add(rID);
            panelRadio.add(rNome);
            //panelSearch.add(lSearch);
            panelSearch.add(panelRadio);
            panelSearch.add(tProc);            

            panelAux.add(panelSearch, BorderLayout.NORTH);
            //panelAux.add(panelRadio, BorderLayout.CENTER);
            
            //Botoes do top
            GridBagConstraints gbcAdd=new GridBagConstraints();
            gbcAdd.gridx=0;
            gbcAdd.gridy=0;
            gbcAdd.insets=new Insets(5, 0, 5,0);
            panelTop.add(bAdd,gbcAdd);
            
            //Paneil da tabela sendo integrado no painel Top
            GridBagConstraints gbcP=new GridBagConstraints();
            gbcP.gridx=0;
            gbcP.gridy=1;
            //gbcAdd.insets=new Insets(0, 0, 5,0);
            panelTop.add(p,gbcP);
                    
            
            //panelButton.add(bAdd,gbcAdd);            
            
            //Botoes a baixo da lista
            GridBagConstraints gbcSearc=new GridBagConstraints();
            gbcSearc.gridx=0;
            gbcSearc.gridy=0;
            gbcSearc.insets=new Insets(0, 0, 5, 5);
            panelButtonBottom.add(bSearch,gbcSearc);
            
            GridBagConstraints gbcRemov=new GridBagConstraints();
            gbcRemov.gridx=1;
            gbcRemov.gridy=0;
            gbcRemov.insets=new Insets(0, 0, 5, 5);
            panelButtonBottom.add(bRemove,gbcRemov);           
                        
            pGeral.add(panelTop, BorderLayout.NORTH);
            pGeral.add(panelAux, BorderLayout.CENTER);
            pGeral.add(panelButtonBottom, BorderLayout.SOUTH);
            this.add(pGeral);
              	
        }
        public void actualizarTabela(List<Fornecedor> ff) {
            for (Fornecedor e : ff) {
                modelo.addRow(new Object[]{String.valueOf(e.getIdForn()), e.getNome(), e.getEspecialidade()});
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        CadastrarFornecedor fr;
        if(e.getSource()==itemForn){
            dispose();
            fr=new CadastrarFornecedor();
            //if(fr.getCadControl()==1)            
                //new FornecedorTab();
                      
        }
    }    

    /**
     * @retornar o pGeral
     */
    public JPanel getpGeral() {
        return pGeral;
    }

}
