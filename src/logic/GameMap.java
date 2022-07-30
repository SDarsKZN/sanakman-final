package logic;

public class GameMap {
    private final Cell[][] cells;

    public GameMap(int width, int height) {
        cells = new Cell[width][height];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (i == 1 && j == 1) {
                    cells[i][j] = new Cell(i, j, CellState.SANAKMAN_UP);
                } else if (i == width - 2 && j == height - 2) {
                    cells[i][j] = new Cell(i, j, CellState.EXIT);
                } else if (i % 5 == 1 && j % 7 == 1) {
                    cells[i][j] = new Cell(i, j, CellState.DOSHIK);
                } else if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    cells[i][j] = new Cell(i, j, CellState.WALL);
                } else {
                    cells[i][j] = new Cell(i, j, CellState.EMPTY);
                }
            }
        }
        cells[1][3].setState(CellState.WALL);
        cells[2][3].setState(CellState.WALL);
        cells[3][3].setState(CellState.WALL);
        cells[4][3].setState(CellState.WALL);
        cells[5][3].setState(CellState.WALL);
        cells[6][3].setState(CellState.WALL);
        cells[7][3].setState(CellState.WALL);
        cells[7][4].setState(CellState.WALL);
        cells[7][5].setState(CellState.WALL);
        cells[7][6].setState(CellState.WALL);
        cells[7][9].setState(CellState.WALL);
        cells[7][10].setState(CellState.WALL);
        cells[7][11].setState(CellState.WALL);
        cells[7][12].setState(CellState.WALL);
        cells[6][12].setState(CellState.WALL);
        cells[5][12].setState(CellState.WALL);
        cells[4][12].setState(CellState.WALL);
        cells[3][12].setState(CellState.WALL);
        cells[3][11].setState(CellState.WALL);
        cells[3][10].setState(CellState.WALL);
        cells[3][9].setState(CellState.WALL);
        cells[3][8].setState(CellState.WALL);
        cells[3][7].setState(CellState.WALL);
        cells[16][7].setState(CellState.WALL);
        cells[17][7].setState(CellState.WALL);
        cells[18][7].setState(CellState.WALL);
        cells[19][7].setState(CellState.WALL);
        cells[20][7].setState(CellState.WALL);
        cells[15][7].setState(CellState.WALL);
        cells[14][7].setState(CellState.WALL);
        cells[13][7].setState(CellState.WALL);
        cells[13][8].setState(CellState.WALL);
        cells[13][9].setState(CellState.WALL);
        cells[13][10].setState(CellState.WALL);
        cells[20][6].setState(CellState.WALL);
        cells[20][5].setState(CellState.WALL);
        cells[20][3].setState(CellState.WALL);
        cells[20][2].setState(CellState.WALL);
        cells[20][4].setState(CellState.WALL);
        cells[20][13].setState(CellState.WALL);
        cells[20][12].setState(CellState.WALL);
        cells[20][11].setState(CellState.WALL);
        cells[20][10].setState(CellState.WALL);
        cells[19][10].setState(CellState.WALL);
        cells[18][10].setState(CellState.WALL);
        cells[17][10].setState(CellState.WALL);
        cells[12][1].setState(CellState.WALL);
        cells[12][2].setState(CellState.WALL);
        cells[28][5].setState(CellState.WALL);
        cells[27][5].setState(CellState.WALL);
        cells[26][5].setState(CellState.WALL);
        cells[25][5].setState(CellState.WALL);
        cells[25][6].setState(CellState.WALL);
        cells[25][7].setState(CellState.WALL);
        cells[25][8].setState(CellState.WALL);
    }
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}
