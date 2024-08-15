package com.ifsul.savapc.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;
import com.ifsul.savapc.web.dto.UsuarioRegistrationDto;

public interface usuarioService extends UserDetailsService{
    List<usuario> findAll();
    usuario findById(Integer id);
    usuario save(UsuarioRegistrationDto registrationDto);
    usuario save(usuario usuarioexistente);
    boolean deleteById(Integer id);
    boolean existsById(Integer usuarioId);

    List<usuario> findByIdentificadorLike(String identificador);
    List<usuario> findByTurmasIn(List<turma> turmas);
    usuario findByUsername(String username);
    List<usuario> findByNomeLike(String nome);
    List<usuario> findByTipoLike(String tipo);
    usuario trocarSenha(usuario usuario,String novaSenha);
    
}

