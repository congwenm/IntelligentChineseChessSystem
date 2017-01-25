package chess;

import java.util.Map;

/**
 * Created by Tong on 12.03.
 * Chess > Board entity
 */


public class Board{
    public final int BOARD_WIDTH = 9, BOARD_HEIGHT = 10;
    public Map<String, Piece> pieces;
    public char player = 'r';
    private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];

    public boolean isInside(int[] position) {
        return isInside(position[0], position[1]);
    }

    public boolean isInside(int x, int y) { // checkfor !(All conditions that marks the piece fall out of board)
        return !(x < 0 || x >= BOARD_HEIGHT
                || y < 0 || y >= BOARD_WIDTH);
    }

    public boolean isEmpty(int[] position) {
        return isEmpty(position[0], position[1]);
    }

    public boolean isEmpty(int x, int y) {
        return isInside(x, y) && cells[x][y] == null;
    }


    public boolean update(Piece piece) {
        int[] pos = piece.position;
        cells[pos[0]][pos[1]] = piece;
        return true;
    }

    public Piece updatePiece(String key, int[] newPos) {
        Piece orig = pieces.get(key);
        Piece inNewPos = getPiece(newPos); // Return the killed piece or null, find if there are any pieces at the current move
        /* If the new slot has been taken by another piece, then it will be killed.*/
        if (inNewPos != null)
            pieces.remove(inNewPos.key);
        /* Clear original slot and updatePiece new slot.*/
        int[] origPos = orig.position;
        cells[origPos[0]][origPos[1]] = null; // empty original piece position
        cells[newPos[0]][newPos[1]] = orig; // move piece to new position
        orig.position = newPos;         // update piece's position
        player = (player == 'r') ? 'b' : 'r'; // simulating a swap of turn?
        return inNewPos;
    }

    public boolean backPiece(String key) {
        int[] origPos = pieces.get(key).position;
        cells[origPos[0]][origPos[1]] = pieces.get(key);
        return true;
    }

    public Piece getPiece(int[] pos) {
        return getPiece(pos[0], pos[1]);
    }

    public Piece getPiece(int x, int y) {
        return cells[x][y];
    }
}