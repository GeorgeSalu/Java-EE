package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Customer;
import java.util.List;
import java.util.Queue;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
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
    
    @Resource(mappedName = "jms/FaturaQueue")
    private Queue faturaQueue;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    
    private static final long serialVersionUID = 1L;
    private InfoMDB infoMDB = null;
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void started() {
        System.out.println("[ManutencaoFaturaService] ManutencaoFaturaService started and ready to work !");
    }
    
    @Lock(LockType.WRITE)
    public void processarEnvioDeFaturas(List<Customer> lista,int tipoEnvio) {
        if (infoMDB != null && !infoMDB.isConcluido()) {
            throw new IllegalArgumentException("Ja existe um item sendo processado !");
        }
        infoMDB = new InfoMDB();
        infoMDB.setTipoEnvio(tipoEnvio);
        infoMDB.setCustomers(lista);
        infoMDB.setMensagem("Iniciando processamento");
        setInfoMDB(infoMDB);
        // Aqui eu vou enviar para a fila e o processamento em segundo plano
        context.createProducer().send((Destination) faturaQueue, infoMDB);
    }
    
    

    public InfoMDB getInfoMDB() {
        return infoMDB;
    }

    public void setInfoMDB(InfoMDB infoMDB) {
        this.infoMDB = infoMDB;
    }
    

}
