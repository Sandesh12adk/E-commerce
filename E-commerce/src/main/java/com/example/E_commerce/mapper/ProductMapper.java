package com.example.E_commerce.mapper;

import com.example.E_commerce.dto.ProductDTO;
import com.example.E_commerce.entity.Product;

public class ProductMapper {
    public static ProductDTO createProductDTO(Product product){
        ProductDTO productDTO= new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setCategory(product.getCategory().name());
        productDTO.setDescription(product.getDescription());
        productDTO.setSellerId(product.getSeller().getId());
        return productDTO;
    }
}
