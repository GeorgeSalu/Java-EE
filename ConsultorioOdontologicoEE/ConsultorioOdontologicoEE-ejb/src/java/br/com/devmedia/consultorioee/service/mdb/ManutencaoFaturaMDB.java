package br.com.devmedia.consultorioee.service.mdb;

import br.com.devmedia.consultorioee.service.InfoMDB;
import br.com.devmedia.consultorioee.service.ManutencaoFaturaService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author George salu
 */
@JMSDestinationDefinition(name = "jms/FaturaQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "FaturaQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/FaturaQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
}) 
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ManutencaoFaturaMDB implements MessageListener {
    
    @EJB
    private ManutencaoFaturaService manutencaoFaturaService;
    
    public ManutencaoFaturaMDB() {
    }
    
    @Override
    public void onMessage(Message message) {
        if (message == null) {
            return;
        }
        InfoMDB infoMDB = null;
        try {
            infoMDB = message.getBody(InfoMDB.class);
        } catch (JMSException ex) {
            Logger.getLogger(ManutencaoFaturaMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        infoMDB.setMensagem("Processando essa leva de faturas");
        manutencaoFaturaService.setInfoMDB(infoMDB);
        System.out.println("a Mensagem chegou !!! "+message);
    }
    
}
