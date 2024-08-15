package com.ifsul.savapc.model;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class habForm {
	
	private List<habilidade> habilidades;

	public List<habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	@Override
	public String toString() {
		return "habForm [habilidades=" + habilidades + "]";
	}

	
	

	

	
	
}
