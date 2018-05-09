import java.util.*;

/**
 * Computer will take a random move from list of legal moves
 * 
 * @author Denis Atikpladza
 * @version: 1.0
 */

public class RandomMove implements MoveStrategy {

    public Move selectMove(BoardGame game) {
        List<Move> moves = game.generateMoves();
        if (moves.isEmpty()) return null;
        Random r = new Random();
        int moveIndex = r.nextInt(moves.size());
        return moves.get(moveIndex);
    }
}
