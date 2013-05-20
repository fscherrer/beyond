package br.com.einsteinlimeira.beyond.web.converters;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Converter para a entidade {@link Casa}.
 */
@Named
public class CasaConverter implements Converter {

  /**
   * Services de Casa.
   */
  @Inject
  private CasaServices casaServices;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value)
      throws ConverterException {
    // System.out.println("--- getAsObject: " + value);
    try {
      return value == null
          ? null
          : casaServices.getPeloId(Integer.valueOf(value));
    }
    catch (EntidadeServicesException ese) {
      throw new ConverterException(ese);
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    // System.out.println("--- getAsString: " + value);
    return value == null ? null : String.valueOf(((Casa)value).getId());
  }
}