package br.com.zup.edu.funcionariosmanager;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;
import br.com.zup.edu.funcionariosmanager.repository.FuncionarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;

    public DataLoader(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Funcionario funcionarioAntigo = new Funcionario(
            "Thiago", "809.763.970-05", "Rua X, Bairro Y, Cidade Z", LocalDate.now().minusYears(2),
            new BigDecimal("1000.0")
        );
        Funcionario funcionarioRecente = new Funcionario(
            "José", "738.121.230-70", "Rua X, Bairro Y, Cidade Z", LocalDate.now(),
            new BigDecimal("1000.0")
        );

        funcionarioRepository.save(funcionarioAntigo);
        funcionarioRepository.save(funcionarioRecente);
    }

}
