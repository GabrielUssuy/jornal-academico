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

import br.edu.utfpr.gerenciadorbackend.model.Arquivo;
import br.edu.utfpr.gerenciadorbackend.service.ArquivoService;

@RestController
@RequestMapping("arquivo")
public class ArquivoController {

	private final ArquivoService arquivoService;

	@Autowired
	public ArquivoController(ArquivoService arquivoService) {
		this.arquivoService = arquivoService;
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvar(@RequestBody Arquivo arquivo) {
		try {
			return ResponseEntity.ok(arquivoService.salvar(arquivo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	
	@DeleteMapping("/deletar")
	public ResponseEntity<?> deletar(@RequestBody Arquivo arquivo){
		try {
			return ResponseEntity.ok(arquivoService.deletar(arquivo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
