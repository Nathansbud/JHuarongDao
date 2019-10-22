import com.sun.istack.internal.NotNull;
import processing.core.PApplet;

public class Piece {
    private static Piece selected = null;
    private static Piece hovered = null;

    private static float PIECE_WIDTH = 100;
    private static float PIECE_HEIGHT = 100;

    enum Type {
        LONG(2, 1, "ffffff33"),
        SQUARE(1,1, "ffffb6c1"),
        TALL(1, 2, "ff89cff0"),
        KING(2, 2, "ff98fb98");

        public int width;
        public int height;
        public String color;
        Type(int _width, int _height, String _color) {
            width = _width;
            height = _height;
            color = _color;
        }
    }

    enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN;

        public static MoveDirection invert(MoveDirection dir) {
            switch(dir) {
                case UP: return DOWN;
                case DOWN: return UP;
                case LEFT: return RIGHT;
                case RIGHT: return LEFT;
            }
            return null;
        }
    }

    private Type type;
    private String name;

    private float width;
    private float height;

    private int position;
    private int extendX;
    private int extendY;

    public Piece(Type _type, int _position, String _name) {
        type = _type;
        position = _position;
        name = _name;

        extendX = type.width;
        extendY = type.height;
        width = type.width * PIECE_WIDTH;
        height = type.height * PIECE_HEIGHT;
    }

    public void draw() {
        Game.gui.fill(PApplet.unhex(type.color));
        Game.gui.strokeWeight(2);
        if(isSelected()) {
            Game.gui.stroke(255, 0, 0);
        } else if(isMoused()) {
            Game.gui.stroke(0, 0, 255);
        } else {
            Game.gui.stroke(0);
        }
        Game.gui.rect(getX(), getY(), width, height);
    }

    public boolean move(MoveDirection direction) {
        boolean moveMade = true;
        switch(direction) {
            case UP:
                if(position / 4 > 0) position -= 4;
                else moveMade = false;
                break;
            case DOWN:
                if((type == Type.KING && (position == 13 || position == 17)) || (position + (type.height-1)*4) / 4 < 4) {
                    position += 4;
                    if(position == 21 && type == Type.KING) {
                        Board.setWon(true);
                    }
                }
                else moveMade = false;
                break;
            case LEFT:
                if((position) % 4 > 0) position -= 1;
                else moveMade = false;
                break;
            case RIGHT:
                if((position + type.width - 1) % 4 < 3) position += 1;
                else moveMade = false;
                break;
        }
        return moveMade;
    }

    public boolean isMoused() {
        boolean moused = Game.gui.mouseX > getX() && Game.gui.mouseX < getX() + width && Game.gui.mouseY > getY() && Game.gui.mouseY < getY() + height;
        if(moused) {
            hovered = this;
        }
        return moused;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type _type) {
        type = _type;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public float getX() {
       return (position % 4) * getPieceWidth();
    }

    public float getY() {
        return (position / 4)*getPieceHeight();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float _width) {
        width = _width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float _height) {
        height = _height;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int _position) {
        position = _position;
    }

    public int getExtendX() {
        return extendX;
    }

    public void setExtendX(int _extendX) {
        extendX = _extendX;
    }

    public int getExtendY() {
        return extendY;
    }

    public void setExtendY(int _extendY) {
        extendY = _extendY;
    }

    public int getColumn() {
        return position % 4;
    }

    public int getRow() {
        return position / 4;
    }



    public static float getPieceWidth() {
        return PIECE_WIDTH;
    }
    public static void setPieceWidth(float _pieceWidth) {
        PIECE_WIDTH = _pieceWidth;
    }

    public static float getPieceHeight() {
        return PIECE_HEIGHT;
    }
    public static void setPieceHeight(float _pieceHeight) {
        PIECE_HEIGHT = _pieceHeight;
    }

    public boolean isSelected() {
        return this.equals(selected);
    }
    public static Piece getSelected() {
        return selected;
    }
    public static void setSelected(Piece _selected) {
        selected = _selected;
    }

    public boolean isHovered() {
        return this.equals(hovered);
    }
    public static Piece getHovered() {
        return hovered;
    }
    public static void setHovered(Piece _hovered) {
        hovered = _hovered;
    }
}

