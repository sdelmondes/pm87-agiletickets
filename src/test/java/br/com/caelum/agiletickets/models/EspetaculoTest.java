package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}
	
	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void DeveCriarUnicaSessaoSeInicioIgualFim() {
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 11, 24);
		LocalDate fim = new LocalDate(2016, 11, 24);
		LocalTime horario = new LocalTime(21, 0);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);

		Assert.assertNotNull(sessoes);
		Assert.assertEquals(1, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("24/11/16", sessao.getDia());
		Assert.assertEquals("21:00", sessao.getHora());
		Assert.assertEquals(ivete, sessao.getEspetaculo());
	}
	
	@Test
	public void deveCriar3EspetaculosSemanaisDoDia9a23(){
		Espetaculo ivete = new Espetaculo();
		LocalDate inicio = new LocalDate(2011, 01, 9);
		LocalDate fim = new LocalDate(2011, 01, 23);
		LocalTime horario = new LocalTime(17, 0);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);

		Assert.assertNotNull(sessoes);
		Assert.assertEquals(3, sessoes.size());
		Sessao sessao = sessoes.get(0);
		Assert.assertEquals("09/01/11", sessao.getDia());
		Assert.assertEquals("17:00", sessao.getHora());
		Assert.assertEquals(ivete, sessao.getEspetaculo());
		sessao = sessoes.get(1);
		Assert.assertEquals("16/01/11", sessao.getDia());
		Assert.assertEquals("17:00", sessao.getHora());
		Assert.assertEquals(ivete, sessao.getEspetaculo());
		sessao = sessoes.get(2);
		Assert.assertEquals("23/01/11", sessao.getDia());
		Assert.assertEquals("17:00", sessao.getHora());
		Assert.assertEquals(ivete, sessao.getEspetaculo());
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
