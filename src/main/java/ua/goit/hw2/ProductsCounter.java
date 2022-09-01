package ua.goit.hw2;

import ua.goit.hw2.dto.ProductDto;
import ua.goit.hw2.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsCounter {
    private final ProductsRepository productsRepository = new ProductsRepository();

    public List<ProductDto> countProductListFrom(String basket) {
        List<ProductDto> productsList = new ArrayList<>();
        char[] chars = basket.toCharArray();

        for (char aChar : chars) {
            ProductDto product = productsRepository.findOneByCode(aChar);

            if (product == null) {
                continue;
            }
            productsList.add(product);
        }

        return productsList;
    }
}

