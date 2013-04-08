/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.einsteinlimeira.beyond.web.services;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.web.model.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import java.util.List;

/**
 *
 * @author Iron
 */
public class BandaServices {

  public List<Banda> getBandas() throws DAOException {
    return new BandaDAO().listar();

  }

  public Banda getBanda(int id) throws DAOException {
    return new BandaDAO().getPeloId(id);
  }
}

