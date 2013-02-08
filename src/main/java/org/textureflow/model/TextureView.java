package org.textureflow.model;

import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.skin.ComponentSkin;

import java.awt.*;

/**
 *
 */
public class TextureView extends Component {

    private final TextureViewSkin textureViewSkin = new TextureViewSkin();
    private Texture texture;

    private int tiles = 1;

    private int w = 256;
    private int h = 256;

    public TextureView(int tiles) {
        this(null, tiles, 256, 256);
    }

    public TextureView(Texture textureIn, int tiles, int w, int h) {
        setSkin(textureViewSkin);
        setTexture(textureIn);
        this.tiles = tiles;
        this.w = w;
        this.h = h;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        if (this.texture != null) {
            texture.getTextureListeners().remove(textureViewSkin);
        }

        this.texture = texture;

        if (this.texture != null) {
            texture.getTextureListeners().add(textureViewSkin);
        }
    }


    private class TextureViewSkin extends ComponentSkin  implements TextureListener {

        @Override
        public void layout() {
            // Nothing to do
        }

        @Override
        public int getPreferredWidth(int height) {
            return w;
        }

        @Override
        public int getPreferredHeight(int width) {
            return h;
        }

        @Override
        public void paint(Graphics2D graphics) {
            if (texture != null) {
                int tw = w / tiles;
                int th = h / tiles;
                for (int y = 0; y < tiles; y++) {
                    for (int x = 0; x < tiles; x++) {

                        texture.renderTexture(graphics, x * tw, y * th, tw, th);
                    }
                }
            }
        }

        @Override
        public void onTextureContentsChanged() {
            invalidateComponent();
        }

        @Override
        public void onTextureSizeChanged(int w, int h) {
            invalidateComponent();
        }

    }


}
