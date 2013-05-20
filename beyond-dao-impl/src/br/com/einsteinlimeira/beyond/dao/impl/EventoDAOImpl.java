package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.EventoDAO;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Implementação padrão de {@link EventoDAO}.
 */
public class EventoDAOImpl implements EventoDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(EventoDAOImpl.class.getName());
  private static final String INCLUIR_EVENTO_QUERY = ""
          + " insert into "
          + "   evento("
          + "     nome, "
          + "     casaid, "
          + "     datahora, "
          + "     valor) "
          + " values(?, ?, ?,?)";

  /**
   * DAO de Casa.
   */
  @Inject
  private CasaDAO casaDAO;

  /**
   * DAO de Banda.
   */
  @Inject
  private BandaDAO bandaDAO;

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Evento entidade) throws DAOException {
    final String mensagem = "Falha ao incluir evento";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_EVENTO_QUERY,
              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setObject(2, entidade.getCasa() == null ? null : entidade.getCasa().getId());
      preparedStatement.setDate(3, new java.sql.Date(entidade.getDatahora().getTime()));
      preparedStatement.setDouble(4, entidade.getValor());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }

      String mensagemFalhaObterId = "Não foi possível obter o ID do evento incluído";

      LOGGER.log(Level.SEVERE, mensagemFalhaObterId);
      throw new DAOException(mensagemFalhaObterId);
    } catch (BancoDeDadosException bdde) {
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    } catch (SQLException sqle) {
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    } finally {
      if (conexao != null) {
        try {
          conexao.close();
        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
  }
  private static final String EXCLUIR_EVENTO_QUERY = ""
          + " delete "
          + " from "
          + "   evento "
          + " where "
          + "   id = ?";

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Evento entidade) throws DAOException {
    final String mensagem = "Falha ao remover cidade";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_EVENTO_QUERY);
      preparedStatement.setInt(1, entidade.getId());

      preparedStatement.executeUpdate();
    } catch (BancoDeDadosException bdde) {
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    } catch (SQLException sqle) {
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    } finally {
      if (conexao != null) {
        try {
          conexao.close();
        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Evento getPeloId(int id) throws DAOException {
    try {
      return getEventos(BancoDeDados.getInstancia().executarQuery(
              "select * from evento where id = " + id)).get(0);
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Evento";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }

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
              + "     left join eventobanda as eventobanda"
              + "       on eventobanda.eventoid = evento.id "
              + "  order by "
              + "    evento.dataHora, "
              + "    evento.nome"));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Eventos";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }
  private static final String EDITA_EVENTO_QUERY = ""
          + " update "
          + "   evento "
          + " set "
          + "   nome = ?, "
          + "   casaid = ?, "
          + "   datahora = ?, "
          + "   valor = ? "
          + " where "
          + "   id = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Evento entidade) throws DAOException {
    final String mensagem = "Falha ao editar evento";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_EVENTO_QUERY);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setObject(2, entidade.getCasa() == null ? null : entidade.getCasa().getId());
      preparedStatement.setDate(3, new java.sql.Date(entidade.getDatahora().getTime()));
      preparedStatement.setDouble(4, entidade.getValor());
      preparedStatement.setInt(5, entidade.getId());

      preparedStatement.executeUpdate();
    } catch (BancoDeDadosException bdde) {
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    } catch (SQLException sqle) {
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    } finally {
      if (conexao != null) {
        try {
          conexao.close();
        } catch (Exception e) {
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
  }

  private List<Evento> getEventos(ResultSet resultSet) throws DAOException {
    try {
      List<Evento> eventos = new ArrayList<Evento>();

      int eventoId;
      String eventoNome;
      String eventoCoordenada;
      double eventoValor;
      Date eventoDataHora;
      int eventoCasaId;
      Integer eventoBandaId;
      Banda banda;
      Casa casa;
      Evento evento = null;

      int eventoIdAtual = 0;

      while (resultSet.next()) {
        eventoId = resultSet.getInt("Id");
        eventoNome = resultSet.getString("nome");
        eventoValor = resultSet.getDouble("valor");
        eventoDataHora = resultSet.getTimestamp("dataHora");
        eventoCasaId = resultSet.getInt("casaid");
        eventoCoordenada = resultSet.getString("coordenada");
        eventoBandaId = resultSet.getInt("bandaid");
        
        if (!resultSet.wasNull()) {
          banda = bandaDAO.getPeloId(eventoBandaId);
        }
        else {
          banda = null;
        }

        casa = casaDAO.getPeloId(eventoCasaId);

        if (eventoId != eventoIdAtual) {
          evento = new Evento(eventoId, eventoNome, eventoDataHora, eventoValor, casa,
                  new ArrayList<Banda>(), eventoCoordenada);
          eventos.add(evento);
        }

        if(banda != null){
          evento.getBandas().add(banda);
        }
        
        eventoIdAtual = eventoId;
      }

      return eventos;
    } catch (SQLException sqle) {
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

    if (where.length() > 0) {
      where.insert(0, " where ");
    }

    try {
      String query = ""
              + " select "
              + "   evento.*, "
              + "   eventobanda.bandaid as bandaid "
              + " from "
              + "   evento as evento "
              + "     left join eventobanda as eventobanda"
              + "       on eventobanda.eventoid = evento.id "
              + "     join casa as casa "
              + "       on casa.id = evento.casaid "
              + where.toString()
              + " order by "
              + "   evento.dataHora, "
              + "   evento.nome";

      // DEBUG
      // System.out.println(query);

      return getEventos(BancoDeDados.getInstancia().executarQuery(query));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Eventos";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }
}