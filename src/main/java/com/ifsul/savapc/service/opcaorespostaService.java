package com.ifsul.savapc.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.perguntaTeste;

@Service
public interface opcaorespostaService {
    List<opcaoresposta> findAll();
    opcaoresposta findById(Integer id);
    opcaoresposta save(opcaoresposta opcaoresposta);
    boolean deleteById(Integer id);
    boolean existsById(Integer id);

    List<opcaoresposta> findByVerdadeira(boolean verdadeira);
    List<opcaoresposta> findOpcaoRespostaIdByPerguntasTesteAndVerdadeira(perguntaTeste p,boolean verdadeira);
}
