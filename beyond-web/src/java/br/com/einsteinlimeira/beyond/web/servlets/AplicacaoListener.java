package br.com.einsteinlimeira.beyond.web.servlets;

import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import br.com.einsteinlimeira.beyond.services.impl.ServicesFactoryImpl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Implementa {@link ServletContextListener} para registrar a implementação de 
 * {@link ServicesFactory} a ser utilizada.<br />
 * TODO: alterar futuramente para usar CDI e não ServletContextListener.
 */
public class AplicacaoListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServicesFactory.setFactory(new ServicesFactoryImpl());
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}