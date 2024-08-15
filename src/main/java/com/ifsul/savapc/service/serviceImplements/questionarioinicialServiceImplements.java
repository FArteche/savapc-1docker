package com.ifsul.savapc.service.serviceImplements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.perguntaquestionario;
import com.ifsul.savapc.model.perguntasQuestForm;
import com.ifsul.savapc.model.questionarioinicial;
import com.ifsul.savapc.model.regQuestionarios;
import com.ifsul.savapc.model.respostaQuestionarios;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.opcaorespostaRepository;
import com.ifsul.savapc.repository.perguntaQuestionarioRepository;
import com.ifsul.savapc.repository.questionarioinicialRepository;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.repository.usuarioRepository;
import com.ifsul.savapc.service.questionarioinicialService;
@Service
public class questionarioinicialServiceImplements implements questionarioinicialService{
    @Autowired
    questionarioinicialRepository questionarioinicialRepository;
    @Autowired
    usuarioRepository usuarioRepository;
    @Autowired
    perguntaQuestionarioRepository perguntaQuestionarioRepository;
    @Autowired
    regQuestionariosRepository regQuestionariosRepository;
    @Autowired
    opcaorespostaRepository opcaorespostaRepository;
    @Override
    public List<questionarioinicial> findByTurmas(turma turma) {
        return questionarioinicialRepository.findByTurmas(turma);
    }
    @Override
    public void atualizarVisibilidade() {
         List<questionarioinicial> allQuests = questionarioinicialRepository.findAll();
        LocalDate firstDate = LocalDate.now();
        for (questionarioinicial quest : allQuests) {
            LocalDate secondDate = quest.getDisponibilidade();
           boolean a =  secondDate.isAfter(firstDate);
           if(a==false){
            quest.setVisibilidade(false);
            questionarioinicialRepository.save(quest);
           }
        }
    }
    @Override
    public boolean salvarRespostasQuest(turma turma, questionarioinicial quest, perguntasQuestForm lresp) {
        List<respostaQuestionarios> ListRespostas = new ArrayList<>();
            regQuestionarios reg = new regQuestionarios();
            if (usuarioRepository.findByUsername(lresp.getUsername()) != null) {
                for (perguntaquestionario Pergunta : lresp.getPerguntas()) {
                    respostaQuestionarios resposta = new respostaQuestionarios();
                        perguntaquestionario p = perguntaQuestionarioRepository
                            .findById(Pergunta.getPerguntaQuestionarioId()).orElseThrow(null);
                         resposta.setPerguntaQuestionario(p);
                         resposta.setTipo(Pergunta.getTipo());
                         if (Pergunta.getTipo().equalsIgnoreCase("multipla escolha")) {
                            if(Pergunta.getOpRespostaId()!=null){
                                opcaoresposta resp = opcaorespostaRepository.findById(Integer.valueOf(Pergunta.getOpRespostaId())).get();
                                System.out.println("\n \n "+resp.getDescricao());
                                if(resp.getDescricao().equalsIgnoreCase("Não selecionado")){
                                     resposta.setResposta("Nao selecionado");
                                }else{
                                    resposta.setResposta(resp.getDescricao());
                                }
                                resposta.setOpRespostaId(resp.getOpcaoRespostaId());   
                               
                                ListRespostas.add(resposta);
                            } 
                            else{
                                resposta.setOpRespostaId(0);
                                resposta.setResposta("Não respondeu");   
                                ListRespostas.add(resposta);
                            }
                        }
                        if (Pergunta.getTipo().equalsIgnoreCase("dissertativa")) {
                            if(!Pergunta.getResposta().isEmpty()){
                                resposta.setResposta(Pergunta.getResposta());
                                ListRespostas.add(resposta);
                            }else{
                                resposta.setResposta(" Em branco");
                            ListRespostas.add(resposta);
                            }
                            
                        }
                    
                    
                }
                reg.setUsuario(usuarioRepository.findByUsername(lresp.getUsername()));
                reg.setQuestionario(quest);
                reg.setTurma(turma);
                reg.setRespostasQuestionario(ListRespostas);
                regQuestionariosRepository.save(reg);
                return true;
                }else{
                return false;}
                
            }
    @Override
    public List<questionarioinicial> findAll() {
        return questionarioinicialRepository.findAll();
    }
    @Override
    public questionarioinicial findById(Integer id) {
        return questionarioinicialRepository.findById(id).get();
    }
    @Override
    public questionarioinicial save(questionarioinicial quest) {
        return questionarioinicialRepository.save(quest);
    }
    @Override
    public boolean deleteById(Integer id) {
        try {
            if (questionarioinicialRepository.existsById(id)) {
                questionarioinicialRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean existsById(Integer questId) {
        return questionarioinicialRepository.existsById(questId);
    }
}