import processing.core.PApplet;
import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> pieces = new ArrayList<>();

    public Board() {
        pieces.add(new Piece(Piece.Type.SQUARE, 16));
        pieces.add(new Piece(Piece.Type.SQUARE, 17));
        pieces.add(new Piece(Piece.Type.SQUARE, 18));
        pieces.add(new Piece(Piece.Type.SQUARE, 19));
        pieces.add(new Piece(Piece.Type.TALL, 0));
        pieces.add(new Piece(Piece.Type.TALL, 3));
        pieces.add(new Piece(Piece.Type.TALL, 8));
        pieces.add(new Piece(Piece.Type.TALL, 11));
        pieces.add(new Piece(Piece.Type.LONG, 13));
        pieces.add(new Piece(Piece.Type.KING, 5));
    }

    public void draw() {
        for(Piece p : pieces) {
            p.draw();
        }
        if(Piece.getHovered() != null) {
            Piece.getHovered().draw();
        }

        if(Piece.getSelected() != null) {
            Piece.getSelected().draw();
        }
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> _pieces) {
        pieces = _pieces;
    }
}
