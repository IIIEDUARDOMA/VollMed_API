package med.voll.api.domain.usuario;

public record DadosAutenticacaoDTO(String login, String senha) {

    public DadosAutenticacaoDTO(Usuario user){
        this(user.getLogin(), user.getSenha());
    }
}
