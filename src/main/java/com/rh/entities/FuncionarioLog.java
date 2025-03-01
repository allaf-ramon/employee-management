package com.rh.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "funcionario_logs")
@Data
@NoArgsConstructor
public class FuncionarioLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long funcionarioId;
    private String nome;
    private String endereco;
    private String ramal;
    private String emailProfissional;
    private String departamento;
    private BigDecimal salario;
    private OffsetDateTime dataAdmissao;

    @Enumerated(EnumType.STRING)
    private TipoAcao tipoAcao;

    @Column(columnDefinition = "TEXT")
    private String dadosJson;

    private OffsetDateTime dataLog = OffsetDateTime.now();

    public FuncionarioLog(Funcionario funcionario, TipoAcao tipoAcao, String dadosJson) {
        this.funcionarioId = funcionario.getId();
        this.nome = funcionario.getNome();
        this.endereco = funcionario.getEndereco();
        this.ramal = funcionario.getRamal();
        this.emailProfissional = funcionario.getEmailProfissional();
        this.departamento = funcionario.getDepartamento();
        this.salario = funcionario.getSalario();
        this.dataAdmissao = funcionario.getDataAdmissao();
        this.tipoAcao = tipoAcao;
        this.dadosJson = dadosJson;
    }
}