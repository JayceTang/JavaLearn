package com.jayce.thinkinginjava;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Games_9_9
 * @description a Game framework using Factory Method
 * @date 2018/10/4 19:06
 */
public class Games_9_9 {
    public static void playGame(GameFactory fact) {
        Game g = fact.getGame();
        while (g.move()) {
            ;
        }
    }

    public static void main(String[] args) {
        playGame(new CheckersFactory());
        playGame(new ChessFactory());
    }
}

interface Game {
    boolean move();
}
interface GameFactory {
    Game getGame();
}

class Checkers implements Game {
    private int moves = 0;
    private static final int MOVES = 3;
    @Override
    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }
}
class CheckersFactory implements GameFactory {

    @Override
    public Game getGame() {
        return new Checkers();
    }
}

class Chess implements Game {
    private int moves = 0;
    private static final int MOVES = 4;

    @Override
    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++moves != MOVES;
    }
}

class ChessFactory implements GameFactory {

    @Override
    public Game getGame() {
        return new Chess();
    }
}
