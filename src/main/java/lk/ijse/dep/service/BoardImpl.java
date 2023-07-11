package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private Piece piece[][];
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI=boardUI;
        piece = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                piece[i][j] = Piece.EMPTY;
            }
        }
    }


    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (piece[col][i].equals(Piece.EMPTY)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        int NextAvailableSpot = findNextAvailableSpot(col);
        if (NextAvailableSpot == -1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0 ; i < 6 ; i++){
            if(isLegalMove(i)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existLegalMove() {
        return existLegalMoves();
    }

    @Override
    public void updateMove(int col, Piece move) {

        int nextAvailableSpot = findNextAvailableSpot(col);
        if (nextAvailableSpot != -1) {
            piece[col][nextAvailableSpot] = move;

        }
    }

    @Override
    public Winner findWinner() {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (piece[i][1].equals(Piece.BLUE)){
                    if ((piece[i][1]==piece[i][2])&&(piece[i][2]==piece[i][3])&&(piece[i][1]==piece[i][0])){
                        return new Winner(Piece.BLUE,i,0,i,3);
                    }else if (((piece[i][1]==piece[i][2])&&(piece[i][2]==piece[i][3]))&&(piece[i][3]==piece[i][4])){
                        return new Winner(Piece.BLUE,i,1,i,4);
                    }
                }
                if (piece[i][1]==Piece.GREEN){
                    if (((piece[i][1]==piece[i][2])&&(piece[i][2]==piece[i][3]))&&(piece[i][1]==piece[i][0])){
                        return new Winner(Piece.GREEN,i,0,i,3);
                    }else if (((piece[i][1]==piece[i][2])&&(piece[i][2]==piece[i][3]))&&(piece[i][3]==piece[i][4])){
                        return new Winner(Piece.GREEN,i,1,i,4);
                    }
                }
                if (piece[2][j]==Piece.BLUE){
                    if (((piece[2][j]==piece[3][j])&&(piece[1][j]==piece[2][j])&&(piece[1][j]==piece[0][j]))){
                        return new Winner(Piece.BLUE,0,j,3,j);
                    }else if (((piece[2][j]==piece[3][j])&&(piece[1][j]==piece[2][j])&&(piece[3][j]==piece[4][j]))){
                        return new Winner(Piece.BLUE,1,j,4,j);
                    }else if (((piece[2][j]==piece[3][j])&&(piece[3][j]==piece[4][j])&&(piece[4][j]==piece[5][j]))){
                        return new Winner(Piece.BLUE,2,j,5,j);
                    }
                }
                if (piece[2][j]==Piece.GREEN){
                    if (((piece[2][j]==piece[3][j])&&(piece[1][j]==piece[2][j])&&(piece[1][j]==piece[0][j]))){
                        return new Winner(Piece.GREEN,0,j,3,j);
                    }else if (((piece[2][j]==piece[3][j])&&(piece[1][j]==piece[2][j])&&(piece[3][j]==piece[4][j]))){
                        return new Winner(Piece.GREEN,1,j,4,j);
                    }else if (((piece[2][j]==piece[3][j])&&(piece[3][j]==piece[4][j])&&(piece[4][j]==piece[5][j]))){
                        return new Winner(Piece.GREEN,2,j,5,j);
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

    /*@Override
    public boolean existLegalMove() {
        return false;
    }
*/

}







