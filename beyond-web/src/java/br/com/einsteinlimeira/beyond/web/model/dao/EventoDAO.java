package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Evento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDAO implements EntidadeDAO<Evento> {

  @Override
  public int inserir(Evento entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remover(Evento entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Evento getPeloId(int id) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(EventoDAO.class.getName());

  @Override
  public List<Evento> listar() throws DAOException {
    try {
      return getEventos(BancoDeDados.getInstancia().executarQuery("select * from evento"));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Eventos";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

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
      int eventoBandaId;
      Banda banda;

      while (resultSet.next()) {
        eventoId = resultSet.getInt("Id");
        eventoNome = resultSet.getString("nome");
        eventoValor = resultSet.getDouble("valor");
        eventoDataHora = resultSet.getTimestamp("dataHora");
        eventoCasaId = resultSet.getInt("casaid");
        //eventoBandaId = resultSet.getInt("bandaid");
        
        //banda = null ; //new BandaDAO().getBanda(eventoBandaId);

        eventos.add(new Evento(eventoId, eventoNome, eventoDataHora, eventoValor, null, null));
      }

      return eventos;
    } catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair eventos do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}