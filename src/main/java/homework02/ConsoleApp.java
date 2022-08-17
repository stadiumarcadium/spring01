package homework02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean(ProductService.class);
        CartService cartService = context.getBean(CartService.class);
        Long id;
        System.out.println("Консольное команды управление корзиой:");
        commandList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nВведите команду: ");
            String str = scanner.nextLine();
            if (!str.isEmpty()) {
                try {
                    String[] parts = str.split("\\s");
                    String command = parts[0];
                    switch (command) {
                        case "/list":
                            System.out.println("список продуктов:");
                            System.out.println(productService.getProductList());
                            break;
                        case "/add":
                            id = Long.valueOf(parts[1]);
                            if (productService.isProductIdExist(id)) {
                                cartService.addToCartByProductId(id);
                                System.out.println("В корзину добавлен товар: " + productService.findById(id));
                            }
                            break;
                        case "/del":
                            id = Long.valueOf(parts[1]);
                            if (cartService.isProductIdExist(id)) {
                                cartService.removeFromCartId(id);
                                System.out.println("Из корзины удален товар: " + productService.findById(id));
                            }
                            break;
                        case "/print":
                            System.out.println("В корзине:");
                            cartService.printCart();
                            break;
                        case "/sum":
                            System.out.println(cartService.getSum());
                            break;
                        case "/new":
                            cartService.clearCurrentCart();
                            break;
                        case "/end":
                            System.exit(1);
                            break;
                        default:
                            wrongInput();
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    wrongInput();
                    continue;
                }
            }
        }
    }

    private static void commandList() {
        System.out.println("/list - список всех продуктов");
        System.out.println("/add id - добавить продукт");
        System.out.println("/del id - удалть продукт");
        System.out.println("/print - содержимое корзины");
        System.out.println("/sum - стоимость корзины");
        System.out.println("/new - создать новую корзину");
        System.out.println("/end - выход");
    }

    private static void wrongInput() {
        System.out.println("Неправильная команда");
    }
}
