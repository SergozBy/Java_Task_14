import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    ShopRepository repository = new ShopRepository();

    Product product1 = new Product(23, "Potato", 2);
    Product product2 = new Product(77, "Tomato", 5);
    Product product3 = new Product(43, "Apple", 7);
    Product product4 = new Product(12, "Pineapple", 13);
    Product product5 = new Product(3, "Blueberry", 9);

    @BeforeEach
    public void setup() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);
        repository.add(product5);
    }


    @Test
    public void shouldFindById() {
        Product expected = product2;
        Product actual = repository.findById(77);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindById() {
        Product expected = null;

        // not existed id
        Product actual = repository.findById(4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveProductById() throws Exception {
        repository.removeById(77);

        Product expected = null;
        Product actual = repository.findById(77);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveProductById() {

        Exception exception = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    repository.removeById(4);
                }
        );

        String expected = "No Product with id = 4";
        String actual = exception.getMessage();

        Assertions.assertEquals(expected, actual);
    }
}
