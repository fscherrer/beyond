package br.com.einsteinlimeira.beyond.model;

public class Usuario extends Entidade {

  private String login;

  private String senha;

  private String nome;

  private Casa casa;

  public Usuario() {
  }

  public Usuario(int id, String login, String senha, String nome, Casa casa) {
    super(id);
    this.login = login;
    this.senha = senha;
    this.nome = nome;
    this.casa = casa;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Casa getCasa() {
    return casa;
  }

  public void setCasa(Casa casa) {
    this.casa = casa;
  }
}
