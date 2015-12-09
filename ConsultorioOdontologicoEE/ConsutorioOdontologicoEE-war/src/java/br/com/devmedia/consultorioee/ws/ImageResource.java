/*
 *  LinkedByUS - Todos Direitos Reservados - 2014/2015
 * 
 */
package br.com.devmedia.consultorioee.ws;

import br.com.devmedia.consultorioee.entities.Imagem;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Dyego Souza do Carmo
 */
@Path("image")
@RequestScoped
public class ImageResource {

    @Context
    private UriInfo context;

    public ImageResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idOrcamento}/{idCategoria}")
    public List<Imagem> getImagensData(@PathParam("idOrcamento") int idOrcamento,@PathParam("idCategoria") int idCategoria) {
        System.out.println("Id do orcamento eh : "+idOrcamento);
        System.out.println("Id da Categoria eh : "+idCategoria);
        return null;
    }

}
