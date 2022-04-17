package br.com.zup.edu.funcionariosmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
