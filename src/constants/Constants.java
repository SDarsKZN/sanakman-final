package constants;

//Класс, содержащий все константы игры
public class Constants {
    ///Размер игровой ячейки
    public static final int CELL_SIZE = 64;

    ///Размеры игрового поля в ячейках
    public static final int CELLS_COUNT_X = 30;
    public static final int CELLS_COUNT_Y = 15;

    ///В нашем случае главный герой проходит одну клетку за один фрейм.
    public static final int FPS = 10;

    ///Константы для создания окна, названия достаточно говорящие.
    public static final int SCREEN_WIDTH =CELLS_COUNT_X*CELL_SIZE;
    public static final int SCREEN_HEIGHT = CELLS_COUNT_Y*CELL_SIZE;
    public static final String SCREEN_NAME = "SANAKMAN";
}
