package br.edu.utfpr.gerenciadorbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.gerenciadorbackend.model.Noticia;
import br.edu.utfpr.gerenciadorbackend.service.NoticiaService;

@RestController
@RequestMapping("noticia")
public class NoticiaController {

	private final NoticiaService noticiaService;
	
	@Autowired
	public NoticiaController(NoticiaService noticiaService) {
		this.noticiaService = noticiaService;
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody Noticia noticia){
		try {
			return ResponseEntity.ok(noticiaService.salvar(noticia).getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<?> deletar(@RequestBody Noticia noticia){
		try {
			return ResponseEntity.ok(noticiaService.deletar(noticia));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/edicao/{id}/listar")
	public ResponseEntity<?> listar(@PathVariable("id") Integer idEdicao){
		try {
			return ResponseEntity.ok(noticiaService.listarPorEdicao(idEdicao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
