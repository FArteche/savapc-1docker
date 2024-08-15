package com.ifsul.savapc.model;


import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "regtestes")
public class regTestes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regTestesId")
    private int regTestesId;

    @ManyToOne
    @JoinColumn(name = "testeId")
    private teste teste;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "turmaId")
    private turma turma;
   
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "regtestes_respostateste",
            joinColumns = @JoinColumn(
                    name = "regTestesId", referencedColumnName = "regTestesId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "respostaTesteId", referencedColumnName = "respostaTesteId"
            )
    )
    private List<respostaTeste> respostasTeste;

    

    

    
    public regTestes() {
        super();
    }

    public regTestes(usuario usuario,turma turma, teste teste, List<respostaTeste> respostas) {
        super();
        this.usuario = usuario;
        this.turma= turma;
        this.teste = teste;
        this.respostasTeste = respostas;
    }
    
    
    public teste getTeste() {
        return teste;
    }

    public void setTeste(teste teste) {
        this.teste = teste;
    }

    public int getRegTestesId() {
        return regTestesId;
    }

    public void setRegTestesId(int regTestesId) {
        this.regTestesId = regTestesId;
    }

    public List<respostaTeste> getRespostasTeste() {
        return respostasTeste;
    }

    public void setRespostasTeste(List<respostaTeste> respostasTeste) {
        this.respostasTeste = respostasTeste;
    }

    public turma getTurma() {
        return turma;
    }

    public void setTurma(turma turma) {
        this.turma = turma;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "regTestes [regTestesId=" + regTestesId + ", teste=" + teste + ", usuario=" + usuario + ", turma="
                + turma + "]";
    }

}
