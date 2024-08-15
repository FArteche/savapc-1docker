package com.ifsul.savapc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ifsul.savapc.model.regQuestionarios;
import com.ifsul.savapc.model.regTestes;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.repository.regTestesRepository;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.service.usuarioService;

import jakarta.validation.Valid;

@Controller
public class usuarioController {
    @Autowired
    usuarioService usuarioService;
    @Autowired
    turmaRepository turmaRepository;
    @Autowired
    testeRepository testeRepository;
    @Autowired
    regTestesRepository regTestesRepository;
    @Autowired
    regQuestionariosRepository regQuestionariosRepository;
    // /*Redireciona o usuário logado */
    // @GetMapping("/aluno/rec")
    // String LoginSucefull(){
    //     if(){
    //         return "redirect:/aluno/turmas";
    //     }else{
    //         return "redirect:/auth/painelgeral";
    //     }
    // }
    /* LISTAR ALUNOS (Prof) */
    @GetMapping(value = "/auth/alunos")
    public ModelAndView listarAlunos() {
        ModelAndView mv = new ModelAndView("aluno");
        List<usuario> list = usuarioService.findByTipoLike("aluno");
        mv.addObject("alunos", list);
        return mv;
    }
    /*Listar Professores (Prof)*/
    @GetMapping(value = "/auth/professores")
    public ModelAndView listarProfessores() {
        ModelAndView mv = new ModelAndView("professor");
        List<usuario> list = usuarioService.findByTipoLike("prof");
        mv.addObject("profs", list);
        return mv;
    }
    /* CADASTRAR USUÁRIO (Prof)*/
    @PostMapping("/auth/usuario/saveUsuario")
    public String saveAluno(@Valid usuario a, BindingResult result, RedirectAttributes attributes, @RequestParam("file") MultipartFile img) {
        if (result.hasErrors()) {
            if (a.getTipo().equalsIgnoreCase("aluno")) {
                attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + a.toString());
                return "redirect:/auth/alunos";
            } else {
                attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + a.toString());
                return "redirect:/auth/professores";
            }
        }
        if (!img.isEmpty()) {
            try {
                byte[] bytes = img.getBytes();
                Path caminho = Paths.get("./src/main/resources/static/images/" + a.getUsername());//img.getOriginalFilename()
                Files.write(caminho, bytes);
                a.setImg(img.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            a.setImg("não selecionado");
        }
        if(a.getNome().isEmpty() || a.getIdentificador().isEmpty() || a.getEmail().isEmpty() || a.getUsername().isEmpty() || a.getPassword().isEmpty()){
            attributes.addFlashAttribute("erro", "Verifique os campos obrigatórios:" + a.toString());
            return "redirect:/auth/alunos";
        }
        a.setPassword(new BCryptPasswordEncoder().encode(a.getPassword()));
        usuarioService.save(a);
        if (a.getTipo().equalsIgnoreCase("aluno")) {
            attributes.addFlashAttribute("sucesso", "Aluno cadastrado");
            return "redirect:/auth/alunos";
        } else {
            attributes.addFlashAttribute("sucesso", "Professor cadastrado");
            return "redirect:/auth/professores";
        }
    }
    /* DELETAR UNIDADE (Prof) */
    @GetMapping(value = "/auth/usuario/deleteusuario/{id}")
    public String deleteUsuario(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {       
            usuario a = usuarioService.findById(id);
            usuarioService.deleteById(a.getUsuarioId());
            if (a.getTipo().equalsIgnoreCase("aluno")) {
                attributes.addFlashAttribute("sucesso", "Usuário deletado");
                return "redirect:/auth/alunos";
            } else {
                attributes.addFlashAttribute("sucesso", "Usuário deletado");
                return "redirect:/auth/professores";
            }
        } catch (Exception e) {
            usuario a = usuarioService.findById(id);
            if (a.getTipo().equalsIgnoreCase("aluno")) {
                attributes.addFlashAttribute("erro", e.toString());
                return "redirect:/auth/alunos";
            } else {
                attributes.addFlashAttribute("erro", e.toString());
                return "redirect:/auth/professores";
            }

        }
    }
    /* Atualizar usuario (Prof)*/
    @PostMapping("/auth/usuario/updateusuario/{id}")
    public String setUpdateAluno(@PathVariable("id") int usuarioId, @Valid usuario novo,
            RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile img) {
        
        if (usuarioService.existsById(usuarioId)) {
            if (!img.isEmpty()) {
                try {
                    byte[] bytes = img.getBytes();
                    Path caminho = Paths.get("./src/main/resources/static/images/" + novo.getUsername());//img.getOriginalFilename()
                    Files.write(caminho, bytes);
                    novo.setImg(img.getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                novo.setImg("não selecionado");
            }
            usuario Existente = usuarioService.findById(usuarioId);
            Existente.setNome(novo.getNome());
            Existente.setTipo(novo.getTipo());
            Existente.setIdentificador(novo.getIdentificador());
            Existente.setEmail(novo.getEmail());
            Existente.setDataNascimento(novo.getDataNascimento());
            Existente.setTelefone(novo.getTelefone());
            Existente.setUsername(novo.getUsername());
            Existente.setPassword(new BCryptPasswordEncoder().encode(novo.getPassword()));
            usuarioService.save(Existente);
            if (Existente.getTipo().equalsIgnoreCase("aluno")) {
                redirectAttributes.addFlashAttribute("sucesso", "Aluno editado com sucesso");
                return "redirect:/auth/alunos";
            } else {
                redirectAttributes.addFlashAttribute("sucesso", "Professor editado com sucesso");
                return "redirect:/auth/professores";
            }
        }
        redirectAttributes.addFlashAttribute("erro", "Não foi possivel salvar");
        if (novo.getTipo().equalsIgnoreCase("aluno")) {
            return "redirect:/auth/alunos";
        } else {
            return "redirect:/auth/professores";
        }

    }
    /* Exibir painel do professor (Prof)*/
    @GetMapping("/auth/painelgeral")
    public ModelAndView getPainelGeral() {
        ModelAndView mv = new ModelAndView("PainelGeral");
        try {
            if (turmaRepository.count() > 0) {
                List<turma> turmas = turmaRepository.findAll();
                mv.addObject("turmas", turmas);
            }
            if (testeRepository.count() > 0) {
                List<teste> testes = testeRepository.findAll();
                mv.addObject("testes", testes);
            }
            List<regTestes> regTestList = new ArrayList<>();
            long count = regTestesRepository.count();
            if(count > 0){
                if(count>=5){
                    regTestList =  regTestesRepository.findAll(PageRequest.of(0, 5, Sort.by("regTestesId").descending())).toList();
                }else{
                    regTestList =   regTestesRepository.findAll(PageRequest.of(0, (int) count, Sort.by("regTestesId").descending())).toList();
                }
                mv.addObject("regTestes", regTestList);
            }
            List<regQuestionarios> regQuestList = new ArrayList<>();
            long count2 = regQuestionariosRepository.count();
            if(count2 > 0){
                if(count2>=5){
                    regQuestList =  regQuestionariosRepository.findAll(PageRequest.of(0, 5, Sort.by("regQuestionarioId").descending())).toList();
                }else{
                    regQuestList =   regQuestionariosRepository.findAll(PageRequest.of(0, (int) count2, Sort.by("regTestesId").descending())).toList();
                }
                mv.addObject("regQuestionarios", regQuestList);
            }
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }
    @PostMapping(value="/index/login/esqueceuSenha")
    public ModelAndView verificacaoEsqueceuSenha(@RequestParam("identificador") String iden, @RequestParam("email") String email, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("login");
        try {
            if(usuarioService.findByIdentificadorLike(iden.trim()).isEmpty()){
                redirectAttributes.addFlashAttribute("erro","Usuario não encontrado pelo identificador");
                mv.addObject("identInvalida", true);
                return mv;
            }
            usuario u = usuarioService.findByIdentificadorLike(iden.trim()).get(0);
            if(u.getEmail().equals(email)){
                redirectAttributes.addFlashAttribute("esqueceuSenha", "Identidade confirmada, redefina sua senha no botão abaixo!");
                mv.addObject("esqueceuSenha", true);
                mv.addObject("identificador",u.getIdentificador());
                 return mv;
            }else{
                redirectAttributes.addFlashAttribute("erro", "Os dados não correspondem a um usuário");
                mv.addObject("identInvalida", true);
                return mv;
            }
        } catch (Exception e) {
            mv.addObject("erro", e.toString());
            return mv;
        }
    }
    @PostMapping("/index/login/trocarSenha")
    public String trocarSenha(@RequestParam("identificador") String ident, @RequestParam("password") String novaSenha, RedirectAttributes redirectAttributes){
        try {
            if(usuarioService.findByIdentificadorLike(ident).isEmpty()){
                redirectAttributes.addFlashAttribute("erro", "Usuario não encontrado com esse identificador");
                return "redirect:/login";
            }
            usuario u = usuarioService.findByIdentificadorLike(ident).get(0);
            
            usuarioService.trocarSenha(u,novaSenha);
            redirectAttributes.addFlashAttribute("sucesso", "Senha alterada!");
            return "redirect:/login";   
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro: "+e.toString());
            return "redirect:/login";
        }
    }
}
