package com.example.demo.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Aluno;
import com.example.demo.models.AlunoProfessor;
import com.example.demo.models.Professor;
import com.example.demo.repository.AlunoProfessorRepository;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.ProfessorRepository;

@Controller
public class Professores {

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	AlunoProfessorRepository alunoProfessorRepository;

	@RequestMapping(value = "/")
	public String index() {
		return "professor/login";
	}

	@PostMapping(value = "/")
	public String login(String senha, String username,Model model) {
		try {
			Professor prof = professorRepository.findByUsernameSenha(username, senha);
			System.out.println("Username" + prof.getUsername() + " Senha: " + prof.getSenha());

			if (prof != null) {
				return "redirect:professor/inicio/" + prof.getIdProfessor();
			} else {
				model.addAttribute("logError","logError");
				return "professor/login";
			}
		} catch (NullPointerException e) {
			model.addAttribute("logError","logError");
			return "professor/login";
		}
	}

	public boolean verificaUsuario(Professor p) {
		Professor prof = professorRepository.findByUsername(p.getUsername());

		if (prof == null) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping(value = "/professor/cadastro")
	public String cadastraProfessor() {
		return "professor/cadastro";
	}

	@RequestMapping(value = "/professor/cadastro", method = RequestMethod.POST)
	public String cadastraProfessor(Professor p) {
		if (verificaUsuario(p)) {
			professorRepository.save(p);
			return "professor/login";
		} else {
			return "professor/cadastro";
		}
	}

	@GetMapping(value = "professor/inicio/{id}")
	public ModelAndView inicio(@PathVariable(value = "id") int id) {

		ModelAndView mv = new ModelAndView("professor/inicio");
		List<AlunoProfessor> a = alunoProfessorRepository.findAll();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		for (AlunoProfessor alunoProfessor : a) {
			if(alunoProfessor.getProfessor().getIdProfessor()==id)
				alunos.add(alunoRepository.findById(alunoProfessor.getAluno().getIdAluno()));
		}
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("professor", professorRepository.findById(id));
		hm.put("alunos", alunos);
		mv.addAllObjects(hm);
		return mv;
	}
}
