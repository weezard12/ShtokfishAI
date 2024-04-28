package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;

import com.weezard12.shtokfishai.main.Point;

public class Shtokfish {

    public static ShtokfishThread thread;
    public static BoardEval currentBoardEval = new BoardEval(new PositionEval(), new PositionEval());

    public static GameStage stage;

    private static Array<Opening> openings = new Array<>();

    public static void init(GameBoard gameBoard) {
        thread = new ShtokfishThread(gameBoard);

        setupOpenings();

        stage = GameStage.OPENING;
    }

    private static void setupOpenings() {
        openings.clear();
        //region d4 d5
        openings.add(new Opening("London System",

            "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //start
                "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,p,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //d4
                "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //d5
                "Bp,Bp,Bp,e,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,Bp,e,e,e,e," +
                "e,e,e,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //bf4
                "Bp,Bp,Bp,e,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,Bp,e,e,e,e," +
                "e,e,e,p,e,b,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,e,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,e,Br," + //Nf6
                "Bp,Bp,Bp,e,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,Bk,e,e," +
                "e,e,e,Bp,e,e,e,e," +
                "e,e,e,p,e,b,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,e,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //e3
                "Bp,Bp,Bp,e,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,Bk,e,e," +
                "e,e,e,Bp,e,e,e,e," +
                "e,e,e,p,e,b,e,e," +
                "e,e,e,e,p,e,e,e," +
                "p,p,p,e,e,p,p,p," +
                "r,k,e,q,K,b,k,r,"

        ));
        //endregion

        //region d4 e5
        openings.add(new Opening("Englund Gambit",
            "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //d4
                "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //e5
                "Bp,Bp,Bp,Bp,e,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,Bp,e,e,e," +
                "e,e,e,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //dxe5
                "Bp,Bp,Bp,Bp,e,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,p,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,e,Bb,Bq,BK,Bb,Bk,Br," + //Nc6
                "Bp,Bp,Bp,Bp,e,Bp,Bp,Bp," +
                "e,e,Bk,e,e,e,e,e," +
                "e,e,e,e,p,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,e,Bb,Bq,BK,Bb,Bk,Br," + //Nf3
                "Bp,Bp,Bp,Bp,e,Bp,Bp,Bp," +
                "e,e,Bk,e,e,e,e,e," +
                "e,e,e,e,p,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,k,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,e,r," +

                "Br,e,Bb,e,BK,Bb,Bk,Br," + //Qe7
                "Bp,Bp,Bp,Bp,Bq,Bp,Bp,Bp," +
                "e,e,Bk,e,e,e,e,e," +
                "e,e,e,e,p,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,e,k,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,e,r," +

                "Br,e,Bb,e,BK,Bb,Bk,Br," + //Nc3
                "Bp,Bp,Bp,Bp,Bq,Bp,Bp,Bp," +
                "e,e,Bk,e,e,e,e,e," +
                "e,e,e,e,p,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,k,e,e,k,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,e,b,q,K,b,e,r," +

                "Br,e,Bb,e,BK,Bb,Bk,Br," + //Nxe5
                "Bp,Bp,Bp,Bp,Bq,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,Bk,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,k,e,e,k,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,e,b,q,K,b,e,r," +

                "Br,e,Bb,e,BK,Bb,Bk,Br," + //e4
                "Bp,Bp,Bp,Bp,Bq,Bp,Bp,Bp," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,e,Bk,e,e,e," +
                "e,e,e,e,p,e,e,e," +
                "e,e,k,e,e,k,e,e," +
                "p,p,p,e,e,p,p,p," +
                "r,e,b,q,K,b,e,r,"

        ));
        //endregion

        //region d4
        openings.add(new Opening("Queen's Pawn Opening: Horwitz Defense",
            "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //e5
                "Bp,Bp,Bp,Bp,e,Bp,Bp,Bp," +
                "e,e,e,e,Bp,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,Bk,Br," + //e5
                "Bp,Bp,Bp,Bp,e,Bp,Bp,Bp," +
                "e,e,e,e,Bp,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,p,e,b,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,e,q,K,b,k,r,"


        ));
        //endregion

        //region d4 Nh6
        openings.add(new Opening("King’s Indian Defense",
            "Br,Bk,Bb,Bq,BK,Bb,e,Br," + //Nh6
                "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,Bk,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,e,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,p,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,e,Br," + //c4
                "Bp,Bp,Bp,Bp,Bp,Bp,Bp,Bp," +
                "e,e,e,e,e,Bk,e,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,p,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,e,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,e,Br," + //g6
                "Bp,Bp,Bp,Bp,Bp,Bp,e,Bp," +
                "e,e,e,e,e,Bk,Bp,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,p,p,e,e,e,e," +
                "e,e,e,e,e,e,e,e," +
                "p,p,e,e,p,p,p,p," +
                "r,k,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,Bb,e,Br," + //Nc3
                "Bp,Bp,Bp,Bp,Bp,Bp,e,Bp," +
                "e,e,e,e,e,Bk,Bp,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,p,p,e,e,e,e," +
                "e,e,k,e,e,e,e,e," +
                "p,p,e,e,p,p,p,p," +
                "r,e,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,e,e,Br," + //Bg7
                "Bp,Bp,Bp,Bp,Bp,Bp,Bb,Bp," +
                "e,e,e,e,e,Bk,Bp,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,p,p,e,e,e,e," +
                "e,e,k,e,e,e,e,e," +
                "p,p,e,e,p,p,p,p," +
                "r,e,b,q,K,b,k,r," +

                "Br,Bk,Bb,Bq,BK,e,e,Br," + //e4
                "Bp,Bp,Bp,Bp,Bp,Bp,Bb,Bp," +
                "e,e,e,e,e,Bk,Bp,e," +
                "e,e,e,e,e,e,e,e," +
                "e,e,p,p,p,e,e,e," +
                "e,e,k,e,e,e,e,e," +
                "p,p,e,e,e,p,p,p," +
                "r,e,b,q,K,b,k,r,"


        ));
        //endregion
    }

    public static void getBestPosition(BasePiece[][] board, boolean forBlack) {

        PositionEval bestEval = new PositionEval(0);
        PositionEval bestEvalForEnemy = new PositionEval(0);

        Gdx.app.log("shtokfish", "thinking for "+(forBlack?"black":"white"));


        currentBoardEval = getBestPosition(board, forBlack, 1, bestEval, bestEvalForEnemy);

        if (stage == GameStage.OPENING)
            for (Opening opening : openings) {
                BasePiece[][] p = opening.tryGetPositionIdx(board);
                if (p != null) {
                    currentBoardEval.whiteEval.position = p;
                    currentBoardEval.blackEval.position = p;
                    Gdx.app.log("shtokfish", "opening:" + opening.name);
                    break;
                }

            }

        Gdx.app.log("shtokfish", "\n" + currentBoardEval.toString());
    }

    private static BoardEval getBestPosition(BasePiece[][] board, boolean forBlack, int steps, PositionEval bestEval, PositionEval bestEvalForEnemy) {

        if (Thread.interrupted())
            return currentBoardEval;

        Array<BasePiece[][]> allPositions = new Array<>();
        int movesCount = 0;

        PositionEval currentEval = new PositionEval();
        PositionEval currentEvalForEnemy = new PositionEval();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                if (board[y][x] != null)
                    //getPosition only for one color
                    if (board[y][x].isEnemy == forBlack) {

                        allPositions.clear();
                        board[y][x].getAllPossibleMoves(x, y, allPositions);
                        for (BasePiece[][] position : allPositions) {
                            if (position != null) {
                                if (steps > 0) {

                                    //moves the other color to get the real position
                                    BoardEval boardEval = getBestPosition(GameBoard.cloneBoard(position), !forBlack, steps - 1, new PositionEval(), new PositionEval());

                                    //sets the current eval to the eval after other color moved
                                    currentEval = forBlack ? boardEval.blackEval : boardEval.whiteEval;

                                    //sets the position after the other color moved to the current position (overriding the other color move)
                                    currentEval.position = position;

                                    currentEvalForEnemy = forBlack ? boardEval.whiteEval : boardEval.blackEval;

                                    currentEvalForEnemy.position = position;

                                } else {
                                    //gets the eval in the position (foe black and white)
                                    calculateEvalForPosition(position, currentEval, forBlack);
                                    calculateEvalForPosition(position, currentEvalForEnemy, !forBlack);
                                }

                                if (PositionEval.isLeftBiggerThanRight(currentEval, currentEvalForEnemy, bestEval, bestEvalForEnemy)) {
                                    bestEval = currentEval;
                                    bestEvalForEnemy = currentEvalForEnemy;
                                    currentEval = new PositionEval();
                                    currentEvalForEnemy = new PositionEval();
                                    //Gdx.app.log("shtokfish","found pos for "+(forBlack?"black":"white"));
                                }
                                movesCount++;
                            }

                        }

                    }
            }
        }

        //is checkmate
        if(movesCount==0){
            bestEval.kingMoves = -100;
            //Gdx.app.log("shtokfish","moves possible: " + forBlack);
        }


        return new BoardEval(forBlack ? bestEvalForEnemy : bestEval, forBlack ? bestEval : bestEvalForEnemy);
    }

    public static void calculateEvalForPosition(BasePiece[][] position, PositionEval eval, boolean forBlack) {

        eval.position = position;

        eval.piecesActivity = 0;
        eval.materialValue = 0;
        eval.kingMoves = 0;

        Array<BasePiece[][]> moves = new Array<>();

        //region king safety
        Point p = GameBoard.finedKingInBoard(position, forBlack);
        BasePiece king;

        if (p.x != -10) {
            king = position[p.y][p.x];

        } else {
            eval.kingMoves = -100;
            return;
        }

        eval.kingMoves = king.getKingSafety(p.x, p.y, position) * -0.004f;


        //endregion


        //for checkmate
        int movesCount = 0;
        boolean canEnemyMove = false;
        boolean isEnemyChecked = false;
        Point ep = GameBoard.finedKingInBoard(position, !forBlack);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (position[y][x] != null)
                    if (forBlack == position[y][x].isEnemy) {

                        //for enemy check
                        if(!isEnemyChecked)
                            if(position[y][x].doesCheck(x,y,ep.x, ep.y)){
                                isEnemyChecked = true;
                            }


                        //clears the moves every time
                        moves.clear();

                        //material
                        eval.materialValue += position[y][x].type.materialValue;

                        //piece activity
                        position[y][x].getAllPossibleMoves(x, y, moves);

                        eval.piecesActivity += moves.size * position[y][x].type.movementValue * 0.002f;

                        movesCount += moves.size;
                        //Gdx.app.log("shtokfish move count","count: "+moves.size);
                    }
                    else if(!canEnemyMove){

                        //clears the moves every time
                        moves.clear();

                        //piece activity
                        position[y][x].getAllPossibleMoves(x, y, moves);
                        if(moves.size > 0)
                            canEnemyMove = true;

                    }

            }
        }
        Gdx.app.log("shtokfish enemy","count: " + isEnemyChecked);
        if(!canEnemyMove && isEnemyChecked)
            eval.isCheckMated = true;



    }
}
