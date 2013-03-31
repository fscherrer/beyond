package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Uf;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UfDAO implements EntidadeDAO<Uf> {

  @Override
  public void inserir(Uf entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean remover(Uf entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Uf getPeloId(int id) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(BancoDeDados.class.getName());

  @Override
  public List<Uf> listar() throws DAOException {
    final String mensagemException = "Falha ao listar UFs";

    Connection conexao = null;
    try {
      conexao = BancoDeDados.getInstancia().getConexao();
      Statement statement = conexao.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from uf");

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
      logaELancaDAOException(mensagemException, sqle);
    }
    catch (BancoDeDadosException bdde) {
      logaELancaDAOException(mensagemException, bdde);
    }
    finally {
      if (conexao != null) {
        try {
          conexao.close();
        }
        catch (SQLException ex) {
          logaFalhaAoFecharConexao(ex);
        }
      }
    }

    // código nunca atingido, já que logaELancaDAOException SEMPRE lança exception
    return null;
  }

  @Override
  public boolean atualizar(Uf entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private void logaELancaDAOException(String mensagemException, Exception exception)
      throws DAOException {
    LOGGER.log(Level.SEVERE, mensagemException, exception);
    throw new DAOException(mensagemException, exception);
  }

  private void logaFalhaAoFecharConexao(Exception exception) {
    LOGGER.log(Level.WARNING, "Falha ao fechar conexão", exception);
  }
}
