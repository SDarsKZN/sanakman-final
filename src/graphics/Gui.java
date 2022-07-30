package graphics;

import logic.GameMap;
import logic.CellState;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static constants.Constants.*;
import static org.lwjgl.opengl.GL11.*;

//Класс, отвечающий за отрисовку графики
public class Gui {

    public Gui() {
        initializeOpenGL();
    }

    //метод, создающий игровое окно
    private static void initializeOpenGL() {
        try {
            //Задаём размер будущего окна
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));

            //Задаём имя будущего окна
            Display.setTitle(SCREEN_NAME);
            //Центрируем окно посередине экрана
            Display.setLocation(-10, 10);

            //Создаём окно
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        //куча непонятных, но важных настроек библиотеки
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        //Для поддержки текстур
        glEnable(GL_TEXTURE_2D);

        //Для поддержки прозрачности
        glEnable(GL_BLEND);

        //Белый фоновый цвет
        glClearColor(1, 1, 1, 1);
    }

    //метод, отвечающий за отрисовку игровой карты
    public void draw(GameMap map) {
        glClear(GL_COLOR_BUFFER_BIT);

        for (int i = 0; i < CELLS_COUNT_X; i++) {
            for (int j = 0; j < CELLS_COUNT_Y; j++) {
                drawSprite(CELL_SIZE * i, CELL_SIZE * j, calculateSprite(map.getCell(i, j).getState()));
            }
        }

        //постоянно обновляем окно с игрой
        Display.update();
        //устанавливаем скорость обновления окна
        Display.sync(FPS);
    }

    //метод, отвечающий за определение спрайта, который надо отрисовать
    private SpriteSystem.Sprite calculateSprite(CellState state) {

        SpriteSystem.Sprite result;
        switch (state) {
            case AIDAMAN -> result = SpriteSystem.Sprite.AIDAMAN;
            case WALL -> result = SpriteSystem.Sprite.WALL;
            case EXIT -> result = SpriteSystem.Sprite.EXIT;
            case DOSHIK -> result = SpriteSystem.Sprite.DOSHIK;
            case SANAKMAN_UP -> result = SpriteSystem.Sprite.SANAKMAN_UP;
            case SANAKMAN_DOWN -> result = SpriteSystem.Sprite.SANAKMAN_DOWN;
            case SANAKMAN_LEFT -> result = SpriteSystem.Sprite.SANAKMAN_LEFT;
            case SANAKMAN_RIGHT -> result = SpriteSystem.Sprite.SANAKMAN_RIGHT;
            default -> result = SpriteSystem.Sprite.SPACE;
        }
        return result;
    }

    /**
     * Рисует отдельную ячейку
     *
     * @param x      Координата отрисовки X
     * @param y      Координата отрисовки Y
     * @param sprite Текстура, которую надо отрисовать
     */
    private void drawSprite(int x, int y, SpriteSystem.Sprite sprite) {
        sprite.getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y + CELL_SIZE);
        glTexCoord2f(1, 0);
        glVertex2f(x + CELL_SIZE, y + CELL_SIZE);
        glTexCoord2f(1, 1);
        glVertex2f(x + CELL_SIZE, y);
        glTexCoord2f(0, 1);
        glVertex2f(x, y);
        glEnd();
    }

    public void destroy() {
        Display.destroy();
    }
}
