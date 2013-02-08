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


    public TextureView() {
        this(null);
    }

    public TextureView(Texture textureIn) {
        setSkin(textureViewSkin);
        setTexture(textureIn);
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
            if (texture != null) return texture.getWidth();
            else return 0;
        }

        @Override
        public int getPreferredHeight(int width) {
            if (texture != null) return texture.getHeight();
            else return 0;
        }

        @Override
        public void paint(Graphics2D graphics) {
            if (texture != null) {
                texture.renderTexture(graphics);
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
