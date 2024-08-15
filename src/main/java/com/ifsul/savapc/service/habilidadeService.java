package com.ifsul.savapc.service;

import java.util.List;


import com.ifsul.savapc.model.habilidade;
import com.ifsul.savapc.model.perguntaTeste;

public interface habilidadeService {
    List<habilidade> findAll();
    habilidade findById(Integer id);
    habilidade save(habilidade teste);
    boolean deleteById(Integer id);
    boolean existsById(Integer habilidadeId);

    List<habilidade> findByPerguntasTeste(perguntaTeste pergunta);
}
