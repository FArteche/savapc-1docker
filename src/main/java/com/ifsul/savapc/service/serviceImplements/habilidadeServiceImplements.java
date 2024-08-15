package com.ifsul.savapc.service.serviceImplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.habilidade;
import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.repository.habilidadeRepository;
import com.ifsul.savapc.service.habilidadeService;
@Service
public class habilidadeServiceImplements implements habilidadeService{
    @Autowired
    habilidadeRepository habilidadeRepository;
    
    @Override
    public List<habilidade> findByPerguntasTeste(perguntaTeste pergunta) {
        return habilidadeRepository.findByPerguntasTeste(pergunta);
    }

    @Override
    public List<habilidade> findAll() {
        return habilidadeRepository.findAll();
    }

    @Override
    public habilidade findById(Integer id) {
        return habilidadeRepository.findById(id).get();
    }

    @Override
    public habilidade save(habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            if (habilidadeRepository.existsById(id)) {
                habilidadeRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existsById(Integer habilidadeId) {
        return habilidadeRepository.existsById(habilidadeId);
    }
    
}
