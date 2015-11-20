import Controller.Controller;
import Model.GameModel;
import View.MainView;

/**
 * Created by raphael on 15/11/15.
 */
public class Main {
    public static void main(String[] args){
        GameModel model = new GameModel();
        Controller controller = new Controller(model);
        MainView view = new MainView(controller, model);
    }
}
