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

    public boolean move(Piece p, Piece.MoveDirection dir) {
        for(Piece cp : pieces) {
            if(cp != p) {
                if(dir == Piece.MoveDirection.LEFT && cp.getColumn() + cp.getExtendX() == p.getColumn() && cp.getRow() + cp.getExtendY() - 1 >= p.getRow() && cp.getRow() <= p.getRow() + p.getExtendY() - 1) return false;
                if(dir == Piece.MoveDirection.RIGHT && cp.getColumn() == p.getColumn()+ p.getExtendX() && cp.getRow() + cp.getExtendY() - 1 >= p.getRow() && cp.getRow() <= p.getRow() + p.getExtendY() - 1) return false;
                if(dir == Piece.MoveDirection.UP && cp.getRow() + cp.getExtendY() == p.getRow() && cp.getColumn() + cp.getExtendX() - 1 >= p.getColumn() && cp.getColumn() <= p.getColumn() + p.getExtendX() - 1) return false;
                if(dir == Piece.MoveDirection.DOWN && cp.getRow() == p.getRow()+ p.getExtendY() && cp.getColumn() + cp.getExtendX() - 1 >= p.getColumn() && cp.getColumn() <= p.getColumn() + p.getExtendX() - 1) return false;
            }
        }
        p.move(dir);
        return true;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> _pieces) {
        pieces = _pieces;
    }
}
