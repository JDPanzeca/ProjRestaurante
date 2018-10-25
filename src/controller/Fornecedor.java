
package controller;


public class Fornecedor {
    
    private String nome,especialidade;
    private int idForn;
    
    public Fornecedor(){}
    public Fornecedor(String nome, String especialidade){
        this.nome=nome;
        this.especialidade=especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * @return the idForn
     */
    public int getIdForn() {
        return idForn;
    }

    /**
     * @param idForn the idForn to set
     */
    public void setIdForn(int idForn) {
        this.idForn = idForn;
    }
    
}
