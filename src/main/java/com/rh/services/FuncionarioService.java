package com.rh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rh.exceptions.ResourceNotFoundException;
import com.rh.entities.Funcionario;
import com.rh.entities.FuncionarioLog;
import com.rh.entities.TipoAcao;
import com.rh.repositories.FuncionarioLogRepository;
import com.rh.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioLogRepository funcionarioLogRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Funcionario obterPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + id));
    }

    @Transactional
    public Funcionario criar(Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        registrarLog(funcionarioSalvo, TipoAcao.INCLUSAO);
        return funcionarioSalvo;
    }

    @Transactional
    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = obterPorId(id);

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setEndereco(funcionarioAtualizado.getEndereco());
        funcionarioExistente.setRamal(funcionarioAtualizado.getRamal());
        funcionarioExistente.setEmailProfissional(funcionarioAtualizado.getEmailProfissional());
        funcionarioExistente.setDepartamento(funcionarioAtualizado.getDepartamento());
        funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionarioExistente);
        registrarLog(funcionarioSalvo, TipoAcao.ATUALIZACAO);
        return funcionarioSalvo;
    }

    @Transactional
    public void deletar(Long id) {
        Funcionario funcionario = obterPorId(id);
        registrarLog(funcionario, TipoAcao.REMOCAO);
        funcionarioRepository.deleteById(id);
    }

    private void registrarLog(Funcionario funcionario, TipoAcao tipoAcao) {
        try {
            String dadosJson = objectMapper.writeValueAsString(funcionario);
            FuncionarioLog log = new FuncionarioLog(funcionario, tipoAcao, dadosJson);
            funcionarioLogRepository.save(log);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao processar JSON para log", e);
        }
    }
}