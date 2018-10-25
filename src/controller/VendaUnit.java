
package controller;


public class VendaUnit {
    private String nome;
    private double preco;
    private float quant;
    private int id;
    
    public VendaUnit(int id,String nome,double preco,float quant){
        this.id=id;
        this.nome=nome;
        this.preco=preco;
        this.quant=quant;
    }
    
    public VendaUnit(){
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }    
    public float getQuant() {
        return quant;
    }    
    public void setQuant(float quant) {
        this.quant = quant;
    }

  
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }
            
    
}
