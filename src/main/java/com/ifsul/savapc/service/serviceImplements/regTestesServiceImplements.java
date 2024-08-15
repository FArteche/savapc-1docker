package com.ifsul.savapc.service.serviceImplements;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.correcoesUsuario;
import com.ifsul.savapc.model.opcaoresposta;
import com.ifsul.savapc.model.regTestes;
import com.ifsul.savapc.model.respostaTeste;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;
import com.ifsul.savapc.repository.correcoesUsuarioRepository;
import com.ifsul.savapc.repository.opcaorespostaRepository;
import com.ifsul.savapc.repository.regTestesRepository;
import com.ifsul.savapc.repository.testeRepository;
import com.ifsul.savapc.repository.turmaRepository;
import com.ifsul.savapc.repository.usuarioRepository;
import com.ifsul.savapc.service.regTestesService;

@Service
public class regTestesServiceImplements implements regTestesService{

    @Autowired
    regTestesRepository regTestesRepository;
    @Autowired
    opcaorespostaRepository opcaorespostaRepository;
    @Autowired
    usuarioRepository usuarioRepository;
    @Autowired
    correcoesUsuarioRepository correcoesUsuarioRepository;
    @Autowired
    testeRepository testeRepository;
    @Autowired
    turmaRepository turmaRepository;
    

    public void fazerCorrecaoTestes() {
        
        List<regTestes> regs = regTestesRepository.findAll();
        for(int i=0;i<regs.size();i++){
            if(regs.get(i).getUsuario().getUsuarioId()==-1
                || regs.get(i).getTeste().getTesteId()==-1){
                    regs.remove(i);  
            }
            if(regs.get(i).getRespostasTeste().size()>0){
                for(int j=0;j<regs.get(i).getRespostasTeste().size();j++){
                    if(regs.get(i).getRespostasTeste().get(j).getPerguntaTeste()==null){
                        regs.get(i).getRespostasTeste().remove(j);
                    }
                }
            }
        }
        
        for (regTestes r : regs) { 
            
            
            for (respostaTeste resp : r.getRespostasTeste()) {
                
                boolean acertou = false;
                if(correcoesUsuarioRepository.findByUsuarioAndTurmaAndTesteAndPerguntaTeste(r.getUsuario(), r.getTurma(), r.getTeste(), resp.getPerguntaTeste()).size()<=0){
                List<opcaoresposta> OPrespostas = new ArrayList<>();
                OPrespostas.clear();
                    if(opcaorespostaRepository.findOpcaoRespostaIdByPerguntasTesteAndVerdadeira(resp.getPerguntaTeste(), true).size()>0){
                        OPrespostas = opcaorespostaRepository.findOpcaoRespostaIdByPerguntasTesteAndVerdadeira(resp.getPerguntaTeste(), true);
                    }
                
                
                for (opcaoresposta op : OPrespostas) {
                    
                if (resp.getOpRespostaId() == op.getOpcaoRespostaId()) { //Resposta Certa
                    acertou=true;
                    opcaoresposta opR =  opcaorespostaRepository.findById(resp.getOpRespostaId()).get();            
                    correcoesUsuario cA = new correcoesUsuario(r.getUsuario(), r.getTurma(),r.getTeste(),
                            resp.getPerguntaTeste(), opR , true);
                    correcoesUsuarioRepository.save(cA);
                    
                    
                }     
                }
                if(acertou==false) {                   
                                                 //Resposta Errada
                    opcaoresposta opR =  opcaorespostaRepository.findById(resp.getOpRespostaId()).get();
                    correcoesUsuario cA = new correcoesUsuario(r.getUsuario(),r.getTurma(), r.getTeste(), resp.getPerguntaTeste(), opR, false);
                    correcoesUsuarioRepository.save(cA);
                }
                
            }
         }
        }
    }

    @Override
    public List<regTestes> findByTurma(turma turma) {
        return regTestesRepository.findByTurma(turma);
    }

    @Override
    public List<regTestes> findRegTestesByTurmaAndUsuario(turma t, usuario a) {
        return regTestesRepository.findRegTestesByTurmaAndUsuario(t, a);
    }

    @Override
    public List<regTestes> findByTesteAndTurmaAndUsuario(teste t, turma tu, usuario u) {
        return regTestesRepository.findByTesteAndTurmaAndUsuario(t, tu, u);
    }

    @Override
    public Boolean deletarTentativa(Integer testeId,Integer turmaId, Integer usuarioId) {
        if(testeRepository.existsById(testeId)|| turmaRepository.existsById(turmaId) || usuarioRepository.existsById(usuarioId)){
            teste teste = testeRepository.findById(testeId).get();
            turma turma = turmaRepository.findById(turmaId).get();
            usuario usuario = usuarioRepository.findById(usuarioId).get();
            regTestes tentativa = regTestesRepository.findByTesteAndTurmaAndUsuario(teste, turma, usuario).get(0);
            tentativa.getRespostasTeste().clear();
            regTestesRepository.save(tentativa);
            regTestesRepository.deleteById(tentativa.getRegTestesId());

            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<regTestes> findByTurmaAndTeste(turma t, teste test) {
        return regTestesRepository.findByTurmaAndTeste(t, test);
    }
    
}
