package com.ifsul.savapc.service.serviceImplements;




import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.savapc.model.correcoesUsuario;
import com.ifsul.savapc.model.notas;
import com.ifsul.savapc.model.regQuestionarios;
import com.ifsul.savapc.model.respostaQuestionarios;
import com.ifsul.savapc.model.turma;
import com.ifsul.savapc.repository.correcoesUsuarioRepository;
import com.ifsul.savapc.repository.notasRepository;
import com.ifsul.savapc.repository.regQuestionariosRepository;
import com.ifsul.savapc.service.relatoriosService;

import jakarta.servlet.http.HttpServletResponse;
@Service
public class relatoriosServiceImplements implements relatoriosService{
    @Autowired
    notasRepository notasRepository;
    @Autowired
    correcoesUsuarioRepository correcoesUsuarioRepository;
    @Autowired
    regQuestionariosRepository regQuestionariosRepository;
    @Override
    public void exportarRelatorioTestes(turma turma, HttpServletResponse response) throws IOException {
        String filename = "relatorio_PChabilidades.csv";

        // Configura o tipo de conteúdo do arquivo de resposta
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        // Cria um arquivo CSV
        try (PrintWriter writer = response.getWriter()) {

            writer.write(
                    "Aluno;Turma;nPerguntasCorretas;nPerguntas;H1;H2;H3;H4;H5;NotaProjeto;NotaTestes;NotaFinal;SabeProgramar;Recomendação");
            List<notas> contabilizacaoListTurma = notasRepository.findByTurmaAndDesclassificado(turma, false);
            for (notas cL : contabilizacaoListTurma) {
                String line = String.format("\"%s\";%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        cL.getUsuario().getNome(), cL.getTurma().getNome(), cL.getnPerguntasCorretas(),
                        cL.getnPerguntas(), cL.getH1(), cL.getH2(), cL.getH3(), cL.getH4(), cL.getH5(),
                        cL.getNotaProjetoFinal(), cL.getNotaTestes(), cL.getNotaFinal(), cL.getSabeProgramar(),
                        cL.getRecomendacao());
                writer.append('\n');
                writer.write(line);
            }
            response.getWriter().flush();
        }
    }

    @Override
    public void exportarRelatorioTestesComPerguntas(turma turma, HttpServletResponse response) throws IOException{
        String filename = "relatorio_PCcorrecaoPerguntas.csv";

        // Configura o tipo de conteúdo do arquivo de resposta
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        // Cria um arquivo CSV
        try (PrintWriter writer = response.getWriter()) {

            writer.write(
                    "Aluno;Matricula;Turma;Teste;Pergunta;Resposta;Acertou");
            List<correcoesUsuario> correcao = correcoesUsuarioRepository.findByTurmaOrderByUsuario(turma);
            String Acertou="Nao";
            for (correcoesUsuario cL : correcao) {
                if(cL.isAcertou()){Acertou="Sim";}else{Acertou="Nao";}
                String line = String.format("\"%s\";%s;%s;%s;%s;%s;%s",
                        cL.getUsuario().getNome(), cL.getUsuario().getIdentificador(),cL.getTurma().getNome(), cL.getTeste().getNome(),
                        cL.getPerguntaTeste().getTitulo().replace(',','.'), cL.getOpcaoResposta().getDescricao(),Acertou);
                writer.append('\n');
                writer.write(line);
            }
            response.getWriter().flush();
        }
    }

    @Override
    public void exportarRelatorioQuestionario(turma turma, HttpServletResponse response) throws IOException {
        String filename = "relatorio_PCquestionario.csv";

        // Configura o tipo de conteúdo do arquivo de resposta
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        // Cria um arquivo CSV
        try (PrintWriter writer = response.getWriter()) {

            writer.write(
                    "Aluno;Matricula;Turma;Questionario;Pergunta;Tipo;Resposta");
            List<regQuestionarios> regQuestionarios = regQuestionariosRepository.findByTurma(turma);
            
            for (regQuestionarios cL : regQuestionarios) {
                for (respostaQuestionarios resp : cL.getRespostasQuestionario()) {
                    String line = String.format("\"%s\";%s;%s;%s;%s;%s;%s",
                        cL.getUsuario().getNome(), cL.getUsuario().getIdentificador(),cL.getTurma().getNome(), cL.getQuestionario().getNome(),
                        resp.getPerguntaQuestionario().getDescricao().replace(',','.'),resp.getTipo(), resp.getResposta());
                writer.append('\n');
                writer.write(line);
                }
                
            }
            response.getWriter().flush();
        }
    }

    
    
}
