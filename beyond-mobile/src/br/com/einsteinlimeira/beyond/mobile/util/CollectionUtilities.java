package br.com.einsteinlimeira.beyond.mobile.util;

import java.util.List;

/**
 * Provê métodos utilitários para Collections e Arrays.
 */
public class CollectionUtilities {

  /**
   * Retorna um array de inteiro primitivo contendo os elementos de <code>lista</code>, ou
   * </code>null</code> se <code>lista == null</code>.
   * 
   * @param lista
   *   Lista contendo os elementos que deseja-se retornar no array.
   * 
   * @return
   *   Array de inteiros primitivos.
   */
  public static int[] getArrayPrimitivo(List<Integer> lista) {
    int[] retorno = null;

    if (lista != null) {
      if (lista.isEmpty()) {
        retorno = new int[] {};
      }
      else {
        retorno = new int[lista.size()];

        for (int i = 0; i < retorno.length; i++) {
          retorno[i] = lista.get(i);
        }
      }
    }

    return retorno;
  }
}
