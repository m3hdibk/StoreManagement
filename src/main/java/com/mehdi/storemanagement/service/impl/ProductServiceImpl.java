package com.mehdi.storemanagement.service.impl;


import com.mehdi.storemanagement.exception.SchemeNotFoundException;
import com.mehdi.storemanagement.model.Product;
import com.mehdi.storemanagement.model.Stock;
import com.mehdi.storemanagement.model.dto.SchemeData;
import com.mehdi.storemanagement.model.dto.StockData;
import com.mehdi.storemanagement.model.dto.StockHistoryData;
import com.mehdi.storemanagement.model.dto.request.ProductRequest;
import com.mehdi.storemanagement.model.dto.request.ProductUpdateRequest;
import com.mehdi.storemanagement.model.dto.response.PageResponse;
import com.mehdi.storemanagement.model.dto.ProductData;
import com.mehdi.storemanagement.repository.ProductRepository;
import com.mehdi.storemanagement.repository.SchemeRepository;
import com.mehdi.storemanagement.repository.StockHistoryRepository;
import com.mehdi.storemanagement.repository.StockRepository;
import com.mehdi.storemanagement.service.ProductService;
import com.mehdi.storemanagement.util.SchemeUtils;
import com.mehdi.storemanagement.util.StockUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public record ProductServiceImpl(ProductRepository productRepository, StockRepository stockRepository,
                                 StockHistoryRepository stockHistoryRepository, SchemeRepository schemeRepository)
        implements ProductService {


    @Override
    public ProductData createProduct(ProductRequest productRequest) {
        ProductData productData = new ProductData();
        productData.setProductCode(productRequest.getProductCode());
        productData.setBuyPrice(productRequest.getBuyPrice());
        productData.setSellPrice(productRequest.getSellPrice());
        productData.setLength(productRequest.getLength());
        productData.setWidth(productRequest.getWidth());
        productData.setWeight(productRequest.getWeight());
        productData.setUpc(productRequest.getUpc());
        SchemeData brand = new SchemeData();
        brand.setId(productRequest.getBrandId());
        productData.setBrand(brand);
        Set<SchemeData> categories = productRequest.getCategoriesIds().stream().map(categoryId -> {
            SchemeData category = new SchemeData();
            category.setId(categoryId);
            return category;
        }).collect(Collectors.toSet());
        productData.setCategories(categories);
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
        stockHistoryRepository.save(stockHistoryData.convertToEntity());
        return productData;
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
        productData.setLength(productUpdateRequest.getLength());
        productData.setHeight(productUpdateRequest.getHeight());
        productData.setWeight(productUpdateRequest.getWeight());
        productData.setWidth(productUpdateRequest.getWidth());
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
    public PageResponse<ProductData> getAllProducts(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Product> pageProduct = productRepository.findAll(paging);

        List<ProductData> products = pageProduct.getContent().stream()
                .map(Product::convertToData)
                .collect(Collectors.toList());

        return new PageResponse<>(products, pageProduct.getNumber(),
                pageProduct.getTotalElements(),
                pageProduct.getTotalPages());
    }

    @Override
    public ProductData getProductById(long id) {
        return productRepository.getById(id).convertToData();
    }
}
