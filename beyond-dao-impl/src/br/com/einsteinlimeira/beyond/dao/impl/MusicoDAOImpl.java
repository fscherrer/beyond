package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.MusicoDAO;
import br.com.einsteinlimeira.beyond.model.Musico;
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
 * Implementação padrão de {@link MusicoDAO}.
 */
public class MusicoDAOImpl implements MusicoDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(MusicoDAOImpl.class.getName());
  private static final String INCLUIR_MUSICO_QUERY = ""
          + " insert into "
          + "   musico("
          + "     nome) "
          + " values(?)";

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Musico entidade) throws DAOException {
    final String mensagem = "Falha ao incluir músico";
    Connection conexao = null;
    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_MUSICO_QUERY,
              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, entidade.getNome());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }

      String mensagemFalhaObterId = "Não foi possível obter o ID do músico incluído";

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
  private static final String EXCLUIR_MUSICO_QUERY = ""
          + " delete "
          + " from "
          + "   musico "
          + " where "
          + "   id = ?";

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Musico entidade) throws DAOException {
    final String mensagem = "Falha ao remover musico";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_MUSICO_QUERY);
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
  public Musico getPeloId(int id) throws DAOException {
    try {
      return getMusico(BancoDeDados.getInstancia().executarQuery("select * from musico"
              + " where id = " + id)).get(0);
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Músico";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Musico> listar() throws DAOException {
    try {
      return getMusicos(BancoDeDados.getInstancia().executarQuery("select * from musico"));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Musicos";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  private List<Musico> getMusicos(ResultSet resultSet) throws DAOException {
    try {
      List<Musico> musicos = new ArrayList<Musico>();

      int musicoId;
      String musicoNome;

      while (resultSet.next()) {
        musicoId = resultSet.getInt("id");
        musicoNome = resultSet.getString("nome");

        musicos.add(new Musico(musicoId, musicoNome, null));
      }

      return musicos;
    } catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair musicos do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }


  }
  private static final String EDITA_MUSICO_QUERY = ""
          + " update "
          + "   musico "
          + " set "
          + "   nome = ? "
          + " where "
          + "   id = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Musico entidade) throws DAOException {
    final String mensagem = "Falha ao editar musico";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_MUSICO_QUERY);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setInt(2, entidade.getId());

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

  private List<Musico> getMusico(ResultSet resultSet) throws DAOException {
    try {
      List<Musico> musicos = new ArrayList<Musico>();

      int musicoId;
      String musicoNome;

      while (resultSet.next()) {
        musicoId = resultSet.getInt("id");
        musicoNome = resultSet.getString("nome");


        musicos.add(new Musico(musicoId, musicoNome));
      }

      return musicos;
    } catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair musicos do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }

  }
}
