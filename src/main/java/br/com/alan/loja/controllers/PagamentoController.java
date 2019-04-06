package br.com.alan.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alan.loja.models.CarrinhoCompras;
import br.com.alan.loja.models.Usuario;
import br.com.alan.loja.utils.DadosPagamento;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	final String uri = "http://book-payment.herokuapp.com/payment";

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MailSender sender;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario,
			RedirectAttributes redirectAttributes) {

		return () -> {

			System.out.println(carrinho.getTotal());

			try {

				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),
						String.class);
				carrinho.limpar();

				// envia email para o usuário
				enviaEmailCompraProduto(usuario);

				redirectAttributes.addFlashAttribute("sucesso", response);

			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("falha", "Erro ao efetuar pagamento");

			}

			return new ModelAndView("redirect:/produtos");
		};

	}

	private void enviaEmailCompraProduto(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso");
		email.setTo(usuario.getEmail());
		email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
		email.setFrom("compras@casadocodigo.com.br");
		
		sender.send(email);
	}

}
