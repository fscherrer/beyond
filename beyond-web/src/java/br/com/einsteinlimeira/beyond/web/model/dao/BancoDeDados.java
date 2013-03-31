package br.com.einsteinlimeira.beyond.web.model.dao;

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

      url = bancodadosProperties.getProperty("url");

      propriedadesConexao = new Properties();
      propriedadesConexao.put("user", bancodadosProperties.getProperty("usuario"));
      propriedadesConexao.put("password", bancodadosProperties.getProperty("senha"));
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
  // para testar de forma rápida
  public static void main(String[] args) throws BancoDeDadosException, SQLException {
    Connection conexao = getInstancia().getConexao();

    Statement statement = conexao.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from uf");

    while (resultSet.next()) {
      System.out.println(resultSet.getString("sigla") + " " + resultSet.getString("nome"));
    }

    conexao.close();
  }
}