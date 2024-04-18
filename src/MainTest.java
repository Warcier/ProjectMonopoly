import model.TurnController;
import model.list.CircularLinkedList;
import model.test;

public class MainTest {
    public static void main(String[] args) {
        CircularLinkedList board = new CircularLinkedList(test.initPlayer(), test.initProperty());
        TurnController turnController = new TurnController();
        System.out.println("Board created");
        board.createBoard();

        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 1);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 2);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 3);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 4);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 1);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 2);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 3);
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), 4);

        board.ShowAllPlayerPostion();

    }
}
