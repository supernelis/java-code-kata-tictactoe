package be.boucke.codekata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicTacToe {

    private GameFeedback gameFeedback = GameFeedback.gameStarted;
    private Player currentPlayer = Player.PlayerX;
    private final Map<Position, Player> moves = new HashMap<Position, Player>();

    public Player currentPlayer() {
        return currentPlayer;
    }

    public GameFeedback feedback() {
        return gameFeedback;
    }

    public void play(Position pos) {
        if (moves.containsKey(pos)) {
            gameFeedback = GameFeedback.positionAlreadytaken;
            return;
        }

        moves.put(pos, currentPlayer);
        gameFeedback = GameFeedback.playSuccessfull;

        if (hasThreeInRow()) {
            gameFeedback = GameFeedback.gameEndedWithWin;
            return;
        }

        if (moves.size() == 9) {
            gameFeedback = GameFeedback.gameEndedWithDraw;
        }

        switchPlayer();
    }

    private boolean hasThreeInRow() {
        return getTopRow().stream().allMatch(player -> currentPlayer.equals(player)) ||
                getMiddleRow().stream().allMatch(player -> currentPlayer.equals(player)) ||
                getBottomRow().stream().allMatch(player -> currentPlayer.equals(player));

    }

    private List<Player> getBottomRow() {
        return Arrays.asList(moves.get(Position.bottomLeft), moves.get(Position.bottomMiddle), moves.get(Position.bottomRight));
    }

    private List<Player> getMiddleRow() {
        return Arrays.asList(moves.get(Position.middleLeft), moves.get(Position.middleMiddle), moves.get(Position.middleRight));
    }

    private List<Player> getTopRow() {
        return Arrays.asList(moves.get(Position.topLeft), moves.get(Position.topMiddle), moves.get(Position.topRight));
    }

    private void switchPlayer() {
        if (currentPlayer == Player.PlayerX) {
            currentPlayer = Player.PlayerO;
        } else {
            currentPlayer = Player.PlayerX;
        }
    }
}
