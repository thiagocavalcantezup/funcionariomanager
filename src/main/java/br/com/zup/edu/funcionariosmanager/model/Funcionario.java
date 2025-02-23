package br.com.zup.edu.funcionariosmanager.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Column(nullable = false)
    private BigDecimal salario;

    public Funcionario(String nome, String cpf, String endereco, LocalDate dataAdmissao,
                       BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Funcionario() {}

    public void aumentarSalario() {
        if (!isFuncionarioHaUmAno()) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Impossível atualizar funcionário. Ele não está na empresa há um ano ou mais."
            );
        }

        salario = salario.multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_EVEN);
    }

    public boolean isFuncionarioHaUmAno() {
        return ChronoUnit.YEARS.between(dataAdmissao, LocalDate.now()) >= 1;
    }

    public Long getId() {
        return id;
    }

}
