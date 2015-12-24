package br.com.devmedia.consultorioee.service.mdb;

import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Parcela;
import br.com.devmedia.consultorioee.service.FinanceService;
import br.com.devmedia.consultorioee.service.InfoMDB;
import br.com.devmedia.consultorioee.service.ManutencaoFaturaService;
import java.util.List;
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
    @EJB
    private FinanceService financeService;
    
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
        infoMDB.setMensagem("Iniciando processamento");
        infoMDB.setPorcentagemConcluida(0);
        int quantasQuebras = (infoMDB.getCustomers().size() / 100) + 1;
        manutencaoFaturaService.setInfoMDB(infoMDB);
        for (Customer cus : infoMDB.getCustomers()) {
            infoMDB.setMensagem("Processando "+cus.getCusName()+"...");
            manutencaoFaturaService.setInfoMDB(infoMDB);
            List<Parcela> parcelas = financeService.getParcelasOfCustomerEmAberto(cus.getCusId());
            for (Parcela parcela : parcelas) {
                try {
                    byte[] pdf = financeService.getPDF(parcela);
                    System.out.println("Veja o PDF da Parcela "+pdf);
                    // Aqui voce decide para onde vai jogar conforme o infoMDB.getTipoEnvio()
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    Logger.getLogger(ManutencaoFaturaMDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            infoMDB.setPorcentagemConcluida(infoMDB.getPorcentagemConcluida()+(100/quantasQuebras));
            manutencaoFaturaService.setInfoMDB(infoMDB);
        }
        infoMDB.setPorcentagemConcluida(100);
        infoMDB.setConcluido(true);
        manutencaoFaturaService.setInfoMDB(infoMDB);
    }

    
}
