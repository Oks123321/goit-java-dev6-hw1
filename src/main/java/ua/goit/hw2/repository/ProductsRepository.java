package ua.goit.hw2.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ua.goit.hw2.dto.ProductDto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductsRepository {
    private static final String PRODUCTS_FILE_PATH = "./src/main/java/ua/goit/hw2/storage/products.json";
    private Map<Character, ProductDto> products;

    public ProductsRepository() {
        getProductsFromFile();
    }

    private void getProductsFromFile() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductDto>>() {
        }.getType();
        try {
            FileReader reader = new FileReader(PRODUCTS_FILE_PATH);
            List<ProductDto> productsList = gson.fromJson(new JsonReader(reader), type);

            Map<Character, ProductDto> map = new HashMap<>();
            for (ProductDto item : productsList) {
                if (map.put(item.getCode(), item) != null) {
                    throw new IllegalStateException("Duplicate key");
                }
            }
            products = map;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("File '%s' not found or broken", PRODUCTS_FILE_PATH));
        }
    }

    public ProductDto findOneByCode(Character code) {
        return products.get(code);
    }
}
