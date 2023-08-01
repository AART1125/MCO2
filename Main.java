import VendingController.MenuControllerPack.MenuController;
import VendingView.MenuUIPack.MenuUi;

public class Main {
    public static void main(String[] args) {
        MenuUi menu = new MenuUi();
        MenuController main = new MenuController(menu);
    }
}
