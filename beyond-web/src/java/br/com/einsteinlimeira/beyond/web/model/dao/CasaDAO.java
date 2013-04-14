package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CasaDAO implements EntidadeDAO<Casa> {

  @Override
  public int inserir(Casa entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remover(Casa entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Casa getPeloId(int id) throws DAOException {
    try {
      return getCasas(BancoDeDados.getInstancia().executarQuery(
              "select * from casa where id = " + id)).get(0);
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao obter Casa";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }
  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(CasaDAO.class.getName());

  @Override
  public List<Casa> listar() throws DAOException {
    try {
      return getCasas(BancoDeDados.getInstancia().executarQuery("select * from casa"));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Casas";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  @Override
  public void atualizar(Casa entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
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
      
      EnderecoDAO enderecoDAO = new EnderecoDAO();

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
    } catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair casas do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}