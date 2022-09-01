package ua.goit.hw2.dto;


public class ProductDto {
    Character code;
    Double price;
    Integer promotionalCount;
    Double promotionalPrice;


    public ProductDto() {
    }

    public ProductDto(Character code, Double price, Integer promotionalCount, Double promotionalPrice) {
        this.code = code;
        this.price = price;
        this.promotionalCount = promotionalCount;
        this.promotionalPrice = promotionalPrice;
    }

    public Character getCode() {
        return code;
    }

    public void setCode(Character code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPromotionalCount() {
        return promotionalCount;
    }

    public void setPromotionalCount(Integer promotionalCount) {
        this.promotionalCount = promotionalCount;
    }

    public Double getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(Integer promotionalPrice) {
        this.promotionalPrice = Double.valueOf(promotionalPrice);
    }

    public boolean hasPromotionalPrice(){
        return promotionalCount != null && promotionalPrice != null;
    }


}
