package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaConsulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.consulta.DadosDetalhamentoConsultaDTO;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JacksonTester<DadosAgendamentoConsultaDTO> dadosAgendamentoConsultaDTOJson;

  @Autowired
  private JacksonTester<DadosDetalhamentoConsultaDTO> dadosDetalhamentoConsultaDTOJson;

  @MockBean
  private AgendaConsulta agenda;


  @Test
  @DisplayName("Deveria retonar código HTTP 400 quando informações passadas são inválidas!")
  @WithMockUser
  void agendar_cenario1() throws Exception {
    var response = mvc.perform(post("/consultas"))
            .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @DisplayName("Deveria retonar código HTTP 400 quando informações passadas são inválidas!")
  @WithMockUser
  void agendar_cenario2() throws Exception {
    var data = LocalDateTime.now().plusHours(1);

    var dadosDetalhamento = new DadosDetalhamentoConsultaDTO(null,2l, 5l,data);
    when(agenda.agendar(any())).thenReturn(dadosDetalhamento);


    var response = mvc.perform(
            post("/consultas")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dadosAgendamentoConsultaDTOJson.write(
                            new DadosAgendamentoConsultaDTO(2l, 5l, data, Especialidade.CARDIOLOGIA)
                    ).getJson()))
            .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    var jsonEsperado = dadosDetalhamentoConsultaDTOJson.write(dadosDetalhamento).getJson();

    assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
  }
}
