package br.montreal.application.service;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.model.Empresa;
import br.montreal.application.model.Socios;

public interface IEmpresaService {
    ServiceResponse listarEmpresas(String nome) throws Exception;
    ServiceResponse buscarEmpresaPorId(long id) throws Exception;
    ServiceResponse cadastrarEmpresa(Empresa empresa) throws Exception;
    ServiceResponse atualizarEmpresa(long id, Empresa empresa) throws Exception;
    ServiceResponse removerEmpresa(long id) throws Exception;
    ServiceResponse cadastrarSocio(Socios socio) throws Exception;
    ServiceResponse removerSocioEmpresa(long idSocio, long idEmpresa) throws Exception;
    ServiceResponse removerTodosSociosEmpresa(long idEmpresa) throws Exception;
    ServiceResponse calcularComprometimentoFinanceiro(long id) throws Exception;
}
