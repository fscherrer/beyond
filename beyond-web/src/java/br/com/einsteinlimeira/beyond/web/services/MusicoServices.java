package br.com.einsteinlimeira.beyond.web.services;

import br.com.einsteinlimeira.beyond.model.Musico;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.model.dao.MusicoDAO;
import java.util.List;

/**
 * Serviço relacionado a entidade {@link Musico}.
 */
public class MusicoServices {

    public List<Musico> getMusicos() throws DAOException {
        return new MusicoDAO().listar();
    }

    public Musico getMusico(int id) throws DAOException {
        return new MusicoDAO().getPeloId(id);
    }
}
