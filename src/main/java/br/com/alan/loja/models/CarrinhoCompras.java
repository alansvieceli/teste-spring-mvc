package br.com.alan.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

	public void add(CarrinhoItem carrinhoItem) {

		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);

	}

	private int getQuantidade(CarrinhoItem key) {

		if (!itens.containsKey(key)) {
			itens.put(key, 0);
		}
		return itens.get(key);
	}

	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

}
