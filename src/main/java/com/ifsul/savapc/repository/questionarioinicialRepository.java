package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.questionarioinicial;
import com.ifsul.savapc.model.turma;

public interface questionarioinicialRepository extends JpaRepository<questionarioinicial,Integer>{
    List<questionarioinicial> findByTurmas(turma turma);
}
