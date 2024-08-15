package com.ifsul.savapc.model;


import java.io.Serializable;
import java.util.List;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "regquestionarios")
public class regQuestionarios implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regQuestionarioId")
    private int regQuestionarioId;

    @ManyToOne
    @JoinColumn(name = "questionarioId")
    private questionarioinicial questionario;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "turmaId")
    private turma turma;

   
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "regquestionarios_respostaquestionario",
            joinColumns = @JoinColumn(
                    name = "regQuestionarioId", referencedColumnName = "regQuestionarioId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "respostaQuestionarioId", referencedColumnName = "respostaQuestionarioId"
            )
    )
    private List<respostaQuestionarios> respostasQuestionario;


    
    public regQuestionarios() {
        super();
    }

    public turma getTurma() {
        return turma;
    }

    public void setTurma(turma turma) {
        this.turma = turma;
    }

    public int getRegQuestionarioId() {
        return regQuestionarioId;
    }

    public void setRegQuestionarioId(int regQuestionarioId) {
        this.regQuestionarioId = regQuestionarioId;
    }

    public questionarioinicial getQuestionario() {
        return questionario;
    }

    public void setQuestionario(questionarioinicial questionario) {
        this.questionario = questionario;
    }

    public List<respostaQuestionarios> getRespostasQuestionario() {
        return respostasQuestionario;
    }

    public void setRespostasQuestionario(List<respostaQuestionarios> respostasQuestionario) {
        this.respostasQuestionario = respostasQuestionario;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "regQuestionarios [regQuestionarioId=" + regQuestionarioId + ", questionario=" + questionario
                + ", usuario=" + usuario + ", turma=" + turma + "]";
    }

   
   

    
}
