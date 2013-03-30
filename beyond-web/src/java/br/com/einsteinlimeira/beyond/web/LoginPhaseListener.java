package br.com.einsteinlimeira.beyond.web;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * PhaseListener para proteger páginas que requerem autenticação.
 */
public class LoginPhaseListener implements PhaseListener {

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
  }

  @Override
  public void afterPhase(PhaseEvent event) {
    FacesContext facesContext = event.getFacesContext();

    boolean estaNaPaginaDeLogin = facesContext.getViewRoot().getViewId().equals("/login.xhtml");

    if (!estaNaPaginaDeLogin) {
      Application application = facesContext.getApplication();

      LoginMB loginMB = application.evaluateExpressionGet(facesContext, "#{loginMB}", LoginMB.class);

      if (!loginMB.isLogado()) {
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "login");
      }
    }
  }

  @Override
  public void beforePhase(PhaseEvent event) {
  }
}
