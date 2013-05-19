package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.CidadeDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.dao.UfDAO;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  private static final String INCLUIR_CIDADE_QUERY = ""
          + " insert into "
          + "   cidade("
          + "     nome, "
          + "     ufid) "
          + " values(?, ?)";

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Cidade entidade) throws DAOException {
    final String mensagem = "Falha ao incluir cidade";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_CIDADE_QUERY,
              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setObject(2, entidade.getUf() == null ? null : entidade.getUf().getId());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }

      String mensagemFalhaObterId = "Não foi possível obter o ID da cidade incluída";

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
  private static final String EXCLUIR_CIDADE_QUERY = ""
          + " delete "
          + " from "
          + "   cidade "
          + " where "
          + "   id = ?";

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Cidade entidade) throws DAOException {
    final String mensagem = "Falha ao remover cidade";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_CIDADE_QUERY);
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
  public Cidade getPeloId(int id) throws DAOException {
    try {
      return getCidades(BancoDeDados.getInstancia().executarQuery(
              "select * from cidade where id = " + id)).get(0);
    } catch (BancoDeDadosException bdde) {
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
      return getCidades(BancoDeDados.getInstancia().executarQuery("select * from cidade order by nome"));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Cidades";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */

  private static final String EDITA_CIDADE_QUERY = ""
          + " update "
          + "   cidade "
          + " set "
          + "   nome = ?, "
          + "   ufid = ? "
          + " where "
          + "   id = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Cidade entidade) throws DAOException {
    final String mensagem = "Falha ao editar cidade";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_CIDADE_QUERY);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setObject(2, entidade.getUf() == null ? null : entidade.getUf().getId());
      preparedStatement.setInt(3, entidade.getId());

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

  private List<Cidade> getCidades(ResultSet resultSet) throws DAOException {
    try {
      List<Cidade> cidades = new ArrayList<Cidade>();

      int cidadeId;
      String cidadeNome;
      int ufid;
      Uf uf;

      UfDAO ufDAO = DAOFactory.getFactory().getUfDAO();

      while (resultSet.next()) {
        cidadeId = resultSet.getInt("Id");
        cidadeNome = resultSet.getString("nome");
        ufid = resultSet.getInt("ufid");

        uf = resultSet.wasNull() ? null : ufDAO.getPeloId(ufid);


        cidades.add(new Cidade(cidadeId, cidadeNome, uf));
      }

      return cidades;
    } catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair cidades do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
