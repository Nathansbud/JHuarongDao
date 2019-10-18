public class Piece {
    private static Piece selected = null;
    private static Piece hovered = null;

    private static float PIECE_WIDTH = 100;
    private static float PIECE_HEIGHT = 100;

    enum Type {
        LONG(2, 1),
        SQUARE(1,1),
        TALL(1, 2),
        KING(2, 2);

        public int width;
        public int height;
        Type(int _width, int _height) {
            width = _width;
            height = _height;
        }
    }

    enum MoveDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    private Type type;
//    private float x;
//    private float y;
    private float width;
    private float height;

    private int position;

    public Piece(Type _type, int _position) {
        type = _type;
        position = _position;

        width = type.width * PIECE_WIDTH;
        height = type.height * PIECE_HEIGHT;
    }

    public void draw() {
        Game.gui.fill(152, 118, 84);
        if(isSelected()) {
            Game.gui.stroke(255, 255, 0);
        } else if(isMoused()) {
            Game.gui.stroke(255, 0, 0);
        } else {
            Game.gui.stroke(0);
        }
        Game.gui.rect(getX(), getY(), width, height);
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case UP:
                if(position / 4 > 0) position -= 4;
                break;
            case DOWN:
                if((position + (type.height-1)*4) / 4 < 4) position += 4;
                break;
            case LEFT:
                if((position) % 4 > 0) position -= 1;
                break;
            case RIGHT:
                if((position + type.width - 1) % 4 < 3) position += 1;
                break;
        }
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

