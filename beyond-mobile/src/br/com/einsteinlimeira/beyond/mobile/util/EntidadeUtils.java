package br.com.einsteinlimeira.beyond.mobile.util;

import java.util.List;

import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;

public class EntidadeUtils {
	public static String bandasToString(List<BandaDTO> bandas) {
		StringBuilder stringBuilderBandas = new StringBuilder();

		if (!bandas.isEmpty()) {
			for (BandaDTO banda : bandas) {
				stringBuilderBandas.append(banda.getNome()).append(", ");
			}
			stringBuilderBandas.delete(stringBuilderBandas.length() - 2,
					stringBuilderBandas.length());
		}

		return stringBuilderBandas.toString();
	}
}
