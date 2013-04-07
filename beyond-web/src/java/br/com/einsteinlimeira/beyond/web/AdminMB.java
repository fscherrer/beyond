package br.com.einsteinlimeira.beyond.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Managed Bean para a página inicial da área administrativa.
 */
@ManagedBean
@RequestScoped
public class AdminMB {

  public AdminMB() {
  }
  
  public String usuarios(){
    return "usuarios";
  }
  
  public String ufs(){
    return "ufs";
  }
  
  public String musicos(){
    return "musicos";
  }
}
