package med.voll.api.domain.consulta.cancelamentos;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidaorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCanelamentoConsulta{
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsultaDTO dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24){
            throw new ValidacaoException("Cancelamento deve ser feito com uma antecedência de no mínimo 24 horas!");
        }
    }
}
