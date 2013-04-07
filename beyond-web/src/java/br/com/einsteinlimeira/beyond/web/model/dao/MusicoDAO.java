/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Musico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuário
 */
public class MusicoDAO implements EntidadeDAO<Musico> {

    @Override
    public int inserir(Musico entidade) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remover(Musico entidade) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Musico getPeloId(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Logger para logar mensagens.
     */
    private final static Logger LOGGER = Logger.getLogger(UfDAO.class.getName());

    @Override
    public List<Musico> listar() throws DAOException {
        try {
            return getMusico(BancoDeDados.getInstancia().executarQuery("select * from musico"));
        } catch (BancoDeDadosException bdde) {
            final String mensagem = "Falha ao listar Musicos";

            LOGGER.log(Level.SEVERE, mensagem, bdde);
            throw new DAOException(mensagem, bdde);
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

                musicos.add(new Musico(musicoId, musicoNome, null));
            }

            return musicos;
        } catch (SQLException sqle) {
            final String mensagem = "Falha ao extrair musicos do resultSet";

            LOGGER.log(Level.SEVERE, mensagem, sqle);
            throw new DAOException(mensagem, sqle);
        }

    }

    @Override
    public void atualizar(Musico entidade) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
