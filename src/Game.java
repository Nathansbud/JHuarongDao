import processing.core.PApplet;

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

    @Override public void mouseReleased() {
        if(Piece.getHovered() != null && Piece.getHovered().isMoused()) Piece.setSelected(Piece.getHovered());
    }

    @Override public void keyPressed() {
        if(Piece.getSelected() != null) {
            switch (keyCode) {
                case 37:
                    Piece.getSelected().move(Piece.MoveDirection.LEFT);
                    break;
                case 39:
                    Piece.getSelected().move(Piece.MoveDirection.RIGHT);
                    break;
                case 38:
                    Piece.getSelected().move(Piece.MoveDirection.UP);
                    break;
                case 40:
                    Piece.getSelected().move(Piece.MoveDirection.DOWN);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[]{"Huarong Dao"}, gui);
    }
}
