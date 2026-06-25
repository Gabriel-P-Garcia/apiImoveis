package br.com.atividade.api_imoveis.controller;

import br.com.atividade.api_imoveis.dto.ProprietarioRespostaDTO;
import br.com.atividade.api_imoveis.dto.ProprietarioSalvarDTO;
import br.com.atividade.api_imoveis.service.ProprietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proprietarios")
@RequiredArgsConstructor

public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    // 1. GET
    @GetMapping
    public ResponseEntity<List<ProprietarioRespostaDTO>> listarTodos() {
        List<ProprietarioRespostaDTO> lista = proprietarioService.listarTodos();
        return ResponseEntity.ok(lista); //Retorna HTTP 200 OK
    }

    // 2. GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioRespostaDTO> buscarPorId(@PathVariable Long id) {
        ProprietarioRespostaDTO dto = proprietarioService.buscarPorId(id);
        return  ResponseEntity.ok(dto);
    }

    // 3. GET filtrando por nome (Usando Query Param) ex: /api/proprietarios/filtro?nome=Carla
    @GetMapping ("/filtro")
    public ResponseEntity<List<ProprietarioRespostaDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProprietarioRespostaDTO> lista = proprietarioService.buscarPorNome(nome);
        return ResponseEntity.ok(lista);
    }

    // 4. POST
    @PostMapping
    public ResponseEntity<ProprietarioRespostaDTO> salvar(@RequestBody ProprietarioSalvarDTO dto) {
        ProprietarioRespostaDTO novoProprietario = proprietarioService.salvar(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(novoProprietario); //Retorna HTTP 201 Created
    }

    // 5. PUT. Atualizar dados de proprietario existente
    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioRespostaDTO>
    atualizar(@PathVariable Long id, @RequestBody ProprietarioSalvarDTO dto){
        ProprietarioRespostaDTO proprietarioAtualizado = proprietarioService.atualizar(id, dto);
        return ResponseEntity.ok(proprietarioAtualizado); // Retorna 200
    }

    // 6. Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        proprietarioService.excluir(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 No Content
    }

}
