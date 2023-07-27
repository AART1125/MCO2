import VendingController.mainController;
import VendingView.MenuUi;

public class Main {
    public static void main(String[] args) {
        MenuUi ui = new MenuUi();
        mainController main = new mainController(ui);
    }
}
