package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.CidadeDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.EnderecoDAO;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Implementação padrão de {@link EnderecoDAO}.
 */
public class EnderecoDAOImpl implements EnderecoDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(EnderecoDAOImpl.class.getName());

  /**
   * DAO de Cidade.
   */
  @Inject
  private CidadeDAO cidadeDAO;

  private static final String INCLUIR_ENDERECO_QUERY = ""
      + " insert into "
      + "   endereco( "
      + "     cidadeid, "
      + "     bairro, "
      + "     cep, "
      + "     logradouro, "
      + "     complemento,"
      + "     numero) "
      + " values(?, ?, ?, ?, ?, ?)";

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Endereco entidade) throws DAOException {
    final String mensagem = "Falha ao incluir endereço";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_ENDERECO_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, entidade.getCidade().getId());
      preparedStatement.setString(2, entidade.getBairro());
      preparedStatement.setString(3, entidade.getCep());
      preparedStatement.setString(4, entidade.getLogradouro());
      preparedStatement.setString(5, entidade.getComplemento());
      preparedStatement.setString(6, entidade.getNumero());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }

      String mensagemFalhaObterId = "Não foi possível obter o ID do endereço incluído";

      LOGGER.log(Level.SEVERE, mensagemFalhaObterId);
      throw new DAOException(mensagemFalhaObterId);
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
  
  private static final String EXCLUIR_ENDERECO_QUERY = ""
      + " delete "
      + " from "
      + "   endereco "
      + " where "
      + "   id = ?";

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Endereco entidade) throws DAOException {
    final String mensagem = "Falha ao remover endereço";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_ENDERECO_QUERY);
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
  public Endereco getPeloId(int id) throws DAOException {
    try {
      return getEnderecos(BancoDeDados.getInstancia().executarQuery(
          "select * from endereco where id = " + id)).get(0);
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Endereço";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Endereco> listar() throws DAOException {
    try {
      return getEnderecos(BancoDeDados.getInstancia().executarQuery("select * from endereco"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Endereços";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  private static final String EDITA_ENDERECO_QUERY = ""
      + " update "
      + "   endereco "
      + " set "
      + "   cidadeid = ?, "
      + "   bairro = ?, "
      + "   cep = ?, "
      + "   logradouro = ?, "
      + "   complemento = ?, "
      + "   numero = ? "
      + " where "
      + "   id = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Endereco entidade) throws DAOException {
    final String mensagem = "Falha ao editar endereço";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_ENDERECO_QUERY);
      preparedStatement.setInt(1, entidade.getCidade().getId());
      preparedStatement.setString(2, entidade.getBairro());
      preparedStatement.setString(3, entidade.getCep());
      preparedStatement.setString(4, entidade.getLogradouro());
      preparedStatement.setString(5, entidade.getComplemento());
      preparedStatement.setString(6, entidade.getNumero());

      preparedStatement.setInt(7, entidade.getId());

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

  private List<Endereco> getEnderecos(ResultSet resultSet) throws DAOException {
    try {
      List<Endereco> enderecos = new ArrayList<Endereco>();

      int enderecoId;
      int cidadeId;
      String enderecoBairro;
      String enderecoCep;
      String enderecoLogradouro;
      String enderecoComplemento;
      String enderecoNumero;
      Cidade cidade;

      while (resultSet.next()) {
        enderecoId = resultSet.getInt("Id");
        cidadeId = resultSet.getInt("cidadeId");
        enderecoBairro = resultSet.getString("bairro");
        enderecoCep = resultSet.getString("cep");
        enderecoLogradouro = resultSet.getString("logradouro");
        enderecoComplemento = resultSet.getString("complemento");
        enderecoNumero = resultSet.getString("numero");

        cidade = cidadeDAO.getPeloId(cidadeId);

        enderecos.add(new Endereco(enderecoId, enderecoBairro, enderecoCep, enderecoLogradouro,
            enderecoComplemento, enderecoNumero, null, cidade));
      }

      return enderecos;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair enderecos do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}