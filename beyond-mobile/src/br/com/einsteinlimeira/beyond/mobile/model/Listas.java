package br.com.einsteinlimeira.beyond.mobile.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Evento;

public class Listas {
	
	public static List<Evento> eventos;
	public static List<Casa> casas;
	public static List<Cidade> cidades;
	public static List<Banda> bandas;
	
	static{
		
		eventos = new ArrayList<Evento>();
		casas = new ArrayList<Casa>();
		cidades = new ArrayList<Cidade>();
		bandas = new ArrayList<Banda>();
		
		Banda bandaUm = new Banda(1, "Unisonic", null, null, null, null, null); 
		Banda bandaDois = new Banda(2, "Avantasia", null, null, null, null, null); 
		Banda bandaTres = new Banda(3, "Edguy", null, null, null, null, null); 
		Banda bandaQuatro = new Banda(4, "Unidroid", null, null, null, null, null); 
		Banda bandaCinco = new Banda(5, "Hells Bells", null, null, null, null, null); 
		Banda bandaSeis = new Banda(6, "Highway", null, null, null, null, null); 
		Banda bandaSete = new Banda(7, "Thunder", null, null, null, null, null);
		Banda bandaOito = new Banda(8, "Shook me all", null, null, null, null, null);
		
		List<Banda> bandasUm = new ArrayList<Banda>();
		bandasUm.add(bandaUm);
		
		List<Banda> bandasDois = new ArrayList<Banda>();
		bandasDois.add(bandaUm);
		bandasDois.add(bandaDois);
		
		List<Banda> bandasTres = new ArrayList<Banda>();
		bandasTres.add(bandaTres);
		bandasTres.add(bandaQuatro);
		bandasTres.add(bandaCinco);
		bandasTres.add(bandaSeis);
		
		List<Banda> bandasQuatro = new ArrayList<Banda>();
		bandasQuatro.add(bandaSete);
		bandasQuatro.add(bandaOito);
		
		bandas.add(bandaUm);
		bandas.add(bandaDois);
		bandas.add(bandaTres);
		bandas.add(bandaQuatro);
		bandas.add(bandaCinco);
		bandas.add(bandaSeis);
		bandas.add(bandaSete);
		bandas.add(bandaOito);
		
		Casa casaUm = new Casa();
		Casa casaDois = new Casa();
		Casa casaTres = new Casa();
		Casa casaQuatro = new Casa();
		Casa casaCinco = new Casa();
		Casa casaSeis = new Casa();
		
		casas.add(casaUm);
		casas.add(casaDois);
		casas.add(casaTres);
		casas.add(casaQuatro);
		casas.add(casaCinco);
		casas.add(casaSeis);
		
		Cidade cidadeUm = new Cidade(1, "São Paulo", null);
		Cidade cidadeDois = new Cidade(2, "Limeira", null);
		Cidade cidadeTres = new Cidade(3, "Iracemápol(i|e)s", null);
		Cidade cidadeQuatro = new Cidade(4, "Bate-pau", null);
		Cidade cidadeCinco = new Cidade(5, "Americana", null);
		Cidade cidadeSeis = new Cidade(6, "Campinas", null);
		
		cidades.add(cidadeUm);
		cidades.add(cidadeDois);
		cidades.add(cidadeTres);
		cidades.add(cidadeQuatro);
		cidades.add(cidadeCinco);
		cidades.add(cidadeSeis);
		cidades.add(new Cidade(7, "Teste 7", null));
		cidades.add(new Cidade(8, "Teste 8", null));
		cidades.add(new Cidade(9, "Teste 9", null));
		cidades.add(new Cidade(10, "Teste 10", null));
		cidades.add(new Cidade(11, "Teste 11", null));
		cidades.add(new Cidade(12, "Teste 12", null));
		cidades.add(new Cidade(13, "Teste 13", null));
		cidades.add(new Cidade(14, "Teste 14", null));
		
		
		try {
			eventos.add(new Evento(1, "Evento 1", DateUtils.dateHourFormat.parse("08/02/2013 22:30"), 15d, null, bandasUm));
			eventos.add(new Evento(2, "Evento 2", DateUtils.dateHourFormat.parse("08/03/2013 21:30"), 20d, null, bandasDois));
			eventos.add(new Evento(3, "Evento 3", DateUtils.dateHourFormat.parse("08/04/2013 23:30"), 25d, null, bandasTres));
			eventos.add(new Evento(4, "Evento 4", DateUtils.dateHourFormat.parse("08/05/2013 22:00"), 15d, null, bandasUm));
			eventos.add(new Evento(5, "Evento 5", DateUtils.dateHourFormat.parse("08/06/2013 20:30"), 30d, null, bandasDois));
			eventos.add(new Evento(6, "Evento 6", DateUtils.dateHourFormat.parse("08/07/2013 21:30"), 20d, null, bandasQuatro));
			eventos.add(new Evento(7, "Evento 7", DateUtils.dateHourFormat.parse("08/08/2013 21:00"), 25d, null, bandasUm));
			eventos.add(new Evento(8, "Evento 8", DateUtils.dateHourFormat.parse("08/09/2013 19:30"), 15d, null, bandasDois));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
