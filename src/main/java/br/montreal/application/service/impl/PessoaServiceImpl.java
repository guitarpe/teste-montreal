package br.montreal.application.service.impl;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.model.Pessoa;
import br.montreal.application.model.enuns.MensagensSistema;
import br.montreal.application.repository.PessoaRepository;
import br.montreal.application.service.IPessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaServiceImpl implements IPessoaService {

    private final PessoaRepository pessoaRepository;

    @Override
    public ServiceResponse listarPessoas(String nome) {
        List<Pessoa> pessoas = new ArrayList<>();

        if (nome == null) {
            pessoas.addAll(pessoaRepository.findAll());
        }else {
            pessoas.addAll(pessoaRepository.findByNomeContaining(nome));
        }

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(pessoas).build();
    }

    @Override
    public ServiceResponse buscarPessoaPorId(long id) throws Exception {
        Pessoa pessoaData = pessoaRepository.findById(id)
                .orElseThrow(() -> new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value()));

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(pessoaData).build();
    }

    @Override
    public ServiceResponse cadastrarPessoa(Pessoa pessoa) {
        Pessoa pessoaData = pessoaRepository.save(pessoa);

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(pessoaData).build();
    }

    @Override
    public ServiceResponse atualizarPessoa(long id, Pessoa pessoa) throws Exception{
        Pessoa pessoaData = pessoaRepository.findById(id)
                .orElseThrow(() -> new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value()));

        Pessoa editPessoa = pessoaRepository.save(Pessoa.builder()
                                                    .id(pessoaData.getId())
                                                    .tipo(pessoa.getTipo())
                                                    .nome(pessoa.getNome())
                                                    .endereco(pessoa.getEndereco())
                                                    .bensImoveis(pessoa.getBensImoveis())
                                                    .build());

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(editPessoa).build();
    }

    @Override
    public ServiceResponse removerPessoa(long id) throws Exception{
        Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

        if(pessoaData.isPresent()) {
            pessoaRepository.deleteById(id);
        }else{
            throw new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value());
        }

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(null).build();
    }
}
