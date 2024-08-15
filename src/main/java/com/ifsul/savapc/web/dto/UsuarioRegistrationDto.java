package com.ifsul.savapc.web.dto;

import java.math.BigInteger;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;


public class UsuarioRegistrationDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotNull
    private BigInteger telefone;
    @NotBlank
    private String tipo;
    @NotBlank
    private String identificador;
    @NotBlank
	private String dataNascimento;
    
    private String img;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    
   
    

    public UsuarioRegistrationDto(String nome, String email, BigInteger telefone, String tipo,String identificador, String dataNascimento,
            String img, String username, String password) {
        super();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo=tipo;
        this.identificador = identificador;
        this.dataNascimento = dataNascimento;
        this.img = img;
        this.username = username;
        this.password = password;
    }



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
   
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    public String getTipo() {
        return tipo;
    }



    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public String getIdentificador() {
        return identificador;
    }



    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }



    @Override
    public String toString() {
        return "UsuarioRegistrationDto [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", tipo=" + tipo
                + ", identificador=" + identificador + ", dataNascimento=" + dataNascimento + ", img=" + img
                + ", username=" + username + ", password=" + password + "]";
    }



  



   

    
    
}
