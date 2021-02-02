package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.repository.ProductRepository;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    @Override
    public Product create(String name, Double price, Integer quantity, List<Long> categories) {
        for(Long id:categories){
            this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);
        }
        return this.productRepository.save(new Product(name, price, quantity, this.categoryRepository.findAllById(categories)));
    }

    @Override
    public Product update(Long id, String name, Double price, Integer quantity, List<Long> categories) {
        this.productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
        this.productRepository.deleteById(id);
        return this.productRepository.save(new Product(name,price,quantity,this.categoryRepository.findAllById(categories)));
    }

    @Override
    public Product delete(Long id) {
        this.productRepository.deleteById(id);
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        Category category = (categoryId!=null) ? this.categoryRepository.findById(categoryId).orElse(null) : null;
        String NAME = "%" + name + "%";
        if(name!=null && !name.isEmpty() && category!=null) return this.productRepository.findAllByNameLikeAndCategoriesContaining(NAME, category);
        else if(name!=null && !name.isEmpty()) return this.productRepository.findByNameLike(NAME);
        else if(category!=null) return this.productRepository.findAllByCategoriesContaining(category);
        else return this.productRepository.findAll();

    }
}
