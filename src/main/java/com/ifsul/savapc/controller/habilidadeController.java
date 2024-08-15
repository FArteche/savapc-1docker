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

import com.ifsul.savapc.model.habForm;
import com.ifsul.savapc.model.habilidade;
import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.repository.habilidadeRepository;
import com.ifsul.savapc.repository.perguntaTesteRepository;

import jakarta.validation.Valid;

@Controller
public class habilidadeController {
    @Autowired
    habilidadeRepository habilidadeRepository;
    @Autowired
    perguntaTesteRepository perguntaTesteRepository;

    /* Listar Habilidades (Prof)*/
    @GetMapping(value = "/auth/habilidades")
    public ModelAndView listarHabilidades() {
        ModelAndView mv = new ModelAndView("habilidade");
        try {
            List<habilidade> list = habilidadeRepository.findAll();
            mv.addObject("habilidades", list);
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }
    /* Salvar uma nova habilidade (Prof)*/
    @PostMapping("/auth/habilidade/saveHabilidade")
    public String saveHabilidade(@Valid habilidade h, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + h.toString());
            return "redirect:/auth/habilidades";
        }
        try {
            habilidadeRepository.save(h);
            attributes.addFlashAttribute("sucesso", "Habilidade cadastrada");
            return "redirect:/auth/habilidades";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro desconhecido!" + e.toString());
            return "redirect:/auth/habilidades";// TODO: handle exception
        }
    }
    /* Deletar habilidade (Prof)*/
    @GetMapping(value = "/auth/habilidade/deletehabilidade/{id}")
    public String deleteHabilidade(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            habilidadeRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Habilidade deletada");
            return "redirect:/auth/habilidades";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "id inexistente ou erro desconhecido");
            return "redirect:/auth/habilidades";
        }
    }
    /* Atualizar uma habilidade (Prof)*/
    @PostMapping("/auth/habilidade/updatehabilidade/{id}")
    public String setHabilidadeUpdate(@PathVariable("id") int habilidadeId, @Valid habilidade novahabilidade,
                                      RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            habilidade habilidadeExistente = habilidadeRepository.findById(habilidadeId).orElseThrow(null);
            habilidadeExistente.setNome(novahabilidade.getNome());
            habilidadeRepository.save(habilidadeExistente);
            redirectAttributes.addFlashAttribute("sucesso", "Habilidade Editada com sucesso");
            return "redirect:/auth/habilidades";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/habilidades";

        }
    }
    /* Ver habilidades de uma pergunta em uma turma(Prof)*/
    @GetMapping("/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades")
    public ModelAndView getHabilidadesPergunta(@PathVariable("perguntaId") int perguntaId,
                                               @PathVariable("testeId") int testeId,
                                               @PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("habilidadePerguntaTeste");
        try {
            perguntaTeste p = perguntaTesteRepository.findById(perguntaId).get();
            List<habilidade> habilidadesAll = habilidadeRepository.findAll();
            List<habilidade> habilidadePergunta = habilidadeRepository.findByPerguntasTeste(p);
            mv.addObject("habilidades", habilidadesAll);
            mv.addObject("perguntaId", perguntaId);
            mv.addObject("testeId", testeId);
            mv.addObject("turmaId", turmaId);
            mv.addObject("habilidadesPergunta", habilidadePergunta);
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }
    /* Salvar uma nova habilidade dentro de uma pergunta (Prof)*/
    @PostMapping("/auth/t/{turmaId}/t/{testeId}/p/{perguntaId}/habilidade/saveHabilidade")
    public String saveHabilidade_Pergunta(@Valid habilidade h, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + h.toString());
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        }
        try {
            habilidadeRepository.save(h);
            attributes.addFlashAttribute("sucesso", "Habilidade cadastrada");
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro desconhecido!" + e.toString());
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";// TODO: handle exception
        }
    }
    /* Deletar Habilidade dentro de uma pergunta de uma turma (Prof)*/
    @GetMapping(value = "/auth/t/{turmaId}/t/{testeId}/pergunta/{perguntaId}/deletehabilidade/{id}")
    public String deleteHabilidade_Pergunta(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            habilidadeRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Habilidade deletada");
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "id inexistente ou erro desconhecido");
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        }
    }
    /* Atualizar uma habilidade de dentro de uma pergunta (Prof)*/
    @PostMapping("/auth/t/{turmaId}/t/{testeId}/pergunta/{perguntaId}/updatehabilidade/{id}")
    public String setHabilidadeUpdate_Pergunta(@PathVariable("id") int habilidadeId, @Valid habilidade novahabilidade,
            RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            habilidade habilidadeExistente = habilidadeRepository.findById(habilidadeId).orElseThrow(null);
            habilidadeExistente.setNome(novahabilidade.getNome());
            habilidadeRepository.save(habilidadeExistente);
            redirectAttributes.addFlashAttribute("sucesso", "Habilidade Editada com sucesso");
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";

        }
    }
    /* Ver habilidades de uma perguntaTesteOnly(Prof)*/
    @GetMapping("/auth/pergunta/habilidades/{perguntaId}/{testeId}")
    public ModelAndView getHabilidadesPerguntaTeste(@PathVariable("perguntaId") int perguntaId,
                                               @PathVariable("testeId") int testeId) {
        ModelAndView mv = new ModelAndView("habilidadePerguntaTesteOnly");
        try {
            perguntaTeste p = perguntaTesteRepository.findById(perguntaId).get();
            List<habilidade> habilidadesAll = habilidadeRepository.findAll();
            List<habilidade> habilidadePergunta = habilidadeRepository.findByPerguntasTeste(p);
            mv.addObject("habilidades", habilidadesAll);
            mv.addObject("perguntaId", perguntaId);
            mv.addObject("testeId", testeId);
            mv.addObject("habilidadesPergunta", habilidadePergunta);
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }
    /* Salvar uma nova habilidade dentro de uma perguntaTesteOnly (Prof)*/
    @PostMapping("/auth/pergunta/habilidades/{perguntaId}/{testeId}/saveHabilidade")
    public String saveHabilidade_PerguntaTesteOnly(@Valid habilidade h, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + h.toString());
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        }
        try {
            habilidadeRepository.save(h);
            attributes.addFlashAttribute("sucesso", "Habilidade cadastrada");
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro desconhecido!" + e.toString());
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";// TODO: handle exception
        }
    }
    /* Deletar Habilidade dentro de uma pergunta de uma turma (Prof)*/
    @GetMapping(value = "/auth/t/{testeId}/pergunta/{perguntaId}/deletehabilidade/{id}")
    public String deleteHabilidade_PerguntaTesteOnly(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            habilidadeRepository.deleteById(id);
            attributes.addFlashAttribute("sucesso", "Habilidade deletada");
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "id inexistente ou erro desconhecido");
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        }
    }
    /* Atualizar uma habilidade de dentro de uma perguntaTesteOnly (Prof)*/
    @PostMapping("/auth/t/{testeId}/pergunta/{perguntaId}/updatehabilidade/{id}")
    public String setHabilidadeUpdate_PerguntaOnly(@PathVariable("id") int habilidadeId, @Valid habilidade novahabilidade,
            RedirectAttributes redirectAttributes, BindingResult result) {
        try {
            habilidade habilidadeExistente = habilidadeRepository.findById(habilidadeId).orElseThrow(null);
            habilidadeExistente.setNome(novahabilidade.getNome());
            habilidadeRepository.save(habilidadeExistente);
            redirectAttributes.addFlashAttribute("sucesso", "Habilidade Editada com sucesso");
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";

        }
    }
    /* Incluir habilidades em uma pergunta (Prof)*/
    @PostMapping("/auth/t/{turmaId}/t/{testeId}/pergunta/{perguntaId}/habilidades")
    public String setHabilidadePergunta(@PathVariable("perguntaId") int perguntaId,
            @PathVariable("testeId") int testeId, habForm lhab, RedirectAttributes attributes) {
        try {
            perguntaTeste p = perguntaTesteRepository.findById(perguntaId).get();
            List<habilidade> habpergunta = new ArrayList<>();
            p.getHabilidades().clear();
            if (lhab.getHabilidades() != null) {
                for (habilidade h : lhab.getHabilidades()) {
                    if (h.getHabilidadeId() > 0) {
                        habpergunta.add(habilidadeRepository.findById(h.getHabilidadeId()).get());
                    }
                }
                p.setHabilidades(habpergunta);
            }
            perguntaTesteRepository.save(p);
            attributes.addFlashAttribute("sucesso", "habilidades do teste atualizado!");
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Nao foi possivel alterar as habilidades" + e.toString());
            return "redirect:/auth/turma/{turmaId}/teste/{testeId}/pergunta/{perguntaId}/habilidades";
        }
    }
    /* Incluir habilidades em uma pergunta (TesteOnly) (Prof)*/
    @PostMapping("/auth/pergunta/habilidades/{perguntaId}/{testeId}/habilidades")
    public String setHabilidadePerguntaOnly(@PathVariable("perguntaId") int perguntaId,
                                        @PathVariable("testeId") int testeId, habForm lhab, RedirectAttributes attributes) {
        try {
            perguntaTeste p = perguntaTesteRepository.findById(perguntaId).get();
            List<habilidade> habpergunta = new ArrayList<>();
            p.getHabilidades().clear();
            if (lhab.getHabilidades() != null) {
                for (habilidade h : lhab.getHabilidades()) {
                    if (h.getHabilidadeId() > 0) {
                        habpergunta.add(habilidadeRepository.findById(h.getHabilidadeId()).get());
                    }
                }
                p.setHabilidades(habpergunta);
            }
            perguntaTesteRepository.save(p);
            attributes.addFlashAttribute("sucesso", "habilidades do teste atualizado!");
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Nao foi possivel alterar as habilidades" + e.toString());
            return "redirect:/auth/pergunta/habilidades/{perguntaId}/{testeId}";
        }
    }
}
