package com.ifsul.savapc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.savapc.model.teste;
import com.ifsul.savapc.model.turma;




public interface testeRepository extends JpaRepository<teste,Integer>{
    List<teste> findByTurmas(turma turma);
    
}
