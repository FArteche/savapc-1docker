package com.ifsul.savapc.model;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class testeForm {
	private String nome; 
	private LocalDate disponibilidade; 
	private boolean visibilidade;
	private double peso;
	private List<perguntaTeste> perguntas;

	public List<perguntaTeste> getPerguntas() {
		return this.perguntas;
	}

	public void setPerguntas(List<perguntaTeste> p) {
		this.perguntas = p;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDisponibilidade() {
		return this.disponibilidade;
	}

	public void setDisponibilidade(LocalDate getDisponibilidade) {
		this.disponibilidade = getDisponibilidade;
	}

	public boolean isVisibilidade() {
		return this.visibilidade;
	}

	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}

	

	@Override
	public String toString() {
		return "testeForm [nome=" + nome + ", disponibilidade=" + disponibilidade + ", visibilidade="
				+ visibilidade + ", peso=" + peso + "]";
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	

	
	

	

	
	
}
