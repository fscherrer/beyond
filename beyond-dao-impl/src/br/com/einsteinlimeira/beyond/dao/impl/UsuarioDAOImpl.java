package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.UsuarioDAO;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Usuario;
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
 * Implementação padrão de {@link UsuarioDAO}.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(UsuarioDAOImpl.class.getName());
  
  /**
   * DAO de Casa.
   */
  @Inject
  private CasaDAO casaDAO;

  private static final String INCLUIR_USUARIO_QUERY = ""
      + " insert into "
      + "   usuario("
      + "     login, "
      + "     senha, "
      + "     nome, "
      + "     casaid) "
      + " values(?, ?, ?, ?)";

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Usuario entidade) throws DAOException {
    final String mensagem = "Falha ao incluir usuário";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_USUARIO_QUERY,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, entidade.getLogin());
      preparedStatement.setString(2, entidade.getSenha());
      preparedStatement.setString(3, entidade.getNome());
      preparedStatement.setObject(4, entidade.getCasa() == null ? null : entidade.getCasa().getId());

      preparedStatement.executeUpdate();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return resultSet.getInt("id");
      }

      String mensagemFalhaObterId = "Não foi possível obter o ID do usuário incluído";

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

  private static final String EXCLUIR_USUARIO_QUERY = ""
      + " delete "
      + " from "
      + "   usuario "
      + " where "
      + "   id = ?";

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Usuario entidade) throws DAOException {
    final String mensagem = "Falha ao remover usuário";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_USUARIO_QUERY);
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
  public Usuario getPeloId(int id) throws DAOException {
    try {
      return getUsuarios(BancoDeDados.getInstancia().executarQuery("select * from usuario"
          + " where id = " + id)).get(0);
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Usuário";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  private static final String LISTA_USUARIO_QUERY = ""
      + " select "
      + "   * "
      + " from "
      + "   usuario "
      + " order by "
      + "   nome";

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Usuario> listar() throws DAOException {
    try {
      return getUsuarios(BancoDeDados.getInstancia().executarQuery(LISTA_USUARIO_QUERY));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Usuários";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  private static final String USUARIO_LOGIN_SENHA_QUERY = ""
      + " select "
      + "   * "
      + " from "
      + "   usuario "
      + " where "
      + "   login = ? "
      + "     and "
      + "   senha = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public Usuario getUsuario(String login, String senha) throws DAOException {
    final String mensagem = "Falha ao autenticar usuário";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(USUARIO_LOGIN_SENHA_QUERY);
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, senha);

      List<Usuario> usuarios = getUsuarios(preparedStatement.executeQuery());

      if (!usuarios.isEmpty()) {
        return usuarios.get(0);
      }

      return null;
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

  private static final String EDITA_USUARIO_QUERY = ""
      + " update "
      + "   usuario "
      + " set "
      + "   login = ?, "
      + "   senha = ?, "
      + "   nome = ?, "
      + "   casaid = ? "
      + " where "
      + "   id = ? ";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Usuario entidade) throws DAOException {
    final String mensagem = "Falha ao editar usuário";
    Connection conexao = null;

    try {
      conexao = BancoDeDados.getInstancia().getConexao();

      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_USUARIO_QUERY);
      preparedStatement.setString(1, entidade.getLogin());
      preparedStatement.setString(2, entidade.getSenha());
      preparedStatement.setString(3, entidade.getNome());
      preparedStatement.setObject(4, entidade.getCasa() == null ? null : entidade.getCasa().getId());
      preparedStatement.setInt(5, entidade.getId());

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

  private List<Usuario> getUsuarios(ResultSet resultSet) throws DAOException {
    List<Usuario> usuarios = new ArrayList<Usuario>();

    int id;
    String login;
    String senha;
    String nome;
    int idCasa;
    Casa casa;

    try {
      while (resultSet.next()) {
        id = resultSet.getInt("id");
        login = resultSet.getString("login");
        senha = resultSet.getString("senha");
        nome = resultSet.getString("nome");
        idCasa = resultSet.getInt("casaid");

        casa = resultSet.wasNull() ? null : casaDAO.getPeloId(idCasa);

        usuarios.add(new Usuario(id, login, senha, nome, casa));
      }

      return usuarios;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair usuários do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
