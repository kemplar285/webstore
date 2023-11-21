package com.shop.webstore;

import com.shop.webstore.data.model.product.Product;
import com.shop.webstore.data.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebstoreApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setSkipNullEnabled(true)
				.setMatchingStrategy(MatchingStrategies.LOOSE)
		;
		return modelMapper;
	}

	@Bean
	public ApplicationRunner loadData(ProductRepository productRepository) {
		List<Product> productList = productRepository.findAll();
		if(productList.size() == 0){
			List<Product> initialProducts = List.of(
					new Product("The Catcher in the Rye", 11, 1, "descr"),
					new Product("To Kill a Mockingbird", 22, 2,"descr"),
					new Product("1984", 33, 3,"descr"),
					new Product("Animal Farm", 44, 4,"descr"),
					new Product("The Great Gatsby", 55, 5,"descr"),
					new Product("One Hundred Years of Solitude", 66, 6,"descr")
			);

			for(Product p : initialProducts){
				productRepository.save(p);
				System.out.println(p.toString());
			}
		}
		return null;
	}
}
