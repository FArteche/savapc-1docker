package com.ifsul.savapc.service;

import java.util.List;

import com.ifsul.savapc.model.notas;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;


public interface notasService {
    List<notas> findAll();
    notas findById(Integer id);
    notas save(notas nota);
    boolean deleteById(Integer id);
    boolean existsById(Integer id);

    void SalvarNotas(turma turma);
    List<notas> findByUsuario(usuario usuario);
    List<notas> findByTurma(turma turma);
    List<notas> findByUsuarioAndTurmaOrderByUsuario(usuario aluno,turma turma);
    List<notas> findByTurmaAndDesclassificado(turma t, boolean desclassificado);
    
}
