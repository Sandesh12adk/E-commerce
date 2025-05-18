package com.example.E_commerce.controller;

import com.example.E_commerce.Constant.PRODUCT_CATEGORY;
import com.example.E_commerce.Constant.USER_ROLE;
import com.example.E_commerce.dto.ProductDTO;
import com.example.E_commerce.dto.ProductRequestDTO;
import com.example.E_commerce.entity.Product;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.mapper.ProductMapper;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @PostMapping("/add/{sellerId}")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable int sellerId){
        Product product= new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setStockQuantity(productRequestDTO.getQuantity());
        try{
           PRODUCT_CATEGORY category = PRODUCT_CATEGORY.valueOf(productRequestDTO.getCategory());
           product.setCategory(category);
        }catch (Exception ex){}
        User seller= userService.findBYId(sellerId).orElseThrow(()->
                new ResourceNotFoundException("Cannot find the user with provided Id"));
        product.setSeller(seller);
        productService.save(product);
        return ResponseEntity.ok(ProductMapper.createProductDTO(product));
    }
    @PutMapping("/updateproduct/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable int productId){
        Product product= productService.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the product with Specified Id"));
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setStockQuantity(productRequestDTO.getQuantity());
        try{
            PRODUCT_CATEGORY category = PRODUCT_CATEGORY.valueOf(productRequestDTO.getCategory());
            product.setCategory(category);
        }catch (Exception ex){}
        productService.save(product);
        return ResponseEntity.ok(ProductMapper.createProductDTO(product));
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        productService.delete(productId);
        return ResponseEntity.ok("SuccessFully Deleted");
    }
}
