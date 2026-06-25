package br.com.atividade.api_imoveis.dto;

//Função: Retorna os dados do veículo simplificados. Veja que aplicamos a técnica de
//"achatamento": trazemos o nomeProprietario como uma String direta no primeiro nível do
//JSON, evitando objetos aninhados complexos para o frontend processar.

import java.math.BigDecimal;

public record ImovelRespostaDTO (
        Long id,
        String nome,
        BigDecimal valor,
        Long idProprietario,
        String nomeProprietario
) {}
