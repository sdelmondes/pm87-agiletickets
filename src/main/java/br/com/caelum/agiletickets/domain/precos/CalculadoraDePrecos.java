package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

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
		
	
	/*
		if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) || sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = sessao.getPreco();
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)) {
			if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 
*/
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