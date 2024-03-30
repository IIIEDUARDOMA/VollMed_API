package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorFuncionamentoDaClinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsultaDTO dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesHorarioFuncionamento = dataConsulta.getHour() < 7;
        var aposHorarioFuncionamento = dataConsulta.getHour() >18;

        if (domingo || antesHorarioFuncionamento || aposHorarioFuncionamento){
            throw new ValidacaoException("Fora do horário de funcionamento da clínica!");
        }
    }
}
