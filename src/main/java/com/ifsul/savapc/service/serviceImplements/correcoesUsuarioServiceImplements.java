package com.ifsul.savapc.service.serviceImplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.correcoesUsuario;
import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;
import com.ifsul.savapc.repository.correcoesUsuarioRepository;
import com.ifsul.savapc.service.correcoesUsuarioService;

@Service
public class correcoesUsuarioServiceImplements implements correcoesUsuarioService{
    @Autowired
    correcoesUsuarioRepository correcoesUsuarioRepository;
    @Override
    public List<correcoesUsuario> findByUsuario(usuario a) {
        return correcoesUsuarioRepository.findByUsuario(a);
    }
    @Override
    public List<correcoesUsuario> findByUsuarioAndTurmaAndTesteAndPerguntaTeste(usuario a, turma t, teste test,
            perguntaTeste perguntaTeste) {
        return correcoesUsuarioRepository.findByUsuarioAndTurmaAndTesteAndPerguntaTeste(a, t, test, perguntaTeste);
    }
    @Override
    public List<correcoesUsuario> findByTurmaOrderByTeste(turma t) {
        return correcoesUsuarioRepository.findByTurmaOrderByTeste(t);
    }
    @Override
    public List<correcoesUsuario> findByTurmaOrderByUsuario(turma t) {
        return correcoesUsuarioRepository.findByTurmaOrderByUsuario(t);
    }
    @Override
    public List<correcoesUsuario> findByUsuarioAndTurma(usuario a, turma t) {
        return correcoesUsuarioRepository.findByUsuarioAndTurma(a, t);
    }
    @Override
    public List<correcoesUsuario> findAll() {
        return correcoesUsuarioRepository.findAll();
    }
    @Override
    public correcoesUsuario findById(Integer id) {
        return correcoesUsuarioRepository.findById(id).get();
    }
    @Override
    public correcoesUsuario save(correcoesUsuario correcao) {
        return correcoesUsuarioRepository.save(correcao);
    }
    @Override
    public boolean deleteById(Integer id) {
        try {
            if (correcoesUsuarioRepository.existsById(id)) {
                correcoesUsuarioRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean existsById(Integer id) {
        return correcoesUsuarioRepository.existsById(id);
    }
    
}
