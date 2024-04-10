package com.weezard12.shtokfishai.gameLogic.ai;

import com.weezard12.shtokfishai.gameLogic.pieces.*;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;

public class Shtokfish {


    //region Checks
    public static boolean isInCheck(BasePiece[][] board, boolean isCheckForWhite) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (board[y][x] != null)
                    switch (board[y][x].type) {
                        case KING:


                            break;
                        case KNIGHT:

                            break;
                        case ROOK:

                            break;
                        case BISHOP:

                            break;
                        case QUEEN:

                            break;
                        case PAWN:

                            break;
                        default:

                            break;
                    }

                return false;
            }
            //endRegion
        }
        return false;
    }

}
