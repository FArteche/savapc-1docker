package com.ifsul.savapc.controller;


import java.util.List;

import com.ifsul.savapc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.repository.perguntaQuestionarioRepository;
import com.ifsul.savapc.repository.questionarioinicialRepository;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.repository.usuarioRepository;
import com.ifsul.savapc.service.questionarioinicialService;
import com.ifsul.savapc.service.serviceImplements.questionarioinicialServiceImplements;

import jakarta.validation.Valid;

@Controller
public class questionarioController {
    @Autowired
    questionarioinicialRepository questionarioinicialRepository;
    @Autowired
    questionarioinicialService questionarioinicialService;
    @Autowired
    turmaRepository turmaRepository;
    @Autowired
    questionarioinicialServiceImplements questImplements;

    /* Listar Questionarios (Prof)*/
    @GetMapping(value = "/auth/questionarios")
    public ModelAndView listarQuestionarios() {
        ModelAndView mv = new ModelAndView("questionarioinicial");
        try {
            questionarioinicialService.atualizarVisibilidade();
            List<questionarioinicial> list = questionarioinicialRepository.findAll();
            mv.addObject("questionarios", list);
        } catch (Exception e) {
            System.err.println(e);
        }
        return mv;
    }

    /* Salvar questionario (Prof)*/
    @PostMapping("/auth/quest/saveQuestInicial")
    public String setSaveQuestInicial(@Valid questionarioinicial qi, RedirectAttributes redirectAttributes,
            BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "confira os campos obrigatórios" + result.toString());
            return "redirect:/auth/questionarios";
        }
        try {
            questionarioinicialRepository.save(qi);
            redirectAttributes.addFlashAttribute("sucesso", "Questionario salvo");
            return "redirect:/auth/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "não foi possivel salvar:" + e.toString());
            return "redirect:/auth/questionarios";
        }

    }
    /* Deletar questionario (Prof)*/
    @GetMapping("/auth/quest/deletequestionario/{id}")
    public String deleteQuestionario(@PathVariable("id") int questionarioId, RedirectAttributes redirectAttributes) {
        try {
            questionarioinicialRepository.deleteById(questionarioId);
            redirectAttributes.addFlashAttribute("sucesso", "Questionario deletado com sucesso!");
            return "redirect:/auth/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Questionario não foi deletado com sucesso");
            return "redirect:/auth/questionarios";
        }
    }
    /* Atualizar questionario (Prof)*/
    @PostMapping("/auth/quest/updatequestionario/{id}")
    public String setUpdateQuestionario(@PathVariable("id") int questionarioId, @Valid questionarioinicial novoQ,
            RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Confira os campos");
            return "redirect:/auth/questionarios/{id}";
        }
        try {
            questionarioinicial qi = questionarioinicialRepository.findById(questionarioId).get();
            qi.setDisponibilidade(novoQ.getDisponibilidade());
            qi.setNome(novoQ.getNome());
            qi.setVisibilidade(novoQ.isVisibilidade());
            questionarioinicialRepository.save(qi);
            redirectAttributes.addFlashAttribute("sucesso", "Questionario atualizado com sucesso!");
            return "redirect:/auth/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Questionario não foi atualizado com sucesso!");
            return "redirect:/auth/questionarios";
        }
    }
    /* Listar os questionarios da turma (Prof)*/
    @GetMapping("/auth/turma/{turmaId}/questionarios")
    public ModelAndView getQuestionariosTurma(@PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("questionariosTurma");
        try {
            questionarioinicialService.atualizarVisibilidade();
            List<questionarioinicial> quests = questionarioinicialRepository.findAll();
            List<questionarioinicial> questsTurma = questionarioinicialRepository
                    .findByTurmas(turmaRepository.findById(turmaId).get());
            mv.addObject("questsAll", quests);
            mv.addObject("questsTurma", questsTurma);
            mv.addObject("turmaId", turmaId);
        } catch (Exception e) {
            return mv;
        }
        return mv;
    }
    /* Salvar questionario em uma turma (Prof)*/
    @PostMapping("/auth/t/{turmaId}/quest/saveQuestInicial")
    public String setSaveQuestInicial_turma(@Valid questionarioinicial qi, RedirectAttributes redirectAttributes,
                                            BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "confira os campos obrigatórios" + result.toString());
            return "redirect:/auth/turma/{turmaId}/questionarios";
        }
        try {
            questionarioinicialRepository.save(qi);
            redirectAttributes.addFlashAttribute("sucesso", "Questionario salvo");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "não foi possivel salvar:" + e.toString());
            return "redirect:/auth/turma/{turmaId}/questionarios";
        }
    }
    /* Deletar questionario em uma turma (Prof)*/
    @GetMapping("/auth/t/{turmaId}/quest/{id}/deletequestionario")
    public String deleteQuestionarioTurma(@PathVariable("id") int questionarioId, RedirectAttributes redirectAttributes) {
        try {
            questionarioinicialRepository.deleteById(questionarioId);
            redirectAttributes.addFlashAttribute("sucesso", "Questionario deletado com sucesso!");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Questionario não foi deletado com sucesso");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        }
    }
    /* Atualizar questionario em uma turma (Prof)*/
    @PostMapping("/auth/t/{turmaId}/quest/{id}/updatequestionario")
    public String setUpdateQuestionario_turma(@PathVariable("id") int questionarioId, questionarioinicial novoQ,
            RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Confira os campos");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        }
        try {
            questionarioinicial qi = questionarioinicialRepository.findById(questionarioId).get();
            qi.setDisponibilidade(novoQ.getDisponibilidade());
            qi.setNome(novoQ.getNome());
            qi.setVisibilidade(novoQ.isVisibilidade());
            questionarioinicialRepository.save(qi);
            redirectAttributes.addFlashAttribute("sucesso", "Questionario atualizado com sucesso!");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Questionario não foi atualizado com sucesso!");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        }
    }
    /* Inclui os questionarios selecionados na turma (Prof)*/
    @PostMapping("/auth/turma/{turmaId}/questionarios")
    public String setQuestionariosTurma(@PathVariable("turmaId") int turmaId, turmaQuestsForm questsTurma,
                                        RedirectAttributes redirectAttributes) {
        try {
            turma turma = turmaRepository.findById(turmaId).get();
            turma.getQuestionarios().clear();
            if (questsTurma.getQuestionarios() != null && questsTurma.getQuestionarios().size() > 0) {
                for (questionarioinicial quest : questsTurma.getQuestionarios()) {
                    if (quest.getQuestionarioId() > 0) {

                        turma.getQuestionarios()
                                .add(questionarioinicialRepository.findById(quest.getQuestionarioId()).get());
                    }
                }
            }
            turmaRepository.save(turma);
            redirectAttributes.addFlashAttribute("sucesso", "Questionarios da turma alterados!");
            return "redirect:/auth/turma/{turmaId}/questionarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro",
                    "Ocorreu um erro e os questionarios não foram alterados: " + e.toString());
            return "redirect:/auth/turma/{turmaId}/questionarios";
        }
    }
    /* Aplicar questionario (Aluno ou Prof)*/
    @GetMapping(value = "/aluno/aplicacaoquest/{turmaId}/{questionarioId}")
    public ModelAndView getQuestionarioAplication(@PathVariable("questionarioId") int qId,
                                                  @PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("aplicacaoQuestionario");
        questionarioinicial q = questionarioinicialRepository.findById(qId).get();
        turma turma = turmaRepository.findById(turmaId).get();
        List<perguntaquestionario> perguntas = q.getPerguntasQuestionario();
        mv.addObject("perguntas", perguntas);
        mv.addObject("nomeQuest", q.getNome());
        mv.addObject("questionarioId", q.getQuestionarioId());
        mv.addObject("turmaId", turma.getTurmaId());
        return mv;
    }
    /* Aplicar questionario (Aluno ou Prof)*/
    @PostMapping(value = "/aluno/aplicacaoquest/{turmaId}/{questionarioId}")
    public String setQuestionarioAplication(@PathVariable("questionarioId") int questionarioId,
            @PathVariable("turmaId") int turmaId, @ModelAttribute perguntasQuestForm lresp,
            RedirectAttributes attributes) {
        try {
            turma turma = turmaRepository.findById(turmaId).get();
            questionarioinicial quest = questionarioinicialRepository.findById(questionarioId).orElseThrow(null);
            for (perguntaquestionario perg : lresp.getPerguntas()) {
                 if(perg.isObrigatorio()){
                    if((perg.getTipo().equalsIgnoreCase("multipla escolha") && perg.getOpRespostaId()==null) || (perg.getTipo().equalsIgnoreCase("dissertativa") && perg.getResposta().isEmpty())){
                        attributes.addFlashAttribute("erro", "Confira e responda os campos obrigatórios!");
                        return "redirect:/aluno/aplicacaoquest/{turmaId}/{questionarioId}";
                    }
                    
                }
            }
            if(questImplements.salvarRespostasQuest(turma, quest, lresp)){
                attributes.addFlashAttribute("sucesso", "Questionario respondido com sucesso!");
                return "redirect:/aluno/turma/{turmaId}";
            } else {
                attributes.addFlashAttribute("erro", "A resposta não pôde ser salva!");
                return "redirect:/aluno/turma/{turmaId}";
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Questionario não foi respondido com sucesso" + e);
            return "redirect:/aluno/turma/{turmaId}";
        }
    }

}
