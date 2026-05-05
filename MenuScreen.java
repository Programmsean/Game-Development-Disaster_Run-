package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends ScreenAdapter {

    private Main game;
    private Stage stage;
    private Skin skin;
    private Texture backgroundTexture;

    public MenuScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Background
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        // Skin
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Buttons
        TextButton startButton = new TextButton("START GAME", skin);
        TextButton aboutButton = new TextButton("ABOUT US", skin);
        TextButton exitButton = new TextButton("EXIT", skin);

        // Layout
        Table table = new Table();
        table.setFillParent(true);
        table.top().padTop(220);

        table.add(startButton).width(300).height(60).pad(10);
        table.row();
        table.add(aboutButton).width(300).height(60).pad(10);
        table.row();
        table.add(exitButton).width(300).height(60).pad(10);

        stage.addActor(table);

        // Actions
        startButton.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                game.setScreen(new StoryScreen(game)); // GO TO STORY
            }
            return true;
        });

        exitButton.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                Gdx.app.exit();
            }
            return true;
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
    }
}
