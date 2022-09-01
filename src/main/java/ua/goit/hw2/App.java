package ua.goit.hw2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BasketService basketService = new BasketService();

        Double result = basketService.calculateTotalPrice(" ABCDABA");

        System.out.println("result = " + result);
    }
}
