package com.ifsul.savapc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table (name = "teste")
public class teste implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testeId")
    private int testeId;

    @Column(nullable = false)
    private boolean visibilidade;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate disponibilidade;

    @ManyToMany
    @JoinTable(
        name = "perguntateste_teste",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaTesteId","testeId"}),
        joinColumns =  @JoinColumn(name = "testeId"),
        inverseJoinColumns = @JoinColumn(name = "perguntaTesteId")
    )
    @JsonManagedReference
    private List<perguntaTeste> perguntasTeste;

    @ManyToMany
    @JoinTable(
        name = "testesturma",
        uniqueConstraints = @UniqueConstraint(columnNames = {"turmaId","testeId"}),
        joinColumns =  @JoinColumn(name = "testeId"),
        inverseJoinColumns = @JoinColumn(name = "turmaId")
    )
    @JsonBackReference
    private List<turma> turmas;

    @OneToMany(mappedBy = "teste")
    @JsonBackReference
     List<regTestes> regTeste;

     

    public int getTesteId() {
        return testeId;
    }

    public void setTesteId(int testeId) {
        this.testeId = testeId;
    }

    public boolean getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public LocalDate getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(LocalDate disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public teste(boolean visibilidade, String nome, LocalDate disponibilidade, List<perguntaTeste> perguntas) {
        super();
        this.visibilidade = visibilidade;
        this.nome = nome;
        this.disponibilidade = disponibilidade;
        this.perguntasTeste = perguntas;
        
    }

    public teste() {
        super();
    }

    public List<perguntaTeste> getPerguntasTeste() {
        return perguntasTeste;
    }

    @Override
    public String toString() {
        return "teste [testeId=" + testeId + ", visibilidade=" + visibilidade + ", nome=" + nome + ", disponibilidade="
                + disponibilidade + "]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<turma> turmas) {
        this.turmas = turmas;
    }

    

    public List<regTestes> getRegTeste() {
        return regTeste;
    }

    public void setRegTeste(List<regTestes> regTeste) {
        this.regTeste = regTeste;
    }

    public void setPerguntasTeste(List<perguntaTeste> perguntasTeste) {
        this.perguntasTeste = perguntasTeste;
    }

    

    
}
