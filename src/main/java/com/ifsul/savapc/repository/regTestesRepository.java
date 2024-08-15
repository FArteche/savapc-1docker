package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.regTestes;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;

public interface regTestesRepository extends JpaRepository<regTestes,Integer>{
     
     List<regTestes> findByTurma(turma turma);
     List<regTestes> findByTesteAndTurmaAndUsuario(teste t, turma tu, usuario u);
     List<regTestes> findRegTestesByTurmaAndUsuario(turma t,usuario a);
     List<regTestes> findByTurmaAndTeste(turma t,teste a);
}
