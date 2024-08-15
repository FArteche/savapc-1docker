package com.ifsul.savapc.controller;


import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.model.correcoesUsuario;
import com.ifsul.savapc.model.notas;

import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.correcoesUsuarioRepository;
import com.ifsul.savapc.repository.notasRepository;
import com.ifsul.savapc.repository.questionarioinicialRepository;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.repository.regTestesRepository;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.repository.usuarioRepository;
import com.ifsul.savapc.service.correcoesUsuarioService;
import com.ifsul.savapc.service.regTestesService;
import com.ifsul.savapc.service.serviceImplements.notasServiceImplements;
import com.ifsul.savapc.service.serviceImplements.regTestesServiceImplements;
import com.ifsul.savapc.service.serviceImplements.relatoriosServiceImplements;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class relatoriosController {
    @Autowired
    regTestesServiceImplements regTestesServiceImplements;
    @Autowired
    regTestesService regTestesService;
    @Autowired
    turmaRepository turmaRepository;
    @Autowired
    notasRepository notasRepository;
    @Autowired
    notasServiceImplements notasServiceImplements;
    @Autowired
    relatoriosServiceImplements relatoriosServiceImplements;
    @Autowired
    correcoesUsuarioService correcoesUsuarioService;

    /* Exibir relatorio de Perguntas de uma turma (Prof)*/
    @GetMapping("/auth/turma/relatorioPergunta/{turmaId}")
    @ResponseBody
    public ModelAndView getRelatorioPerguntas(@PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("relatorioComPerguntas");
          regTestesService.fazerCorrecaoTestes();
        turma t = turmaRepository.findById(turmaId).get();
        List<correcoesUsuario> correcao = correcoesUsuarioService.findByTurmaOrderByUsuario(t);
        System.out.println("\n \n "+correcao.toString());
        mv.addObject("correcao", correcao);
        mv.addObject("turmaNome", t.getNome());
        return mv;
    }

    /* Atualiza relatório de habilidades e exibe novamente (Prof)*/
    @GetMapping("/auth/turma/relatorio/atualizarRelatorio/{turmaId}")
    public String setUpdateRelatorioHabilidades(@PathVariable("turmaId") int turmaId, RedirectAttributes redirectAttributes) {
        turma turma = turmaRepository.findById(turmaId).get();
        try {
            regTestesServiceImplements.fazerCorrecaoTestes();
            notasServiceImplements.SalvarNotas(turma);
            return "redirect:/auth/turma/relatorioTeste/{turmaId}";
        } catch (Exception e) {
            return "redirect:/auth/turma/relatorioTeste/{turmaId}";
        }

    }

    /* Exibe relatorio de habilidades de uma turma (Prof)*/
    @GetMapping("/auth/turma/relatorioTeste/{turmaId}")
    @ResponseBody
    public ModelAndView getRelatorioHabilidades(@PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("relatorioTestePorHabilidade");
        turma turma = turmaRepository.findById(turmaId).get();
        try {
            regTestesServiceImplements.fazerCorrecaoTestes();
            List<notas> contabilizacaoListTurma = notasRepository.findByTurma(turma);
            mv.addObject("contabilizacoes", contabilizacaoListTurma);
            mv.addObject("turma", turma);
            mv.addObject("turmaNome", turma.getNome());
            mv.addObject("turmaId", turma.getTurmaId());
            return mv;
        } catch (Exception e) {
            System.out.println("\n \n " + e + "\n \n ");
            return mv;
        }
    }
    /* Exporta Relatório de Habilidades (Prof)*/
    @GetMapping("/auth/turma/relatorio/exportarRelatorioTeste/{turmaId}/download")
    public void exportRelatorioHabilidades(HttpServletResponse response, @PathVariable("turmaId") int turmaId) throws IOException {
        turma turma = turmaRepository.findById(turmaId).get();
        relatoriosServiceImplements.exportarRelatorioTestes(turma, response);
    }

    /*Exporta relatório com perguntas e respostas (Prof)*/
    @GetMapping("/auth/turma/relatorio/exportarRelatorioTestePerguntas/{turmaId}/download")
    public void exportRelatorioTestePerguntas(HttpServletResponse response, @PathVariable("turmaId") int turmaId) throws IOException {
        turma turma = turmaRepository.findById(turmaId).get();
        relatoriosServiceImplements.exportarRelatorioTestesComPerguntas(turma, response);
    }
    /*Exporta relatório com questionarios e respostas (Prof)*/
    @GetMapping("/auth/turma/relatorio/exportarRelatorioQuestionario/{turmaId}/download")
    public void exportRelatorioQuestionario(HttpServletResponse response, @PathVariable("turmaId") int turmaId) throws IOException {
        turma turma = turmaRepository.findById(turmaId).get();
        relatoriosServiceImplements.exportarRelatorioQuestionario(turma, response);
    }

    /* Atualiza nota de um aluno (Prof)*/
    @PostMapping("/auth/turma/relatorio/updatenota/{turmaId}/{notaId}")
    public String setUpdateNota(@PathVariable("turmaId") int turmaId, @PathVariable("notaId") int notaId,
            @RequestParam(value = "elimin", required = false) String desclassificado, @RequestParam("nota") double nota,
            @RequestParam("recomendacao") String recomendacao, RedirectAttributes redirectAttributes) {
        try {
            turma turma = turmaRepository.findById(turmaId).get();
            if (notasRepository.findById(notaId) != null) {
                notas novaNota = notasRepository.findById(notaId).get();
                novaNota.setNotaProjetoFinal(nota);
                novaNota.setRecomendacao(recomendacao);
                if (desclassificado != null) {
                    novaNota.setDesclassificado(true);
                } else {
                    novaNota.setDesclassificado(false);
                }
                notasRepository.save(novaNota);
                notasServiceImplements.SalvarNotas(turma);
                redirectAttributes.addFlashAttribute("sucesso", "Nota editada com sucesso!");
                return "redirect:/auth/turma/relatorioTeste/{turmaId}";
            }
            redirectAttributes.addFlashAttribute("erro", "Nota nao encontrada!");
            return "redirect:/auth/turma/relatorioTeste/{turmaId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Nota não editada com sucesso!");
            return "redirect:/auth/turma/relatorioTeste/{turmaId}";
        }
    }

}
