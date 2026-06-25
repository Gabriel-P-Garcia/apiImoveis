package br.com.atividade.api_imoveis.dto;

//Função: Devolver o Token JWT gerado e assinado após o sistema validar que o login e a
//senha estão corretos.
public record AutenticacaoResDTO (
        String token,
        String tipo,
            Long expiracaoEm
) {}
