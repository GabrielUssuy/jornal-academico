package br.edu.utfpr.gerenciadorbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.gerenciadorbackend.model.Edicao;
import br.edu.utfpr.gerenciadorbackend.service.EdicaoService;

@RestController
@RequestMapping("edicao")
public class EdicaoController {

	private final EdicaoService edicaoService;

	@Autowired
	public EdicaoController(EdicaoService edicaoService) {
		this.edicaoService = edicaoService;
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody Edicao edicao){
		try {
			return ResponseEntity.ok(edicaoService.salvar(edicao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<?> deletar(@RequestBody Edicao edicao){
		try {
			return ResponseEntity.ok(edicaoService.deletar(edicao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarTodas(){
		try {
			return ResponseEntity.ok(edicaoService.listarTodas());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
