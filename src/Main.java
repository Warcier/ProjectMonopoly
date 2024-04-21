import controller.GameController;
import model.GameModel;
import model.test;
import view.GameView;
import view.GameEditor;

public class Main {


    public static void main(String[] args) {

        GameView gameView = new GameView();
        GameController gameController = new GameController();
        GameModel gameModel = new GameModel(test.initPlayer(), test.initProperty());

        gameController.setGameModel(gameModel);
        gameController.setGameView(gameView);
        gameView.setGameController(gameController);
        gameView.setViewBoard(gameController.getBoard());
        gameView.setGameBoardController(gameController);
        gameView.setPlayerPanelController(gameController);
        gameModel.setGameController(gameController);
        gameController.setGameEditor(new GameEditor(gameController));

        if (gameModel.getBoard() == null) {
            gameModel.createBoard();
        }




        java.awt.EventQueue.invokeLater(() -> gameView.setVisible(true));

        System.out.println("Game started");

    }



}