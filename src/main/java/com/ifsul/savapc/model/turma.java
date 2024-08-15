package com.ifsul.savapc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table (name = "turma")
public class turma implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int turmaId;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private boolean visibilidade;

    @ManyToMany
    @JoinTable(
        name = "turma_usuario",
        uniqueConstraints = @UniqueConstraint(columnNames ={"turmaId","usuarioId"} ),
        joinColumns =  @JoinColumn(name = "turmaId"),
        inverseJoinColumns = @JoinColumn(name = "usuarioId")
    )
    @JsonManagedReference
    private List<usuario> usuarios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "testesturma",
        uniqueConstraints = @UniqueConstraint(columnNames = {"turmaId","testeId"}),
        joinColumns =  @JoinColumn(name = "turmaId"),
        inverseJoinColumns = @JoinColumn(name = "testeId")
    )
    @JsonManagedReference
    private List<teste> testes;

    @ManyToMany
    @JoinTable(
        name = "questionarioturmas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"turmaId","questionarioId"}),
        joinColumns =  @JoinColumn(name = "turmaId"),
        inverseJoinColumns = @JoinColumn(name = "questionarioId")
    )
    @JsonManagedReference
    private List<questionarioinicial> questionarios;
    
    @OneToMany(mappedBy="turma")
    @JsonBackReference
     List<regTestes> regTeste;

    

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<notas> notas;

    public List<String> usernames(){
        List<String> usernames = new ArrayList<>();
        usernames.clear();
        if(usuarios.size()>0){
            for (usuario u : usuarios) {
                usernames.add(u.getUsername());
            }
        }
        
        return usernames;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public turma() {
    }

    public turma(String nome, boolean visibilidade, List<teste> testes, double pesoTestes) {
        this.nome = nome;
        this.visibilidade = visibilidade;
        this.testes = testes;
    }

    @Override
    public String toString() {
        return "turma : turmaId=" + turmaId + ", nome=" + nome +", visibilidade="+visibilidade;
    }

   

    public boolean isVisibilidade() {
        return this.visibilidade;
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

    public List<questionarioinicial> getQuestionarios() {
        return questionarios;
    }

    public void setQuestionarios(List<questionarioinicial> questionarios) {
        this.questionarios = questionarios;
    }

    public List<regTestes> getRegTeste() {
        return regTeste;
    }

    public void setRegTeste(List<regTestes> regTeste) {
        this.regTeste = regTeste;
    }

    

    public List<notas> getNotas() {
        return notas;
    }

    public void setNotas(List<notas> notas) {
        this.notas = notas;
    }

    
    public List<usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<usuario> usuarios) {
        this.usuarios = usuarios;
    }

    

   

    
    

    
}
