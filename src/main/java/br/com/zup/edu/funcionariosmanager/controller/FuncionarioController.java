package br.com.zup.edu.funcionariosmanager.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;
import br.com.zup.edu.funcionariosmanager.repository.FuncionarioRepository;

@RestController
@RequestMapping(FuncionarioController.BASE_URI)
public class FuncionarioController {

    public final static String BASE_URI = "/funcionarios";

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<Void> aumentarSalario(@PathVariable Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                                                       .orElseThrow(
                                                           () -> new ResponseStatusException(
                                                               HttpStatus.NOT_FOUND,
                                                               "Não existe um funcionário com o id informado."
                                                           )
                                                       );

        funcionario.aumentarSalario();
        funcionarioRepository.save(funcionario);

        return ResponseEntity.noContent().build();
    }

}
