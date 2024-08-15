package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.notas;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;


public interface notasRepository extends JpaRepository<notas,Integer> {
    List<notas> findByUsuario(usuario aluno);
    List<notas> findByTurma(turma turma);
    List<notas> findByUsuarioAndTurmaOrderByUsuario(usuario usuario, turma turma);
    List<notas> findByTurmaAndDesclassificado(turma t, boolean desclassificado);
    
    
}
