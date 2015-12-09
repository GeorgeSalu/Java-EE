package br.com.devmedia.consultorioee.ws;

import br.com.devmedia.consultorioee.entities.Imagem;
import br.com.devmedia.consultorioee.service.ImageService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
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
 * @author George Salu
 */
@Path("image")
@RequestScoped
public class ImageResource {

    @Context
    private UriInfo context;
    @EJB
    private ImageService imageService;

    public ImageResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{idOrcamento}/{idCategoria}")
    public List<Imagem> getImagensData(@PathParam("idOrcamento") int idOrcamento,@PathParam("idCategoria") int idCategoria) {
        System.out.println("Id do orcamento eh : "+idOrcamento);
        System.out.println("Id da Categoria eh : "+idCategoria);
        List<Imagem> imgList = new LinkedList<Imagem>();
        List<Imagem> fromJPA= imageService.getImagensOfOrcamento(idOrcamento,idCategoria);
        for (Imagem img  : fromJPA) {
            Imagem toJson = new Imagem();
            toJson.setImgId(img.getImgId());
            toJson.setImgDescricao(img.getImgDescricao());
            imgList.add(toJson);
        }
        return imgList;
    }

}
