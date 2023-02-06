package br.montreal.application.controller;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.dto.SuccessDetails;
import br.montreal.application.model.Pessoa;
import br.montreal.application.model.enuns.TipoPessoa;
import br.montreal.application.service.IPessoaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaController {

    private final IPessoaService pessoaService;

    @GetMapping("/pessoas")
    public ResponseEntity<SuccessDetails> listarPessoas(@RequestParam(required = false) String nome) throws Exception {

        ServiceResponse serviceResponse = pessoaService.listarPessoas(nome);

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

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<SuccessDetails> buscarPessoaPorId(@PathVariable("id") long id) throws Exception {
        ServiceResponse serviceResponse = pessoaService.buscarPessoaPorId(id);

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

    @PostMapping(value="/pessoas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessDetails> cadastrarPessoa(@RequestBody Pessoa pessoa) throws Exception {

        ServiceResponse serviceResponse = pessoaService.cadastrarPessoa(Pessoa.builder()
                        .tipo(TipoPessoa.fromValue(pessoa.getTipo().value()))
                        .documento(pessoa.getDocumento())
                        .nome(pessoa.getNome())
                        .endereco(pessoa.getEndereco())
                        .bensImoveis(pessoa.getBensImoveis())
                        .build());

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

    @PutMapping(value="/pessoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessDetails> atualizarPessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa) throws Exception {
        ServiceResponse serviceResponse = pessoaService.atualizarPessoa(id, pessoa);

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

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<SuccessDetails> removerPessoa(@PathVariable("id") long id) throws Exception {
        ServiceResponse serviceResponse = pessoaService.removerPessoa(id);

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
