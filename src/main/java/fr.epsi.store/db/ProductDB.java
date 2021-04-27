package t.db;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import t.model.Product;

public class ProductDB {
	
	private static List<Product> products = new ArrayList<Product>();
	
	public ProductDB() {
	}
	
	public List<Product> findAll() {
		return ProductDB.products;
	}
	
	public boolean existsById(Long id) {
		return ProductDB.products.stream().filter(product -> product.getId().equals(id)).count() > 0;
	}
	
	public Product findById(Long id) {
		if (existsById(id))
			return ProductDB.products.stream().filter(product -> product.getId().equals(id)).collect(Collectors.toList()).get(0);
		else
			return null;
	}
	
	public Product save(Product product) {
		if (product != null) {
			if (product.getId() != null) {
				if (!existsById(product.getId()))
					ProductDB.products.add(product);
				else {
					ProductDB.products.remove(findById(product.getId()));
					ProductDB.products.add(product);
				}
			} else {
				product.setId(new Long(findAll().size()+1));
				ProductDB.products.add(product);
			}
			return product;
		}
		return null;
	}
	
	public List<Product> save(List<Product> products) {
		if (products != null) {
			for (Product product : products) {
				if (product.getId() != null) {
					if (!existsById(product.getId()))
						ProductDB.products.add(product);
					else {
						ProductDB.products.remove(findById(product.getId()));
						ProductDB.products.add(product);
					}
				} else {
					product.setId(new Long(findAll().size()+1));
					ProductDB.products.add(product);
				}
			}
			return findAll();
		}
		return null;
	}
	
	public void delete(Long id) {
		if (existsById(id))
			products.remove(findById(id));
	}
	
}
