package com.ifsul.savapc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.model.questionarioinicial;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.turmaForm;
import com.ifsul.savapc.model.turmaQuestsForm;
import com.ifsul.savapc.model.turmaTestesForm;
import com.ifsul.savapc.model.usuario;
import com.ifsul.savapc.repository.questionarioinicialRepository;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.repository.usuarioRepository;
import com.ifsul.savapc.service.questionarioinicialService;
import com.ifsul.savapc.service.testeService;

import jakarta.validation.Valid;

@Controller
public class turmaController {
    @Autowired
    turmaRepository turmaRepository;
    @Autowired
    usuarioRepository usuarioRepository;
    @Autowired
    testeRepository testeRepository;
    @Autowired
    testeService testeService;
    @Autowired
    questionarioinicialRepository questionarioinicialRepository;
    @Autowired
    questionarioinicialService questionarioinicialService;

    /* Lista todas as turmas (Prof)*/
    @GetMapping(value = "/auth/turmas")
    public ModelAndView listarTurmas() {
        ModelAndView mv = new ModelAndView("turma");
        List<turma> list = turmaRepository.findAll();
        mv.addObject("turmas", list);
        List<teste> testes = testeRepository.findAll();
        mv.addObject("testes", testes);
        return mv;
    }
    /* Salva uma nova turma (Prof)*/
    @PostMapping("/auth/turma/saveTurma")
    public String saveTurma(@Valid turmaForm t, BindingResult result, RedirectAttributes attributes) {

        List<teste> testes = new ArrayList<>();
        testes.clear();
        turma turma = new turma();

        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + t.toString());
            return "redirect:/auth/turma/saveTurma";
        }
        try {
            if (t.getTestes() != null) {
                if (t.getTestes().size() > 0) {
                    for (teste test : t.getTestes()) {
                        if (testeRepository.existsById(test.getTesteId())) {
                            teste te = testeRepository.findById(test.getTesteId()).orElseThrow(null);
                            System.out.println("\n TESTE:" + te.getNome());
                            testes.add(te);
                        }
                    }
                    turma.setNome(t.getNome());
                    turma.setTestes(testes);
                    turma.setVisibilidade(t.isVisibilidade());

                }
            } else {

                turma.setNome(t.getNome());
                turma.setVisibilidade(t.isVisibilidade());

            }
            turmaRepository.save(turma);
            attributes.addFlashAttribute("sucesso", "Turma Cadastrada com Sucesso" + t.getNome());
            return "redirect:/auth/turmas";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Não foi possível cadastrar a turma" + e.toString());
            return "redirect:/auth/turmas";
        }
    }
    /* Delete uma turma por id (Prof)*/
    @GetMapping(value = "/auth/turma/deleteturma/{id}")
    public String deleteTurma(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            turmaRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Turma deletada");
            return "redirect:/auth/turmas";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "ID inexistente ou erro desconhecido");
            return "redirect:/auth/turmas";
        }
    }
    /* Atualizar turma (Prof) */
    @PostMapping("/auth/turma/updateturma/{id}")
    public String setTurmaUpdate(@PathVariable("id") int turmaId, @Valid turma novaturma,
            RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            turma turmaExistente = turmaRepository.findById(turmaId).get();
            turmaExistente.setNome(novaturma.getNome());
            turmaExistente.setVisibilidade(novaturma.isVisibilidade());
            turmaRepository.save(turmaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "Turma editada com sucesso");
            return "redirect:/auth/turmas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/turmas";

        }
    }
    /* Listar turmas que tenham a visibilidade true (Aluno ou Prof)*/
    @GetMapping(value = "/aluno/turmas")
    public ModelAndView turmasAluno() {
        ModelAndView mv = new ModelAndView("turmasAluno");
        List<turma> lturma = turmaRepository.findByVisibilidade(true);
        mv.addObject("turmas", lturma);
        return mv;
    }
    /* Inscrive um aluno na turma por id (Aluno ou Prof)*/
    @PostMapping("/aluno/turma/cadAlunoTurma/{id}")
    public String cadAlunoTurma(@PathVariable("id") int turmaId, @RequestParam("username") String username,
                                RedirectAttributes redirectAttributes) {
        if (username.equalsIgnoreCase("")) {
            redirectAttributes.addFlashAttribute("erro", "identificador não encontrado");
            return "redirect:/aluno/turmas";
        } else {
            turma t = turmaRepository.findById(turmaId).orElseThrow(null);
            List<usuario> listUsuarios = t.getUsuarios();

            if (usuarioRepository.findByUsername(username) == null) {
                redirectAttributes.addFlashAttribute("erro", "usuario não encontrado");
                return "redirect:/aluno/turmas";
            }
            usuario usu = usuarioRepository.findByUsername(username);
            if (listUsuarios.contains(usu)) {
                redirectAttributes.addFlashAttribute("erro", "usuario já inscrito");
                return "redirect:/aluno/turmas";
            }
            t.getUsuarios().add(usu);
            turmaRepository.save(t);
            redirectAttributes.addFlashAttribute("sucesso", "Inscrição realizada com sucesso");
            return "redirect:/aluno/turmas";
        }
    }
    /* Exibir interior da turma (Aluno ou Prof)*/
    @GetMapping("/aluno/turma/{id}")
    public ModelAndView insideTurma(@PathVariable("id") int turmaId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("insideTurma");
        if (turmaRepository.findById(turmaId) != null) {
            turma t = turmaRepository.findById(turmaId).get();
            try {
                testeService.atualizarVisibilidades(); /* Teste de disponibilidade */
                questionarioinicialService.atualizarVisibilidade();
                List<teste> tests = t.getTestes();
                List<teste> testes = new ArrayList<>();
                for (teste test : tests) {
                    if (test.getVisibilidade()) {
                        testes.add(test);
                    }
                }
                List<questionarioinicial> questionarios = t.getQuestionarios();
                List<questionarioinicial> quest = new ArrayList<>();
                for (questionarioinicial q : questionarios) {
                    if (q.isVisibilidade()) {
                        quest.add(q);
                    }
                }
                mv.addObject("testes", testes);
                mv.addObject("turma", t);
                mv.addObject("questionarios", quest);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("erro", "Erro na busca dos testes ou questionarios da turma");
            }

        } else {
            redirectAttributes.addFlashAttribute("erro", "Turma não encontrada");

        }
        return mv;
    }
}
