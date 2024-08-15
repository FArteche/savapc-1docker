package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.correcoesUsuario;
import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;


public interface correcoesUsuarioRepository extends JpaRepository<correcoesUsuario,Integer>{
    List<correcoesUsuario> findByUsuario (usuario a);
    List<correcoesUsuario> findByUsuarioAndTurmaAndTesteAndPerguntaTeste(usuario a, turma t, teste test, perguntaTeste perguntaTeste);
    List<correcoesUsuario> findByTurmaOrderByUsuario(turma turma);
    List<correcoesUsuario> findByTurmaOrderByTeste(turma turma);
    List<correcoesUsuario> findByUsuarioAndTurma(usuario a, turma t);

    
}
