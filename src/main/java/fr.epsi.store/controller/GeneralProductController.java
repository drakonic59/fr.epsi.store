package t.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import t.model.Product;
import t.ressource.ProductRessources;

@Path("/products")
public class GeneralProductController {

	private ProductRessources products = new ProductRessources();
	
    @GET
    @Produces("application/json")
    public List<Product> findAll() {
        return products.getDB().findAll();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public List<Product> save(List<Product> products) {
    	return this.products.getDB().save(products);
    }
	
}
