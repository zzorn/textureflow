package org.textureflow.model;

/**
 *
 */
public interface TextureListener {

    void onTextureContentsChanged();
    void onTextureSizeChanged(int w, int h);

}
