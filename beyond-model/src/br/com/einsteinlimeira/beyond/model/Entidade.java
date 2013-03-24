package br.com.einsteinlimeira.beyond.model;

import java.io.Serializable;

public class Entidade implements Serializable {

  private int id;

  public Entidade() {
  }

  public Entidade(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 71 * hash + this.id;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Entidade other = (Entidade) obj;
    if (this.id != other.id) {
      return false;
    }
    return true;
  }
}
