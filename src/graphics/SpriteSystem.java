package graphics;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;

class SpriteSystem {

    /**
     * Загружает и хранит все доступные в игре текстуры
     */
    enum Sprite {

        SPACE("space"),WALL("wall"), EXIT("exit"), DOSHIK("doshik"),
        SANAKMAN_UP("sanakman_up"), SANAKMAN_DOWN("sanakman_down"),
        SANAKMAN_LEFT("sanakman_left"), SANAKMAN_RIGHT("sanakman_right"),
        AIDAMAN("aidaman");

        private Texture texture;

        Sprite(String textureName) {
            try {
                this.texture = TextureLoader.getTexture("PNG", new FileInputStream("res/sprites/"
                        + textureName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public Texture getTexture() {
            return this.texture;
        }
    }
}
