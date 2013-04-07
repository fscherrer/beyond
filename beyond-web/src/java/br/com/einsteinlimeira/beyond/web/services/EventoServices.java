package br.com.einsteinlimeira.beyond.web.services;

import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.model.dao.EventoDAO;
import java.util.List;

/**
 *
 * @author Iron
 */
public class EventoServices {
  
  public List<Evento> getEventos() throws DAOException {
    return new EventoDAO().listar();
    
}
  public Evento getEvento(int id) throws DAOException{
    return new EventoDAO().getPeloId(id);
}}
