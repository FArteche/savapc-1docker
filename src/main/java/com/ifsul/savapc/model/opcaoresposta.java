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
@Table (name = "opcaoresposta")
public class opcaoresposta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opcaoRespostaId;

    @Column(nullable = false)
    private String descricao ;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private boolean verdadeira;
    

    @ManyToMany
    @JoinTable(
        name = "opcaoresposta_perguntateste",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaTesteId","opcaoRespostaId"}),
        joinColumns =  @JoinColumn(name = "opcaoRespostaId"),
        inverseJoinColumns = @JoinColumn(name = "perguntaTesteId")
    )
    @JsonBackReference
    private List<perguntaTeste> perguntasTeste;

    @ManyToMany
    @JoinTable(
        name = "opcaoresposta_perguntaquestionario",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaQuestionarioId","opcaoRespostaId"}),
        joinColumns =  @JoinColumn(name = "opcaoRespostaId"),
        inverseJoinColumns = @JoinColumn(name = "perguntaQuestionarioId")
    )
    @JsonBackReference
    private List<perguntaquestionario> perguntasQuestionario;

    
    

    public int getOpcaoRespostaId() {
        return opcaoRespostaId;
    }

    public void setOpcaoRespostaId(int opcaoRespostaId) {
        this.opcaoRespostaId = opcaoRespostaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isVerdadeira() {
        return verdadeira;
    }

    public void setVerdadeira(boolean verdadeira) {
        this.verdadeira = verdadeira;
    }

    public opcaoresposta() {
    }

    public opcaoresposta(String descricao, String tipo, boolean verdadeira) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.verdadeira = verdadeira;
    }

   

    @Override
    public String toString() {
        return "opcaoresposta [opcaoRespostaId=" + opcaoRespostaId + ", descricao=" + descricao + ", verdadeira=" + verdadeira + "]";
    }

   

    public List<perguntaTeste> getPerguntasTeste() {
        return perguntasTeste;
    }

    public void setPerguntasTeste(List<perguntaTeste> perguntasTeste) {
        this.perguntasTeste = perguntasTeste;
    }

    public List<perguntaquestionario> getPerguntasQuestionario() {
        return perguntasQuestionario;
    }

    public void setPerguntasQuestionario(List<perguntaquestionario> perguntasQuestionario) {
        this.perguntasQuestionario = perguntasQuestionario;
    }

    

    

    
}
