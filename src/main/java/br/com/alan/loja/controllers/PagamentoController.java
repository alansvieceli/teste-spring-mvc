package br.com.alan.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alan.loja.models.CarrinhoCompras;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes redirectAttributes) {			
		
		System.out.println(carrinho.getTotal());
		
		carrinho.limpar();
		
		redirectAttributes.addFlashAttribute("sucesso", "Pagamento Realizado com Sucesso");
		
		return new ModelAndView("redirect:/produtos");

	}

}
