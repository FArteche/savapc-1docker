package com.ifsul.savapc.service;

import java.util.List;

import com.ifsul.savapc.model.perguntasQuestForm;
import com.ifsul.savapc.model.questionarioinicial;
import com.ifsul.savapc.model.turma;


public interface questionarioinicialService {
    List<questionarioinicial> findAll();
    questionarioinicial findById(Integer id);
    questionarioinicial save(questionarioinicial quest);
    boolean deleteById(Integer id);
    boolean existsById(Integer questId);
    
    List<questionarioinicial> findByTurmas(turma turma);
    void atualizarVisibilidade();
    boolean salvarRespostasQuest(turma t, questionarioinicial quest, perguntasQuestForm lresp);
}
