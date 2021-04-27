package t.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import t.model.Product;
import t.ressource.ProductRessources;

@Path("/product")
public class ProductController {
	
	private ProductRessources products = new ProductRessources();
    
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
    	
    	if (products.getDB().existsById(id)) {
    		return Response
    		      .status(Response.Status.OK)
    		      .entity(products.getDB().findById(id))
    		      .build();
    	}
    	
    	return Response
  		      .status(Response.Status.NOT_FOUND)
  		      .entity("NOT_FOUND_ERROR : bad ID (" + id + ").")
  		      .build();
    }
    
    @GET
    @Produces("application/json")
    @Path("/search/{toFind}")
    public List<Product> search(@DefaultValue("") @PathParam("toFind") String toFind) {
        return products.findByTags(toFind);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
    	if (products.getDB().existsById(id) && id.equals(product.getId())) {
    		return Response
      		      .status(Response.Status.OK)
      		      .entity(products.getDB().save(product))
      		      .build();
    	}
    	
    	return Response
		      .status(Response.Status.NOT_FOUND)
		      .entity("NOT_FOUND_ERROR : bad ID (" + id + ") or product id don't match the path parameter id.")
		      .build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/buy/{id}/{quantity}")
    public Response buy(@PathParam("id") Long id, @PathParam("quantity") Integer quantity) {
    	if (products.getDB().existsById(id) && quantity > 0) {
    		if (products.getDB().findById(id).getQuantity() >= quantity) {

    			products.buy(id, quantity);
    			return Response
    	  		      .status(Response.Status.OK)
    	  		      .entity(products.getDB().findAll())
    	  		      .build();
    		
    		}
    		
    		return Response
	  		      .status(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE)
			      .entity("REQUESTED_RANGE_NOT_SATISFIABLE_ERROR : ask for too big quantity. Available of '" + products.getDB().findById(id).getName() +  "' (id=" + id + ") : " + products.getDB().findById(id).getQuantity() + " items.")
	  		      .build();
    		
    	}
    	
    	return Response
  		      .status(Response.Status.NOT_FOUND)
		      .entity("NOT_FOUND_ERROR : bad ID (" + id + ").")
  		      .build();
    }
    
    @DELETE
    @Produces("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
    	if (products.getDB().existsById(id)) {
	    	products.getDB().delete(id);
	    	return Response
  	  		      .status(Response.Status.OK)
  	  		      .entity(products.getDB().findAll())
  	  		      .build();
    	}

    	return Response
				.status(Response.Status.NOT_FOUND)
				.entity("NOT_FOUND_ERROR : bad ID (" + id + ").")
				.build();
    	
    }
	
}
