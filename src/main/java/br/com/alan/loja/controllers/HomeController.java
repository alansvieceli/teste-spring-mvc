package br.com.alan.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alan.loja.daos.ProdutoDAO;
import br.com.alan.loja.models.Produto;

@Controller
public class HomeController {

	@Autowired
	ProdutoDAO produtosDao;

	@RequestMapping("/")
	@Cacheable(value="home_produtos")
	public ModelAndView index() {
		List<Produto> produtos = produtosDao.listar();

		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;

	}

}
