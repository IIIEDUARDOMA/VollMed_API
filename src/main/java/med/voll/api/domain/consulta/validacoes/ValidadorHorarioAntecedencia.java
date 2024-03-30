package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var agendamentoComAntecedencia = Duration.between(agora, dataConsulta).toMinutes();

        if (agendamentoComAntecedencia < 30){
            throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência!");
        }
    }
}
