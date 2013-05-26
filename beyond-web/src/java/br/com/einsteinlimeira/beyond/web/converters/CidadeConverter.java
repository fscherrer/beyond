package br.com.einsteinlimeira.beyond.web.converters;

import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.services.CidadeServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Converter para a entidade {@link Cidade}.
 */
@Named
public class CidadeConverter implements Converter {

  /**
   * Services de Cidade.
   */
  @Inject
  private CidadeServices cidadeServices;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value)
      throws ConverterException {
    // System.out.println("--- getAsObject: " + value);
    try {
      return value == null
          ? null
          : cidadeServices.getPeloId(Integer.valueOf(value));
    }
    catch (EntidadeServicesException ese) {
      throw new ConverterException(ese);
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    // System.out.println("--- getAsString: " + value);
    return value == null ? null : String.valueOf(((Cidade) value).getId());
  }
}