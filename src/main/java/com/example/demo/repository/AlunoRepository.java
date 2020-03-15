package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	Aluno findById(int id);
}
