package br.com.atividade.api_imoveis.dto;

//Função: Dados formatados e seguros do proprietario que serão devolvidos para renderizar
//em tabelas ou telas de listagem do frontend

public record ProprietarioRespostaDTO (
        Long id,
        String nome,
        String cpf
) {}
