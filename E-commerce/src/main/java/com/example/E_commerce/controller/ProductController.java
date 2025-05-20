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
import com.example.E_commerce.service.security.service.JWTservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        int sellerId= JWTservice.getAuthenticatiedUser().getId();
        Product product= new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setStockQuantity(productRequestDTO.getStockQuantity());
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

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/updateproduct/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO, @PathVariable int productId){
        int sellerId= JWTservice.getAuthenticatiedUser().getId();
        Product product= productService.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the product with Specified Id"));
        if(product.getSeller().getId()!=sellerId){
            throw new AccessDeniedException("Sorry, the Product with provided Id donot belongs to you");
        }
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setStockQuantity(productRequestDTO.getStockQuantity());
        try{
            PRODUCT_CATEGORY category = PRODUCT_CATEGORY.valueOf(productRequestDTO.getCategory());
            product.setCategory(category);
        }catch (Exception ex){}
        productService.save(product);
        return ResponseEntity.ok(ProductMapper.createProductDTO(product));
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        int sellerId= JWTservice.getAuthenticatiedUser().getId();
        Product product= productService.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Cannot Find the product with Specified Id"));
        if(product.getSeller().getId()!=sellerId){
            throw new AccessDeniedException("Sorry, the Product with provided Id Do not belongs to you");
        }
        productService.delete(productId);
        return ResponseEntity.ok("SuccessFully Deleted");
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/findbyid/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable int productId){
        int sellerId= JWTservice.getAuthenticatiedUser().getId();
        Product product= productService.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Cannot find the product with provided id"));
        return ResponseEntity.ok(ProductMapper.createProductDTO(product));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/findall")
    public ResponseEntity<List<ProductDTO>> findALl(){
        List<Product> productList= new ArrayList<>();
        productService.findAll().forEach((product)->
                productList.add(product)
                );
       List<ProductDTO> productDTOList= productList.stream().map((product)->{
           return  ProductMapper.createProductDTO(product);
                }).toList();
       return  ResponseEntity.ok(productDTOList);
    }
}
