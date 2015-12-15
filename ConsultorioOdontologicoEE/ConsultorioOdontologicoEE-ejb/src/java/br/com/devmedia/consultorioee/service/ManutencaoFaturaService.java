package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Customer;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George salu
 */
@Singleton
@Startup
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManutencaoFaturaService extends BasicService {
    
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    
    public void processarEnvioDeFaturas(List<Customer> lista) {
        
    }

}
