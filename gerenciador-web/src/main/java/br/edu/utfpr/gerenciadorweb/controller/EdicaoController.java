package br.edu.utfpr.gerenciadorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.gerenciadorweb.model.Edicao;
import br.edu.utfpr.gerenciadorweb.model.JSonResult;
import br.edu.utfpr.gerenciadorweb.service.EdicaoService;

@Controller
@RequestMapping("edicao")
public class EdicaoController {

	private final EdicaoService edicaoService;
	
	@Autowired
	public EdicaoController(EdicaoService edicaoService) {
		this.edicaoService = edicaoService;
	}

	@GetMapping
	public ModelAndView gerenciar() {
		ModelAndView mv = new ModelAndView("edicao");
		return mv;
	}
	
	@PostMapping("/salvar")
	public @ResponseBody JSonResult salvar(Edicao edicao) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(edicaoService.salvar(edicao));
		} catch (Exception e) {
			jr.setId(0);
			jr.setMensagem("Erro");
		}
		return jr;
	}
	
	@PostMapping("/deletar")
	public @ResponseBody JSonResult deletar(Edicao edicao) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(edicaoService.deletar(edicao));
		} catch (Exception e) {
			jr.setId(0);
			jr.setMensagem("Erro");
		}
		return jr;
	}
	
	@PostMapping("/publicar")
	public @ResponseBody JSonResult publicar(Edicao edicao) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(edicaoService.publicar(edicao));
		} catch (Exception e) {
			jr.setId(0);
			jr.setMensagem("Erro");
		}
		return jr;
	}
	
	@GetMapping("/listar")
	public @ResponseBody JSonResult listarTodas() {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(edicaoService.listarTodas());
		} catch (Exception e) {
			jr.setId(0);
			jr.setMensagem("erro");
		}
		return jr;
	}

}
