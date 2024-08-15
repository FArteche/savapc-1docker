package com.ifsul.savapc.service;

import java.io.IOException;

import com.ifsul.savapc.model.turma;

import jakarta.servlet.http.HttpServletResponse;

public interface relatoriosService {
    void exportarRelatorioTestes(turma t, HttpServletResponse response) throws IOException;
    void exportarRelatorioTestesComPerguntas(turma t, HttpServletResponse response) throws IOException;
    void exportarRelatorioQuestionario(turma t, HttpServletResponse response) throws IOException;
}
