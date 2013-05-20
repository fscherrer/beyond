package br.com.einsteinlimeira.beyond.web.converters;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.UfServices;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Converter para a entidade {@link Uf}.
 */
@Named
public class UfConverter implements Converter{
  
  /**
   * Services de Uf.
   */
  @Inject
  private UfServices ufServices;
  
   @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value)
      throws ConverterException {
 
    try {
      return value == null
          ? null
          : ufServices.getPeloId(Integer.valueOf(value));
    }
    catch (EntidadeServicesException ese) {
      throw new ConverterException(ese);
    }
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {

    return value == null ? null : String.valueOf(((Uf)value).getId());
  }
}
  

