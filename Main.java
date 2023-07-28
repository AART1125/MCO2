import VendingController.MenuController;
import VendingView.MenuUi;

public class Main {
    public static void main(String[] args) {
        MenuUi menu = new MenuUi();
        MenuController main = new MenuController(menu);
    }
}
