package Visao;

import controller.Cliente;
import controller.Funcionario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ClienteDAO;
import javax.swing.table.DefaultTableModel;
import model.FuncionarioDAO;


public class Trabalhador extends JFrame implements ActionListener {
	private final JLabel lSearch;
	private final JTextField tProc;
	private final JPanel panelTable,panelSearch,panelBottomButton,panelRadio,panelAux,pGeral,panelTop;
	private final JButton bSearch,bCategoria,bActualizar,bAdd;
        private DefaultTableModel modelo;
	private final JRadioButton rID,rNome,rCateg;
	private final JMenuBar barraMenu;
	private final JMenu menu,subMenu;
	private JMenuItem item,itemTrabNov;
	
	
	public Trabalhador(){
            super("Trabalhadores");
            
            panelTable=new JPanel(new GridLayout(1,1));
            panelSearch=new JPanel();
            panelBottomButton=new JPanel();
            panelRadio=new JPanel();
            panelAux=new JPanel(new BorderLayout());
            pGeral=new JPanel(new BorderLayout());
            panelTop=new JPanel(new GridBagLayout());
            pGeral.setPreferredSize(new Dimension(720,440));

            lSearch=new JLabel("Procurar:");

            tProc=new JTextField(7);

            rID=new JRadioButton("ID",false);
            rNome=new JRadioButton("Nome",false);
            rCateg=new JRadioButton("Categoria",false);
            //menu
            barraMenu=new JMenuBar();
            this.setJMenuBar(barraMenu);
            menu=new JMenu("Operacoes");
            barraMenu.add(menu);
            itemTrabNov=new JMenuItem("Novo Trabalhador");
            menu.add(itemTrabNov);
            item=new JMenuItem("Actualizar Categoria");
            menu.add(item);
            subMenu=new JMenu("Categorias");
            menu.add(subMenu);
            item=new JMenuItem("Caixa");
            subMenu.add(item);
            subMenu.addSeparator();
            item=new JMenuItem("Gerente");
            subMenu.add(item);
            subMenu.addSeparator();
            item=new JMenuItem("Adminitrador");
            subMenu.add(item);
            subMenu.addSeparator();
            item=new JMenuItem("Supervisor");
            subMenu.add(item);
            
            bSearch=new JButton("Procurar");
            bActualizar=new JButton("Actualizar");
            bCategoria=new JButton("Categoria");
            bAdd=new JButton("Adicionar");
            bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("new-icon_1.png")));
            
            itemTrabNov.addActionListener(this);
            trabalhador();
	}
	
	
	public void trabalhador(){            
            FuncionarioDAO d=new FuncionarioDAO();
            modelo=new DefaultTableModel();
            JTable tabClie=new JTable(modelo);
            modelo.addColumn("ID");
            modelo.addColumn("nome");
            modelo.addColumn("BI");
            modelo.addColumn("Data de Nascimento");
            modelo.addColumn("nrNuit");
            modelo.addColumn("contacto");
            modelo.addColumn("endereco");
            modelo.addColumn("Categoria");
            modelo.addColumn("sexo");
            
            tabClie.getColumnModel().getColumn(0).setPreferredWidth(30);
            List <Funcionario> func=d.listarFuncionario();
            actualizarTabela(func);
            JScrollPane scroll = new JScrollPane(tabClie);
            JPanel p=new JPanel();
            p.setLayout(null);
            p.setPreferredSize(new Dimension(720, 300));
            p.add(scroll);
            scroll.setBounds(5, 0, 710, 300);            
            panelSearch.add(lSearch);
            panelSearch.add(tProc);
            panelBottomButton.add(bSearch);
            
            panelRadio.add(rID);
            panelRadio.add(rNome);
            panelRadio.add(rCateg);

            panelAux.add(panelSearch, BorderLayout.NORTH);
            panelAux.add(panelRadio, BorderLayout.CENTER);
            
            //Botoes do top
            GridBagConstraints gbcAdd=new GridBagConstraints();
            gbcAdd.gridx=0;
            gbcAdd.gridy=0;
            gbcAdd.ipadx=30;
            gbcAdd.ipady=3;
            //gbcAdd.anchor=GridBagConstraints.WEST;
            gbcAdd.insets=new Insets(5, 5, 5,0);
            panelTop.add(bAdd,gbcAdd);
            
            GridBagConstraints gbcCat=new GridBagConstraints();
            gbcCat.gridx=1;
            gbcCat.gridy=0;
            gbcCat.ipadx=30;
            gbcCat.ipady=6;
            //gbcDep.anchor=GridBagConstraints.EAST;
            gbcCat.insets=new Insets(5, 5, 5,5);
            panelTop.add(bCategoria,gbcCat);
            
            GridBagConstraints gbcActu=new GridBagConstraints();
            gbcActu.gridx=2;
            gbcActu.gridy=0;
            gbcActu.ipadx=30;
            gbcActu.ipady=6;
            gbcActu.anchor=GridBagConstraints.EAST;
            gbcActu.insets=new Insets(5, 5, 5,5);
            panelTop.add(bActualizar,gbcActu);
            
            //Paneil da tabela sendo integrado no painel Top
            GridBagConstraints gbcP=new GridBagConstraints();
            gbcP.gridx=0;
            gbcP.gridy=1;
            gbcP.gridwidth=3;
            //gbcAdd.insets=new Insets(0, 0, 5,0);
            panelTop.add(p,gbcP);
            
            pGeral.add(panelTop, BorderLayout.NORTH);
            pGeral.add(panelAux, BorderLayout.CENTER);
            pGeral.add(panelBottomButton, BorderLayout.SOUTH);
            this.add(pGeral);            	
                       
	}
        public void actualizarTabela(List<Funcionario> func) {
            for (Funcionario e : func) {
                modelo.addRow(new Object[]{String.valueOf(e.getIdFunc()), e.getNome(), e.getBi(),e.getDataNasc(),e.getNrNuit(),e.getContacto(),e.getEndereco(),e.getCategoria(),e.getSexo()});
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        CadastrarCliente cd;
        if(e.getSource()==itemTrabNov){
            CadastrarFuncionario cf=new CadastrarFuncionario();
            cf.cadastroFunc();
        }
    }
    
    public JPanel getpGeral() {
        return pGeral;
    }

}
