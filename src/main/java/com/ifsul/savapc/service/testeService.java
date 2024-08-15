package com.ifsul.savapc.service;

import java.util.List;

import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;

public interface testeService {
    List<teste> findAll();
    teste findById(Integer id);
    teste save(teste teste);
    boolean deleteById(Integer id);
    boolean existsById(Integer testeId);

    List<teste> findByTurmas(turma turma);
    void atualizarVisibilidades();
}
