package com.ifsul.savapc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ifsul.savapc.model.regQuestionarios;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.perguntaQuestionarioRepository;
import com.ifsul.savapc.repository.questionarioinicialRepository;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.repository.respostaQuestionarioRepository;
import com.ifsul.savapc.repository.turmaRepository;

@Controller
public class regQuestionariosController {
    @Autowired
    regQuestionariosRepository regQuestionariosRepository;
    @Autowired
    turmaRepository turmaRepository;


    /* Listar registros de questionarios de uma turma */
    @GetMapping("/auth/turma/relatorioQuestionario/{turmaId}")
    public ModelAndView getRelatorioQuestionario(@PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("registroquestionario");
        try {
            turma turma = turmaRepository.findById(turmaId).get();
            List<regQuestionarios> regquest = regQuestionariosRepository.findByTurma(turma);
            mv.addObject("turma", turma);
            if (regquest.size() > 0 && regquest.get(0).getQuestionario() != null
                    && regquest.get(0).getUsuario() != null) {
                mv.addObject("registros", regquest);
            }
        } catch (Exception e) {
            return mv;
        }
        return mv;
    }

    

}
