package br.com.einsteinlimeira.beyond.model;

public class Contato extends Entidade {

  private String telefone;

  private String fax;

  private String email;

  private String site;

  private String facebook;

  private String twitter;

  public Contato() {
  }

  public Contato(int id, String telefone, String fax, String email, String site, String facebook,
    String twitter) {
    super(id);
    this.telefone = telefone;
    this.fax = fax;
    this.email = email;
    this.site = site;
    this.facebook = facebook;
    this.twitter = twitter;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }
}
