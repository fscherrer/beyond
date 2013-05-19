package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.dao.EventoDAO;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Evento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação padrão de {@link EventoDAO}.
 */
public class EventoDAOImpl implements EventoDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(EventoDAOImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Evento entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Evento entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Evento getPeloId(int id) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Evento> listar() throws DAOException {
    try {
      return getEventos(BancoDeDados.getInstancia().executarQuery(""
          + " select "
          + "   evento.*, "
          + "   eventobanda.bandaid as bandaid "
          + " from "
          + "   evento as evento "
          + "     join eventobanda as eventobanda"
          + "       on eventobanda.eventoid = evento.id "
          + "  order by "
          + "    evento.dataHora, "
          + "    evento.nome"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Eventos";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Evento entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Evento> getEventos(ResultSet resultSet) throws DAOException {
    try {
      List<Evento> eventos = new ArrayList<Evento>();

      int eventoId;
      String eventoNome;
      double eventoValor;
      Date eventoDataHora;
      int eventoCasaId;
      Integer eventoBandaId;
      Banda banda;
      Casa casa;
      Evento evento = null;

      CasaDAO casaDAO = DAOFactory.getFactory().getCasaDAO();
      BandaDAO bandaDAO = DAOFactory.getFactory().getBandaDAO();

      int eventoIdAtual = 0;

      while (resultSet.next()) {
        eventoId = resultSet.getInt("Id");
        eventoNome = resultSet.getString("nome");
        eventoValor = resultSet.getDouble("valor");
        eventoDataHora = resultSet.getTimestamp("dataHora");
        eventoCasaId = resultSet.getInt("casaid");
        eventoBandaId = resultSet.getInt("bandaid");

        casa = casaDAO.getPeloId(eventoCasaId);
        banda = bandaDAO.getPeloId(eventoBandaId);

        if (eventoId != eventoIdAtual) {
          evento = new Evento(eventoId, eventoNome, eventoDataHora, eventoValor, casa,
              new ArrayList<Banda>());
          eventos.add(evento);
        }

        evento.getBandas().add(banda);
        eventoIdAtual = eventoId;
      }

      return eventos;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair eventos do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Evento> getEventos(int[] idsCasas, int[] idsBandas) throws DAOException {
    StringBuilder where = new StringBuilder();

    // filtro casas
    if (idsCasas != null && idsCasas.length > 0) {
      where.append(DAOUtils.getFiltroQuery("evento.casaid", idsCasas));
    }

    // filtro bandas (especial, já que um evento pode ter várias bandas)
    if (idsBandas != null && idsBandas.length > 0) {
      if (where.length() > 0) {
        where.append(" and ");
      }

      // não posso simplesmente filtrar eventobanda.bandaid pois um evento pode ter várias bandas
      // no caso de filtrado "Stratovários", as demais bandas de um evento em que "Stratovários"
      // participa devem aparecer junto
      where.append("exists (select 0 from eventobanda where eventoid = evento.id and ");
      where.append(DAOUtils.getFiltroQuery("eventobanda.bandaid", idsBandas));
      where.append(")");
    }
    
    if(where.length() > 0){
      where.insert(0, " where ");
    }

    try {
      String query = ""
          + " select "
          + "   evento.*, "
          + "   eventobanda.bandaid as bandaid "
          + " from "
          + "   evento as evento "
          + "     join eventobanda as eventobanda"
          + "       on eventobanda.eventoid = evento.id "
          + "     join casa as casa "
          + "       on casa.id = evento.casaid "
          + where.toString()
          + " order by "
          + "   evento.dataHora, "
          + "   evento.nome";

      // DEBUG
      System.out.println(query);

      return getEventos(BancoDeDados.getInstancia().executarQuery(query));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Eventos";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }
}