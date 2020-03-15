package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AlunoProfessor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAlunoProfessor;
	
	@OneToOne
	private Professor professor;
	
	@OneToOne
	private Aluno aluno;

	public int getIdAlunoProfessor() {
		return idAlunoProfessor;
	}

	public void setIdAlunoProfessor(int idAlunoProfessor) {
		this.idAlunoProfessor = idAlunoProfessor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}
