package com.ifsul.savapc.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table (name = "perguntaquestionario")
public class perguntaquestionario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int perguntaQuestionarioId;

    @Column(nullable = false,name="titulo")
    private String titulo;

    @Column(nullable = true,name="opRespostaId")
    private String opRespostaId;

    @Column(nullable = false,name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "tipo")
    private String tipo;

    @Lob
    private String resposta;
    
    private boolean obrigatorio;
    @ManyToMany
    @JoinTable(
        name = "questpergunta",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaQuestionarioId","questionarioId"}),
        joinColumns =  @JoinColumn(name = "perguntaQuestionarioId"),
        inverseJoinColumns = @JoinColumn(name = "questionarioId")
    )
    @JsonBackReference
    private List<questionarioinicial> questionarios;

    @ManyToMany
    @JoinTable(
        name = "opcaoresposta_perguntaquestionario",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaQuestionarioId","opcaoRespostaId"}),
        joinColumns =  @JoinColumn(name = "perguntaQuestionarioId"),
        inverseJoinColumns = @JoinColumn(name = "opcaoRespostaId")
    )
    @JsonManagedReference
    private List<opcaoresposta> opcoesResposta;

    

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

    public perguntaquestionario() {
    }

    public perguntaquestionario(String descricao, String tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }

   

    @Override
    public String toString() {
        return "perguntaquestionario [perguntaQuestionarioId=" + perguntaQuestionarioId + ", titulo=" + titulo
                + ", opRespostaId=" + opRespostaId + ", descricao=" + descricao + ", tipo=" + tipo +", obrigatorio=" +obrigatorio+"]";
    }

    public List<questionarioinicial> getQuestionarios() {
        return questionarios;
    }

    public void setQuestionarios(List<questionarioinicial> questionarios) {
        this.questionarios = questionarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOpRespostaId() {
        return opRespostaId;
    }

    public void setOpRespostaId(String opRespostaId) {
        this.opRespostaId = opRespostaId;
    }

    public List<opcaoresposta> getOpcoesResposta() {
        return opcoesResposta;
    }

    public void setOpcoesResposta(List<opcaoresposta> opcoesResposta) {
        this.opcoesResposta = opcoesResposta;
    }

    public int getPerguntaQuestionarioId() {
        return perguntaQuestionarioId;
    }

    public void setPerguntaQuestionarioId(int perguntaQuestionarioId) {
        this.perguntaQuestionarioId = perguntaQuestionarioId;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    

    
}
