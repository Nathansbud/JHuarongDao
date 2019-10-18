import processing.core.PApplet;

public class Game extends PApplet {
    public static Game gui = new Game();
    private Board b = new Board();
    private int lastTriggered = 0;

    @Override public void settings() {
        size(400, 780);
    }

    @Override public void draw() {
        background(0);
        b.draw();
    }

    @Override public void mousePressed() {
        if(Piece.getHovered() != null && Piece.getHovered().isMoused()) Piece.setSelected(Piece.getHovered());
    }

    @Override public void mouseDragged() {
        if(millis() - lastTriggered > 200) {
            if (Piece.getSelected() != null && mouseX > pmouseX && Piece.getSelected().isMoused()) {
                if(b.move(Piece.getSelected(), Piece.MoveDirection.RIGHT)) {
                    lastTriggered = millis();
                    return;
                }
            } else if (mouseX < pmouseX) {
                if(b.move(Piece.getSelected(), Piece.MoveDirection.LEFT)) {
                    lastTriggered = millis();
                    return;
                }
            }

            if (Piece.getSelected() != null && mouseY > pmouseY && Piece.getSelected().isMoused()) {
                if(b.move(Piece.getSelected(), Piece.MoveDirection.DOWN)) lastTriggered = millis();
            }
            else if (mouseY < pmouseY) {
                if(b.move(Piece.getSelected(), Piece.MoveDirection.UP)) lastTriggered = millis();
            }
        }
    }

    @Override public void keyPressed() {
        if(Piece.getSelected() != null) {
            switch (keyCode) {
                case 37: b.move(Piece.getSelected(), Piece.MoveDirection.LEFT); break;
                case 39: b.move(Piece.getSelected(), Piece.MoveDirection.RIGHT); break;
                case 38: b.move(Piece.getSelected(), Piece.MoveDirection.UP); break;
                case 40: b.move(Piece.getSelected(), Piece.MoveDirection.DOWN); break;
            }
        }
        int ci;
        switch(keyCode) {
            case 90:
                if(!b.getMoveLog().empty()) {
                    MoveLog toTake = b.getMoveLog().pop();
                    b.setMovesMade(b.getMovesMade() - 1);
                    toTake.getReference().move(Piece.MoveDirection.invert(toTake.getMove()));
                }
                break;

            case 82:
                Piece.setHovered(null);
                Piece.setSelected(null);
                Board.setWon(false);
                b = new Board();
                break;
            case 44:
                ci = (Piece.getSelected() == null) ? (-1) : b.getPieces().indexOf(Piece.getSelected());
                Piece.setSelected(b.getPieces().get((ci == b.getPieces().size() - 1) ? (0) : (ci + 1)));
                break;
            case 46:
                ci = (Piece.getSelected() == null) ? (1) : b.getPieces().indexOf(Piece.getSelected());
                Piece.setSelected(b.getPieces().get((ci == 0) ? (b.getPieces().size() - 1) : (ci - 1)));
                break;
        }
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"Huarong Dao"}, gui);

    }
}
