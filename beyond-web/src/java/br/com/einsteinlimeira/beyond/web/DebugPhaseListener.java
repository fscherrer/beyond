package br.com.einsteinlimeira.beyond.web;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * PhaseListener para fins de debug.
 */
public class DebugPhaseListener implements PhaseListener {

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.ANY_PHASE;
  }

  @Override
  public void afterPhase(PhaseEvent event) {
    System.out.println("--- afterPhase: " + event.getPhaseId());
  }

  @Override
  public void beforePhase(PhaseEvent event) {
    System.out.println("--- beforePhase: " + event.getPhaseId());
  }
}
