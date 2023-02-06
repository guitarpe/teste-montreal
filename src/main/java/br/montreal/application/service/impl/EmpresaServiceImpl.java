package br.montreal.application.service.impl;

import br.montreal.application.dto.ServiceResponse;
import br.montreal.application.model.Bem;
import br.montreal.application.model.Empresa;
import br.montreal.application.model.Pessoa;
import br.montreal.application.model.Socios;
import br.montreal.application.model.enuns.MensagensSistema;
import br.montreal.application.repository.EmpresaRepository;
import br.montreal.application.repository.SociosRepository;
import br.montreal.application.service.IEmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaServiceImpl implements IEmpresaService {

    private final EmpresaRepository empresaRepository;
    private final SociosRepository sociosRepository;

    @Override
    public ServiceResponse listarEmpresas(String nome) throws Exception {
        List<Empresa> empresas = new ArrayList<>();

        if (nome == null) {
            empresas.addAll(empresaRepository.findAll());
        }else {
            empresas.addAll(empresaRepository.findByPessoaNome(nome));
        }

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(empresas).build();
    }

    @Override
    public ServiceResponse buscarEmpresaPorId(long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(empresa.get()).build();
    }

    @Override
    public ServiceResponse cadastrarEmpresa(Empresa empresa) {
        Empresa empresaData = empresaRepository.save(empresa);

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(empresaData).build();
    }

    @Override
    public ServiceResponse atualizarEmpresa(long id, Empresa empresa) throws Exception {
        Optional<Empresa> empresaData = empresaRepository.findById(id);

        empresaData.map(value -> empresaRepository.save(Empresa.builder()
                        .id(value.getId())
                        .pessoa(empresa.getPessoa())
                        .socios(empresa.getSocios())
                        .comprometimentoFinanceiro(empresa.getComprometimentoFinanceiro())
                        .build()))
                .orElseThrow(() -> new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value()));

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(empresaData).build();
    }

    @Override
    public ServiceResponse removerEmpresa(long id) throws Exception {
        Optional<Empresa> empresaData = empresaRepository.findById(id);

        if(empresaData.isPresent()) {
            empresaRepository.deleteById(id);
        }else{
            throw new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value());
        }

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(null).build();
    }

    @Override
    public ServiceResponse cadastrarSocio(Socios socio) throws Exception {
        sociosRepository.save(socio);

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(null).build();
    }

    @Override
    public ServiceResponse removerSocioEmpresa(long idSocio, long idEmpresa) throws Exception {
        Optional<Socios> sociosData = sociosRepository.findByIdAndEmpresa(idSocio, idEmpresa);

        if(sociosData.isPresent()) {
            sociosRepository.deleteSociosByIdAndEmpresa(idSocio, idEmpresa);
        }else{
            throw new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value());
        }

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(null).build();
    }

    @Override
    public ServiceResponse removerTodosSociosEmpresa(long idEmpresa) throws Exception {
        Optional<Empresa> empresaData = empresaRepository.findById(idEmpresa);

        if(empresaData.isPresent()) {
            sociosRepository.deleteAllByEmpresa(idEmpresa);
        }else{
            throw new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value());
        }

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(null).build();
    }

    @Override
    public ServiceResponse calcularComprometimentoFinanceiro(long id) throws Exception {
        Optional<Empresa> empresa = Optional.ofNullable(empresaRepository.findById(id).orElseThrow(
                () -> new Exception(MensagensSistema.REGISTRO_NAO_ENCONTRADO.value())
        ));

        AtomicReference<Double> comprometimento = new AtomicReference<>(0.0);

        List<Bem> bensEmpresa = empresa.get().getPessoa().getBensImoveis();
        List<Socios> sociosEmpresa = empresa.get().getSocios();

        Double sumBensEmpresa = bensEmpresa.stream().mapToDouble(f -> f.getValor()).sum();
        comprometimento.updateAndGet(v -> v + sumBensEmpresa);

        sociosEmpresa.stream().forEach(
            socio -> {
                Double sumBensSocio = socio.getPessoa().getBensImoveis().stream().mapToDouble(f -> f.getValor()).sum();
                comprometimento.updateAndGet(v -> v + sumBensSocio);
            }
        );

        return ServiceResponse.builder()
                .status(true)
                .mensagem(MensagensSistema.REALIZADO_COM_SUCESSO.value())
                .data(comprometimento).build();
    }
}
