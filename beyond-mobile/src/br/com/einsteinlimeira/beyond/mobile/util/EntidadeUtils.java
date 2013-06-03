package br.com.einsteinlimeira.beyond.mobile.util;

import java.util.List;

import br.com.einsteinlimeira.beyond.model.Banda;

public class EntidadeUtils {
	public static String bandasToString(List<Banda> bandas) {
		StringBuilder stringBuilderBandas = new StringBuilder();

		if (!bandas.isEmpty()) {
			for (Banda banda : bandas) {
				stringBuilderBandas.append(banda.getNome()).append(", ");
			}
			stringBuilderBandas.delete(stringBuilderBandas.length() - 2,
					stringBuilderBandas.length());
		}

		return stringBuilderBandas.toString();
	}
}
