package org.textureflow;

import org.apache.pivot.wtk.*;
import org.apache.pivot.util.Resources;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.collections.Map;
import java.net.URL;

public class TextureFlowMainWindow extends Window implements Bindable  {

    @BXML private PushButton testButton = null;

    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        testButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
                handleTestButtonPress();
            }
        });
    }

    @Override
    public void open(Display display, Window owner) {
        super.open(display, owner);

        testButton.requestFocus();
    }

    private void handleTestButtonPress() {
        Prompt.prompt("Testing!", this);
    }

}
