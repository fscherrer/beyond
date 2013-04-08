/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.einsteinlimeira.beyond.web.services;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.web.model.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import java.util.List;

/**
 *
 * @author Iron
 */
public class CasaServices {
    
  public List<Casa> getEventos() throws DAOException {
    return new CasaDAO().listar();
    
}
  public Casa getEvento(int id) throws DAOException{
    return new CasaDAO().getPeloId(id);
}}
  

