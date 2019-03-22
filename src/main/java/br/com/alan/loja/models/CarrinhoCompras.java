package br.com.alan.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.alan.loja.models.enums.TipoPreco;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {
	private static final long serialVersionUID = -713263644275715170L;

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public void add(CarrinhoItem carrinhoItem) {

		itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);

	}

	public Integer getQuantidade(CarrinhoItem item) {

		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(this.getQuantidade(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(this.getTotal(item));
		}

		return total;
	}

	public void limpar() {
		itens.clear();

	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto(produtoId);
		
		itens.remove(new CarrinhoItem(produto, tipoPreco));
		
	}

}
