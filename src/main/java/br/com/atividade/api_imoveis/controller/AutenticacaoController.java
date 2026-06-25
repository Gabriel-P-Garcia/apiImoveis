package br.com.atividade.api_imoveis.controller;
//Cria o endpoint público /api/auth/login.

import br.com.atividade.api_imoveis.dto.AutenticacaoReqDTO;
import br.com.atividade.api_imoveis.dto.AutenticacaoResDTO;
import br.com.atividade.api_imoveis.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AutenticacaoController {
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AutenticacaoResDTO> login(@RequestBody AutenticacaoReqDTO dto) {
        //Validação estática de credenciais para o workshop
        if ("admin".equals(dto.login()) && "123456".equals(dto.senha())) {
            String token = tokenService.gerarToken(dto.login());
            return ResponseEntity.ok(new AutenticacaoResDTO(token, "Bearer", 7200L));
        }
        //Se errar a senha, retorna 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
