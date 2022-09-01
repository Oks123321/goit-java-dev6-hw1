package ua.goit.hw2;

import ua.goit.hw2.dto.BasketItemDto;
import ua.goit.hw2.dto.ProductDto;

import java.util.Map;

public class BasketService {
    private final BasketItemsService basketItemsService = new BasketItemsService();

    public Double calculateTotalPrice(String basketRow){
        return doCalculate(getBasketItems(basketRow));
    }

    private Map<Character, BasketItemDto> getBasketItems(String basketRow) {
        return basketItemsService.countBasketItems(basketRow);
    }


    private double doCalculate(Map<Character, BasketItemDto> basketItems){
        double result = 0.0;

        for (Map.Entry<Character, BasketItemDto> entry : basketItems.entrySet()){
            BasketItemDto basketItem = entry.getValue();
            ProductDto product = basketItem.getProduct();
            int quantity = basketItem.getQuantity();
            Integer promotionalCount = product.getPromotionalCount();

            if(product.hasPromotionalPrice() && promotionalCount <= quantity) {
                int remainder = quantity % promotionalCount;
                int promotionalGroups = quantity / promotionalCount;

                result += promotionalGroups * product.getPromotionalPrice() + remainder * product.getPrice();
            }else{
                result += product.getPrice() * quantity;
            }
        }
        return result;
    }
}
