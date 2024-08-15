package com.ifsul.savapc.controller;

import java.util.List;

import com.ifsul.savapc.model.perguntaquestionario;
import com.ifsul.savapc.repository.perguntaQuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.repository.opcaorespostaRepository;
import com.ifsul.savapc.repository.perguntaTesteRepository;

import jakarta.validation.Valid;

@Controller
public class opcaorespostaController {
    @Autowired
    opcaorespostaRepository opcaorespostaRepository;
    @Autowired
    perguntaTesteRepository perguntaTesteRepository;
    @Autowired
    perguntaQuestionarioRepository perguntaquestionarioRepository;

    /* Lista as opções resposta de uma perguntaTeste de uma turma (Prof)*/
    @GetMapping(value = "/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{id}/opcoesresposta")
    public ModelAndView getOpcoesRespostaPergunta(@PathVariable("id") int id,
                                                  @PathVariable("turmaId") int turmaId,
                                                  @PathVariable("testeId") int testeId) {
        ModelAndView mv = new ModelAndView("opcaoresposta");
        perguntaTeste p = perguntaTesteRepository.findById(id).orElseThrow(null);
        List<opcaoresposta> opR = p.getOpcoesResposta();
        mv.addObject("opcoesrespostas", opR);
        mv.addObject("perguntaId", p.getPerguntaTesteId());
        mv.addObject("turmaId", turmaId);
        mv.addObject("testeId", testeId);
        return mv;
    }

    /* Salvar uma opção resposta em uma perguntaTeste de uma Turma (Prof)*/
    @PostMapping("/auth/turma/{turmaId}/teste/{testeId}/perguntaTeste/{id}/saveopcaoresposta")
    public String saveOpcaoResposta(@PathVariable("id") int id, @Valid opcaoresposta or, BindingResult result,
                                    RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + or.toString());
            return "redirect:/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{id}/opcoesresposta";
        }
        opcaorespostaRepository.save(or);
        perguntaTeste p = perguntaTesteRepository.findById(id).orElseThrow(null);
        List<opcaoresposta> listOR = p.getOpcoesResposta();
        listOR.add(or);
        p.setOpcoesResposta(listOR);
        perguntaTesteRepository.save(p);
        attributes.addFlashAttribute("sucesso", "Opção resposta cadastrada!");
        return "redirect:/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{id}/opcoesresposta";
    }

    /* Deletar opçãoResposta de uma perguntaTeste de uma turma (Prof)*/
    @GetMapping(value = "/auth/turma/{turmaId}/teste/{testeId}/perguntaTeste/{perguntaTesteId}/deleteopcaoresposta/{id}")
    public String deleteOpcaoResposta(@PathVariable("id") int id, @PathVariable("perguntaTesteId") int perguntaTesteId,
                                      RedirectAttributes attributes) {
        try {
            opcaorespostaRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Opção Resposta deletada!");
            return "redirect:/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{perguntaTesteId}/opcoesresposta";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Não foi possivel deletar. Erro:" + e.toString());
            return "redirect:/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{perguntaTesteId}/opcoesresposta";
        }
    }

    /* Atualiza uma opção resposta de uma perguntaTeste de uma turma (Prof)*/
    @PostMapping("/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{perguntaId}/updateopcaoresposta/{id}")
    public String setOpcaoRespostaUpdate(@PathVariable("id") int opcaoRespostaId,
                                         @PathVariable("perguntaId") int perguntaTesteId,
                                         @Valid opcaoresposta novaOpcaoresposta, RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            opcaoresposta opcaorespostaExistente = opcaorespostaRepository.findById(opcaoRespostaId).orElseThrow(null);
            opcaorespostaExistente.setTipo(novaOpcaoresposta.getTipo());
            opcaorespostaExistente.setDescricao(novaOpcaoresposta.getDescricao());
            opcaorespostaExistente.setVerdadeira(novaOpcaoresposta.isVerdadeira());
            opcaorespostaRepository.save(opcaorespostaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "OpcaoResposta editada com sucesso!");

            return "redirect:/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{perguntaId}/opcoesresposta";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar, Erro:" + e);
            return "redirect:/auth/t/{turmaId}/t/{testeId}/perguntaTeste/{perguntaId}/opcoesresposta";

        }
    }

    /* Lista as opções resposta de uma perguntaTeste Inicial(Prof)*/
    @GetMapping(value = "/auth/perguntaTeste/opcoesresposta/{id}")
    public ModelAndView getOpcoesRespostaPergunta(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("opcaorespostaTesteOnly");
        perguntaTeste p = perguntaTesteRepository.findById(id).orElseThrow(null);
        List<opcaoresposta> opR = p.getOpcoesResposta();
        mv.addObject("opcoesrespostas", opR);
        mv.addObject("perguntaId", p.getPerguntaTesteId());

        return mv;
    }

    /* Salvar uma opção resposta em uma perguntaTeste Inicial(Prof)*/
    @PostMapping("/auth/perguntaTeste/opcoesresposta/saveopcaoresposta/{id}")
    public String saveOpcaoRespostaOnly(@PathVariable("id") int id, @Valid opcaoresposta or, BindingResult result,
                                    RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + or.toString());
            return "redirect:/auth/perguntaTeste/opcoesresposta/{id}";
        }
        opcaorespostaRepository.save(or);
        perguntaTeste p = perguntaTesteRepository.findById(id).orElseThrow(null);
        List<opcaoresposta> listOR = p.getOpcoesResposta();
        listOR.add(or);
        p.setOpcoesResposta(listOR);
        perguntaTesteRepository.save(p);
        attributes.addFlashAttribute("sucesso", "Opção resposta cadastrada!");
        return "redirect:/auth/perguntaTeste/opcoesresposta/{id}";
    }

    /* Deletar opçãoResposta de uma perguntaTeste Inicial (Prof)*/
    @GetMapping(value = "/auth/perguntaTeste/opcoesresposta/deleteopcaoresposta/{id}/{perguntaTesteId}")
    public String deleteOpcaoRespostaOnly(@PathVariable("id") int id, @PathVariable("perguntaTesteId") int perguntaTesteId,
                                      RedirectAttributes attributes) {
        try {
            opcaorespostaRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Opção Resposta deletada!");
            return "redirect:/auth/perguntaTeste/opcoesresposta/{perguntaTesteId}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Não foi possivel deletar. Erro:" + e.toString());
            return "redirect:/auth/perguntaTeste/opcoesresposta/{perguntaTesteId}";
        }
    }

    /* Atualiza uma opção resposta de uma perguntaTeste Inicial (Prof)*/
    @PostMapping("/auth/perguntaTeste/opcoesresposta/updateopcaoresposta/{id}/{perguntaTesteId}")
    public String setOpcaoRespostaUpdateOnly(@PathVariable("id") int opcaoRespostaId,
                                         @PathVariable("perguntaTesteId") int perguntaTesteId,
                                         @Valid opcaoresposta novaOpcaoresposta, RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            opcaoresposta opcaorespostaExistente = opcaorespostaRepository.findById(opcaoRespostaId).orElseThrow(null);
            opcaorespostaExistente.setTipo(novaOpcaoresposta.getTipo());
            opcaorespostaExistente.setDescricao(novaOpcaoresposta.getDescricao());
            opcaorespostaExistente.setVerdadeira(novaOpcaoresposta.isVerdadeira());
            opcaorespostaRepository.save(opcaorespostaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "OpcaoResposta editada com sucesso!");

            return "redirect:/auth/perguntaTeste/opcoesresposta/{perguntaTesteId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar, Erro:" + e);
            return "redirect:/auth/perguntaTeste/opcoesresposta/{perguntaTesteId}";

        }
    }


    /* Listar opçãoResposta da pergunta Questionario (Prof)*/
    @GetMapping(value = "/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{id}/opcoesresposta")
    public ModelAndView getOpcoesRespostaPerguntaQuestionario(@PathVariable("id") int perguntaquestionarioId,
                                                              @PathVariable("turmaId") int turmaId,
                                                              @PathVariable("questionarioId") int questionarioId) {
        ModelAndView mv = new ModelAndView("opcaorespostaPergQuest");
        perguntaquestionario p = perguntaquestionarioRepository.findById(perguntaquestionarioId).get();
        List<opcaoresposta> opR = p.getOpcoesResposta();
        mv.addObject("turmaId", turmaId);
        mv.addObject("questionarioId", questionarioId);
        mv.addObject("opcoesrespostas", opR);
        mv.addObject("perguntaQuestionarioId", p.getPerguntaQuestionarioId());
        mv.addObject("perguntaQuestionarioNome", p.getTitulo());
        return mv;
    }

    /* Listar opçãoResposta da pergunta Questionario Inicial (Prof)*/
    @GetMapping(value = "/auth/pergquest/opcoesresposta/{id}")
    public ModelAndView getOpcoesRespostaPerguntaQuestionario(@PathVariable("id") int perguntaquestionarioId) {
        ModelAndView mv = new ModelAndView("opcaorespostaPergQuestInicial");
        perguntaquestionario p = perguntaquestionarioRepository.findById(perguntaquestionarioId).orElseThrow(null);
        List<opcaoresposta> opR = p.getOpcoesResposta();
        mv.addObject("opcoesrespostas", opR);
        mv.addObject("perguntaQuestionarioId", p.getPerguntaQuestionarioId());
        mv.addObject("perguntaQuestionarioNome", p.getTitulo());
        return mv;
    }

    /*Salvar opçãoResposta em uma pergunta Questionario (Prof)*/
    @PostMapping("/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{id}/saveopcaoresposta")
    public String saveOpcaoResposta_PerguntaQuestionario(@PathVariable("id") int id, @Valid opcaoresposta or,
                                                         BindingResult result,
                                                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + or.toString());
            return "redirect:/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{id}/opcoesresposta";
        }
        opcaorespostaRepository.save(or);
        perguntaquestionario p = perguntaquestionarioRepository.findById(id).get();
        List<opcaoresposta> listOR = p.getOpcoesResposta();
        listOR.add(or);
        p.setOpcoesResposta(listOR);
        perguntaquestionarioRepository.save(p);
        attributes.addFlashAttribute("sucesso", "Opcao Resposta cadastrada");
        return "redirect:/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{id}/opcoesresposta";
    }

    /*Salvar opçãoResposta em uma pergunta Questionario Inicial (Prof)*/
    @PostMapping("/auth/pergquest/saveopcaoresposta/{id}")
    public String saveOpcaoResposta_PerguntaQuestionarioInicial(@PathVariable("id") int id, @Valid opcaoresposta or,
                                                                BindingResult result,
                                                                RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + or.toString());
            return "redirect:/auth/pergquest/opcoesresposta/{id}";
        }
        opcaorespostaRepository.save(or);
        perguntaquestionario p = perguntaquestionarioRepository.findById(id).get();
        List<opcaoresposta> listOR = p.getOpcoesResposta();
        listOR.add(or);
        p.setOpcoesResposta(listOR);
        perguntaquestionarioRepository.save(p);
        attributes.addFlashAttribute("sucesso", "Opcao Resposta cadastrada");
        return "redirect:/auth/pergquest/opcoesresposta/{id}";
    }

    /*Deleta opção resposta de uma pergunta Questionario (Prof)*/
    @GetMapping(value = "/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{perguntaQuestId}/deleteopcaoresposta/{id}")
    public String deleteOpcaoResposta_PerguntaQuestionario(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            opcaorespostaRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Opção-Resposta deletada");
            return "redirect:/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{perguntaQuestId}/opcoesresposta";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "ID inexistente ou erro desconhecido");
            return "redirect:/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{perguntaQuestId}/opcoesresposta";
        }
    }

    /*Deleta opção resposta de uma pergunta Questionario Inicial(Prof)*/
    @GetMapping(value = "/auth/pergquest/{perguntaQuestId}/deleteopcaoresposta/{id}")
    public String deleteOpcaoResposta_PerguntaQuestionarioInicial(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            opcaorespostaRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Opção-Resposta deletada");
            return "redirect:/auth/pergquest/opcoesresposta/{perguntaQuestId}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "ID inexistente ou erro desconhecido");
            return "redirect:/auth/pergquest/opcoesresposta/{perguntaQuestId}";
        }
    }

    /* Atualiza uma opção resposta de uma perguntaTeste */
    @PostMapping("/auth/t/{turmaId}/quest/{questionarioId}/pergQuest/{perguntaQuestId}/updateopcaoresposta/{id}")
    public String setUpdateOpcaoResposta_PerguntaQuestionario(@PathVariable("id") int opcaoRespostaId,
                                                              opcaoresposta novaOpcaoresposta, RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            opcaoresposta opcaorespostaExistente = opcaorespostaRepository.findById(opcaoRespostaId).orElseThrow(null);
            opcaorespostaExistente.setTipo(novaOpcaoresposta.getTipo());
            opcaorespostaExistente.setDescricao(novaOpcaoresposta.getDescricao());
            opcaorespostaExistente.setVerdadeira(novaOpcaoresposta.isVerdadeira());
            opcaorespostaRepository.save(opcaorespostaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "OpcaoResposta Editada com sucesso");

            return "redirect:/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{perguntaQuestId}/opcoesresposta";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/t/{turmaId}/quest/{questionarioId}/pergquest/{perguntaQuestId}/opcoesresposta";

        }
    }

    /* Atualiza uma opção resposta de uma perguntaTesteInicial */
    @PostMapping("/auth/pergQuest/{perguntaQuestId}/updateopcaoresposta/{id}")
    public String setUpdateOpcaoResposta_PerguntaQuestionarioInicial(@PathVariable("id") int opcaoRespostaId,
                                                                     opcaoresposta novaOpcaoresposta, RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            opcaoresposta opcaorespostaExistente = opcaorespostaRepository.findById(opcaoRespostaId).orElseThrow(null);
            opcaorespostaExistente.setTipo(novaOpcaoresposta.getTipo());
            opcaorespostaExistente.setDescricao(novaOpcaoresposta.getDescricao());
            opcaorespostaExistente.setVerdadeira(novaOpcaoresposta.isVerdadeira());
            opcaorespostaRepository.save(opcaorespostaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "OpcaoResposta Editada com sucesso");

            return "redirect:/auth/pergquest/opcoesresposta/{perguntaQuestId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/pergquest/opcoesresposta/{perguntaQuestId}";

        }
    }
}
