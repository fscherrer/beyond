package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Entidade;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para manipulação de {@link Casa}.
 */
@ManagedBean
@RequestScoped
public class CasasMB extends BaseManagedBeanEntidade<Casa>{

    @Override
    public EntidadeServices<Casa> getEntidadeServices() {
        return ServicesFactory.getFactory().getCasaServices();
    }

    @Override
    public Casa getNovaEntidade() {
        return new Casa();
    }
}