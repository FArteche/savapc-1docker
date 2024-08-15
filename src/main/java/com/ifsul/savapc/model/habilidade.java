package com.ifsul.savapc.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table (name = "habilidade")
public class habilidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int habilidadeId;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "habilidade_usuario",
        uniqueConstraints = @UniqueConstraint(columnNames ={"habilidadeId","usuarioId"} ),
        joinColumns =  @JoinColumn(name = "habilidadeId"),
        inverseJoinColumns = @JoinColumn(name = "usuarioId")
    )
    @JsonBackReference
    private List<usuario> usuarios;
    
    @ManyToMany
    @JoinTable(
        name = "habilidade_perguntaTeste",
        uniqueConstraints = @UniqueConstraint(columnNames = {"habilidadeId","perguntaTesteId"}),
        joinColumns =  @JoinColumn(name = "habilidadeId"),
        inverseJoinColumns = @JoinColumn(name = "perguntaTesteId")
    )
    @JsonBackReference
    private List<perguntaTeste> perguntasTeste;
    public int getHabilidadeId() {
        return habilidadeId;
    }

    public void setHabilidadeId(int habilidadeId) {
        this.habilidadeId = habilidadeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public habilidade() {
    }

    public habilidade(String nome) {
        
        this.nome = nome;
    }



    @Override
    public String toString() {
        return "habilidade [habilidadeId=" + habilidadeId + "]";
    }

    public List<usuario> getUsuario() {
        return usuarios;
    }

    public void setUsuario(List<usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<perguntaTeste> getPerguntasTeste() {
        return perguntasTeste;
    }

    public void setPerguntasTeste(List<perguntaTeste> perguntasTeste) {
        this.perguntasTeste = perguntasTeste;
    }

    

    

    
}
