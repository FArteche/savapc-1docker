package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.perguntaTeste;

public interface opcaorespostaRepository extends JpaRepository<opcaoresposta,Integer>{
    List<opcaoresposta> findByVerdadeira(boolean verdadeira);
    List<opcaoresposta> findOpcaoRespostaIdByPerguntasTesteAndVerdadeira(perguntaTeste p,boolean verdadeira);
}
