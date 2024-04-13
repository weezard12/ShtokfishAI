package com.weezard12.shtokfishai.gameLogic.ai;

public class BoardEval {
    public PositionEval whiteEval;
    public PositionEval blackEval;

    public BoardEval(PositionEval whiteEval,PositionEval blackEval){
        this.whiteEval = whiteEval;
        this.blackEval = blackEval;
    }
}
