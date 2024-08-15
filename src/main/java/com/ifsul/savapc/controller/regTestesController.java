package com.ifsul.savapc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ifsul.savapc.model.regTestes;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.regTestesRepository;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.service.regTestesService;
import com.ifsul.savapc.service.testeService;
import com.ifsul.savapc.service.turmaService;
import com.ifsul.savapc.service.usuarioService;



@Controller
public class regTestesController {
    @Autowired
    regTestesService regTestesService;
    @Autowired
    turmaService turmaService;
    @Autowired
    testeService testeService;
    @GetMapping("/auth/turma/teste/tentativas/{turmaId}/{testeId}")
    public ModelAndView tentativas(@PathVariable("turmaId") int turmaId, @PathVariable("testeId") int testeId){
        ModelAndView mv = new ModelAndView("regTestes");
        if(!testeService.existsById(testeId) || !turmaService.existsById(turmaId) ){
            return mv;
        }
        turma turma = turmaService.findById(turmaId);
        teste teste = testeService.findById(testeId);
        List<regTestes> tentativas = regTestesService.findByTurmaAndTeste(turma, teste);
        mv.addObject("tentativas", tentativas);
        mv.addObject("turmaId", turma.getTurmaId());
        return mv;
    }
    @GetMapping("/auth/turma/{turmaId}/deletarRegTeste/{testeId}/{usuarioId}")
    public String deletarTentativa(@PathVariable("testeId") int testeId, @PathVariable("usuarioId") int usuarioId, @PathVariable("turmaId") int turmaId){
        regTestesService.deletarTentativa(testeId, turmaId,usuarioId);
        return "redirect:/auth/turma/{turmaId}/testes";
    }
}
