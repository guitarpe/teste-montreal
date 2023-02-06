package br.montreal.application.service;

import br.montreal.application.dto.ServiceResponse;

public interface ISociosService {
    ServiceResponse listarSociosEmpresa(String nomeEmpresa) throws Exception;
    ServiceResponse buscarSociosPorId(long id) throws Exception;
}
