package br.com.alan.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alan.loja.daos.ProdutoDAO;
import br.com.alan.loja.models.Produto;
import br.com.alan.loja.models.enums.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("/produtos/form")
	private ModelAndView form() {
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tiposPreco", TipoPreco.values());
		
		return modelAndView;
	}

	@RequestMapping("/produtos")
	public String gravar(Produto produto) {

		produtoDao.gravar(produto);
		
		return "produtos/ok";

	}

}
