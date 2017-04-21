package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Usuario;
import com.app.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value="/users")
	public String usuario(Model model){
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("alertSuccess", true);
		return "usuario/usuarios";
	}
	
	@GetMapping("/user/new")
	public String createView(Model model){
		model.addAttribute("usuario", new Usuario());
		return "usuario/new";
	}
	
	@PostMapping("/user/new")
	public String create(Model model, @ModelAttribute Usuario u){
		try{
			usuarioRepository.save(u);
			model.addAttribute("msg", "Usuário cadastrado com sucesso!");
			model.addAttribute("alert-sucess", false);
			return "redirect:/users";
		} catch(Exception ex){
			model.addAttribute("msg", ex.getMessage());
			model.addAttribute("alert-error", false);
			return "redirect:/users/new";
			
		}
	}
	
	@GetMapping("/user/delete/{id}")
	public String delete(Model model, @PathVariable String id){
		try{
			usuarioRepository.delete(new Long(id));
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return "redirect:/users";
	}
	
	@GetMapping("/user/edit/{id}")
	public String editView(Model model, @PathVariable String id){
		model.addAttribute("usuario", usuarioRepository.findOne(new Long(id)));
		return "usuario/edit";
	}
	
	@PostMapping("/user/edit")
	public String edit(Model model, @ModelAttribute Usuario u){
		usuarioRepository.save(u);
		return "redirect:/users";
	}
	
}
