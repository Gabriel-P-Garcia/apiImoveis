package br.com.atividade.api_imoveis.service;

import br.com.atividade.api_imoveis.dto.ProprietarioRespostaDTO;
import br.com.atividade.api_imoveis.dto.ProprietarioSalvarDTO;
import br.com.atividade.api_imoveis.model.Proprietario;
import br.com.atividade.api_imoveis.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)

public class ProprietarioService {
    private final ProprietarioRepository proprietarioRepository;

    // 1. Listar Todos
    public List<ProprietarioRespostaDTO> listarTodos() {
        return proprietarioRepository.findAll().stream().map(this::converterParaRespostaDTO).toList();
    }
    // 2. Buscar por ID
    public ProprietarioRespostaDTO buscarPorId(Long id) {
        Proprietario proprietario = proprietarioRepository.findById(id).orElseThrow(() -> new RuntimeException
                ("Proprietário não encontrado com o ID: " + id));
        return converterParaRespostaDTO(proprietario);
        }
    // 3. Buscar por Nome (Usando o Query Method ILIKE do Reposítory)
    public List<ProprietarioRespostaDTO> buscarPorNome(String nome) {
        return proprietarioRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::converterParaRespostaDTO).toList();
        }
    // 4. Salvar / Cadastrar
    @Transactional
    public ProprietarioRespostaDTO salvar (ProprietarioSalvarDTO dto) {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome(dto.nome());
        proprietario.setCpf(dto.cpf());
        Proprietario proprietarioSalva = proprietarioRepository.save(proprietario);
        return converterParaRespostaDTO (proprietarioSalva);
        }
    // 5. Atualizar existing
    @Transactional
    public ProprietarioRespostaDTO atualizar (Long id, ProprietarioSalvarDTO dto){
        Proprietario proprietario = proprietarioRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Proprietário não encontrado para atualização. ID: " + id));
        proprietario.setNome(dto.nome());
        proprietario.setCpf(dto.cpf());
        Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario);
        return converterParaRespostaDTO (proprietarioAtualizado);
        }
    // 6. Excluir
    @Transactional
    public void excluir (Long id) {
        if (!proprietarioRepository.existsById(id)){
            throw new RuntimeException("Não é possível excluir. Proprietário não encontrado. ID: " + id);
            }
        proprietarioRepository.deleteById(id);
        }

    //Métodos Auxiliares de Conversão (Mapper manual para fins didadicos, como é dito no doc do professor)
    private ProprietarioRespostaDTO converterParaRespostaDTO (Proprietario proprietario){
        return new ProprietarioRespostaDTO(
                proprietario.getId(),
                proprietario.getNome(),
                proprietario.getCpf()
            );
        }
    }

