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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table (name = "questionarioinicial")
public class questionarioinicial implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionarioId;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate disponibilidade;

    @Column(nullable = false)
    private boolean visibilidade;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "questpergunta",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaQuestionarioId","questionarioId"}),
        joinColumns =  @JoinColumn(name = "questionarioId"),
        inverseJoinColumns = @JoinColumn(name = "perguntaQuestionarioId")
    )
    @JsonManagedReference
    private List<perguntaquestionario> perguntasQuestionario;

    @ManyToMany
    @JoinTable(
        name = "questionarioturmas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"turmaId","questionarioId"}),
        joinColumns =  @JoinColumn(name = "questionarioId"),
        inverseJoinColumns = @JoinColumn(name = "turmaId")
    )
    @JsonBackReference
    private List<turma> turmas;

    
    
     
    public int getQuestionarioId() {
        return questionarioId;
    }

    public void setQuestionarioId(int questionarioId) {
        this.questionarioId = questionarioId;
    }

    public LocalDate getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(LocalDate disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public questionarioinicial(LocalDate disponibilidade, boolean visibilidade, String nome) {
        this.disponibilidade = disponibilidade;
        this.visibilidade = visibilidade;
        this.nome = nome;
    }

    public questionarioinicial() {
    }

    @Override
    public String toString() {
        return "questionarioinicial : questionarioId=" + questionarioId + ", disponibilidade=" + disponibilidade
                + ", visibilidade=" + visibilidade + ", nome=" + nome ;
    }

    public List<perguntaquestionario> getPerguntasQuestionario() {
        return perguntasQuestionario;
    }

    public void setPerguntasQuestionario(List<perguntaquestionario> perguntasQuestionario) {
        this.perguntasQuestionario = perguntasQuestionario;
    }

   

    public List<turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<turma> turmas) {
        this.turmas = turmas;
    }

    
   

    
}
