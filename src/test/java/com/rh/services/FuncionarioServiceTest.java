package com.rh.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rh.entities.Funcionario;
import com.rh.exceptions.ResourceNotFoundException;
import com.rh.repositories.FuncionarioLogRepository;
import com.rh.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioLogRepository funcionarioLogRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private FuncionarioService funcionarioService;

    private Funcionario funcionario;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);

        funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("João Silva");
        funcionario.setEndereco("Rua ABC, 123");
        funcionario.setRamal("1234");
        funcionario.setEmailProfissional("joao.silva@empresa.com");
        funcionario.setDepartamento("TI");
        funcionario.setSalario(new BigDecimal("5000.00"));
        funcionario.setDataAdmissao(OffsetDateTime.now());

        when(objectMapper.writeValueAsString(any())).thenReturn("{}");
    }

    @Test
    void deveRetornarFuncionarioQuandoObterPorIdExistente() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Funcionario resultado = funcionarioService.obterPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoObterPorIdInexistente() {
        when(funcionarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            funcionarioService.obterPorId(99L);
        });
    }

    @Test
    void deveCriarFuncionarioERegistrarLog() throws Exception {
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario resultado = funcionarioService.criar(funcionario);

        assertNotNull(resultado);
        verify(funcionarioLogRepository, times(1)).save(any());
    }
}