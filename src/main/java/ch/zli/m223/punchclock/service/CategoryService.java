package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.CategoryRepository;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepostiory) {
        this.categoryRepository = categoryRepostiory;
    }

    public Category createCategory(Category category) {
        if (!categoryRepository.exists(Example.of(category))) {
            return categoryRepository.saveAndFlush(category);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Crate Category: Category '" + category.getName() + "' already exists!");
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
