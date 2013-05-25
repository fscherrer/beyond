package br.com.einsteinlimeira.beyond.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Managed Bean para a página inicial da área administrativa.
 */
@Named
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
  
  public String bandas(){
  return "bandas";
  }
  
   public String cidades(){
    return "cidades";
  }
   
   public String casas(){
     return "casas";
   }
   
    public String eventos(){
    return "eventos";
}
}
