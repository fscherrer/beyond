package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.model.Banda;
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

/**
 * Implementação padrão de {@link BandaDAO}.
 */
public class BandaDAOImpl implements BandaDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(BandaDAOImpl.class.getName());
  private static final String INCLUIR_BANDA_QUERY = ""
          + " insert into "
          + "   banda("
          + "     nome, "
          + "     dataformacao, "
          + "     estilo) "
          + " values(?, ?, ?)";

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Banda entidade) throws DAOException {
    final String mensagem = "Falha ao incluir banda";
    Connection conexao = null;
    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_BANDA_QUERY,
              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setDate(2, new java.sql.Date(entidade.getDataformacao().getTime()));
      preparedStatement.setString(3, entidade.getEstilo());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }

      String mensagemFalhaObterId = "Não foi possível obter o ID da banda incluída";

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
 private static final String EXCLUIR_BANDA_QUERY = ""
      + " delete "
      + " from "
      + "   banda "
      + " where "
      + "   id = ?";
  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Banda entidade) throws DAOException {
        final String mensagem = "Falha ao remover banda";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_BANDA_QUERY);
      preparedStatement.setInt(1, entidade.getId());

      preparedStatement.executeUpdate();
    }
    catch (BancoDeDadosException bdde) {
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
    catch (SQLException sqle) {
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
    finally {
      if (conexao != null) {
        try {
          conexao.close();
        }
        catch (Exception e) {
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
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
  
    private static final String EDITA_BANDA_QUERY = ""
      + " update "
      + "   banda "
      + " set "
      + "   nome = ?, "
      + "   dataformacao = ?, "
      + "   estilo = ? "
      + " where "
      + "   id = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Banda entidade) throws DAOException {
    final String mensagem = "Falha ao editar banda";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_BANDA_QUERY);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setDate(2, new java.sql.Date(entidade.getDataformacao().getTime()));
      preparedStatement.setString(3, entidade.getEstilo());
      preparedStatement.setInt(4, entidade.getId());

      preparedStatement.executeUpdate();
    }
    catch (BancoDeDadosException bdde) {
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
    catch (SQLException sqle) {
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
    finally {
      if (conexao != null) {
        try {
          conexao.close();
        }
        catch (Exception e) {
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
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
//  private List<Banda> getBandas(ResultSet resultSet) throws DAOException {
// List<Banda> bandas = new ArrayList<Banda>();
//
//      int bandaId;
//      String bandaNome;
//      String bandaEstilo;
//      Date dataFormacao;
//
//try {
//while (resultSet.next()) {
//        bandaId = resultSet.getInt("Id");
//        bandaEstilo = resultSet.getString("estilo");
//        bandaNome = resultSet.getString("nome");
//        dataFormacao = resultSet.getTimestamp("dataformacao");
//
//        bandas.add(new Banda(bandaId, bandaNome, bandaEstilo, dataFormacao));
//      }
//
//      return bandas;
//    }
//    catch (SQLException sqle) {
//      final String mensagem = "Falha ao extrair bandas do resultSet";
//
//      LOGGER.log(Level.SEVERE, mensagem, sqle);
//      throw new DAOException(mensagem, sqle);
//}
//  }