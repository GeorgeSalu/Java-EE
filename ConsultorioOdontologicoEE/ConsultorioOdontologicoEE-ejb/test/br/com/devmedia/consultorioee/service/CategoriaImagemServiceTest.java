
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Categoriaimagem;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author George salu
 */
public class CategoriaImagemServiceTest {

        private static EJBContainer container;
    private CategoriaImagemService instance;
    private Categoriaimagem catImagemOne;
    private Categoriaimagem catImagemTwo;
    
    public CategoriaImagemServiceTest() {
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
        instance = (CategoriaImagemService) container.getContext().lookup("java:global/classes/CategoriaImagemService");
        // mock de objetos
        catImagemOne = new Categoriaimagem();
        catImagemOne.setCigNome("Categoria Imagem Test One "+new Random().nextLong());
        
        catImagemTwo = new Categoriaimagem();
        catImagemTwo.setCigNome("Categoria Imagem Test Two "+new Random().nextLong());

        instance.addCategoriaimagem(catImagemOne);
        instance.addCategoriaimagem(catImagemTwo);
    }
    
    @After
    public void tearDown() {
        instance.removeCategoriaimagem(catImagemOne);
        instance.removeCategoriaimagem(catImagemTwo);
    }

    /**
     * Test of addCategoriaimagem method, of class CategoriaImagemService.
     */
    @Test
    public void testAddCategoriaimagem() throws Exception {
        System.out.println("addCategoriaimagem");
        Categoriaimagem categoriaimagem = new Categoriaimagem();
        categoriaimagem.setCigNome("The Add Operation "+new Random().nextLong());
        Categoriaimagem result = instance.addCategoriaimagem(categoriaimagem);
        assertNotNull(result.getCigId());
        instance.removeCategoriaimagem(categoriaimagem);
    }

    /**
     * Test of setCategoriaimagem method, of class CategoriaImagemService.
     */
    @Test
    public void testSetCategoriaimagem() throws Exception {
        System.out.println("setCategoriaimagem");
        Categoriaimagem categoriaimagem = catImagemOne;
        String newName = "New Name "+new Random().nextLong();
        catImagemOne.setCigNome(newName);
        Categoriaimagem result = instance.setCategoriaimagem(categoriaimagem);
        assertEquals(newName, result.getCigNome());
    }

    /**
     * Test of removeCategoriaimagem method, of class CategoriaImagemService.
     */
    @Test
    public void testRemoveCategoriaimagem() throws Exception {
        System.out.println("removeCategoriaimagem");
        Categoriaimagem categoriaimagem = new Categoriaimagem();
        categoriaimagem.setCigNome("The Remove Operation "+new Random().nextLong());
        Categoriaimagem result = instance.addCategoriaimagem(categoriaimagem);
        assertNotNull(result.getCigId());
        instance.removeCategoriaimagem(categoriaimagem);
        result = instance.getCategoriaimagem(categoriaimagem.getCigId());
        assertNull(result);
    }

    /**
     * Test of getCategoriaimagem method, of class CategoriaImagemService.
     */
    @Test
    public void testGetCategoriaimagem() throws Exception {
        System.out.println("getCategoriaimagem");
        int idOfCategoriaimagem = catImagemTwo.getCigId();
        Categoriaimagem expResult = catImagemTwo;
        Categoriaimagem result = instance.getCategoriaimagem(idOfCategoriaimagem);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoriasDeImagem method, of class CategoriaImagemService.
     */
    @Test
    public void testGetCategoriasDeImagem() throws Exception {
        System.out.println("getCategoriasDeImagem");
        List<Categoriaimagem> result = instance.getCategoriasDeImagem();
        assertTrue(result.size() >= 2);
    }

    /**
     * Test of getCategoriaimagemByName method, of class CategoriaImagemService.
     */
    @Test
    public void testGetCategoriaimagemByName() throws Exception {
        System.out.println("getCategoriaimagemByName");
        String localizar = "Categoria Imagem Test One";
        List<Categoriaimagem> result = instance.getCategoriaimagemByName(localizar);
        assertTrue(result.size() >= 1);
    }

    
}
