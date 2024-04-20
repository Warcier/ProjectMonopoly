import controller.GameController;
import model.GameModel;
import view.GameView;

public class Main {


    public static void main(String[] args) {

        GameView gameView = new GameView();
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController();

        gameController.setGameModel(gameModel);
        gameController.setGameView(gameView);
        gameView.setGameController(gameController);
        gameModel.setGameController(gameController);


    }



}