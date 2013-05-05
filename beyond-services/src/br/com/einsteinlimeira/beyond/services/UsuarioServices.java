package br.com.einsteinlimeira.beyond.services;

import br.com.einsteinlimeira.beyond.model.Usuario;

/**
 * Define os serviços relacionados à entidade {@link Usuario}.
 */
public interface UsuarioServices extends EntidadeServices<Usuario> {

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
   * @throws EntidadeServicesException lançada caso ocorra algum problema durante a obtenção do
   * {@link Usuario}.
   */
  public Usuario getUsuario(String login, String senha) throws EntidadeServicesException;
}