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
	
	static{
		
		eventos = new ArrayList<Evento>();
		casas = new ArrayList<Casa>();
		cidades = new ArrayList<Cidade>();
		
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
		
		Casa casaUm = new Casa(01, "Casa 1", 0, null, null, null, null, null, null);
		Casa casaDois = new Casa(02, "Casa 2", 0, null, null, null, null, null, null);
		Casa casaTres = new Casa(03, "Casa 3", 0, null, null, null, null, null, null);
		Casa casaQuatro = new Casa(04, "Casa 4", 0, null, null, null, null, null, null);
		Casa casaCinco = new Casa(05, "Casa 5", 0, null, null, null, null, null, null);
		Casa casaSeis = new Casa(07, "Casa 6", 0, null, null, null, null, null, null);
		
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
		Cidade cidadeCinco = new Cidade(4, "Americana", null);
		Cidade cidadeSeis = new Cidade(6, "Campinas", null);
		
		cidades.add(cidadeUm);
		cidades.add(cidadeDois);
		cidades.add(cidadeTres);
		cidades.add(cidadeQuatro);
		cidades.add(cidadeCinco);
		cidades.add(cidadeSeis);
		
		
		try {
			eventos.add(new Evento(1, "Evento 1", DateUtils.dateHourFormat.parse("08/02/2013 22:30"), 15d, null, bandasUm, null));
			eventos.add(new Evento(2, "Evento 2", DateUtils.dateHourFormat.parse("08/03/2013 21:30"), 20d, null, bandasDois, null));
			eventos.add(new Evento(3, "Evento 3", DateUtils.dateHourFormat.parse("08/04/2013 23:30"), 25d, null, bandasTres, null));
			eventos.add(new Evento(4, "Evento 4", DateUtils.dateHourFormat.parse("08/05/2013 22:00"), 15d, null, bandasUm, null));
			eventos.add(new Evento(5, "Evento 5", DateUtils.dateHourFormat.parse("08/06/2013 20:30"), 30d, null, bandasDois, null));
			eventos.add(new Evento(6, "Evento 6", DateUtils.dateHourFormat.parse("08/07/2013 21:30"), 20d, null, bandasQuatro, null));
			eventos.add(new Evento(7, "Evento 7", DateUtils.dateHourFormat.parse("08/08/2013 21:00"), 25d, null, bandasUm, null));
			eventos.add(new Evento(8, "Evento 8", DateUtils.dateHourFormat.parse("08/09/2013 19:30"), 15d, null, bandasDois, null));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
