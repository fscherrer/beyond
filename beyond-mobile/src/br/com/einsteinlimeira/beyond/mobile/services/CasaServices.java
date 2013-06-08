package br.com.einsteinlimeira.beyond.mobile.services;

import java.util.List;

import android.content.Context;
import br.com.einsteinlimeira.beyond.mobile.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.mobile.dao.EntidadeDAO;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

public class CasaServices extends EntidadeServices<CasaDTO> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeDAO<CasaDTO> getDAO() {
    return new CasaDAO();
  }

  /**
   * Lista as Entidades.
   * 
   * @param context
   *   Contexto.
   * @param idsCidades
   *   Ids das Cidades das quais deseja-se obter as Casas (filtro).
   *   
   * @return
   *   Lista de Entidades.
   */
  public List<CasaDTO> listar(Context context, int[] idsCidades) {
    return new CasaDAO().listar(context, idsCidades);
  }
}
