package com.ifsul.savapc.model;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class perguntasForm {
	private String username;
	private int turmaId;
	private List<perguntaTeste> perguntas;
	
	
	
	

	

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	


	
	public perguntasForm(String username, int turmaId, List<perguntaTeste> perguntas) {
		this.username = username;
		this.turmaId = turmaId;
		this.perguntas = perguntas;
	}

	@Override
	public String toString() {
		return "perguntasForm [username=" + username + ", turmaId=" + turmaId + ", perguntas=" + perguntas + "]";
	}

	public List<perguntaTeste> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<perguntaTeste> perguntas) {
		this.perguntas = perguntas;
	}

	public perguntasForm() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
