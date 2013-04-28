package br.com.einsteinlimeira.beyond.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provê métodos relacionados a integração com base de dados.<br />
 * Implementa pattern <i>Singleton</i>
 */
public class BancoDeDados {

  /**
   * Instância dessa classe.
   */
  private static BancoDeDados instancia;

  /**
   * URL para conexão com o banco de dados.
   */
  private String url;

  /**
   * Propriedades da conexão (conterá usuário e senha inicialmente)
   */
  private Properties propriedadesConexao;

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(BancoDeDados.class.getName());

  /**
   * Construtor privado (Singleton).
   * 
   * @throws BancoDeDadosException se ocorrer algum problema ao obter as configurações para acesso
   * a base de dados.
   */
  private BancoDeDados() throws BancoDeDadosException {
    InputStream arquivoPropertiesBancoDadosInputStream = null;

    try {
      arquivoPropertiesBancoDadosInputStream = getClass().getResourceAsStream(
          "/" + getClass().getPackage().getName().replaceAll("\\.", "/") + "/bancodados.properties");
      Properties bancodadosProperties = new Properties();
      bancodadosProperties.load(arquivoPropertiesBancoDadosInputStream);

      // registro da classe do driver para acesso à base de dados.
      Class.forName(bancodadosProperties.getProperty("driver"));

      url = bancodadosProperties.getProperty("url");

      propriedadesConexao = new Properties();
      propriedadesConexao.put("user", bancodadosProperties.getProperty("usuario"));
      propriedadesConexao.put("password", bancodadosProperties.getProperty("senha"));
    }
    catch (ClassNotFoundException cnfe) {
      LOGGER.log(Level.SEVERE, null, cnfe);
      throw new BancoDeDadosException(
          "Erro ao registrar Driver para acesso à base de dados", cnfe);
    }
    catch (IOException ioe) {
      LOGGER.log(Level.SEVERE, null, ioe);
      throw new BancoDeDadosException(
          "Erro de E/S ao ler arquivo de configurações da base de dados", ioe);
    }
    finally {
      if (arquivoPropertiesBancoDadosInputStream != null) {
        try {
          arquivoPropertiesBancoDadosInputStream.close();
        }
        catch (Exception ex) {
          // não há muito o que fazer
          LOGGER.log(Level.WARNING, "Falha ao fechar stream do arquivo de configurações da "
              + "base de dados", ex);
        }
      }
    }
  }

  /**
   * Retorna a única instância dessa classe. Caso a instância ainda não exista será criada.<br />
   * Pattern Singleton.
   * 
   * @return
   *   Instância dessa classe.
   * 
   * @throws BancoDeDadosException se ocorrer algum problema ao obter as configurações para acesso
   * a base de dados.
   */
  public static synchronized BancoDeDados getInstancia() throws BancoDeDadosException {
    if (instancia == null) {
      instancia = new BancoDeDados();
    }

    return instancia;
  }

  /**
   * Retorna uma conexão com a base de dados<br />
   * <b>IMPORTANTE:</b> não esquecer de fechar a conexão após o uso ({@link Connection#close()})!
   * 
   * @return
   *   Uma conexão com o banco de dados.
   * 
   * @throws BancoDeDadosException se ocorrer algum problema ao obter a conexão.
   */
  public Connection getConexao() throws BancoDeDadosException {
    try {
      return DriverManager.getConnection(url, propriedadesConexao);
    }
    catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
      throw new BancoDeDadosException("SQLException ao obter conexão com a base de dados", ex);
    }
  }

  /**
   * Executa a <code>query</code>.
   * 
   * @param query
   *   Query a ser executada.
   * 
   * @return
   *   {@link ResultSet} retornado da execução da query.
   * 
   * @throws BancoDeDadosException se acontecer algum problema ao executar a query.
   */
  public ResultSet executarQuery(String query) throws BancoDeDadosException {
    Connection conexao = null;

    try {
      conexao = getInstancia().getConexao();
      Statement statement = conexao.createStatement();

      return statement.executeQuery(query);
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao executar query: " + query;
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new BancoDeDadosException(mensagem, sqle);
    }
    finally {
      if (conexao != null) {
        try {
          conexao.close();
        }
        catch (SQLException sqle) {
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", sqle);
        }
      }
    }
  }

  // para testar de forma rápida
  public static void main(String[] args) throws BancoDeDadosException, SQLException {
    ResultSet resultSet = getInstancia().executarQuery("select * from uf");

    while (resultSet.next()) {
      System.out.println(resultSet.getString("sigla") + " " + resultSet.getString("nome"));
    }
  }
}