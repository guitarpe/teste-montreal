package br.montreal.application.controller;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.dto.SocioDTO;
import br.montreal.application.dto.SuccessDetails;
import br.montreal.application.model.Empresa;
import br.montreal.application.model.Pessoa;
import br.montreal.application.model.Socios;
import br.montreal.application.service.IEmpresaService;
import br.montreal.application.service.IPessoaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaController {

    private final IEmpresaService empresaService;
    private final IPessoaService pessoaService;

    @GetMapping("/empresas")
    public ResponseEntity<SuccessDetails> listarEmpresas(@RequestParam(required = false) String nome) {
        try {
            ServiceResponse serviceResponse = empresaService.listarEmpresas(nome);

            if(!serviceResponse.isStatus()) {
                throw new EntityNotFoundException(serviceResponse.getMensagem());
            }

            return ResponseEntity.ok().body(SuccessDetails.builder()
                    .code(200)
                    .sucesso(true)
                    .timestamp(LocalDateTime.now())
                    .message(serviceResponse.getMensagem())
                    .data(serviceResponse.getData()).build());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<SuccessDetails> buscarEmpresaPorId(@PathVariable("id") long id) throws Exception {
        ServiceResponse serviceResponse = empresaService.buscarEmpresaPorId(id);

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

    @PostMapping(value="/empresas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessDetails> cadastrarEmpresa(@RequestBody Empresa empresa) throws Exception {

        ServiceResponse serviceResponse = empresaService.cadastrarEmpresa(Empresa.builder()
                                                .pessoa(empresa.getPessoa())
                                                .comprometimentoFinanceiro(empresa.getComprometimentoFinanceiro())
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

    @PutMapping(value="/empresas/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessDetails> atualizarEmpresa(@PathVariable("id") long id, @RequestBody Empresa empresa) throws Exception {
        ServiceResponse serviceResponse = empresaService.atualizarEmpresa(id, empresa);

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

    @DeleteMapping("/empresas/{id}")
    public ResponseEntity<SuccessDetails> removerEmpresa(@PathVariable("id") long id) throws Exception {
        ServiceResponse serviceResponse = empresaService.removerEmpresa(id);

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

    @PostMapping(value="/empresas/socios", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessDetails> cadastrarSocio(@RequestBody SocioDTO socio) throws Exception {
        Empresa empresaData = (Empresa) empresaService.buscarEmpresaPorId(socio.getEmpresa()).getData();
        Pessoa pessoaData = (Pessoa) pessoaService.buscarPessoaPorId(socio.getPessoa()).getData();

        ServiceResponse serviceResponse = empresaService.cadastrarSocio(Socios.builder()
                .empresa(empresaData)
                .pessoa(pessoaData).build());

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

    @DeleteMapping("/empresas/socios/{idSocio}/{idEmpresa}")
    public ResponseEntity<SuccessDetails> removerSociosEmpresa(@PathVariable("idSocio") long idSocio, @PathVariable("idEmpresa") long idEmpresa) throws Exception {
        ServiceResponse serviceResponse = empresaService.removerSocioEmpresa(idSocio, idEmpresa);

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

    @DeleteMapping("/empresas/socios/{idEmpresa}")
    public ResponseEntity<SuccessDetails> removerTodosSociosEmpresa(@PathVariable("idEmpresa") long id) throws Exception {
        ServiceResponse serviceResponse = empresaService.removerTodosSociosEmpresa(id);

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

    @GetMapping("/empresas/comprometimento-financeiro/{id}")
    public ResponseEntity<SuccessDetails> calcularComprometimentoFinanceiro(@PathVariable("id") long id) throws Exception {
        ServiceResponse serviceResponse = empresaService.calcularComprometimentoFinanceiro(id);

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
