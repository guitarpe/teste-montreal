package br.montreal.application.controller;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.dto.SuccessDetails;
import br.montreal.application.service.ISociosService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SociosController {

    private final ISociosService sociosService;

    @GetMapping("/socios-empresa")
    public ResponseEntity<SuccessDetails> listarSociosEmpresa(@RequestParam(required = false) String nome) throws Exception {

        ServiceResponse serviceResponse = sociosService.listarSociosEmpresa(nome);

        if(!serviceResponse.isStatus()) {
            throw new EntityNotFoundException(serviceResponse.getMensagem());
        }

        return ResponseEntity.ok().body(SuccessDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(serviceResponse.getMensagem())
                .data(serviceResponse.getData()).build());
    }

    @GetMapping("/socios/{id}")
    public ResponseEntity<SuccessDetails> buscarSociosPorId(@PathVariable("id") long id) throws Exception {
        ServiceResponse serviceResponse = sociosService.buscarSociosPorId(id);

        if(!serviceResponse.isStatus()) {
            throw new EntityNotFoundException(serviceResponse.getMensagem());
        }

        return ResponseEntity.ok().body(SuccessDetails.builder()
                .code(200)
                .sucesso(true)
                .timestamp(LocalDateTime.now())
                .message(serviceResponse.getMensagem())
                .data(serviceResponse.getData()).build());
    }
}
