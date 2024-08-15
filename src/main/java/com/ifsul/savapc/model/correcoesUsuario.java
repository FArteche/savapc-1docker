package com.ifsul.savapc.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="correcoesusuario")
public class correcoesUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "correcaoId")
    private int correcaoId;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="usuarioId")
    private usuario usuario;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="turmaId")
    private turma turma;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="testeId")
    private teste teste;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perguntaTesteId", referencedColumnName = "perguntaTesteId")
    private perguntaTeste perguntaTeste;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opcaoRespostaId", referencedColumnName = "opcaoRespostaId")
    private opcaoresposta opcaoResposta;
    
    @Column(nullable = false)
    private boolean acertou;

    public int getCorrecaoId() {
        return correcaoId;
    }

    public void setCorrecaoId(int correcaoId) {
        this.correcaoId = correcaoId;
    }

   

    public turma getTurma() {
        return turma;
    }

    public void setTurma(turma turma) {
        this.turma = turma;
    }

    public teste getTeste() {
        return teste;
    }

    public void setTeste(teste teste) {
        this.teste = teste;
    }

    public perguntaTeste getPerguntaTeste() {
        return perguntaTeste;
    }

    public void setPerguntaTeste(perguntaTeste perguntaTeste) {
        this.perguntaTeste = perguntaTeste;
    }

    

    public boolean isAcertou() {
        return acertou;
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

   
    


    public correcoesUsuario() {
    }

    @Override
    public String toString() {
        return "correcoesUsuario [correcaoId=" + correcaoId + ", usuario=" + usuario + ", turma=" + turma + ", teste="
                + teste + ", perguntaTeste=" + perguntaTeste + ", opcaoResposta=" + opcaoResposta + ", acertou="
                + acertou + "]";
    }

    public correcoesUsuario(usuario usuario, turma turma, teste teste,
            perguntaTeste perguntaTeste, opcaoresposta opcaoResposta, boolean acertou) {
        this.usuario = usuario;
        this.turma = turma;
        this.teste = teste;
        this.perguntaTeste = perguntaTeste;
        this.opcaoResposta = opcaoResposta;
        this.acertou = acertou;
    }

    public opcaoresposta getOpcaoResposta() {
        return opcaoResposta;
    }

    public void setOpcaoResposta(opcaoresposta opcaoResposta) {
        this.opcaoResposta = opcaoResposta;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }
    
    
    

    
}
