package com.ifsul.savapc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.turma;
public interface turmaRepository extends JpaRepository<turma,Integer>{
    
     List<turma> findByVisibilidade(boolean visibilidade);
     
}