package br.com.atividade.api_imoveis.context;

public class UsuarioLogadoContext {
    private static final ThreadLocal<String> usuarioAtual = new ThreadLocal<>();

    public static void definirUsuario (String login) {
        usuarioAtual.set(login);
    }

    public static String obterUsuario() {
        return usuarioAtual.get();
    }

    public static void limpar() {
        usuarioAtual.remove();
    }
}
