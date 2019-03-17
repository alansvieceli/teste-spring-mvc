package br.com.alan.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alan.loja.daos.ProdutoDAO;
import br.com.alan.loja.models.Produto;
import br.com.alan.loja.models.enums.TipoPreco;
import br.com.alan.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@InitBinder
	public void InitBinde(WebDataBinder binder) {
	
		binder.addValidators( new ProdutoValidation());
		
	}

	@RequestMapping("/form")
	private ModelAndView form(Produto produto) {
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tiposPreco", TipoPreco.values());
		
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Produto produto, BindingResult bindResult, RedirectAttributes redirectAttributes) {
		
		if (bindResult.hasErrors()) {
			return form(produto);
		}

		produtoDao.gravar(produto);
		
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "Produto Cadastrado com sucesso");
		
		return modelAndView;

	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		
		List<Produto> produtos = produtoDao.listar();
		
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}

}
