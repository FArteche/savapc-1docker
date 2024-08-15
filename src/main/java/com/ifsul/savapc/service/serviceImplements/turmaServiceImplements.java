package com.ifsul.savapc.service.serviceImplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.service.turmaService;
@Service
public class turmaServiceImplements implements turmaService{
    @Autowired
    turmaRepository turmaRepository;

    @Override
    public List<turma> findByVisibilidade(boolean visibilidade) {
        return turmaRepository.findByVisibilidade(visibilidade);
    }

    @Override
    public List<turma> findAll() {
        return turmaRepository.findAll();
    }

    @Override
    public turma findById(Integer id) {
        return turmaRepository.findById(id).get();
    }

    @Override
    public turma save(turma turma) {
        return turmaRepository.save(turma);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            if(turmaRepository.existsById(id)){
                turmaRepository.deleteById(id);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existsById(Integer usuarioId) {
        return turmaRepository.existsById(usuarioId);
    }
    
}
