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
		
		
		Banda banda = new Banda(1, "Unisonic", null, null, null, null); 
		Banda bandaDois = new Banda(2, "Avantasia", null, null, null, null); 
		Banda bandaTres = new Banda(3, "Edguy", null, null, null, null); 
		Banda bandaQuatro = new Banda(4, "Unidroid", null, null, null, null); 
		
		try {
			eventos.add(new Evento(1, DateUtils.dateFormat.parse("08/02/2013"), 15d, null, banda));
			eventos.add(new Evento(2, DateUtils.dateFormat.parse("08/03/2013"), 16d, null, bandaDois));
			eventos.add(new Evento(3, DateUtils.dateFormat.parse("08/04/2013"), 17d, null, bandaTres));
			eventos.add(new Evento(4, DateUtils.dateFormat.parse("08/05/2013"), 18d, null, bandaQuatro));
			eventos.add(new Evento(5, DateUtils.dateFormat.parse("08/06/2013"), 19d, null, null));
			eventos.add(new Evento(6, DateUtils.dateFormat.parse("08/07/2013"), 20d, null, null));
			eventos.add(new Evento(7, DateUtils.dateFormat.parse("08/08/2013"), 21d, null, null));
			eventos.add(new Evento(8, DateUtils.dateFormat.parse("08/09/2013"), 22d, null, null));
			eventos.add(new Evento(9, DateUtils.dateFormat.parse("08/10/2013"), 23d, null, null));	
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
