package com.ifsul.savapc.service.serviceImplements;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.service.testeService;

@Service
public class testeServiceImplements implements testeService{
    @Autowired
    testeRepository testeRepository;

    @Override
    public List<teste> findByTurmas(turma turma) {
        return testeRepository.findByTurmas(turma);
    }

    @Override
    public void atualizarVisibilidades() {
        List<teste> allTests = testeRepository.findAll();
        LocalDate firstDate = LocalDate.now();
        for (teste test : allTests) {
            LocalDate secondDate = test.getDisponibilidade();
           boolean a =  secondDate.isAfter(firstDate);
           if(a==false){
            test.setVisibilidade(false);
            testeRepository.save(test);
           }
        }
    }

    @Override
    public List<teste> findAll() {
        return testeRepository.findAll();
    }

    @Override
    public teste findById(Integer id) {
        return testeRepository.findById(id).get();
    }

    @Override
    public teste save(teste teste) {
        return testeRepository.save(teste);
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            if (testeRepository.existsById(id)){
                testeRepository.deleteById(id);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existsById(Integer testeId) {
        return testeRepository.existsById(testeId);
    }

    
    
}
