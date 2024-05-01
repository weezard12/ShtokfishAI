package com.weezard12.shtokfishai.gameLogic.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.weezard12.shtokfishai.gameLogic.board.GameBoard;
import com.weezard12.shtokfishai.gameLogic.pieces.baseClasses.BasePiece;
import com.weezard12.shtokfishai.main.Point;

import java.util.Arrays;

public class Opening {

    String name;
    Array<BasePiece[][]> positions = new Array<>();

    public Opening(String name, String moves){
        this.name = name;

        String s = "";
        int count = 0;
        for (char c : moves.toCharArray()) {
             s += c;
             if(c==',')
                count++;
             if(count==64){
                 BasePiece[][] pos = new BasePiece[8][8];
                 GameBoard.setBoardByString(pos,s);
                 positions.add(pos);
                 count=0;
                 s = "";
             }

        }


    }

    public BasePiece[][] tryGetPositionIdx(BasePiece[][] board){
        int count = 1;
        for (BasePiece[][] position : positions) {
            //Gdx.app.log("",""+count);
            if(Arrays.deepEquals(position, board))
                if(count > positions.size-1)
                    return null;
                else
                    return GameBoard.cloneBoard(positions.get(count));
            count++;

        }
        return null;
    }
}
