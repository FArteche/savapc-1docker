package com.ifsul.savapc.model;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class perguntasQuestForm {
	private String username;
	private int turmaId;
	private List<perguntaquestionario> perguntas;

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

	@Override
	public String toString() {
		return "perguntasQuestForm [username=" + username + ", turmaId=" + turmaId + ", perguntas=" + perguntas + "]";
	}

	public List<perguntaquestionario> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<perguntaquestionario> perguntas) {
		this.perguntas = perguntas;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
