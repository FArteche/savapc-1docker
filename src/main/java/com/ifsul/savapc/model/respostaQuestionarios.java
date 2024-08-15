package com.ifsul.savapc.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "respostaquestionario")
public class respostaQuestionarios implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="respostaQuestionarioId")
    private int respostaQuestionarioId;
    @Column(name = "opRespostaId")
    private int opRespostaId;
    @Lob
    private String resposta;
    
    @Column(name="tipo")
    private String tipo;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "perguntaQuestionarioId")
    private perguntaquestionario perguntaQuestionario;
    
    public int getOpRespostaId() {
        return opRespostaId;
    }
    public void setOpRespostaId(int opRespostaId) {
        this.opRespostaId = opRespostaId;
    }
    public respostaQuestionarios() {
        super();
    }
    public int getRespostaQuestionarioId() {
        return respostaQuestionarioId;
    }
    public void setRespostaQuestionarioId(int respostaQuestionarioId) {
        this.respostaQuestionarioId = respostaQuestionarioId;
    }
    public perguntaquestionario getPerguntaQuestionario() {
        return perguntaQuestionario;
    }
    public void setPerguntaQuestionario(perguntaquestionario perguntaQuestionario) {
        this.perguntaQuestionario = perguntaQuestionario;
    }
    public respostaQuestionarios(int respostaQuestionarioId, perguntaquestionario perguntaQuestionario) {
        this.respostaQuestionarioId = respostaQuestionarioId;
        this.perguntaQuestionario = perguntaQuestionario;
    }
    @Override
    public String toString() {
        return "respostaQuestionarios [respostaQuestionarioId=" + respostaQuestionarioId + ", opRespostaId="
                + opRespostaId + "]";
    }
    public String getResposta() {
        return resposta;
    }
    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
   
    
    

}
