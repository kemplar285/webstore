package com.shop.webstore.data.service;

import com.shop.webstore.data.model.product.Product;
import com.shop.webstore.data.model.product.ProductDTO;
import com.shop.webstore.data.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements IService<ProductDTO, Long> {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.modelMapper = mapper;
    }

    public ProductDTO mapToDTO(Product product){
        return Objects.isNull(product) ? null : modelMapper.map(product, ProductDTO.class);
    }

    public Product mapToEntity(ProductDTO dto){
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Product.class);
    }

    @Override
    public ProductDTO add(ProductDTO dto) {
        logger.info(dto.toString());
        Product product = mapToEntity(dto);
        logger.info(product.toString());
        productRepository.save(product);
        return findById(product.getId());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return mapToDTO(product);
    }

    @Override
    public ProductDTO edit(Long id, ProductDTO productDTO) {
        productRepository.findById(id).orElseThrow();
        Product product = modelMapper.map(productDTO, Product.class);
        product.setId(id);
        productRepository.save(product);
        logger.debug(productDTO.toString());
        logger.debug(product.toString());
        return mapToDTO(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDTO> allDTOProducts = new ArrayList<>();
        for(Product product : allProducts){
            allDTOProducts.add(mapToDTO(product));
        }
        return allDTOProducts;
    }
}

