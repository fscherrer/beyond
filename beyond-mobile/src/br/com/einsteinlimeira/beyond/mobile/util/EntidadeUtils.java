package br.com.einsteinlimeira.beyond.mobile.util;

import java.util.List;

import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;

public class EntidadeUtils {
	public static String bandasToString(List<BandaDTO> bandas) {
		StringBuilder stringBuilderBandas = new StringBuilder();

		if (!bandas.isEmpty()) {
		  for(int i = 0 ; i < bandas.size(); i++){
		    stringBuilderBandas.append(bandas.get(i).getNome());
		  
		    if(i + 2 < bandas.size()){
		      stringBuilderBandas.append(", ");
		    }
		    else if(i + 1 < bandas.size()){
		      stringBuilderBandas.append(" e ");
		    }
		  }
		}

		return stringBuilderBandas.toString();
	}
}
