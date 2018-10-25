
package controller;
import java.sql.Date;

public class Produto {
    private String nome,tipo;
    private double preco;
    private int idProd;
    private Date dataVali;
    
    public Produto(){}
    public Produto(String nome,String tipo,double preco,Date dataVali){
        this.nome=nome;
        this.tipo=tipo;
        this.preco=preco;
        this.dataVali=dataVali;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataVali() {
        return dataVali;
    }

    public void setDataVali(Date dataVali) {
        this.dataVali = dataVali;
    }

    /**
     * @return the idProd
     */
    public int getIdProd() {
        return idProd;
    }

    /**
     * @param idProd the idProd to set
     */
    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
    
}
