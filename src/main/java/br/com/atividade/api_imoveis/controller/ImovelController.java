package br.com.atividade.api_imoveis.controller;

import br.com.atividade.api_imoveis.dto.ImovelRespostaDTO;
import br.com.atividade.api_imoveis.dto.ImovelSalvarDTO;
import br.com.atividade.api_imoveis.service.ImovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/imoveis")
@RequiredArgsConstructor

public class ImovelController {

    private final ImovelService imovelService;

    // 1. GET
    @GetMapping
    public ResponseEntity<List<ImovelRespostaDTO>> listarTodos() {
        List<ImovelRespostaDTO> lista = imovelService.listarTodos();
        return ResponseEntity.ok(lista); //Retorna HTTP 200 OK
    }

    // 2. GET por id especifico
    @GetMapping("/{id}")
    public ResponseEntity<ImovelRespostaDTO> buscarPorId(@PathVariable Long id) {
        ImovelRespostaDTO dto = imovelService.buscarPorID(id);
        return ResponseEntity.ok(dto);
    }

    // 3. POST
    @PostMapping
    public ResponseEntity<ImovelRespostaDTO> salvar(@RequestBody ImovelSalvarDTO dto) {
        ImovelRespostaDTO novoImovel = imovelService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoImovel); // Retorna HTTP 201 Created
    }

    // 4. PUT
    @PutMapping("/{id}")
    public ResponseEntity<ImovelRespostaDTO> atualizar(@PathVariable Long id, @RequestBody ImovelSalvarDTO dto){
        ImovelRespostaDTO imovelAtualizado = imovelService.atualizar(id,dto);
        return ResponseEntity.ok(imovelAtualizado);
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        imovelService.excluir(id);
        return ResponseEntity.noContent().build(); //Retorna HTTP 204 No Content
    }

}
