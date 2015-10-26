
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.service.ServiceService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ServiceControl extends BasicControl implements java.io.Serializable {

    @EJB
    private ServiceService serviceService;
    
    private String localizar;
    private List<Service> srvFiltrado;
    private Service serviceSelected;

    
    public String doLocalizar() {
        srvFiltrado = serviceService.getServicesByName(localizar);
        return "/restrito/services.faces";
    }

    public String doStartAddService() {
        setServiceSelected(new Service());
        return "/restrito/addService.faces";
    }

    public String doFinishAddService() {
        srvFiltrado = null;
        serviceService.addService(serviceSelected);
        return "/restrito/services.faces";
    }
    
    public List<Service> getServices() {
        return serviceService.getServices();
    }
    
    
    
    public String getLocalizar() {
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }

    public List<Service> getSrvFiltrado() {
        if (srvFiltrado == null) return getServices();
        return srvFiltrado;
    }

    public void setSrvFiltrado(List<Service> srvFiltrado) {
        this.srvFiltrado = srvFiltrado;
    }

    public Service getServiceSelected() {
        return serviceSelected;
    }

    public void setServiceSelected(Service serviceSelected) {
        this.serviceSelected = serviceSelected;
    }
    
}
