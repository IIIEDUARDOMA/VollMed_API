package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoConsulta {
    void validar(DadosAgendamentoConsultaDTO dados);
}
