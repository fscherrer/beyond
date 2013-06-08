package br.com.einsteinlimeira.beyond.mobile.services;

import java.util.List;

import android.content.Context;
import br.com.einsteinlimeira.beyond.mobile.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.mobile.dao.EntidadeDAO;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;

public class BandaServices extends EntidadeServices<BandaDTO> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeDAO<BandaDTO> getDAO() {
    return new BandaDAO();
  }

  /**
   * Retorna os estilos encontrados nas bandas.
   * 
   * @param context
   *   Contexto.
   *   
   * @return
   *   Lista de estilos.
   */
  public List<String> getEstilos(Context context) {
    return new BandaDAO().getEstilos(context);
  }

  /**
   * Retorna as Bandas contendo algum dos <code>estilos</code> informados.
   * 
   * @param context
   *   Contexto.
   * @param estilos
   *   Estilos a filtrar as bandas.
   *   
   * @return
   *   Bandas que contém algum dos <code>estilos</code>.
   */
  public List<BandaDTO> listar(Context context, List<String> estilos) {
    return new BandaDAO().listar(context, estilos);
  }
}
