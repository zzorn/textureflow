package org.textureflow.ui;

import org.apache.pivot.wtk.*;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Panel;
import org.apache.pivot.wtk.Point;
import org.apache.pivot.wtk.Window;
import org.textureflow.model.*;

import java.awt.*;
import java.io.IOException;


/**
 *
 */
public class MainUi2 {

    private final Library basicLibrary;
    private Window mainWindow;
    private Project project;
    private Texture texture;
    private ListView effectList;
    private ListView libraryList;
    private TextureView texturePreview;

    public MainUi2(Library basicLibrary) {
        this.basicLibrary = basicLibrary;
    }

    public void openMainUi(Display display) {
        mainWindow = new Window();

        // Create list view of effects on the current texture
        effectList = createEffectList();

        // Create property editor for selected effect (or put them in the list as an expandable area)


        // Create preview of the texture
        texturePreview = new TextureView();


        // Create menu with export, save, etc.


        // Create library with effects or composite effects
        libraryList = new ListView(basicLibrary.getEntries());

        libraryList.setDragSource(createListDragSource(libraryList));

        /*
        libraryList.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
                System.out.println("MainUi2.buttonPressed");
                Effect effect  = (Effect) libraryList.getSelectedItem();
                if (effect != null) {
                    texture.addEffect(effect.copy());
                }
            }
        });
        */

        /*
        Label label = new Label();
        label.setText("Hello World!");
        label.getStyles().put("font", new Font("Arial", Font.BOLD, 24));
        label.getStyles().put("color", Color.BLUE);
        label.getStyles().put("horizontalAlignment", HorizontalAlignment.CENTER);
        label.getStyles().put("verticalAlignment", VerticalAlignment.CENTER);
        */

        SplitPane split1= new SplitPane(Orientation.HORIZONTAL, libraryList, effectList);
        SplitPane split2= new SplitPane(Orientation.HORIZONTAL, split1, texturePreview);

        mainWindow.setContent(split2);
        mainWindow.setTitle("TextureFlow");
        mainWindow.setMaximized(true);

        mainWindow.open(display);

    }

    private ListView createEffectList() {
        final ListView effectList = new ListView();
        effectList.setSelectMode(ListView.SelectMode.SINGLE);
        effectList.setDropTarget(new DropTarget() {
            @Override
            public DropAction dragEnter(Component component, Manifest dragContent, int supportedDropActions, DropAction userDropAction) {
                return DropAction.COPY;
            }

            @Override
            public void dragExit(Component component) {
            }

            @Override
            public DropAction dragMove(Component component, Manifest dragContent, int supportedDropActions, int x, int y, DropAction userDropAction) {
                return DropAction.COPY;
            }

            @Override
            public DropAction userDropActionChange(Component component, Manifest dragContent, int supportedDropActions, int x, int y, DropAction userDropAction) {
                return DropAction.COPY;
            }

            @Override
            public DropAction drop(Component component, Manifest dragContent, int supportedDropActions, int x, int y, DropAction userDropAction) {
                try {
                    // Create a copy of the source effect
                    Effect effect = (Effect) dragContent.getValue("data");
                    Effect effectCopy = effect.copy();

                    // Determine where to put it
                    int index = effectList.getItemAt(y);
                    if (index < 0) {
                        texture.addEffect(effectCopy);
                    }
                    else {
                        texture.addEffect(effectCopy, index);
                    }

                    return DropAction.COPY;
                } catch (IOException e) {
                    return null;
                }
            }
        });

        return effectList;
    }

    private DragSource createListDragSource(final ListView list) {
        return new DragSource() {
            private Object lastDraggedEntry;

            @Override
            public boolean beginDrag(Component component, int x, int y) {
                int itemAt = list.getItemAt(y);
                if (itemAt < 0) return false;
                else  {
                    lastDraggedEntry = list.getListData().get(itemAt);
                    return true;
                }
            }

            @Override
            public void endDrag(Component component, DropAction dropAction) {
            }

            @Override
            public boolean isNative() {
                return false;
            }

            @Override
            public LocalManifest getContent() {
                LocalManifest manifest = new LocalManifest();
                manifest.putText(lastDraggedEntry.toString());
                manifest.putValue("data", lastDraggedEntry);
                return manifest;
            }

            @Override
            public Visual getRepresentation() {
                return new Visual() {
                    @Override
                    public int getWidth() {
                        return 16;
                    }

                    @Override
                    public int getHeight() {
                        return 16;
                    }

                    @Override
                    public int getBaseline() {
                        return 0;
                    }

                    @Override
                    public void paint(Graphics2D graphics) {
                        graphics.setColor(Color.BLUE);
                        graphics.drawRect(0, 0, 15, 15);
                    }
                };
            }

            @Override
            public Point getOffset() {
                return new Point(8,8);
            }

            @Override
            public int getSupportedDropActions() {
                return DropAction.COPY.getMask();
            }
        };
    }

    public void showProject(Project project) {
        this.project = project;
        showTexture(project.getCurrentTexture());
    }

    public void showTexture(Texture texture) {
        this.texture = texture;

        effectList.setListData(texture.getEffects());
        texturePreview.setTexture(texture);
    }

    public void closeMainUi() {

    }

}
