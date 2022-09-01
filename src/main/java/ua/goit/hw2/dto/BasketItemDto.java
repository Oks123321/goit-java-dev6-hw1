package ua.goit.hw2.dto;


public class BasketItemDto {
    ProductDto product;
    int quantity;

    public BasketItemDto(ProductDto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BasketItemDto() {
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
