package com.ifsul.savapc.service;



import java.util.List;


import com.ifsul.savapc.model.regQuestionarios;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;

public interface regQuestionariosService {
    List<regQuestionarios> findAll();
    regQuestionarios findById(Integer id);
    regQuestionarios save(regQuestionarios regQuestionarios);
    boolean deleteById(Integer id);
    boolean existsById(Integer id);

    List<regQuestionarios> findByUsuarioAndTurma(usuario u, turma turma);
    List<regQuestionarios> findByTurma(turma turma);
}

