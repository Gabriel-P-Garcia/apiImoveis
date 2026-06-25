package br.com.atividade.api_imoveis.dto;

//Função: Capturar as credenciais que o usuário digita na tela de login da aplicação
public record AutenticacaoReqDTO (
        String login,
        String senha
) {}
