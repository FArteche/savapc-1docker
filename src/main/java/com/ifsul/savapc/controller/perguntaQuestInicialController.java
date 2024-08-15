package com.ifsul.savapc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.perguntaquestionario;
import com.ifsul.savapc.model.questionarioinicial;
import com.ifsul.savapc.repository.opcaorespostaRepository;
import com.ifsul.savapc.repository.perguntaQuestionarioRepository;
import com.ifsul.savapc.repository.questionarioinicialRepository;

import jakarta.validation.Valid;

@Controller
public class perguntaQuestInicialController {
    @Autowired
    perguntaQuestionarioRepository perguntaquestionarioRepository;
    @Autowired
    questionarioinicialRepository questionarioRepository;
    @Autowired
    opcaorespostaRepository opcaorespostaRepository;


    /*Listar perguntas de um questionario (Prof)*/
    @GetMapping("/auth/pergquest/{id}")
    public ModelAndView getPerguntasQuest(@PathVariable("id") int questionarioId) {
        ModelAndView mv = new ModelAndView("perguntaquestionarioinicial");
        questionarioinicial qi = new questionarioinicial();
        try {
            if (questionarioRepository.existsById(questionarioId)) {
                qi = questionarioRepository.findById(questionarioId).get();
                mv.addObject("questionarioId", questionarioId);
                List<perguntaquestionario> perguntas = qi.getPerguntasQuestionario();
                mv.addObject("perguntas", perguntas);
                return mv;
            } else {
                return mv;
            }

        } catch (Exception e) {
            return mv;
        }
    }

    /* Salvar nova pergunta em questionario (Prof)*/
    @PostMapping("/auth/pergquest/saveperguntaquestionario/{id}")
    public String setSavePerguntaQuestionario(@PathVariable("id") int questionarioId, @Valid perguntaquestionario pq,
            RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Verifique os campos obrigatórios" + result.toString());
            return "redirect:/auth/pergquest/saveperguntaquestionario/{id}" ;
        }
        try {
            questionarioinicial qi = new questionarioinicial();
            List<perguntaquestionario> perguntas = new ArrayList<>();
            if (questionarioRepository.existsById(questionarioId)) {
                qi = questionarioRepository.findById(questionarioId).get();
                perguntas.addAll(qi.getPerguntasQuestionario());
                perguntas.add(pq);
                perguntaquestionarioRepository.save(pq);
                qi.setPerguntasQuestionario(perguntas);
                questionarioRepository.save(qi);
            }
            redirectAttributes.addFlashAttribute("sucesso", "Pergunta de Questionario Salva!");
            return "redirect:/auth/pergquest/{id}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Ocorreu um erro:" + e.toString());
            return "redirect:/auth/pergquest/saveperguntaquestionario/{id}";
        }
    }

    /* Deletar pergunta de questionario (Prof)*/
    @GetMapping("/auth/pergquest/{questionarioId}/deleteperguntaquest/{id}")
    public String deletePerguntaQuest(@PathVariable("questionarioId") int questionarioId,
            @PathVariable("id") int perguntaQuestId, RedirectAttributes redirectAttributes) {
        try {
            perguntaquestionarioRepository.deleteById(perguntaQuestId);
            redirectAttributes.addFlashAttribute("sucesso", "Pergunta excluida com sucesso!");
            return "redirect:/auth/pergquest/{questionarioId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel excluir pergunta!" + e.toString());
            return "redirect:/auth/pergquest/{questionarioId}";
        }
    }

    /* Atualizar pergunta de questionario (Prof)*/
    @PostMapping("/auth/pergquest/{questionarioId}/updateperguntaquest/{id}")
    public String setUpdatePerguntaQuest(@PathVariable("questionarioId") int questionarioId,
            @PathVariable("id") int perguntaQuestId,
            @Valid perguntaquestionario novaPergunta, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "confira os campos obrigatórios " + result.toString());
            return "redirect:/auth/pergquest/{id}";
        }
        try {
            if (perguntaquestionarioRepository.existsById(perguntaQuestId)) {
                perguntaquestionario perguntaExistente = perguntaquestionarioRepository.findById(perguntaQuestId).get();
                perguntaExistente.setDescricao(novaPergunta.getDescricao());
                perguntaExistente.setTipo(novaPergunta.getTipo());
                perguntaExistente.setTitulo(novaPergunta.getTitulo());
                perguntaquestionarioRepository.save(perguntaExistente);
                redirectAttributes.addFlashAttribute("sucesso", "Pergunta Alterada com sucesso!");
                return "redirect:/auth/pergquest/{questionarioId}";
            } else {
                redirectAttributes.addFlashAttribute("erro", "Questionario ou pergunta não encontrada");
                return "redirect:/auth/pergquest/{questionarioId}";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Ocorreu um erro!: " + e.toString());
            return "redirect:/auth/pergquest/{questionarioId}";
        }
    }
    /*Listar perguntas de um questionario de uma turma(Prof)*/
    @GetMapping("/auth/turma/{turmaId}/quest/{id}/perguntasquest")
    public ModelAndView getPerguntasQuestTurma(@PathVariable("id") int questionarioId,
                                               @PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("perguntaquestionario");
        questionarioinicial qi = new questionarioinicial();
        try {
            if (questionarioRepository.existsById(questionarioId)) {
                qi = questionarioRepository.findById(questionarioId).get();
                mv.addObject("questionarioId", questionarioId);
                List<perguntaquestionario> perguntas = qi.getPerguntasQuestionario();
                mv.addObject("perguntas", perguntas);
                mv.addObject("turmaId",turmaId);
                return mv;
            } else {
                return mv;
            }

        } catch (Exception e) {
            return mv;
        }
    }
    /* Salvar nova pergunta em questionario em uma turma(Prof)*/
    @PostMapping("/auth/turma/{turmaId}/pergquest/saveperguntaquestionario/{id}")
    public String setSavePerguntaQuestionarioTurma(@PathVariable("id") int questionarioId,
                                                   @PathVariable("turmaId") int turmaId, @Valid perguntaquestionario pq,
                                              RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "Verifique os campos obrigatórios" + result.toString());
            return "redirect:/auth/turma/{turmaId}/quest/{id}/perguntasquest" ;
        }
        try {
            questionarioinicial qi = new questionarioinicial();
            List<perguntaquestionario> perguntas = new ArrayList<>();
            if (questionarioRepository.existsById(questionarioId)) {
                qi = questionarioRepository.findById(questionarioId).get();
                perguntas.addAll(qi.getPerguntasQuestionario());
                perguntas.add(pq);
                perguntaquestionarioRepository.save(pq);
                qi.setPerguntasQuestionario(perguntas);
                questionarioRepository.save(qi);
            }
            redirectAttributes.addFlashAttribute("sucesso", "Pergunta de Questionario Salva!");
            return "redirect:/auth/turma/{turmaId}/quest/{id}/perguntasquest";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Ocorreu um erro:" + e.toString());
            return "redirect:/auth/turma/{turmaId}/quest/{id}/perguntasquest";
        }
    }

    /* Deletar pergunta de questionario (Prof)*/
    @GetMapping("/auth/turma/{turmaId}/quest/{questionarioId}/pergquest/{id}/deleteperguntaquest")
    public String deletePerguntaQuest(@PathVariable("questionarioId") int questionarioId,
                                      @PathVariable("id") int perguntaQuestId,
                                      @PathVariable("turmaId") int turmaId, RedirectAttributes redirectAttributes) {
        try {
            perguntaquestionarioRepository.deleteById(perguntaQuestId);
            redirectAttributes.addFlashAttribute("sucesso", "Pergunta excluida com sucesso!");
            return "redirect:/auth/turma/{turmaId}/quest/{questionarioId}/perguntasquest";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel excluir pergunta!" + e.toString());
            return "redirect:/auth/turma/{turmaId}/quest/{questionarioId}/perguntasquest";
        }
    }

    /* Atualizar pergunta de questionario em uma turma(Prof)*/
    @PostMapping("/auth/turma/{turmaId}/quest/{questionarioId}/pergquest/{id}/updateperguntaquest")
    public String setUpdatePerguntaQuest(@PathVariable("questionarioId") int questionarioId,
                                         @PathVariable("id") int perguntaQuestId,
                                         @PathVariable("turmaId") int turmaId,@Valid perguntaquestionario novaPergunta, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("erro", "confira os campos obrigatórios " + result.toString());
            return "redirect:/auth/turma/{turmaId}/quest/{questionarioId}/perguntasquest";
        }
        try {
            if (perguntaquestionarioRepository.existsById(perguntaQuestId)) {
                perguntaquestionario perguntaExistente = perguntaquestionarioRepository.findById(perguntaQuestId).get();
                perguntaExistente.setDescricao(novaPergunta.getDescricao());
                perguntaExistente.setTipo(novaPergunta.getTipo());
                perguntaExistente.setTitulo(novaPergunta.getTitulo());
                perguntaquestionarioRepository.save(perguntaExistente);
                redirectAttributes.addFlashAttribute("sucesso", "Pergunta Alterada com sucesso!");
                return "redirect:/auth/turma/{turmaId}/quest/{questionarioId}/perguntasquest";
            } else {
                redirectAttributes.addFlashAttribute("erro", "Questionario ou pergunta não encontrada");
                return "redirect:/auth/turma/{turmaId}/quest/{questionarioId}/perguntasquest";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Ocorreu um erro!: " + e.toString());
            return "redirect:/auth/turma/{turmaId}/quest/{questionarioId}/perguntasquest";
        }
    }



}
