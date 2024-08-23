package com.ifsul.savapc.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.model.perguntaTeste;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.repository.perguntaTesteRepository;
import com.ifsul.savapc.repository.testeRepository;

import jakarta.validation.Valid;

@Controller
public class perguntaTesteController {
    @Autowired
    perguntaTesteRepository perguntaTesteRepository;
    @Autowired
    testeRepository testeRepository;

    /*classPath utilizado para direcionar o upload das imagens*/
    private String classPathImg = "/usr/local/tomcat/webapps/savapc-1/WEB-INF/classes/static/images/";/*/opt/tomcat/webapps/savapc-1/WEB-INF/classes/static/images/*/

    /* Listar perguntas por teste (Prof)*/
    @GetMapping(value = "/auth/teste/perguntas/{id}")
    public ModelAndView getPerguntasPorTeste(@PathVariable("id") int testeId) {
        ModelAndView mv = new ModelAndView("perguntaTesteOnly");
        List<perguntaTeste> perguntas = new ArrayList<>();
        String testeNome = "";
        int IDteste = 0;
        if (testeRepository.findById(testeId) != null) {
            teste t = testeRepository.findById(testeId).get();
            testeNome = t.getNome();
            IDteste = t.getTesteId();
            perguntas = t.getPerguntasTeste();
        }
        mv.addObject("testeId", IDteste);
        mv.addObject("testeNome", testeNome);
        mv.addObject("perguntas", perguntas);
        return mv;
    }
    /* Salvar pergunta por teste  (Prof)*/
    @PostMapping("/auth/teste/savePergunta/{id}")
    // @Transactional
    public String savePerguntaTeste(@PathVariable("id") int testeId, @Valid perguntaTeste pergunta,
                               @RequestParam("file") MultipartFile img, RedirectAttributes attributes, BindingResult result) {
        teste t = testeRepository.findById(testeId).orElseThrow(null);
        List<perguntaTeste> lperg = t.getPerguntasTeste();
        try {
            if (!img.isEmpty()) {
                try {
                    byte[] bytes = img.getBytes();
                    Path caminho = Paths.get(classPathImg + img.getOriginalFilename());
                    Files.write(caminho, bytes);
                    pergunta.setImg(img.getOriginalFilename());
                } catch (MaxUploadSizeExceededException m) {
                    attributes.addFlashAttribute("Erro", "Arquivo é maior que o limite de 4MB");
                    return "redirect:/auth/teste/perguntas/{id}";
                }
            } else {
                pergunta.setImg(null);
            }
            perguntaTesteRepository.save(pergunta);
            lperg.add(pergunta);
            t.setPerguntasTeste(lperg);
            testeRepository.save(t);
            attributes.addFlashAttribute("sucesso", "Pergunta cadastrada");
            return "redirect:/auth/teste/perguntas/{id}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", e.toString());
            return "redirect:/auth/teste/perguntas/{id}";
        }
    }
    /* Deletar pergunta por id (Prof)*/
    @GetMapping(value = "/auth/deletepergunta/{testeId}/{perguntaId}")
    public String deletePergunta(@PathVariable("testeId") int testeId, @PathVariable("perguntaId") int perguntaId,
                                 RedirectAttributes attributes) {
        try {
            if (testeRepository.existsById(testeId)) { // Deletar da tabela perguntasteste
                if (perguntaTesteRepository.existsById(perguntaId)) {
                    teste t = testeRepository.findById(testeId).get();
                    List<perguntaTeste> lp = t.getPerguntasTeste();
                    for (int i = 0; i < lp.size(); i++) {
                        if (lp.get(i).getPerguntaTesteId() == perguntaId) {
                            lp.remove(i);
                        }
                    }
                    t.setPerguntasTeste(lp);
                    testeRepository.save(t);
                    attributes.addFlashAttribute("sucesso", "Pergunta deletada");
                    return "redirect:/auth/teste/perguntas/{testeId}";
                } else {
                    attributes.addFlashAttribute("erro", "Pergunta não encontrada");
                    return "redirect:/auth/teste/perguntas/{testeId}";
                }
            } else { // Deletar do repository
                perguntaTesteRepository.deleteById(perguntaId);
                attributes.addFlashAttribute("sucesso", "Pergunta deletada");
                return "redirect:/auth/teste/perguntas/{testeId}";
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "ID inexistente ou erro desconhecido");
            return "redirect:/auth/teste/perguntas/{testeId}";
        }
    }
    /*Atualizar pergunta de um teste (Prof)*/
    @PostMapping("/auth/updatepergunta/{id}/{testeId}")
    public String setPerguntaUpdate(@PathVariable("id") int perguntaId, @PathVariable("testeId") int testeId,
                                         @Valid perguntaTeste novaPergunta,
                                         RedirectAttributes redirectAttributes, BindingResult result, @RequestParam("file") MultipartFile img) {
        try {
            perguntaTeste perguntaExistente = perguntaTesteRepository.findById(perguntaId).orElseThrow(null);
            perguntaExistente.setDescricao(novaPergunta.getDescricao());
            perguntaExistente.setTitulo(novaPergunta.getTitulo());
            perguntaExistente.setPedido(novaPergunta.getPedido());
            if (img.isEmpty()) {
                novaPergunta.setImg(null);
            } else {
                try {
                    byte[] bytes = img.getBytes();
                    Path caminho = Paths.get(classPathImg + img.getOriginalFilename());
                    Files.write(caminho, bytes);
                    perguntaExistente.setImg(img.getOriginalFilename());

                } catch (MaxUploadSizeExceededException m) {
                    redirectAttributes.addFlashAttribute("Erro", "Arquivo é maior que o limite de 4MB");
                    return "redirect:/auth/teste/perguntas/{testeId}";
                }
            }
            perguntaTesteRepository.save(perguntaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "Pergunta Editada com sucesso");
            return "redirect:/auth/teste/perguntas/{testeId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/teste/perguntas/{testeId}";

        }
    }
    /* Listar perguntas por teste de turma (Prof)*/
    @GetMapping(value = "/auth/turma/{turmaId}/teste/perguntas/{id}")
    public ModelAndView getPerguntasPorTesteTurma(@PathVariable("id") int testeId, @PathVariable("turmaId") int turmaId) {
        ModelAndView mv = new ModelAndView("perguntaTeste");
        List<perguntaTeste> perguntas = new ArrayList<>();
        String testeNome = "";
        int IDteste = 0;
        if (testeRepository.findById(testeId) != null) {
            teste t = testeRepository.findById(testeId).get();
            testeNome = t.getNome();
            IDteste = t.getTesteId();
            perguntas = t.getPerguntasTeste();
        }
        mv.addObject("turmaId", turmaId);
        mv.addObject("testeId", IDteste);
        mv.addObject("testeNome", testeNome);
        mv.addObject("perguntas", perguntas);
        return mv;
    }
    /* Salvar pergunta por teste e turma  (Prof)*/
    @PostMapping("/auth/turma/{turmaId}/teste/savePergunta/{id}")
    // @Transactional
    public String savePerguntaTesteTurma(@PathVariable("id") int testeId, @Valid perguntaTeste pergunta,
            @RequestParam("file") MultipartFile img, RedirectAttributes attributes, BindingResult result) {
        teste t = testeRepository.findById(testeId).orElseThrow(null);
        List<perguntaTeste> lperg = t.getPerguntasTeste();
        try {
            if (!img.isEmpty()) {
                try {
                    byte[] bytes = img.getBytes();
                    Path caminho = Paths.get(classPathImg + img.getOriginalFilename());
                    Files.write(caminho, bytes);
                    pergunta.setImg(img.getOriginalFilename());
                } catch (MaxUploadSizeExceededException m) {
                    attributes.addFlashAttribute("Erro", "Arquivo é maior que o limite de 4MB");
                    return "redirect:/auth/turma/{turmaId}/teste/perguntas/{id}";
                }
            } else {
                pergunta.setImg(null);
            }
            perguntaTesteRepository.save(pergunta);
            lperg.add(pergunta);
            t.setPerguntasTeste(lperg);
            testeRepository.save(t);
            attributes.addFlashAttribute("sucesso", "Pergunta cadastrada");
            return "redirect:/auth/turma/{turmaId}/teste/perguntas/{id}";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", e.toString());
            return "redirect:/auth/turma/{turmaId}/teste/perguntas/{id}";
        }
    }
    /* Deletar pergunta por id em uma turma(Prof)*/
    @GetMapping(value = "/auth/turma/{turmaId}/deletepergunta/{testeId}/{perguntaId}")
    public String deletePerguntaTurma(@PathVariable("testeId") int testeId, @PathVariable("perguntaId") int perguntaId,
            RedirectAttributes attributes) {
        try {
            if (testeRepository.existsById(testeId)) { // Deletar da tabela perguntasteste
                if (perguntaTesteRepository.existsById(perguntaId)) {
                    teste t = testeRepository.findById(testeId).get();
                    List<perguntaTeste> lp = t.getPerguntasTeste();
                    for (int i = 0; i < lp.size(); i++) {
                        if (lp.get(i).getPerguntaTesteId() == perguntaId) {
                            lp.remove(i);
                        }
                    }
                    t.setPerguntasTeste(lp);
                    testeRepository.save(t);
                    attributes.addFlashAttribute("sucesso", "Pergunta deletada");
                    return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
                } else {
                    attributes.addFlashAttribute("erro", "Pergunta não encontrada");
                    return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
                }
            } else { // Deletar do repository
                perguntaTesteRepository.deleteById(perguntaId);
                attributes.addFlashAttribute("sucesso", "Pergunta deletada");
                return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "ID inexistente ou erro desconhecido");
            return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
        }
    }
    /*Atualizar pergunta de um teste em uma turma (Prof)*/
    @PostMapping("/auth/turma/{turmaId}/updatepergunta/{id}/{testeId}")
    public String setPerguntaUpdateTurma(@PathVariable("id") int perguntaId, @PathVariable("testeId") int testeId,
            @Valid perguntaTeste novaPergunta,
            RedirectAttributes redirectAttributes, BindingResult result, @RequestParam("file") MultipartFile img) {
        try {
            perguntaTeste perguntaExistente = perguntaTesteRepository.findById(perguntaId).orElseThrow(null);
            perguntaExistente.setDescricao(novaPergunta.getDescricao());
            perguntaExistente.setTitulo(novaPergunta.getTitulo());
            perguntaExistente.setPedido(novaPergunta.getPedido());
            if (img.isEmpty()) {
                novaPergunta.setImg(null);
            } else {
                try {
                    byte[] bytes = img.getBytes();
                    Path caminho = Paths.get(classPathImg + img.getOriginalFilename());
                    Files.write(caminho, bytes);
                    perguntaExistente.setImg(img.getOriginalFilename());
                } catch (MaxUploadSizeExceededException m) {
                    redirectAttributes.addFlashAttribute("Erro", "Arquivo é maior que o limite de 4MB");
                    return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
                }
            }
            perguntaTesteRepository.save(perguntaExistente);
            redirectAttributes.addFlashAttribute("sucesso", "Pergunta Editada com sucesso");
            return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possivel editar" + e);
            return "redirect:/auth/turma/{turmaId}/teste/perguntas/{testeId}";
        }
    }
}
