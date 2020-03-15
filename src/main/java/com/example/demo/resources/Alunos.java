package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Aluno;
import com.example.demo.models.AlunoProfessor;
import com.example.demo.models.Professor;
import com.example.demo.repository.AlunoProfessorRepository;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.ProfessorRepository;

@Controller
public class Alunos {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	AlunoProfessorRepository alunoProfessor;

	@Autowired
	ProfessorRepository professorRepository;

	@RequestMapping(value = "/cadastroAluno/{id}")
	public String cadastraAluno() {
		return "/aluno/cadastro";
	}

	public boolean verificaAluno(Aluno a, int id) {
		AlunoProfessor ap = alunoProfessor.findAluno(a.getIdAluno(), id);
		if (ap == null) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping(value = "/cadastroAluno/{id}")
	public String cadastraAluno(Aluno a, @PathVariable(value = "id") int id) {
		System.out.println("Nome: " + a.getNome());

		alunoRepository.save(a);
		Professor p = professorRepository.findById(id);
		AlunoProfessor ap = new AlunoProfessor();
		ap.setAluno(a);
		ap.setProfessor(p);
		alunoProfessor.save(ap);
		return "redirect:/professor/inicio/" + id;

	}

	@GetMapping(value = "/deletaAluno/{id}/{idP}")
	public String deletaAluno(@PathVariable(value = "id") int id, @PathVariable(value = "idP") int idp) {
		AlunoProfessor ap = alunoProfessor.findAluno(id, idp);
		alunoProfessor.delete(ap);
		Aluno a = alunoRepository.findById(id);
		alunoRepository.delete(a);
		return "redirect:/professor/inicio/" + idp;
	}

	@RequestMapping(value = "/updateAluno/{id}/{idP}")
	public String updateAluno() {
		return "/aluno/edita";
	}

	@PostMapping(value = "/updateAluno/{id}/{idP}")
	public String updateAluno(Aluno a, @PathVariable(value = "id") int id, @PathVariable(value = "idP") int idP) {
		Aluno aluno = alunoRepository.findById(id);
		System.out.println("Nome: "+ aluno.getNome());
		aluno.setIdade(a.getIdade());
		aluno.setNome(a.getNome());
		alunoRepository.save(aluno);
		return "redirect:/professor/inicio/" + idP;

	}
}
