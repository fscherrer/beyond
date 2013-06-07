package br.com.einsteinlimeira.beyond.mobile.services;

import java.util.List;

import android.content.Context;
import br.com.einsteinlimeira.beyond.mobile.dao.EventoDAO;
import br.com.einsteinlimeira.beyond.mobile.model.EventoDetalhadoDTO;
import br.com.einsteinlimeira.beyond.mobile.model.EventoSimplificadoDTO;
import br.com.einsteinlimeira.beyond.model.dto.EventoDTO;

/**
 * Provê serviços relacionados a Evento.
 */
public class EventoServices {

  /**
   * Insere o <code>evento</code>.
   * 
   * @param evento
   *   Evento a ser inserido.
   * @param context
   *   Contexto.
   *   
   * @return
   *   <code>True</code> se o evento foi inserido com sucesso.
   */
  public boolean inserir(EventoDTO evento, Context context) {
    return new EventoDAO().inserir(evento, context);
  }

  /**
   * Remove todos os Eventos.
   * 
   * @param context
   *   Contexto.
   */
  public void removerTodos(Context context) {
    new EventoDAO().removerTodos(context);
  }

  /**
   * Lista os Eventos.
   * 
   * @param context
   *   Contexto.
   *   
   * @return
   *   Lista de Eventos.
   */
  public List<EventoSimplificadoDTO> listar(Context context) {
    return new EventoDAO().listar(context);
  }
  
  /**
   * Retorna os detalhes do Evento.
   * 
   * @param context
   *   Context.
   *   
   * @return
   *   Detalhes do Evento.
   */
  public EventoDetalhadoDTO getDetalhes(int id, Context context) {
    return new EventoDAO().getDetalhes(id, context);
  }
}
