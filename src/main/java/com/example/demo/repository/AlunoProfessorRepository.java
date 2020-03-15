package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.AlunoProfessor;

public interface AlunoProfessorRepository extends JpaRepository<AlunoProfessor, Integer>{
	@Query(value = "select * from aluno_professor where professor_id_professor=?1", nativeQuery = true)
	AlunoProfessor findAlunoProfessor(int id);
	
	@Query(value = "select * from aluno_professor where aluno_id_aluno=?1 and professor_id_professor=?2", nativeQuery = true)
	AlunoProfessor findAluno(int idAluno, int idProfessor);
}
