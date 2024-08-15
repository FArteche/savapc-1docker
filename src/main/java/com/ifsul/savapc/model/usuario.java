package com.ifsul.savapc.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table (name = "usuario")
public class usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioId")
    private int usuarioId;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String identificador;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private BigInteger telefone;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @Column(nullable = true)
    private String img;
    @Column(nullable=false, unique=true)
    private String username;
    @Column(nullable=false)
    private String password;
    @ManyToMany
    @JoinTable(
        name = "turma_usuario",
        uniqueConstraints = @UniqueConstraint(columnNames = {"turmaId","usuarioId"}),
        joinColumns =  @JoinColumn(name = "usuarioId"),
        inverseJoinColumns = @JoinColumn(name = "turmaId")
    )
    @JsonBackReference
    private List<turma> turmas;
    @ManyToMany
    @JoinTable(
        name = "habilidade_usuario",
        uniqueConstraints = @UniqueConstraint(columnNames = {"habilidadeId","usuarioId"}),
        joinColumns =  @JoinColumn(name = "usuarioId"),
        inverseJoinColumns = @JoinColumn(name = "habilidadeId")
    )
    @JsonManagedReference
    private List<habilidade> habilidades;

    @OneToMany(mappedBy="usuario")
    @JsonBackReference
     List<regTestes> regTeste;

     @OneToMany(mappedBy="usuario")
     @JsonBackReference
     List<regQuestionarios> regQuestionarios;

     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinTable(name = "tb_users_roles",
             joinColumns = @JoinColumn(name = "usuarioId"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
     private Collection<Role> roles;

    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public usuario(String nome, String email, BigInteger telefone, String tipo,String identificador, LocalDate dataNascimento,
            String img, String username, String password, Collection<Role> roles) {
        super();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo=tipo;
        this.identificador=identificador;
        this.dataNascimento = dataNascimento;
        this.img = img;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public usuario() {
        super();
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

   

    public List<turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<turma> turmas) {
        this.turmas = turmas;
    }

    public List<habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public List<regTestes> getRegTeste() {
        return regTeste;
    }

    public void setRegistroteste(List<regTestes> registroteste) {
        this.regTeste = registroteste;
    }


    public void setRegTeste(List<regTestes> regTeste) {
        this.regTeste = regTeste;
    }

    public List<regQuestionarios> getRegQuestionarios() {
        return regQuestionarios;
    }

    public void setRegQuestionarios(List<regQuestionarios> regQuestionarios) {
        this.regQuestionarios = regQuestionarios;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public BigInteger getTelefone() {
        return telefone;
    }
    public void setTelefone(BigInteger telefone) {
        this.telefone = telefone;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    @Override
    public String toString() {
        return "usuario [usuarioId=" + usuarioId + ", nome=" + nome + ", identificador=" + identificador + ", tipo="
                + tipo + ", email=" + email + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", img="
                + img + ", username=" + username + "]";
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   


    
    
}


    