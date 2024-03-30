package med.voll.api.domain.consulta.cancelamentos;

import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;

public interface ValidadorCanelamentoConsulta {
    void validar(DadosCancelamentoConsultaDTO dados);
}
