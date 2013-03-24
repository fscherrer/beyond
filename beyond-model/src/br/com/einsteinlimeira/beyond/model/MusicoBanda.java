package br.com.einsteinlimeira.beyond.model;

public class MusicoBanda extends Entidade {

  private String funcao;

  private Musico musico;

  private Banda banda;

  public MusicoBanda() {
  }

  public MusicoBanda(int id, String funcao, Musico musico, Banda banda) {
    super(id);
    this.funcao = funcao;
    this.musico = musico;
    this.banda = banda;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public Musico getMusico() {
    return musico;
  }

  public void setMusico(Musico musico) {
    this.musico = musico;
  }

  public Banda getBanda() {
    return banda;
  }

  public void setBanda(Banda banda) {
    this.banda = banda;
  }
}
