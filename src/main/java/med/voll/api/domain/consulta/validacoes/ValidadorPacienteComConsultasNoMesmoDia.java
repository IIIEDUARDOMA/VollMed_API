package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteComConsultasNoMesmoDia implements ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados){
        var primeiraHora = dados.data().withHour(7);
        var ultimaHora = dados.data().withHour(18);
        var pacienteComConsultasNoMesmoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiraHora, ultimaHora);

        if (pacienteComConsultasNoMesmoDia){
            throw new ValidacaoException("Paciente já tem consulta marcada nessa data!");
        }
    }
}
