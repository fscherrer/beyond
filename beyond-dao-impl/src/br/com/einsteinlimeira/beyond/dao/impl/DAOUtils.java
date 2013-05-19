package br.com.einsteinlimeira.beyond.dao.impl;

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
}