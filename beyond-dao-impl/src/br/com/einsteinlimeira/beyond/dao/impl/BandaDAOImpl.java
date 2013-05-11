package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.model.Banda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação padrão de {@link BandaDAO}.
 */
public class BandaDAOImpl implements BandaDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(BandaDAOImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Banda entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Banda entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Banda getPeloId(int id) throws DAOException {
    try {
      return getBandas(BancoDeDados.getInstancia().executarQuery("select * from banda"
          + " where id = " + id)).get(0);
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Banda";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Banda> listar() throws DAOException {
    try {
      return getBandas(BancoDeDados.getInstancia().executarQuery("select * from banda"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Bandas";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Banda entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Banda> getBandas(ResultSet resultSet) throws DAOException {
    try {
      List<Banda> bandas = new ArrayList<Banda>();

      int bandaId;
      String bandaNome;
      String bandaEstilo;
      Date dataFormacao;

      while (resultSet.next()) {
        bandaId = resultSet.getInt("Id");
        bandaEstilo = resultSet.getString("estilo");
        bandaNome = resultSet.getString("nome");
        dataFormacao = resultSet.getTimestamp("dataformacao");

        bandas.add(new Banda(bandaId, bandaNome, bandaEstilo, dataFormacao, null, null, null));
      }

      return bandas;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair bandas do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
