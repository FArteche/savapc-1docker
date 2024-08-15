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
@Table (name = "perguntateste")
public class perguntaTeste implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perguntaTesteId")
    private int perguntaTesteId;
    @Lob
    @Column(nullable = false, columnDefinition="CLOB")
    private String descricao;
    @Column(name = "img")
    private String img;
    @Column(name="opRespostaId")
    private String opRespostaId;
    @Column(name="titulo")
    private String titulo;
    @Column(name="pedido")
    private String pedido;

    @ManyToMany
    @JoinTable(
        name = "habilidade_perguntateste",
        uniqueConstraints = @UniqueConstraint(columnNames = {"habilidadeId","perguntaTesteId"}),
        joinColumns =  @JoinColumn(name = "perguntaTesteId"),
        inverseJoinColumns = @JoinColumn(name = "habilidadeId")
    )
    @JsonManagedReference
    private List<habilidade> habilidades;
    
   

    
    @ManyToMany
    @JoinTable(
        name = "perguntateste_teste",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaTesteId","testeId"}),
        joinColumns =  @JoinColumn(name = "perguntaTesteId"),
        inverseJoinColumns = @JoinColumn(name = "testeId")
    )
    @JsonBackReference
    private List<teste> testes;

    @ManyToMany
    @JoinTable(
        name = "opcaoresposta_perguntateste",
        uniqueConstraints = @UniqueConstraint(columnNames = {"perguntaTesteId","opcaoRespostaId"}),
        joinColumns =  @JoinColumn(name = "perguntaTesteId"),
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
    public perguntaTeste() {   
    }
    public perguntaTeste(String descricao) {
        this.descricao = descricao;
    }
    public List<habilidade> getHabilidades() {
        return habilidades;
    }
    public void setHabilidades(List<habilidade> habilidades) {
        this.habilidades = habilidades;
    }
    public List<teste> getTestes() {
        return testes;
    }
    public void setTestes(List<teste> testes) {
        this.testes = testes;
    }
    public List<opcaoresposta> getOpcoesResposta() {
        return opcoesResposta;
    }
    public void setOpcoesResposta(List<opcaoresposta> opcoesResposta) {
        this.opcoesResposta = opcoesResposta;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
   
    @Override
    public String toString() {
        return "[perguntaTesteId=" + perguntaTesteId + ", descricao=" + descricao + ", opRespostaId=" + opRespostaId
                + ", titulo=" + titulo + "]";
    }
    public String getOpRespostaId() {
        return opRespostaId;
    }
    public void setOpRespostaId(String opRespostaId) {
        this.opRespostaId = opRespostaId;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getPerguntaTesteId() {
        return perguntaTesteId;
    }
    public void setPerguntaTesteId(int perguntaTesteId) {
        this.perguntaTesteId = perguntaTesteId;
    }
    public String getPedido() {
        return pedido;
    }
    public void setPedido(String pedido) {
        this.pedido = pedido;
    }
    
}
