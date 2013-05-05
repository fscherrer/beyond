package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.CidadeDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.UfDAO;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Uf;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação padrão de {@link CidadeDAO}.
 */
public class CidadeDAOImpl implements CidadeDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(CidadeDAOImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Cidade entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Cidade entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Cidade getPeloId(int id) throws DAOException {
    try {
      return getCidades(BancoDeDados.getInstancia().executarQuery(
          "select * from cidade where id = " + id)).get(0);
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Cidade";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Cidade> listar() throws DAOException {
    try {
      return getCidades(BancoDeDados.getInstancia().executarQuery("select * from cidade"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Cidades";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Cidade entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Cidade> getCidades(ResultSet resultSet) throws DAOException {
    try {
      List<Cidade> cidades = new ArrayList<Cidade>();

      int cidadeId;
      String cidadeNome;
      int ufId;
      Uf uf;

      UfDAO ufDAO = DAOFactoryImpl.getFactory().getUfDAO();

      while (resultSet.next()) {
        cidadeId = resultSet.getInt("Id");
        cidadeNome = resultSet.getString("nome");
        ufId = resultSet.getInt("ufId");

        uf = ufDAO.getPeloId(ufId);

        cidades.add(new Cidade(cidadeId, cidadeNome, uf));
      }

      return cidades;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair cidades do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
