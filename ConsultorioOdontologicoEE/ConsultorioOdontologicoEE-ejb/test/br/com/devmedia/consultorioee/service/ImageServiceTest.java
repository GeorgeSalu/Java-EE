package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import br.com.devmedia.consultorioee.entities.Customer;
import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.entities.Orcamento;
import br.com.devmedia.consultorioee.entities.Orcamentoitem;
import br.com.devmedia.consultorioee.entities.PaymentType;
import br.com.devmedia.consultorioee.entities.Service;
import br.com.devmedia.consultorioee.entities.Users;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author George salu
 */
public class ImageServiceTest {
    private static EJBContainer container;
    private ImageService instance;
    private Categoriaimagem catImagemOne;
    private Categoriaimagem catImagemTwo;
    private Imagem imgOne;
    private Imagem imgTwo;
    private Customer customerOne;
    private Users userOne;
    private Service serviceOne;
    private Service serviceTwo;
    private Orcamento orcamentoOne;
    private Orcamento orcamentoTwo;
    private Orcamentoitem orcamentoItemOfOne;
    private Orcamentoitem orcamentoItemOfTwo;
    
    public ImageServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
        container = null;
    }
    
    @Before
    public void setUp() throws Exception {
        instance = (ImageService) container.getContext().lookup("java:global/classes/ImageService");
        // Mock Object One
        customerOne = new Customer();
        customerOne.setCusAddress("Address One "+new Random().nextInt());
        customerOne.setCusAge(Math.abs(new Random().nextInt(99)));
        customerOne.setCusBorndate(new Date());
        customerOne.setCusCity("City "+new Random().nextInt());
        customerOne.setCusComplement("Complement "+new Random().nextInt());
        customerOne.setCusFather("Father "+new Random().nextInt());
        customerOne.setCusMother("Mother "+new Random().nextInt());
        customerOne.setCusName("Customer Name One "+new Random().nextInt());
        customerOne.setCusObs("Obs "+new Random().nextInt());
        customerOne.setCusOcupation("Ocupation "+new Random().nextInt());
        customerOne.setCusState("XX");
        customerOne.setCusTelephone("Tel "+new Random().nextInt());
        customerOne.setCuscelNumber("Cel "+new Random().nextInt());
        customerOne.setCusworkAddress("Work address one "+new Random().nextInt());
        customerOne.setCusworkName("WorkName "+new Random().nextInt());
        customerOne.setCusworkNumber(new Random().nextInt()+"");
        customerOne.setCusworkObs("WorkObs "+new Random().nextInt());
        // Mock User Object
        userOne = new Users();
        userOne.setUsuAdministrator(new Random().nextBoolean());
        userOne.setUsuDentist(new Random().nextBoolean());
        userOne.setUsuLogin("test.LoginOne"+new Random().nextInt());
        userOne.setUsuName("test.NameOne "+new Random().nextInt());
        userOne.setUsuPassword(userOne.getUsuLogin());
    
        // Mock Orcamento Object
        orcamentoOne = new Orcamento();
        orcamentoOne.setOrcCustomer(customerOne);
        orcamentoOne.setOrcDate(new Date());
        orcamentoOne.setOrcDentist(userOne);
        orcamentoOne.setOrcHour(new Date());
        orcamentoOne.setOrcObs("Obs "+new Random().nextInt());
        orcamentoOne.setOrcTimes(new Random().nextInt(10));
        orcamentoOne.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcamentoOne.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        serviceOne = new Service();
        serviceOne.setSrvName("Test Orcamento Service One "+new Random().nextInt());
        serviceOne.setSrvCost(orcamentoOne.getOrcTotal());
        // Mock Of Item
        orcamentoItemOfOne = new Orcamentoitem();
        orcamentoItemOfOne.setOriCost(orcamentoOne.getOrcTotal());
        orcamentoItemOfOne.setOriObs("Obs Item "+new Random().nextInt());
        orcamentoItemOfOne.setOriService(serviceOne);
        orcamentoOne.addItem(orcamentoItemOfOne);

        // Mock Orcamento Object
        orcamentoTwo = new Orcamento();
        orcamentoTwo.setOrcCustomer(customerOne);
        orcamentoTwo.setOrcDate(new Date());
        orcamentoTwo.setOrcDentist(userOne);
        orcamentoTwo.setOrcHour(new Date());
        orcamentoTwo.setOrcObs("Obs "+new Random().nextInt());
        orcamentoTwo.setOrcTimes(new Random().nextInt(5));
        orcamentoTwo.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcamentoTwo.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        serviceTwo = new Service();
        serviceTwo.setSrvName("Test Orcamento Service Two "+new Random().nextInt());
        serviceTwo.setSrvCost(orcamentoTwo.getOrcTotal());
        // Mock Of Item
        orcamentoItemOfTwo = new Orcamentoitem();
        orcamentoItemOfTwo.setOriCost(orcamentoTwo.getOrcTotal());
        orcamentoItemOfTwo.setOriObs("Obs Item Two "+new Random().nextInt());
        orcamentoItemOfTwo.setOriService(serviceTwo);
        orcamentoTwo.addItem(orcamentoItemOfTwo);
        
        
        // mock de objetos
        catImagemOne = new Categoriaimagem();
        catImagemOne.setCigNome("Categoria Imagem Test One "+new Random().nextLong());
        catImagemTwo = new Categoriaimagem();
        catImagemTwo.setCigNome("Categoria Imagem Test Two "+new Random().nextLong());
        
        imgOne = new Imagem();
        imgOne.setImgCategoria(catImagemOne);
        imgOne.setImgDescricao("Descricao Imagem One "+new Random().nextLong());
        imgOne.setImgImagem(readImage("teste_software_bug.jpg"));
        imgOne.setImgOrcamento(orcamentoOne);
        imgOne.setImgdataInclusao(new Date());
        imgOne.setImghoraInclusao(new Date());
        
        imgTwo = new Imagem();
        imgTwo.setImgCategoria(catImagemTwo);
        imgTwo.setImgDescricao("Descricao Imagem Two "+new Random().nextLong());
        imgTwo.setImgImagem(readImage("teste_software_bug.jpg"));
        imgTwo.setImgOrcamento(orcamentoTwo);
        imgTwo.setImgdataInclusao(new Date());
        imgTwo.setImghoraInclusao(new Date());

        imgOne = instance.addImagem(imgOne);
        imgTwo = instance.addImagem(imgTwo);
        
    }
    
    @After
    public void tearDown() {
        instance.removeImagem(imgOne);
        instance.removeImagem(imgTwo);
    }

    /**
     * Test of addImagem method, of class ImageService.
     */
    @Test
    public void testAddImagem() throws Exception {
        
        // Mock Orcamento Object
        Orcamento orcOne = new Orcamento();
        orcOne.setOrcCustomer(customerOne);
        orcOne.setOrcDate(new Date());
        orcOne.setOrcDentist(userOne);
        orcOne.setOrcHour(new Date());
        orcOne.setOrcObs("Obs "+new Random().nextInt());
        orcOne.setOrcTimes(new Random().nextInt(10));
        orcOne.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcOne.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        Service servOne = new Service();
        servOne.setSrvName("Test Orcamento Service One Add "+new Random().nextInt());
        servOne.setSrvCost(orcOne.getOrcTotal());
        // Mock Of Item
        Orcamentoitem oritemOne = new Orcamentoitem();
        oritemOne.setOriCost(orcOne.getOrcTotal());
        oritemOne.setOriObs("Obs Item add  "+new Random().nextInt());
        oritemOne.setOriService(servOne);
        orcOne.addItem(oritemOne);
        Categoriaimagem catOne = new Categoriaimagem();
        catOne.setCigNome("Categoria Imagem Cat Add ne "+new Random().nextLong());

        
        System.out.println("addImagem");
        Imagem imagem = new Imagem();
        imagem.setImgCategoria(catOne);
        imagem.setImgDescricao("Diferenciada no add "+new Random().nextDouble());
        imagem.setImgOrcamento(orcOne);
        imagem.setImgImagem(readImage("teste_software_bug.jpg"));
        imagem.setImgdataInclusao(new Date());
        imagem.setImghoraInclusao(new Date());
        Imagem result = instance.addImagem(imagem);
        assertEquals(instance.getImagem(result.getImgId()), result);
        instance.removeImagem(imagem);
    }
    
    public static byte[] readImage(String filename) throws IOException {
        InputStream imagemStream = ImageServiceTest.class.getResourceAsStream(filename);
        byte[] conteudo = new byte[imagemStream.available()];
        imagemStream.read(conteudo);
        imagemStream.close();
        return conteudo;
    }
    
    /**
     * Test of addImagem method, of class ImageService.
     */
    @Test(expected = Exception.class)
    public void testAntiAddImagem() throws Exception {
        // Mock Orcamento Object
        Orcamento orcOne = new Orcamento();
        orcOne.setOrcCustomer(customerOne);
        orcOne.setOrcDate(new Date());
        orcOne.setOrcDentist(userOne);
        orcOne.setOrcHour(new Date());
        orcOne.setOrcObs("Obs "+new Random().nextInt());
        orcOne.setOrcTimes(new Random().nextInt(10));
        orcOne.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcOne.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        Service servOne = new Service();
        servOne.setSrvName("Test Orcamento Service One Add "+new Random().nextInt());
        servOne.setSrvCost(orcOne.getOrcTotal());
        // Mock Of Item
        Orcamentoitem oritemOne = new Orcamentoitem();
        oritemOne.setOriCost(orcOne.getOrcTotal());
        oritemOne.setOriObs("Obs Item add  "+new Random().nextInt());
        oritemOne.setOriService(servOne);
        orcOne.addItem(oritemOne);
        Categoriaimagem catOne = new Categoriaimagem();
        catOne.setCigNome("Categoria Imagem Cat Add ne "+new Random().nextLong());
        
        System.out.println("AntiAddImagem");
        Imagem imagem = new Imagem();
        imagem.setImgCategoria(catOne);
        imagem.setImgDescricao("Diferenciada no add "+new Random().nextDouble());
        imagem.setImgOrcamento(orcOne);
        imagem.setImgImagem(readImage("erroimagem.jpg"));
        imagem.setImgdataInclusao(new Date());
        imagem.setImghoraInclusao(new Date());
        Imagem result = instance.addImagem(imagem);
        assertEquals(instance.getImagem(result.getImgId()), result);
        instance.removeImagem(imagem);
    }

    /**
     * Test of setImagem method, of class ImageService.
     */
    @Test
    public void testSetImagem() throws Exception {
        System.out.println("setImagem");
        Imagem imagem = imgOne;
        String newObs = "new obs of imagemOne "+new Random().nextLong();
        imagem.setImgDescricao(newObs);
        Imagem result = instance.setImagem(imagem);
        Imagem newResult = instance.getImagem(result.getImgId());
        assertEquals(newResult.getImgDescricao(),newObs);
    }

    /**
     * Test of removeImagem method, of class ImageService.
     */
    @Test
    public void testRemoveImagem() throws Exception {
        System.out.println("removeImagem");
        // Mock Orcamento Object
        Orcamento orcOne = new Orcamento();
        orcOne.setOrcCustomer(customerOne);
        orcOne.setOrcDate(new Date());
        orcOne.setOrcDentist(userOne);
        orcOne.setOrcHour(new Date());
        orcOne.setOrcObs("Obs "+new Random().nextInt());
        orcOne.setOrcTimes(new Random().nextInt(10));
        orcOne.setOrcTotal(new BigDecimal(Math.abs(new Random().nextDouble())));
        orcOne.setOrcpaymentType(PaymentType.CREDITO);
        // Mock Service Object- One
        Service servOne = new Service();
        servOne.setSrvName("Test Orcamento Service One Add "+new Random().nextInt());
        servOne.setSrvCost(orcOne.getOrcTotal());
        // Mock Of Item
        Orcamentoitem oritemOne = new Orcamentoitem();
        oritemOne.setOriCost(orcOne.getOrcTotal());
        oritemOne.setOriObs("Obs Item add  "+new Random().nextInt());
        oritemOne.setOriService(servOne);
        orcOne.addItem(oritemOne);
        Categoriaimagem catOne = new Categoriaimagem();
        catOne.setCigNome("Categoria Imagem Cat Add ne "+new Random().nextLong());

        
        System.out.println("addImagem");
        Imagem imagem = new Imagem();
        imagem.setImgCategoria(catOne);
        imagem.setImgDescricao("Diferenciada no add "+new Random().nextDouble());
        imagem.setImgOrcamento(orcOne);
        imagem.setImgImagem(readImage("teste_software_bug.jpg"));
        imagem.setImgdataInclusao(new Date());
        imagem.setImghoraInclusao(new Date());
        Imagem result = instance.addImagem(imagem);
        assertEquals(instance.getImagem(result.getImgId()), result);
        instance.removeImagem(imagem);
        assertNull(instance.getImagem(imagem.getImgId()));
    }

    /**
     * Test of getImagem method, of class ImageService.
     */
    @Test
    public void testGetImagem() throws Exception {
        System.out.println("getImagem");
        int idOfImagem = imgTwo.getImgId();
        Imagem expResult = imgTwo;
        Imagem result = instance.getImagem(idOfImagem);
        assertEquals(expResult, result);
    }

    /**
     * Test of getImagensOfOrcamento method, of class ImageService.
     */
    @Test
    public void testGetImagensOfOrcamento_int() throws Exception {
        System.out.println("getImagensOfOrcamento");
        int idOrcamento = orcamentoTwo.getOrcId();
        List<Imagem> result = instance.getImagensOfOrcamento(idOrcamento);
        assertTrue(result.size() == 1);
    }

    /**
     * Test of getImagensOfOrcamento method, of class ImageService.
     */
    @Test
    public void testGetImagensOfOrcamento_int_int() throws Exception {
        System.out.println("getImagensOfOrcamento");
        int idOrcamento = orcamentoOne.getOrcId();
        int idCategoria = catImagemOne.getCigId();
        List<Imagem> result = instance.getImagensOfOrcamento(idOrcamento, idCategoria);
        assertTrue(result.size() == 1);
    }

    
}
