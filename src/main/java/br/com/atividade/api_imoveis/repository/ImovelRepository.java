package br.com.atividade.api_imoveis.repository;

import br.com.atividade.api_imoveis.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long>{
    //Query method para buscar pelo id do proprietário
    List<Imovel> findByProprietarioId(Long idProprietario);

    // Query Method Avançado: Busca imóveis com valores menores ou iguais ao parâmetro informado.
    List<Imovel> findByValorLessThanEqual (BigDecimal valorMaximo);
}
