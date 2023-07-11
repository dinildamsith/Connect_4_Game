package lk.ijse.dep.service;

public class AiPlayer extends Player {
    Board newBoard;


    public AiPlayer(Board newBoard) {
        super(newBoard);
        this.newBoard=newBoard;

    }

    @Override
    public void movePiece(int col) {
        do {
            col= (int) (Math.random()*6);
        }while (!board.isLegalMove(col));
        board.updateMove(col,Piece.GREEN);
        board.getBoardUI().update(col,false);
        if (!board.findWinner().getWinningPiece().equals(Piece.EMPTY)) {
            board.getBoardUI().notifyWinner(board.findWinner());
        } else if (!board.existLegalMoves()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
    private int predictColumn() {
        boolean isUserWinning = false;
        int tiedColumn = 0;

        int i;
        for(i = 0; i < 6; ++i) {
            if (this.newBoard.isLegalMove(i)) {
                int row = this.newBoard.findNextAvailableSpot(i);
                this.newBoard.updateMove(i, Piece.GREEN);
                int heuristicVal = this.minimax(0, false);
                this.newBoard.updateMove( row, Piece.EMPTY);
                if (heuristicVal == 1) {
                    return i;
                }

                if (heuristicVal == -1) {
                    isUserWinning = true;
                } else {
                    tiedColumn = i;
                }
            }
        }

        if (isUserWinning && this.newBoard.isLegalMove(tiedColumn)) {
            return tiedColumn;
        } else {
            boolean var6 = false;

            do {
                i = (int)(Math.random() * 6.0D);
            } while(!this.newBoard.isLegalMove(i));

            return i;
        }
    }

    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = this.newBoard.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {
            return -1;
        } else if (this.newBoard.existLegalMove() && depth != 2) {
            int i;
            int row;
            int heuristicVal;
            if (!maximizingPlayer) {
                for (i = 0; i < 6; ++i) {
                    if (this.newBoard.isLegalMove(i)) {
                        row = this.newBoard.findNextAvailableSpot(i);
                        this.newBoard.updateMove(i, Piece.BLUE);
                        heuristicVal = this.minimax(depth + 1, true);
                        this.newBoard.updateMove( row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return heuristicVal;
                        }
                    }
                }
            } else {
                for (i = 0; i < 6; ++i) {
                    if (this.newBoard.isLegalMove(i)) {
                        row = this.newBoard.findNextAvailableSpot(i);
                        this.newBoard.updateMove(i, Piece.GREEN);
                        heuristicVal = this.minimax(depth + 1, false);
                        this.newBoard.updateMove( row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return heuristicVal;
                        }
                    }
                }
            }

            return 0;
        } else {
            return 0;
        }
    }
}


