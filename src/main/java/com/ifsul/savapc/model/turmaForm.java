package com.ifsul.savapc.model;

import java.util.List;

public class turmaForm {
   
   
    private String nome;
    private boolean visibilidade;
    private List<teste> testes;
    private double pesoTestes;
   // private int professorId;
    
   
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean isVisibilidade() {
        return visibilidade;
    }
    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
    public List<teste> getTestes() {
        return testes;
    }
    public void setTestes(List<teste> testes) {
        this.testes = testes;
    }
    public turmaForm() {
        super();
    }
    public double getPesoTestes() {
        return pesoTestes;
    }
    public void setPesoTestes(double pesoTestes) {
        this.pesoTestes = pesoTestes;
    }

    
}
