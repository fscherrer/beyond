package br.com.einsteinlimeira.beyond.web.converters;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Converter para a entidade {@link Casa}.
 */
@FacesConverter(value = "casaConverter", forClass = Casa.class)
public class CasaConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value)
      throws ConverterException {
    // System.out.println("--- getAsObject: " + value);
    try {
      return value == null
          ? null
          : ServicesFactory.getFactory().getCasaServices().getPeloId(Integer.valueOf(value));
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