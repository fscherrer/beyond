package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.EnderecoDAO;
import br.com.einsteinlimeira.beyond.model.Casa;
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
 * Implementação padrão de {@link CasaDAO}.
 */
public class CasaDAOImpl implements CasaDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(CasaDAOImpl.class.getName());
  
  private final static String INCLUIR_CASA_QUERY = ""
          + " insert"
          + " into"
          + "   casa"
          + "     (nome,"
          + "      cnpj,"
          + "      responsavel)"
          + " values(?, ?, ?)";
  
  /**
   * DAO de Endereço.
   */
  @Inject
  private EnderecoDAO enderecoDAO;

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Casa entidade) throws DAOException {
    final String mensagem = "Falha ao incluir banda";
    Connection conexao= null;
    try{
      conexao = BancoDeDados.getInstancia().getConexao();
      
      PreparedStatement preparedStatement = conexao.prepareStatement(INCLUIR_CASA_QUERY,
              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setInt(2, entidade.getCnpj());
      preparedStatement.setString(3, entidade.getResponsavel());
      
      preparedStatement.executeQuery();
      
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if(resultSet.next()){
        return resultSet.getInt("Id");
      }
      
      String mensagemFalhaObterId = "Não foi possível obter o ID da casa incluida";
      LOGGER.log(Level.SEVERE, mensagemFalhaObterId);
      throw new DAOException(mensagemFalhaObterId);
    } catch (BancoDeDadosException bdde){
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw  new DAOException(mensagem, bdde);
    }catch (SQLException sqle){
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
    finally{
      if(conexao != null){
        try{
          conexao.close();
        } catch (Exception e){
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
  }

  private static final String  EXCLUIR_CASA_QUERY = ""
          + " delete"
          + " from"
          + "   casa"
          + " where"
          + "   id = ?";
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Casa entidade) throws DAOException {
    final String mensagem = "Falha ao remover casa";
    Connection conexao = null;
    
    try{
      conexao = BancoDeDados.getInstancia().getConexao();
      PreparedStatement preparedStatement = conexao.prepareStatement(EXCLUIR_CASA_QUERY);
      preparedStatement.setInt(1, entidade.getId());
      
      preparedStatement.executeQuery();
    } catch (BancoDeDadosException bdde){
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    } catch (SQLException sqle){
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
    finally{
      if(conexao != null){
        try{
          conexao.close();
        } catch(Exception e){
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Casa getPeloId(int id) throws DAOException {
    try {
      return getCasas(BancoDeDados.getInstancia().executarQuery(
          "select * from casa where id = " + id)).get(0);
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Casa";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Casa> listar() throws DAOException {
    try {
      return getCasas(BancoDeDados.getInstancia().executarQuery("select * from casa order by nome"));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Casas";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Casa> getCasas(int[] idsCidades) throws DAOException {
    String query = "select * from casa";

    if (idsCidades != null && idsCidades.length > 0) {
      query += ""
          + " join endereco "
          + "   on endereco.id = enderecoid  "
          + " where " + DAOUtils.getFiltroQuery("endereco.cidadeid", idsCidades);
    }
    
    query += " order by nome";

    try {
      return getCasas(BancoDeDados.getInstancia().executarQuery(query));
    }
    catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Casas filtradas";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }
  
  private static final String EDITA_CASA_QUERY = ""
          +" update"
          + "   casa"
          + " set"
          + "   nome = ?"
          + "   cpnj = ?"
          + "   responsavel = ?"
          + " where"
          + "   id = ?";

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Casa entidade) throws DAOException {
    final String mensagem = "Não foi possivel atualizar casa";
    Connection conexao = null;
    
    try{
      conexao = BancoDeDados.getInstancia().getConexao();
      PreparedStatement preparedStatement = conexao.prepareStatement(EDITA_CASA_QUERY);
      preparedStatement.setString(1, entidade.getNome());
      preparedStatement.setInt(2, entidade.getCnpj());
      preparedStatement.setString(3, entidade.getResponsavel());
      
      preparedStatement.executeQuery();
    } catch (BancoDeDadosException bdde){
      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    } catch (SQLException sqle){
      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
    finally{
      if(conexao != null){
        try{
          conexao.close();
        }catch (Exception e){
          LOGGER.log(Level.WARNING, "Falha ao fechar conexão", e);
        }
      }
    }
  }

  private List<Casa> getCasas(ResultSet resultSet) throws DAOException {
    try {
      List<Casa> casas = new ArrayList<Casa>();

      int casaId;
      String casaNome;
      int casacnpj;
      String casaResponsavel;
      int enderecoId;
      Endereco endereco;

      while (resultSet.next()) {
        casaId = resultSet.getInt("Id");
        casaNome = resultSet.getString("nome");
        casacnpj = resultSet.getInt("cnpj");
        casaResponsavel = resultSet.getString("responsavel");
        enderecoId = resultSet.getByte("enderecoId");

        endereco = enderecoDAO.getPeloId(enderecoId);

        casas.add(new Casa(casaId, casaNome, casacnpj, casaResponsavel, endereco, null, null, null, null));
      }

      return casas;
    }
    catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair casas do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}