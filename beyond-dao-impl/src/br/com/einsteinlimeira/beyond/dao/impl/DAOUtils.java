package br.com.einsteinlimeira.beyond.dao.impl;

import java.util.Collection;

/**
 * Provê métodos utilitários aos DAOs.
 */
public class DAOUtils {

  /**
   * Retorna um filtro para o <code>campo</code> <i>in</i> <code>ids</code>.<br />
   * Exemplo de retorno para os parâmetros "casa.id", new long[]{1, 8, 9, 11, 12}:<br />
   * <code>"(casa.id in (1, 8, 9, 11, 12))"</code>
   * 
   * 
   * @param campo
   *   Campo a ser filtrado.
   * @param ids
   *   IDs a serem filtrados.
   * 
   * @return 
   *   String representando um filtro para query.
   */
  public static String getFiltroQuery(String campo, int[] ids) {
    StringBuilder filtro = new StringBuilder();

    filtro.
        append("(").
        append(campo).
        append(" in (");

    for (int id : ids) {
      filtro.append(id).append(',');
    }

    filtro.
        delete(filtro.length() - 1, filtro.length()). // remove a última vírgula sobrando
        append("))");

    return filtro.toString();
  }

  /**
   * Cria um <code>int[]</code> com os <code>int</code> da <code>collection</code> informada.
   *
   * @param collection
   *   Collection da qual obter os dados para criar o array.
   *
   * @return
   *   O array criado, de <code>length = collection.size()</code>, contendo todos os elementos de
   * <code>collection</code>.
   */
  public static int[] criaArray(Collection<Integer> collection) {
    int[] primitiveArray = new int[collection.size()];

    int i = 0;
    for (int value : collection) {
      primitiveArray[i++] = value;
    }

    return primitiveArray;
  }
}