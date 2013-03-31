package br.com.einsteinlimeira.beyond.web;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
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
    UIViewRoot viewRoot = facesContext.getViewRoot();

    // se a página não existir (usuário digitou alguma URL que não existe) a viewRoot é null
    if (viewRoot != null) {
      String viewId = facesContext.getViewRoot().getViewId();

      // protejo tudo abaixo do diretório admin
      if (viewId.startsWith("/admin/")) {
        // meno a própria página de login
        boolean estaNaPaginaDeLogin = viewId.equals("/admin/login.xhtml");

        if (!estaNaPaginaDeLogin) {
          Application application = facesContext.getApplication();

          LoginMB loginMB = application.evaluateExpressionGet(facesContext, "#{loginMB}", LoginMB.class);

          // se não estiver logo, jogo para a página de login
          if (!loginMB.isLogado()) {
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
            navigationHandler.handleNavigation(facesContext, null, "login");
          }
        }
      }
    }
  }

  @Override
  public void beforePhase(PhaseEvent event) {
  }
}
