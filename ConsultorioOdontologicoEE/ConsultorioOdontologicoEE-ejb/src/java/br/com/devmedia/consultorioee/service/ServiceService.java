/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.service.repository.ServiceRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
@LocalBean
public class ServiceService extends BasicService{
    
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private ServiceRepository serviceRepository;
    
    @PostConstruct
    @PostActivate
    private void postConstruct(){
        serviceRepository = new ServiceRepository(em);
    }
    
       public Service addService(Service service) {
        return serviceRepository.addService(service);
    }
    
    public Service setService(Service service) {
        return serviceRepository.setService(service);
    }
    
    public void removeService(Service service) {
        serviceRepository.removeService(service);
    }
    
    public Service getService(int idOfService) {
        return serviceRepository.getService(idOfService);
    }
    
    public List<Service> getServices() {
        return serviceRepository.getServices();
    }
    
    public List<Service> getServicesByName(String name) {
        return serviceRepository.getServiceByName(name);
    }

}
