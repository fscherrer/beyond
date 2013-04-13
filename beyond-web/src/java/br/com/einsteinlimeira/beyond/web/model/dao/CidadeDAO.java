package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Cidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CidadeDAO implements EntidadeDAO<Cidade> {

  @Override
  public int inserir(Cidade entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remover(Cidade entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

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
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(Cidade.class.getName());

  @Override
  public List<Cidade> listar() throws DAOException {
    try {
      return getCidades(BancoDeDados.getInstancia().executarQuery("select * from cidade"));
    } catch (BancoDeDadosException bdde) {
      final String mensagem = "Falha ao listar Cidades";

      LOGGER.log(Level.SEVERE, mensagem, bdde);
      throw new DAOException(mensagem, bdde);
    }
  }

  @Override
  public void atualizar(Cidade entidade) throws DAOException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private List<Cidade> getCidades(ResultSet resultSet) throws DAOException {
    try {
      List<Cidade> cidades = new ArrayList<Cidade>();

      int cidadeId;
      String cidadeNome;
      int ufId;





      while (resultSet.next()) {
        cidadeId = resultSet.getInt("Id");
        cidadeNome = resultSet.getString("nome");
        ufId = resultSet.getInt("ufId");





        cidades.add(new Cidade(cidadeId, cidadeNome, null));
      }

      return cidades;
    } catch (SQLException sqle) {
      final String mensagem = "Falha ao extrair cidades do resultSet";

      LOGGER.log(Level.SEVERE, mensagem, sqle);
      throw new DAOException(mensagem, sqle);
    }
  }
}
