import controller.GameController;
import model.GameModel;
import model.test;
import view.GameView;

public class Main {


    public static void main(String[] args) {

        GameView gameView = new GameView();
        GameModel gameModel = new GameModel(test.initPlayer(), test.initProperty());
        GameController gameController = new GameController();

        gameController.setGameModel(gameModel);
        gameController.setGameView(gameView);
        gameView.setGameController(gameController);
        gameModel.setGameController(gameController);


        java.awt.EventQueue.invokeLater(() -> gameView.setVisible(true));

        System.out.println("Game started");

    }



}