package com.ifsul.savapc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.habilidade;
import com.ifsul.savapc.model.perguntaTeste;


public interface habilidadeRepository extends JpaRepository<habilidade,Integer>{
    List<habilidade> findByPerguntasTeste(perguntaTeste pergunta);
}
