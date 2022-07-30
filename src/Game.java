import graphics.Gui;
import logic.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static constants.Constants.*;

public class Game {
    private static GameMap gameMap;
    private static SanakMan sanakMan;
    private static Aidaman[] aidamans;
    private static int score = 0;
    private static Random random = new Random();
    private static Gui graphicsModule;
    ///Переменная, при обращении которой в true приложение закрывается
    private static boolean isExitRequested = false;
    ///Данные о главном герое. Начинать игру он будет из нижнего левого угла,
    ///Вправо (направления посчитаны по часовой стрелки от севера, т.е.
    /// 0 -- вверх, 1 -- вправо, 2 -- вниз, 3 -- влево
    private final static int startX = 1, startY = 1;

    public static void main(String[] args) {

        initFields();

        while (!isExitRequested) {
            input();
            logic();

            graphicsModule.draw(gameMap);
        }

        graphicsModule.destroy();
    }

    //Метод, отвечающий за обработку команд с клавиатуры
    //А заодно и за логику игры
    private static void input() {
        ///Перебираем события клавиатуры
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                //int direction = 0;
                switch (Keyboard.getEventKey()) {
                    case Keyboard.KEY_ESCAPE -> isExitRequested = true;
                    case Keyboard.KEY_UP -> {
                        //direction = 0;
                        if (!gameMap.getCell(sanakMan.getX(), sanakMan.getY() + 1).getState().equals(CellState.WALL)) {
                            if (gameMap.getCell(sanakMan.getX(), sanakMan.getY() + 1).getState().equals(CellState.AIDAMAN)) {
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY() + 1).setState(CellState.AIDAMAN);
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                                isExitRequested = true;
                            }
                            if (gameMap.getCell(sanakMan.getX(), sanakMan.getY() + 1).getState().equals(CellState.DOSHIK)) {
                                score = score + 1;
                            }
                            if (gameMap.getCell(sanakMan.getX(), sanakMan.getY() + 1).getState().equals(CellState.EXIT)) {
                                if (score < 10) {
                                    gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.SANAKMAN_UP);
                                } else
                                    isExitRequested = true;//здесь потом зделаем переход на новый уровень
                            }
                            if (!gameMap.getCell(sanakMan.getX(), sanakMan.getY() + 1).getState().equals(CellState.EXIT)) {
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                                sanakMan.setY(sanakMan.getY() + 1);
                            }
                        }
                        gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.SANAKMAN_UP);
                    }
                    case Keyboard.KEY_RIGHT -> {
                        //direction = 1;
                        if (!gameMap.getCell(sanakMan.getX() + 1, sanakMan.getY()).getState().equals(CellState.WALL)) {
                            if (gameMap.getCell(sanakMan.getX() + 1, sanakMan.getY()).getState().equals(CellState.AIDAMAN)) {
                                gameMap.getCell(sanakMan.getX() + 1, sanakMan.getY()).setState(CellState.AIDAMAN);
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                                isExitRequested = true;
                            }
                            if (gameMap.getCell(sanakMan.getX() + 1, sanakMan.getY()).getState().equals(CellState.DOSHIK)) {
                                score = score + 1;
                            }                        if (gameMap.getCell(sanakMan.getX() + 1, sanakMan.getY()).getState().equals(CellState.EXIT)){
                                if(score < 10){
                                    gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.SANAKMAN_RIGHT);
                                }
                                else
                                    isExitRequested = true;//и здесь потом тоже сделаем переход на новый уровень
                            }
                            if (!gameMap.getCell(sanakMan.getX() + 1, sanakMan.getY()).getState().equals(CellState.EXIT)) {
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                                sanakMan.setX(sanakMan.getX() + 1);
                            }
                        }
                        gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.SANAKMAN_RIGHT);
                    }
                    case Keyboard.KEY_DOWN -> {
                        //direction = 2;
                        if (!gameMap.getCell(sanakMan.getX(), sanakMan.getY() - 1).getState().equals(CellState.WALL)) {
                            if (gameMap.getCell(sanakMan.getX(), sanakMan.getY() - 1).getState().equals(CellState.AIDAMAN)) {
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY() - 1).setState(CellState.AIDAMAN);
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                                isExitRequested = true;
                            }
                            if (gameMap.getCell(sanakMan.getX(), sanakMan.getY() - 1).getState().equals(CellState.DOSHIK)) {
                                score = score + 1;
                            }
                            gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                            sanakMan.setY(sanakMan.getY() - 1);
                        }
                        gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.SANAKMAN_DOWN);
                    }
                    case Keyboard.KEY_LEFT -> {
                        //direction = 3;
                        if (!gameMap.getCell(sanakMan.getX() - 1, sanakMan.getY()).getState().equals(CellState.WALL)) {
                            if (gameMap.getCell(sanakMan.getX() - 1, sanakMan.getY()).getState().equals(CellState.AIDAMAN)) {
                                gameMap.getCell(sanakMan.getX() - 1, sanakMan.getY()).setState(CellState.AIDAMAN);
                                gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                                isExitRequested = true;
                            }
                            if (gameMap.getCell(sanakMan.getX() - 1, sanakMan.getY()).getState().equals(CellState.DOSHIK)) {
                                score = score + 1;
                            }
                            gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.EMPTY);
                            sanakMan.setX(sanakMan.getX() - 1);
                        }
                        gameMap.getCell(sanakMan.getX(), sanakMan.getY()).setState(CellState.SANAKMAN_LEFT);
                    }
                }
                Display.setTitle(SCREEN_NAME + " (your score = " + score + ")");
            }
        }
        ///Обрабатываем клик по кнопке "закрыть" окна
        isExitRequested = isExitRequested || Display.isCloseRequested();
    }

    private static void playSoundTrack() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("res/music/soundtrack-2.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createAidamans() {
        aidamans = new Aidaman[5];
        aidamans[0] = new Aidaman(5, 5);
        aidamans[1] = new Aidaman(6, 6);
        aidamans[2] = new Aidaman(12, 8);
        aidamans[3] = new Aidaman(15, 9);
        aidamans[4] = new Aidaman(28, 12);
        for (Aidaman current : aidamans) {
            gameMap.getCell(current.getX(), current.getY()).setState(CellState.AIDAMAN);
        }
    }

    private static void initFields() {

        sanakMan = new SanakMan();
        sanakMan.setX(startX);
        sanakMan.setY(startY);
        isExitRequested = false;
        graphicsModule = new Gui();
        gameMap = new GameMap(CELLS_COUNT_X, CELLS_COUNT_Y);
        createAidamans();
        playSoundTrack();
    }

    private static void logic() {
        for (Aidaman aidaman : aidamans) {
            moveAidaman(aidaman);
        }
    }

    private static void moveAidaman(Aidaman aidaman) {
        aidaman.setDirection(random.nextInt(0, 4));
        int x = aidaman.getX(), y = aidaman.getY();
        switch (aidaman.getDirection()) {
            case 0 -> y++;
            case 1 -> x++;
            case 2 -> y--;
            case 3 -> x--;
        }
        if (gameMap.getCell(x, y).getState().equals(CellState.WALL) ||
                gameMap.getCell(x, y).getState().equals(CellState.DOSHIK) ||
                gameMap.getCell(x, y).getState().equals(CellState.EXIT) ||
                gameMap.getCell(x, y).getState().equals(CellState.AIDAMAN)) {
            return;
        }
        if (aidaman.getX() == sanakMan.getX() && aidaman.getY() == sanakMan.getY()) {
            gameMap.getCell(aidaman.getX(), aidaman.getY()).setState(CellState.AIDAMAN);
            isExitRequested = true;
        }
        gameMap.getCell(x, y).setState(CellState.AIDAMAN);
        gameMap.getCell(aidaman.getX(), aidaman.getY()).setState(CellState.EMPTY);
        aidaman.setX(x);
        aidaman.setY(y);
    }
}
