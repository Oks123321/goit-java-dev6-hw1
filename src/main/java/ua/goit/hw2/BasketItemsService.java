package ua.goit.hw2;

import ua.goit.hw2.dto.BasketItemDto;
import ua.goit.hw2.dto.ProductDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketItemsService {
    private final ProductsCounter productsCounter = new ProductsCounter();

    public Map<Character, BasketItemDto> countBasketItems(String basketRow) {
        Map<Character, BasketItemDto> basketItemsMap = new HashMap<>();

        for (ProductDto productDto : countProductsList(basketRow)) {
            Character productCode = productDto.getCode();

            if (!basketItemsMap.containsKey(productCode)) {
                BasketItemDto basketItem = new BasketItemDto(productDto, 1);
                basketItemsMap.put(productCode, basketItem);

                continue;
            }

            BasketItemDto basketItem = basketItemsMap.get(productCode);
            basketItem.setQuantity(basketItem.getQuantity() + 1);
            basketItemsMap.put(productCode, basketItem);
        }

        return basketItemsMap;
    }

    private List<ProductDto> countProductsList(String basketRow) {
        return productsCounter.countProductListFrom(basketRow);
    }
}
