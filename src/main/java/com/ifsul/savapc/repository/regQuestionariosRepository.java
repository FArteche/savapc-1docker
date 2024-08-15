package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.regQuestionarios;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;

public interface regQuestionariosRepository extends JpaRepository<regQuestionarios,Integer>{
     
     List<regQuestionarios> findByTurma(turma turma);
     List<regQuestionarios> findByUsuarioAndTurma(usuario u, turma turma);
}
