package br.com.einsteinlimeira.beyond.mobile.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Evento;

public class ListaEvento {
	
	public static List<Evento> eventos;
	
	static{
		
		eventos = new ArrayList<Evento>();
		
		
		Banda bandaUm = new Banda(1, "Unisonic", null, null, null, null,null); 
		Banda bandaDois = new Banda(2, "Avantasia", null, null, null, null,null); 
		Banda bandaTres = new Banda(3, "Edguy", null, null, null, null,null); 
		Banda bandaQuatro = new Banda(4, "Unidroid", null, null, null, null,null); 
		Banda bandaCinco = new Banda(5, "Hells Bells", null, null, null, null,null); 
		Banda bandaSeis = new Banda(6, "Highway", null, null, null, null,null); 
		Banda bandaSete = new Banda(7, "Thunder", null, null, null, null,null);
		Banda bandaOito = new Banda(8, "Shook me all", null, null, null, null,null);
		
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
