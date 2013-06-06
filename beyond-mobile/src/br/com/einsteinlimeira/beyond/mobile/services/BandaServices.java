package br.com.einsteinlimeira.beyond.mobile.services;

import br.com.einsteinlimeira.beyond.mobile.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.mobile.dao.EntidadeDAO;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;

public class BandaServices  extends EntidadeServices<BandaDTO>{

	@Override
	public EntidadeDAO<BandaDTO> getDAO() {
		return new BandaDAO();
	}

}
