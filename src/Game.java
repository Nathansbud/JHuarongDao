import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    public static Game gui = new Game();
    private Board b = new Board();

    @Override public void settings() {
        size(400, 800);
    }

    @Override public void setup() {

    }

    @Override public void draw() {
        background(0);
        b.draw();
    }

    @Override public void mousePressed() {
        if(Piece.getHovered() != null && Piece.getHovered().isMoused()) Piece.setSelected(Piece.getHovered());
    }

    @Override public void keyPressed() {
        if(Piece.getSelected() != null) {
            Piece p = Piece.getSelected();
            switch (keyCode) {
                case 37:
                    //noinspection DuplicatedCode
                    for(Piece cp : b.getPieces()) {
                        if(cp != p) {
                            if (cp.getColumn() + cp.getExtendX() == p.getColumn() && cp.getRow() + cp.getExtendY() - 1 >= p.getRow() && cp.getRow() <= p.getRow() + p.getExtendY() - 1){
                                return;
                            }
                        }
                    }
                    Piece.getSelected().move(Piece.MoveDirection.LEFT);
                    break;
                case 39:
                    //noinspection DuplicatedCode
                    for(Piece cp : b.getPieces()) {
                        if(cp != p) {
                            if (cp.getColumn() == p.getColumn()+ p.getExtendX() && cp.getRow() + cp.getExtendY() - 1 >= p.getRow() && cp.getRow() <= p.getRow() + p.getExtendY() - 1){
                                return;
                            }
                        }
                    }
                    Piece.getSelected().move(Piece.MoveDirection.RIGHT);
                    break;
                case 38:
                    //noinspection DuplicatedCode
                    for(Piece cp : b.getPieces()) {
                        if(cp != p) {
                            if (cp.getRow() + cp.getExtendY() == p.getRow() && cp.getColumn() + cp.getExtendX() - 1 >= p.getColumn() && cp.getColumn() <= p.getColumn() + p.getExtendX() - 1){
                                return;
                            }
                        }
                    }
                    Piece.getSelected().move(Piece.MoveDirection.UP);
                    break;
                case 40:
                    //noinspection DuplicatedCode
                    for(Piece cp : b.getPieces()) {
                        if(cp != p) {
                            if (cp.getRow() == p.getRow()+ p.getExtendY() && cp.getColumn() + cp.getExtendX() - 1 >= p.getColumn() && cp.getColumn() <= p.getColumn() + p.getExtendX() - 1){
                                return;
                            }
                        }
                    }
                    Piece.getSelected().move(Piece.MoveDirection.DOWN);
                    break;
            }
        }
        int ci;
        switch(keyCode) {
            case 90:
                ci = (Piece.getSelected() == null) ? (1) : b.getPieces().indexOf(Piece.getSelected());
                Piece.setSelected(b.getPieces().get((ci == 0) ? (b.getPieces().size() - 1) : (ci - 1)));
                break;
            case 88:
            case 9:
                ci = (Piece.getSelected() == null) ? (-1) : b.getPieces().indexOf(Piece.getSelected());
                Piece.setSelected(b.getPieces().get((ci == b.getPieces().size() - 1) ? (0) : (ci + 1)));
                break;
        }
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"Huarong Dao"}, gui);
    }
}
