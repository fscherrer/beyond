package br.com.einsteinlimeira.beyond.web.converters;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Iron
 */
@FacesConverter(value = "ufConverter", forClass = Uf.class)
public class UfConverter implements Converter{
  
   @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value)
      throws ConverterException {
 
    try {
      return value == null
          ? null
          : ServicesFactory.getFactory().getUfServices().getPeloId(Integer.valueOf(value));
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
  

