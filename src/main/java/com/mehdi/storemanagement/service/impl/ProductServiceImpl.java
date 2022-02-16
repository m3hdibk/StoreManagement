package com.mehdi.storemanagement.service.impl;


import com.mehdi.storemanagement.exception.SchemeNotFoundException;
import com.mehdi.storemanagement.model.Product;
import com.mehdi.storemanagement.model.Stock;
import com.mehdi.storemanagement.model.dto.*;
import com.mehdi.storemanagement.model.dto.request.ProductRequest;
import com.mehdi.storemanagement.model.dto.request.ProductUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.response.ProductResponse;
import com.mehdi.storemanagement.repository.*;
import com.mehdi.storemanagement.service.ProductService;
import com.mehdi.storemanagement.service.SchemeService;
import com.mehdi.storemanagement.util.ResponseConverterUtils;
import com.mehdi.storemanagement.util.SchemeUtils;
import com.mehdi.storemanagement.util.StockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public record ProductServiceImpl(ProductRepository productRepository, StockRepository stockRepository,
                                 StockHistoryRepository stockHistoryRepository, SchemeRepository schemeRepository,
                                 TaxRepository taxRepository, SchemeService schemeService)
        implements ProductService {


    @Override
    public void createProduct(ProductRequest productRequest) {
        ProductData productData = new ProductData();
        productData.setProductCode(productRequest.getProductCode());
        productData.setName(productRequest.getName());
        productData.setBuyPrice(productRequest.getBuyPrice());
        productData.setSellPrice(productRequest.getSellPrice());
        productData.setUpc(productRequest.getUpc());
        productData.setUnit(productRequest.getUnit());
        if (productRequest.getVat() != 0) {
            TaxData vatData = new TaxData();
            vatData.setId(productRequest.getVat());
            productData.setVat(vatData);
        }
        if (productRequest.getTaxes() != null && !productRequest.getTaxes().isEmpty()) {
            List<TaxData> taxes = productRequest.getTaxes().stream().map(taxId -> {
                TaxData taxData = new TaxData();
                taxData.setId(taxId);
                return taxData;
            }).collect(Collectors.toList());
            productData.setTaxes(taxes);
        }
        if (productRequest.getBrandId() == 0) {
            productData.setBrand(schemeService.getDefaultSchemeByType(SchemeUtils.SchemeType.BRAND.getId()));
        } else {
            SchemeData brand = new SchemeData();
            brand.setId(productRequest.getBrandId());
            productData.setBrand(brand);
        }

        if (productRequest.getCategoriesIds() != null && !productRequest.getCategoriesIds().isEmpty()) {
            Set<SchemeData> categories = productRequest.getCategoriesIds().stream().map(categoryId -> {
                SchemeData category = new SchemeData();
                category.setId(categoryId);
                return category;
            }).collect(Collectors.toSet());
            productData.setCategories(categories);
        } else {
            SchemeData defaultCategory = schemeService.getDefaultSchemeByType(SchemeUtils.SchemeType.CATEGORY.getId());
            productData.setCategories(Collections.singleton(defaultCategory));
        }

        Product product = productRepository.save(productData.convertToEntity());
        productData.setId(product.getId());
        StockData stock = new StockData();
        stock.setProduct(productData);
        SchemeData location = new SchemeData();
        location.setId(productRequest.getLocationId());
        stock.setLocation(location);
        stock.setQuantity(productRequest.getQuantity());
        Stock savedStock = stockRepository.save(stock.convertToEntity());
        stock.setId(savedStock.getId());
        StockHistoryData stockHistoryData = new StockHistoryData();
        stockHistoryData.setStock(stock);
        stockHistoryData.setTransactionType(StockUtils.StockTransactionType.BUY.getId());
        stockHistoryData.setQuantity(productRequest.getQuantity());
        stockHistoryData.setLocation(location);
        stockHistoryData.setDate(LocalDate.now());
        stockHistoryData.setTime(LocalTime.now());
        stockHistoryData.setComment("New Product");
        stockHistoryRepository.save(stockHistoryData.convertToEntity());
    }


    @Override
    public Page<ProductData> findProductsByCategoryId(long categoryId) {
        return null;
    }

    @Override
    public void updateProduct(ProductUpdateRequest productUpdateRequest, long productId) {
        ProductData productData = validateProduct(productId);
        validateScheme(productUpdateRequest.getBrandId(), SchemeUtils.SchemeType.BRAND, "Brand not found, BrandId : ");
        Set<SchemeData> categoriesList = validateCategories(productUpdateRequest.getCategoriesIds());

        productData.setBuyPrice(productUpdateRequest.getBuyPrice());
        productData.setSellPrice(productUpdateRequest.getSellPrice());
        SchemeData brandScheme = new SchemeData();
        brandScheme.setId(productUpdateRequest.getBrandId());
        productData.setBrand(brandScheme);
        productData.setUpc(productUpdateRequest.getUpc());
        productData.setCategories(categoriesList);

        productRepository.save(productData.convertToEntity());
    }

    private ProductData validateProduct(long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new EntityNotFoundException("Product not found, productId : " + productId);
        }
        return optionalProduct.get().convertToData();
    }

    private void validateScheme(long brandId, SchemeUtils.SchemeType brand, String message) {
        boolean isBrandFound =
                schemeRepository.existsSchemeByIdAndStatusTrueAndType(brandId,
                        brand.getId());
        if (!isBrandFound) {
            throw new SchemeNotFoundException(message + brandId);

        }
    }

    private Set<SchemeData> validateCategories(Set<Long> categoriesIds) {
        return categoriesIds.stream().map(categoryId -> {
            validateScheme(categoryId, SchemeUtils.SchemeType.CATEGORY, "Category not found, CategoryId : ");
            SchemeData categoryScheme = new SchemeData();
            categoryScheme.setId(categoryId);
            return categoryScheme;
        }).collect(Collectors.toSet());
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(String productCode, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> pageProduct;
        if (productCode != null) {
            pageProduct = productRepository.findAllByProductCodeStartsWith(productCode, paging);

        } else {
            pageProduct = productRepository.findAll(paging);

        }

        List<ProductData> products = pageProduct.getContent().stream()
                .map(Product::convertToData)
                .collect(Collectors.toList());

        return new PageResponse<>(ResponseConverterUtils.convertToProductResponseList(products), pageProduct.getNumber(),
                pageProduct.getTotalElements(),
                pageProduct.getTotalPages());
    }

    @Override
    public ProductData getProductById(long id) {
        return productRepository.getById(id).convertToData();
    }
}
