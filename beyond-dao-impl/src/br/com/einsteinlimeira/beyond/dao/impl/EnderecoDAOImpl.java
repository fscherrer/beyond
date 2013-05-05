package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.CidadeDAO;
import br.com.einsteinlimeira.beyond.dao.DAOException;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.dao.EnderecoDAO;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Endereco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação padrão de {@link EnderecoDAO}.
 */
public class EnderecoDAOImpl implements EnderecoDAO {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(EnderecoDAOImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public int inserir(Endereco entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remover(Endereco entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void atualizar(Endereco entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
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

      CidadeDAO cidadeDAO = DAOFactory.getFactory().getCidadeDAO();

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