package t.ressource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import t.db.ProductDB;
import t.model.Product;

public class ProductRessources {
	
	private ProductDB db;
	
	public ProductRessources() {
		db = new ProductDB();
	}
	
	public ProductDB getDB() {
		return db;
	}
	
	public List<Product> findByTags(String toFind) {
		List<String> tagList = Arrays.asList(toFind.split(" "));

		return getDB().findAll().stream().filter(
				product -> tagList.stream().filter(
								tag -> product.getDetail().contains(tag) ||
										product.getImage().contains(tag) ||
										product.getInfo().contains(tag) || 
										product.getName().contains(tag) || 
										tag.equalsIgnoreCase(product.getQuantity() + "") ||
										tag.equalsIgnoreCase(product.getPrice() + "")
					).count() > 0
			).collect(Collectors.toList());
	}
	
	public void buy(Long id, Integer quantity) {
		if (getDB().existsById(id)) {
			Product toBuy = getDB().findById(id);
			if (toBuy.getQuantity() >= quantity) {
				toBuy.setQuantity(toBuy.getQuantity()-quantity);
				getDB().save(toBuy);
			}
		}
	}
	
}
