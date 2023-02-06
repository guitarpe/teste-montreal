package br.montreal.application.service;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.model.Pessoa;

public interface IPessoaService {
    ServiceResponse listarPessoas(String nome) throws Exception;
    ServiceResponse buscarPessoaPorId(long id) throws Exception;
    ServiceResponse cadastrarPessoa(Pessoa pessoa) throws Exception;
    ServiceResponse atualizarPessoa(long id, Pessoa pessoa) throws Exception;
    ServiceResponse removerPessoa(long id) throws Exception;
}
