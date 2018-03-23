package br.edu.utfpr.gerenciadorweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.gerenciadorweb.enums.TipoArquivo;
import br.edu.utfpr.gerenciadorweb.model.Edicao;
import br.edu.utfpr.gerenciadorweb.model.JSonResult;
import br.edu.utfpr.gerenciadorweb.model.Noticia;
import br.edu.utfpr.gerenciadorweb.service.ArquivoService;
import br.edu.utfpr.gerenciadorweb.service.EdicaoService;
import br.edu.utfpr.gerenciadorweb.service.NoticiaService;

@Controller
@RequestMapping("noticias")
public class NoticiasController {

	@Autowired
	private ArquivoService arquivoService;
	@Autowired
	private NoticiaService noticiaService;
	@Autowired
	private EdicaoService edicaoService;

	@GetMapping("/edicao/{id}")
	public ModelAndView gerenciar(@PathVariable("id") Integer idEdicao) {
		ModelAndView mv = new ModelAndView("noticias");
		Edicao edicao = edicaoService.buscarPorId(idEdicao);
		mv.addObject("edicao", edicaoService.buscarPorId(idEdicao));
		return mv;
	}

	@PostMapping("/salvar")
	public @ResponseBody JSonResult salvar(Noticia noticia) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setIdGerado(noticiaService.salvar(noticia));
		} catch (Exception e) {
			jr.setId(0);
		}

		return jr;
	}

	@PostMapping("/deletar")
	public @ResponseBody JSonResult deletar(@Valid Noticia noticia) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(noticiaService.deletar(noticia));
		} catch (Exception e) {
			jr.setId(0);
			jr.setMensagem("Erro");
		}
		return jr;
	}

	@GetMapping("/edicao/{id}/listar")
	public @ResponseBody JSonResult listar(@PathVariable("id") Integer idEdicao) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(noticiaService.listarPorEdicao(idEdicao));
		} catch (Exception e) {
			e.printStackTrace();
			jr.setId(0);
		}
		return jr;
	}

	@PostMapping("/upload/noticia/{id}")
	public @ResponseBody JSonResult uploadArquivo(@PathVariable("id") Integer idNoticia,
			@RequestParam("file") MultipartFile mfile, RedirectAttributes redirectAttributes) {
		JSonResult jr = new JSonResult();
		try {
			arquivoService.salvar(mfile, TipoArquivo.NOTICIA.getValue(), idNoticia);
			jr.setId(1);
			jr.setMensagem("Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			jr.setId(0);
			jr.setMensagem("Erro");
		}

		return jr;
	}
	
	@PostMapping("/uploadimagem/noticia/{id}")
	public @ResponseBody JSonResult uploadImagem(@PathVariable("id") Integer idNoticia,
			@RequestParam("file") MultipartFile mfile, RedirectAttributes redirectAttributes) {
		JSonResult jr = new JSonResult();
		try {
			arquivoService.salvar(mfile, TipoArquivo.IMAGEM.getValue(), idNoticia);
			jr.setId(1);
			jr.setMensagem("Sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			jr.setId(0);
			jr.setMensagem("Erro");
		}

		return jr;
	}
	
	@GetMapping("/listar-imagens/noticia/{id}")
	public @ResponseBody JSonResult listarImagensNoticia(@PathVariable("id") Integer idNoticia) {
		JSonResult jr = new JSonResult();
		try {
			jr.setId(1);
			jr.setResultObject(arquivoService.listarPorNoticia(idNoticia));
		} catch (Exception e) {
			e.printStackTrace();
			jr.setId(0);
			jr.setMensagem("Erro");
		}
		return jr;
	}
}
