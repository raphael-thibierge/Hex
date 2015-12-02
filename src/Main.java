/**
 * Created by Raphael Thibierge and Arthur Pavarino (S3A) on 15/11/15.
 */
import Controller.Controller;
import Model.GameModel;
import View.MainView;

public class Main {
    public static void main(String[] args){
        GameModel model;
        Controller controller;
        MainView view;


        // init game
        try {
            model = new GameModel();
            controller = new Controller(model);
            view = new MainView(controller, model);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
