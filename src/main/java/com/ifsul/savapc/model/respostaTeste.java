package com.ifsul.savapc.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "respostateste")
public class respostaTeste implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="respostaTesteId")
    private int respostaTesteId;
    
    @Column(name = "opRespostaId")
    private int opRespostaId;
    
    @ManyToMany(mappedBy = "respostasTeste") // mappedBy = (nome da variavel referente ao outro arquivo e não da tabela)
    List<regTestes> registrosTestes;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "perguntaTesteId")
    private perguntaTeste perguntaTeste;
    
    public int getOpRespostaId() {
        return opRespostaId;
    }
    public void setOpRespostaId(int opRespostaId) {
        this.opRespostaId = opRespostaId;
    }
    public respostaTeste() {
        super();
    }
    
    public respostaTeste(int respostaTesteId, int opRespostaId) {
        this.respostaTesteId = respostaTesteId;
        this.opRespostaId = opRespostaId;
        
    }
    public int getRespostaTesteId() {
        return respostaTesteId;
    }
    public void setRespostaTesteId(int respostaId) {
        this.respostaTesteId = respostaId;
    }
   
    @Override
    public String toString() {
        return " [respostaTesteId=" + respostaTesteId + ", opRespostaId=" + opRespostaId + "]";
    }
    public perguntaTeste getPerguntaTeste() {
        return perguntaTeste;
    }
    public void setperguntaTeste(perguntaTeste pergunta) {
        this.perguntaTeste = pergunta;
    }
    
    public List<regTestes> getRegistrosTestes() {
        return registrosTestes;
    }
    public void setRegistrosTestes(List<regTestes> registrosTestes) {
        this.registrosTestes = registrosTestes;
    }

}
