package com.ifsul.savapc.service.serviceImplements;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.regQuestionarios;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;
import com.ifsul.savapc.repository.opcaorespostaRepository;
import com.ifsul.savapc.repository.questionarioinicialRepository;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.service.regQuestionariosService;


@Service
public class regQuestionariosServiceImplements implements regQuestionariosService{

    @Autowired
    regQuestionariosRepository regQuestionariosRepository;
    @Autowired
    opcaorespostaRepository opcaorespostaRepository;
    @Autowired
    questionarioinicialRepository questionarioinicialRepository;

    @Override
    public List<regQuestionarios> findByTurma(turma turma) {
        return regQuestionariosRepository.findByTurma(turma);
    }

    @Override
    public List<regQuestionarios> findByUsuarioAndTurma(usuario u, turma turma) {
        return regQuestionariosRepository.findByUsuarioAndTurma(u, turma);
    }

    @Override
    public List<regQuestionarios> findAll() {
        return regQuestionariosRepository.findAll();
    }

    @Override
    public regQuestionarios findById(Integer id) {
        return regQuestionariosRepository.findById(id).get();
    }

    @Override
    public regQuestionarios save(regQuestionarios regQuestionarios) {
        return regQuestionariosRepository.save(regQuestionarios);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            if (regQuestionariosRepository.existsById(id)) {
                regQuestionariosRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existsById(Integer Id) {
        return regQuestionariosRepository.existsById(Id);
    }
    
}
