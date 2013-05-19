package br.com.einsteinlimeira.beyond.model;

import java.util.Collection;

/**
 * Provê métodos utilitários para os Entidades.
 */
public class EntidadeUtils {

  /**
   * Retorna um array composto pelos IDs das <code>entidades</code>.
   * 
   * @param entidades
   *   Entidades que cujos IDs comporão o array.
   * 
   * @return 
   *   Array composto pelos IDs das <code>entidades</code>.
   */
  public static int[] getIDs(Collection<? extends Entidade> entidades) {
    if (entidades == null) {
      return null;
    }

    if (entidades.isEmpty()) {
      return new int[]{};
    }

    int[] ids = new int[entidades.size()];

    int i = 0;
    for (Entidade entidade : entidades) {
      ids[i] = entidade.getId();
      i++;
    }

    return ids;
  }
}