package br.com.alan.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.alan.loja.daos.ProdutoDAO;
import br.com.alan.loja.models.CarrinhoCompras;
import br.com.alan.loja.models.CarrinhoItem;
import br.com.alan.loja.models.Produto;
import br.com.alan.loja.models.enums.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");

		CarrinhoItem carrinhoItem = criarItem(produtoId,tipoPreco);
		
		carrinho.add(carrinhoItem);
		
		return modelAndView;

	}

	private CarrinhoItem criarItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}

}
