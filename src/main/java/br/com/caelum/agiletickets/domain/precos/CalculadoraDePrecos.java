package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		
		BigDecimal preco= sessao.getPreco();
		
		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:
		case SHOW:
			preco = calculaPrecoPorLotacao(preco, sessao, 0.05, 0.10);
			break;
		case BALLET:
			preco = calculaPrecoPorLotacao(preco,sessao, 0.50, 0.20);
			preco = calculaPrecoPorDuracao(preco,sessao, 60, 0.10);
			break;
		case ORQUESTRA:
			preco = calculaPrecoPorLotacao(preco,sessao, 0.50, 0.20);
			preco = calculaPrecoPorDuracao(preco,sessao, 60, 0.10);
			break;
		default:
			preco = sessao.getPreco();
			break;
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	public static BigDecimal calculaPrecoPorLotacao (BigDecimal preco, Sessao sessao, double fatorLotacao, double fatorEncarecedor){
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= fatorLotacao){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(fatorEncarecedor)));
		} 
		return preco;
	}
	
	public static BigDecimal calculaPrecoPorDuracao (BigDecimal preco, Sessao sessao, int minutos, double fatorEncarecedor){
		
		if(sessao.getDuracaoEmMinutos() > minutos){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(fatorEncarecedor)));
		}
		return preco;
	}

}