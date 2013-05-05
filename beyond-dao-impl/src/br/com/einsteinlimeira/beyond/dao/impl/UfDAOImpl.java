package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.UfDAO;
import br.com.einsteinlimeira.beyond.model.Uf;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação padrão de {@link UfDAO}.
 */
public class UfDAOImpl implements UfDAO {

  @Override
  public int inserir(Uf entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remover(Uf entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Uf getPeloId(int id) throws DAOException {
    try {
      return getUf(BancoDeDados.getInstancia().executarQuery("select * from uf"
          + " where id = " + id)).get(0);
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter UF";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(UfDAOImpl.class.getName());

  @Override
  public List<Uf> listar() throws DAOException {
    try {
      return getUf(BancoDeDados.getInstancia().executarQuery("select * from uf"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar UFs";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  @Override
  public void atualizar(Uf entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Uf> getUf(ResultSet resultSet) throws DAOException {
    try {
      List<Uf> ufs = new ArrayList<Uf>();

      int ufId;
      String ufSigla;
      String ufNome;

      while (resultSet.next()) {
        ufId = resultSet.getInt("id");
        ufSigla = resultSet.getString("sigla");
        ufNome = resultSet.getString("nome");

        // TODO: e as cidades?
        ufs.add(new Uf(ufId, ufSigla, ufNome, null));
      }

      return ufs;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair ufs do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
