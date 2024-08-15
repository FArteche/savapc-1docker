package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;

public interface usuarioRepository extends JpaRepository<usuario,Integer>{
    List<usuario> findByIdentificadorLike(String matricula);
    List<usuario> findByTurmasIn(List<turma> turmas);
    usuario findByUsername(String username);
    List<usuario> findByNomeLike(String nome);
    List<usuario> findByTipoLike(String tipo);
}
