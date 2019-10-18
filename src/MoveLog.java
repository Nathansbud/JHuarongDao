public class MoveLog {
    private Piece reference;
    private Piece.MoveDirection move;
    MoveLog(Piece _reference, Piece.MoveDirection _move) {
        reference = _reference;
        move = _move;
    }

    public Piece getReference() {
        return reference;
    }

    public Piece.MoveDirection getMove() {
        return move;
    }
}
