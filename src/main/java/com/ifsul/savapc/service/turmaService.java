package com.ifsul.savapc.service;

import java.util.List;

import com.ifsul.savapc.model.turma;

public interface turmaService {
    List<turma> findAll();
    turma findById(Integer id);
    turma save(turma turma);
    boolean deleteById(Integer id);
    boolean existsById(Integer usuarioId);
    
    List<turma> findByVisibilidade(boolean visibilidade);
}
