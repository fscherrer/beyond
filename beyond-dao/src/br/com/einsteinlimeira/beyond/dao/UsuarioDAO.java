package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Usuario;

/**
 * Define o DAO de {@link Usuario}.
 */
public interface UsuarioDAO extends EntidadeDAO<Usuario> {

  /**
   * Retorna o {@link Usuario} cujo login seja <code>login</code> e senha <code>senha</code>.
   * 
   * @param login
   *   Login do usuário.
   * @param senha
   *   Senha do usuário.
   * 
   * @return
   *   {@link Usuario} de <code>login</code> e <code>senha</code> conforme especificados, ou 
   * <code>null</code> caso não exista um {@link Usuario} com esses dados.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção do usuário.
   */
  public Usuario getUsuario(String login, String senha) throws DAOException;
}