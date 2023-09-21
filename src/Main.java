import service.MainService;
import service.impl.MainServiceImpl;

public class Main {
    public static void main(String[] args) {
        MainService service = new MainServiceImpl();
        service.start();
    }
}