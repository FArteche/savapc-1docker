package com.ifsul.savapc.service;



import java.util.List;

import com.ifsul.savapc.model.regTestes;
import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.model.usuario;

public interface regTestesService {
    
    
    void fazerCorrecaoTestes();
    List<regTestes> findByTurma(turma turma);
    List<regTestes> findRegTestesByTurmaAndUsuario(turma t,usuario a);
    List<regTestes> findByTesteAndTurmaAndUsuario(teste t, turma tu, usuario u);
    List<regTestes> findByTurmaAndTeste(turma t,teste a);
    Boolean deletarTentativa(Integer regTesteId,Integer turmaId,Integer usuarioId);
}

