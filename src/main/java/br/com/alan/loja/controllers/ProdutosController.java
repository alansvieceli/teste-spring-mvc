package br.com.alan.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alan.loja.daos.ProdutoDAO;
import br.com.alan.loja.infra.FileSaver;
import br.com.alan.loja.models.Produto;
import br.com.alan.loja.models.enums.TipoPreco;
import br.com.alan.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void InitBinde(WebDataBinder binder) {

		binder.addValidators(new ProdutoValidation());

	}

	@RequestMapping("/form")
	private ModelAndView form(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tiposPreco", TipoPreco.values());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value="home_produtos", allEntries=true) //limpando o cache
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult bindResult,
			RedirectAttributes redirectAttributes) {

		if (bindResult.hasErrors()) {
			return form(produto);
		}

		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		produtoDao.gravar(produto);

		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "Produto Cadastrado com sucesso");

		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		List<Produto> produtos = produtoDao.listar();

		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);

		return modelAndView;
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Integer id) {

		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");

		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);

		return modelAndView;

	}

}
