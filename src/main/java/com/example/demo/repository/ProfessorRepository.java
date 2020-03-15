package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
	
	Professor findById(int id);
	
	@Query(value="select * from professor where username=?1", nativeQuery = true)
	Professor findByUsername(String username);
	
	@Query(value = "SELECT * FROM professor where username = ?1 and senha = ?2", nativeQuery = true)
	Professor findByUsernameSenha(String username, String senha);
}
