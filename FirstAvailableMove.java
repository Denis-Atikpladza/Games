import java.util.*;

/**
 * Computer will take first avaliable move in list of legal moves 
 * 
 * @author Denis Atikpladza
 * @version: 1.0
 */

public class FirstAvailableMove implements MoveStrategy {
    
    public Move selectMove(BoardGame game) {
        List<Move> moves = game.generateMoves();
        if (moves.isEmpty()) return null;
        else return moves.get(0);
    }
}
