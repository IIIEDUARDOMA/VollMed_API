package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var ativo = repository.findAtivoById(dados.idMedico());
        if (!ativo){
            throw new ValidacaoException("Consulta não pode ser agendado com médico excluído");
        }
    }
}
