package br.com.atividade.api_imoveis.service;

import br.com.atividade.api_imoveis.dto.ImovelRespostaDTO;
import br.com.atividade.api_imoveis.dto.ImovelSalvarDTO;
import br.com.atividade.api_imoveis.model.Imovel;
import br.com.atividade.api_imoveis.model.Proprietario;
import br.com.atividade.api_imoveis.repository.ImovelRepository;
import br.com.atividade.api_imoveis.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)

public class ImovelService {

    private final ImovelRepository imovelRepository;
    private final ProprietarioRepository proprietarioRepository;

    // 1. Listar todos imoveis
    public List<ImovelRespostaDTO> listarTodos(){
        return imovelRepository.findAll().stream().map(this::converterParaRespostaDTO).toList();
    }

    // 2. Buscar por ID
    public ImovelRespostaDTO buscarPorID (Long id) {
        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado. ID: " + id));
        return converterParaRespostaDTO(imovel);
    }

    // 3. Salvar (Once ocorre a junção das duas tabelas através das chaves)
    @Transactional
    public ImovelRespostaDTO salvar(ImovelSalvarDTO dto) {
        // Regra de Negócio: validar que o preço do imovel é maior que 0
        if (dto.valor() == null || dto.valor().compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("O valor do imóvel deve ser maior que zero.");
        }
        // Junção: Buscamos a referência do proprietário antes de salvar o imóvel
        Proprietario proprietario = proprietarioRepository.findById(dto.idProprietario())
                .orElseThrow(() -> new RuntimeException
                        ("Não foi possível cadastrar o imóvel. Proprietário não encontrado com o ID: " + dto.idProprietario()));
        Imovel imovel = new Imovel();
        imovel.setNome(dto.nome());
        imovel.setValor(dto.valor());
        imovel.setProprietario(proprietario); // Amarra a FK
        Imovel imovelSalvo = imovelRepository.save(imovel);

        return converterParaRespostaDTO(imovelSalvo);
    }

    // 4. Atualizar
    @Transactional
    public ImovelRespostaDTO atualizar(Long id, ImovelSalvarDTO dto){
        Imovel imovel = imovelRepository.findById(id).orElseThrow(() -> new RuntimeException
                ("Imóvel não encontrado para atualização. ID: " + id));
        Proprietario proprietario = proprietarioRepository.findById(dto.idProprietario()).orElseThrow(() -> new RuntimeException
                ("Proprietário informado não existe. ID: " + dto.idProprietario()));

        imovel.setNome(dto.nome());
        imovel.setValor(dto.valor());
        imovel.setProprietario(proprietario);
        Imovel imovelAtualizado = imovelRepository.save(imovel);

        return converterParaRespostaDTO(imovelAtualizado);
    }

    // 5. Excluir
    @Transactional
    public void excluir(Long id) {
        if(!imovelRepository.existsById(id)) {
            throw new RuntimeException("Não é possível excluir. Imovel não encontrado. ID: " + id);
        }
        imovelRepository.deleteById(id);
    }

    // Métodos Auxiliares de Conversão e Achatamento
    private ImovelRespostaDTO converterParaRespostaDTO(Imovel imovel) {
        return new ImovelRespostaDTO(
              imovel.getId(),
              imovel.getNome(),
              imovel.getValor(),
              imovel.getProprietario().getId(),
              imovel.getProprietario().getNome() // Evita o objeto aninhado enviando apenas a String para o JSON final
        );
    }
}
