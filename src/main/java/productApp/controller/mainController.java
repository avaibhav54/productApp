package productApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import productApp.dao.productDao;
import productApp.models.Product;

@Controller
public class mainController {
	@Autowired
	private productDao productdao;

	@RequestMapping("/")
	public String home(Model m)
	{
		List<Product>list=this.productdao.getProducts();
		m.addAttribute("product",list);
		return "index";
	}
	
	@RequestMapping("/add-product")
	public String addProduct()
	{
		return "add_product_form";
	}
	
	@RequestMapping("/handle_product")
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest req)
	{
		this.productdao.createProduct(product);
		RedirectView redirectview=new RedirectView();
		redirectview.setUrl(req.getContextPath()+"/");
		
		return  redirectview;
		
	}
	

	@RequestMapping("/delete/{productId}")
	public RedirectView deleteHandler(@PathVariable("productId") int id,HttpServletRequest req)
	{
		System.out.println("hi");
		this.productdao.deleteProduct(id);
		RedirectView redirectview=new RedirectView();
		redirectview.setUrl(req.getContextPath()+"/");
			return  redirectview;
	}
	
	@RequestMapping("/update/{productId}")
	public String updateHandler(@PathVariable("productId") int id,Model m)
	{
		Product p =this.productdao.getProduct(id);
		m.addAttribute("product",p);
	
		return  "updateForm";
	}
	
}
