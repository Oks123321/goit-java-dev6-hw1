package ua.goit.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.goit.hw2.dto.BasketItemDto;
import ua.goit.hw2.dto.ProductDto;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class BasketItemsServiceTest {

          //ProductsCounter is internal dependency in the class. You have to mock all calls using Mockito
        //here is simple example https://pastebin.com/1zSjHTC2
        //Done

        @Mock
        private ProductsCounter productsCounter;
        @InjectMocks
        private BasketItemsService basketItemsService;

        @BeforeEach
        void beforeEach() throws NoSuchFieldException, IllegalAccessException {
            Field field = BasketItemsService.class.getDeclaredField("productsCounter");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(basketItemsService, productsCounter);
        }

        private ProductDto getProductDtoA() {
            return new ProductDto('A', 1.25, 3, 3.00);
        }

        private ProductDto getProductDtoB() {
            return new ProductDto('B', 4.25, null, null);
        }

        private ProductDto getProductDtoC() {
            return new ProductDto('C', 1.00, 6, 5.00);
        }

        @Test
        void testShouldContainCorrectSizeAndQuantityForAAABBBB() {
            String basket = "AAABBBB";
            ProductDto productA = getProductDtoA();
            ProductDto productB = getProductDtoB();

            when(productsCounter.countProductListFrom(basket))
                    .thenReturn(Arrays.asList(productA, productA, productA, productB, productB, productB, productB));
            Map<Character, BasketItemDto> basketItems = basketItemsService.countBasketItems(basket);

            Assertions.assertEquals(2, basketItems.size());
            Assertions.assertEquals(3, basketItems.get('A').getQuantity());
            Assertions.assertEquals(4, basketItems.get('B').getQuantity());
        }

        @Test
        void testShouldContainCorrectSizeAndQuantityForAA() {
            String basket = "AA";
            ProductDto productA = getProductDtoA();

            when(productsCounter.countProductListFrom(basket))
                    .thenReturn(Arrays.asList(productA, productA));
            Map<Character, BasketItemDto> cartItems = basketItemsService.countBasketItems(basket);

            Assertions.assertEquals(1, cartItems.size());
            Assertions.assertEquals(2, cartItems.get('A').getQuantity());
        }

        @Test
        void testShouldContainCorrectSizeAndQuantityForACCCBB() {
            String basket = "ACCCBB";
            ProductDto productA = getProductDtoA();
            ProductDto productB = getProductDtoB();
            ProductDto productC = getProductDtoC();


            when(productsCounter.countProductListFrom(basket))
                    .thenReturn(Arrays.asList(productA, productC, productC, productC, productB, productB));
            Map<Character, BasketItemDto> cartItems = basketItemsService.countBasketItems(basket);

            Assertions.assertEquals(3, cartItems.size());
            Assertions.assertEquals(1, cartItems.get('A').getQuantity());
            Assertions.assertEquals(2, cartItems.get('B').getQuantity());
            Assertions.assertEquals(3, cartItems.get('C').getQuantity());
        }

        @Test
        void testShouldContainCorrectSizeAndQuantityForCartWithUninitialisedProducts() {
            String basket = "   AACCBB__U";
            ProductDto productA = getProductDtoA();
            ProductDto productB = getProductDtoB();
            ProductDto productC = getProductDtoC();

            when(productsCounter.countProductListFrom(basket))
                    .thenReturn(Arrays.asList(productA, productA, productC, productC, productB, productB));
            Map<Character, BasketItemDto> cartItems = basketItemsService.countBasketItems(basket);

            Assertions.assertEquals(3, cartItems.size());
            Assertions.assertEquals(2, cartItems.get('A').getQuantity());
            Assertions.assertEquals(2, cartItems.get('B').getQuantity());
            Assertions.assertEquals(2, cartItems.get('C').getQuantity());
        }

}