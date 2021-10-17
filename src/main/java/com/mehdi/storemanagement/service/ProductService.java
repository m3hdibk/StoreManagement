package com.mehdi.storemanagement.service;


import com.mehdi.storemanagement.model.dto.request.ProductRequest;
import com.mehdi.storemanagement.model.dto.request.ProductUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.model.dto.response.ProductResponse;
import org.springframework.data.domain.Page;


public interface ProductService {

    void createProduct(ProductRequest productRequest);
    Page<ProductData> findProductsByCategoryId(long categoryId);
    void updateProduct(ProductUpdateRequest productUpdateRequest, long productId);
    PageResponse<ProductResponse> getAllProducts(String productCode, int page, int size);
    ProductData getProductById(long id);

}