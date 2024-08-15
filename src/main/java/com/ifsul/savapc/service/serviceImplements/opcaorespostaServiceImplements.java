package com.ifsul.savapc.service.serviceImplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.repository.opcaorespostaRepository;
import com.ifsul.savapc.service.opcaorespostaService;

public class opcaorespostaServiceImplements implements opcaorespostaService{
    @Autowired
    opcaorespostaRepository opcaorespostaRepository;

    @Override
    public List<opcaoresposta> findByVerdadeira(boolean verdadeira) {
        return opcaorespostaRepository.findByVerdadeira(verdadeira);
    }

    @Override
    public List<opcaoresposta> findOpcaoRespostaIdByPerguntasTesteAndVerdadeira(perguntaTeste p, boolean verdadeira) {
        return opcaorespostaRepository.findOpcaoRespostaIdByPerguntasTesteAndVerdadeira(p, verdadeira);
    }

    @Override
    public List<opcaoresposta> findAll() {
        return opcaorespostaRepository.findAll();
    }

    @Override
    public opcaoresposta findById(Integer id) {
        return opcaorespostaRepository.findById(id).get();
    }

    @Override
    public opcaoresposta save(opcaoresposta opcaoresposta) {
        return opcaorespostaRepository.save(opcaoresposta);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            if (opcaorespostaRepository.existsById(id)) {
                opcaorespostaRepository.deleteById(id);
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
        return opcaorespostaRepository.existsById(id);
    }
    
}
