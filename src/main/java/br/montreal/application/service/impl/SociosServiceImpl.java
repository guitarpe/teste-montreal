package br.montreal.application.service.impl;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.model.Socios;
import br.montreal.application.model.enuns.MensagensSistema;
import br.montreal.application.repository.SociosRepository;
import br.montreal.application.service.ISociosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SociosServiceImpl implements ISociosService {

    private final SociosRepository sociosRepository;

    @Override
    public ServiceResponse listarSociosEmpresa(String nomeEmpresa) {
        List<Socios> listaSocios = sociosRepository.buscarSociosPorNomeEmpresa(nomeEmpresa);

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(listaSocios).build();
    }

    @Override
    public ServiceResponse buscarSociosPorId(long id) {
        Optional<Socios> socio = sociosRepository.findById(id);

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(socio).build();
    }
}
