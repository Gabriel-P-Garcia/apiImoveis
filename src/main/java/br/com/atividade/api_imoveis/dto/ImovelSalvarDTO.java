package br.com.atividade.api_imoveis.dto;

//Função: Contrato de criação de um imovel. Em vez de enviar o objeto “proprietario” inteiro,
//o cliente envia apenas o número do ID do proprietario correspondente.

import java.math.BigDecimal;

public record ImovelSalvarDTO (
        String nome,
        BigDecimal valor,
        Long idProprietario
) {}
