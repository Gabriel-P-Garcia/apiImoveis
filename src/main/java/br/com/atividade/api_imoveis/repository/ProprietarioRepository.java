package br.com.atividade.api_imoveis.repository;

import br.com.atividade.api_imoveis.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ProprietarioRepository  extends JpaRepository<Proprietario, Long> {
    // Query Method: O Spring gerará automaticamente o SQL filtrando pelo nome.
    // SQL gerado: SELECT * FROM tb_proprietario WHERE nome = ?

    List<Proprietario> findByNomeIgnoreCase(String nome);

    // Query Method Refatorado com ILIKE (Busca parcial e case-insensitive)
    // SQL gerado no Postgres: SELECT * FROM tb_proprietario WHERE nome ILIKE ?
    // O Spring Bootinjetará automaticamente os caracteres '%' antes e depois da String (ex: '%jap%')

    List<Proprietario> findByNomeContainingIgnoreCase(String nome);

    //Novo Metodo de Query para procurar por CPF

    List<Proprietario> findByCpf(String cpf);

}
