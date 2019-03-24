package br.com.alan.loja.utils;

import java.math.BigDecimal;

public class DadosPagamento {

	private BigDecimal value;
	
	public DadosPagamento() {
		
	}

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

}
