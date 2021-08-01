package productApp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import productApp.models.Product;

@Component
public class productDao {
	@Autowired
private HibernateTemplate hibernateTemplate;

	@Transactional
	public void createProduct(Product product)
	{
		this.hibernateTemplate.saveOrUpdate(product);
	}
	
	public List<Product> getProducts()
	{
		return this.hibernateTemplate.loadAll(Product.class);
	}
	
	//delete single product
	@Transactional
	public void deleteProduct(int pid)
	{
		Product p=this.hibernateTemplate.load(Product.class,pid);
		this.hibernateTemplate.delete(p);
	}
	//get singleproduct
	
	public Product getProduct(int id)
	{
		return this.hibernateTemplate.get(Product.class,id);
	}
}
