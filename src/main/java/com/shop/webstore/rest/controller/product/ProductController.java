package com.shop.webstore.rest.controller.product;

import com.shop.webstore.data.model.product.ProductDTO;
import com.shop.webstore.data.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        ProductDTO dto = productService.add(productDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        ProductDTO editedDTO = productService.edit(id, productDTO);
        return new ResponseEntity<>(editedDTO, HttpStatus.OK);
    }

    @GetMapping("/")
        public ResponseEntity<List<ProductDTO>> findAll(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
