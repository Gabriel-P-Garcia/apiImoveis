package br.com.atividade.api_imoveis.dto;

//Função: Contrato com os campos estritos que o cliente deve enviar para cadastrar ou
//atualizar um proprietario. (Note que não possui o campo id)

public record ProprietarioSalvarDTO (
        String nome,
        String cpf
) {}
