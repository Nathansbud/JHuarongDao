import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Stack;

public class Board {
    private static boolean won = false;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private Stack<MoveLog> moveLog = new Stack<>();
    private int movesMade = 0;


    public Board() {
        pieces.add(new Piece(Piece.Type.SQUARE, 16, "Knight 1"));
        pieces.add(new Piece(Piece.Type.SQUARE, 17, "Knight 2"));
        pieces.add(new Piece(Piece.Type.SQUARE, 18, "Knight 3"));
        pieces.add(new Piece(Piece.Type.SQUARE, 19, "Knight 4"));
        pieces.add(new Piece(Piece.Type.TALL, 0, "General 1"));
        pieces.add(new Piece(Piece.Type.TALL, 8, "General 2"));
        pieces.add(new Piece(Piece.Type.TALL, 3, "General 3"));
        pieces.add(new Piece(Piece.Type.TALL, 11, "General 4"));
        pieces.add(new Piece(Piece.Type.LONG, 13, "Prince"));
        pieces.add(new Piece(Piece.Type.KING, 5, "King"));
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
        Game.gui.strokeWeight(3);
        Game.gui.stroke(181, 101, 29);

        Game.gui.line(0, 0, Game.gui.width, 0);
        Game.gui.line(0, 0, 0, 5*Piece.getPieceHeight());
        Game.gui.line(Game.gui.width - 1, 0, Game.gui.width - 1, 5*Piece.getPieceHeight());
        Game.gui.line(0, 5*Piece.getPieceHeight(), Piece.getPieceWidth(), 5*Piece.getPieceHeight());
        Game.gui.line(3*Piece.getPieceWidth(), 5*Piece.getPieceHeight(), Game.gui.width, 5*Piece.getPieceHeight());


        if(won) {
            Game.gui.fill(255, 0, 255);
            Game.gui.text("Victory!", Game.gui.width/2.0f - 0.5f*Game.gui.textWidth("Victory!"), Game.gui.height*0.925f);
        }

        Game.gui.fill(255);
        Game.gui.text("Moves Made: " + movesMade, Game.gui.width*0.1f, Game.gui.height*0.95f);
        Game.gui.text("Currently Selected: " + ((Piece.getSelected() == null) ? ("None") : (Piece.getSelected().getName())), Game.gui.width*0.1f, Game.gui.height*0.975f);
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

        boolean moved = p.move(dir);
        if(moved) {
            movesMade++;
            moveLog.push(new MoveLog(p, dir));
        }
        return moved;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> _pieces) {
        pieces = _pieces;
    }

    public int getMovesMade() {
        return movesMade;
    }

    public void setMovesMade(int _movesMade) {
        movesMade = _movesMade;
    }

    public Stack<MoveLog> getMoveLog() {
        return moveLog;
    }

    public void setMoveLog(Stack<MoveLog> _moveLog) {
        moveLog = _moveLog;
    }

    public static boolean isWon() {
        return won;
    }

    public static void setWon(boolean _won) {
        won = _won;
    }
}
