package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StoryScreen extends ScreenAdapter {

    private Main game;
    private SpriteBatch batch;
    private Array<Texture> pages;
    private int currentPage = 0;

    private String fullText;
    private float charTimer = 0f;
    private int charsShown = 0;

    private Stage stage;
    private Skin skin;
    private Label dialogueLabel;

    private String[] dialogues = {
        "The night was quiet, but Ethan’s thoughts were loud.\n" +
            "He sat on his bed, staring out the window at the calm sky. It looked peaceful, but he couldn’t forget the typhoon from last week—the strong winds, the heavy rain, the fear. Some homes were destroyed, and his friend Marco lost everything.\n" +
            "“Why does this keep happening?” he whispered.\n" +
            "He looked at his books, then at his notebook on the floor. Slowly, he stood up and picked it up.\n" +
            "Maybe he couldn’t stop disasters, but he could help.\n" +
            "He began to write—small plans like volunteering, and bigger dreams like building stronger homes someday.\n" +
            "Ethan looked outside again. The night was still dark, but it didn’t feel as heavy.\n" +
            "This time, he wasn’t just thinking about the disaster—he was thinking about hope.",
        "The room was quiet—too quiet.\n" +
            "He slept on his side, breathing softly, unaware of the strange glow forming just above him.\n" +
            "At first, it was only a faint shimmer… like dust caught in moonlight. But then it twisted, stretched, and bloomed into something vast—something alive.\n" +
            "A ribbon of light spiraled into existence, glowing with deep blues and violets, like a piece of the night sky had slipped into his room.\n" +
            "And within it—\n" +
            "Shapes.\n" +
            "Three figures, barely human, formed from starlight and shadow. They moved slowly, as if suspended in water, their forms flickering between solid and intangible.",
        "The whisper turned into a vision.\n" +
            "Not a dream—something deeper.\n" +
            "He was no longer in his bed.\n" +
            "He stood in the middle of a city… or what used to be one.\n" +
            "The sky roared above him, torn apart by lightning that split the clouds like cracks in glass. Fire raged across buildings, devouring entire blocks in seconds. The air burned his lungs with smoke and ash.\n" +
            "Then came the sound—\n" +
            "A deafening roar.\n" +
            "He turned.\n" +
            "A massive wall of water surged forward, swallowing streets, cars, everything in its path. At the same time, a violent tornado twisted through the city, ripping buildings apart like paper.\n" +
            "And above it all—\n" +
            "Flames fell from the sky.\n" +
            "Meteors.\n" +
            "Dozens of them.\n" +
            "“No…” he whispered, his voice shaking. “This isn’t real…”\n" +
            "But it felt real.\n" +
            "Too real." +
            "I wiped the sweat from my forehead, my hand shaking. It was just a dream—a chaotic, impossible nightmare. But as I looked out at the peaceful city lights, I couldn't shake the feeling that the ground was still trembling.\n" +
            "\n" +
            "\"It felt too real,\" I whispered to the dark.\n" +
            "\n" +
            "I didn't go back to sleep that night. I just watched the horizon, waiting for the sky to break..",
        "He freezes for a second, heart pounding, the distant noise outside growing louder—sirens, shouting, something crashing. The calm morning light in his room suddenly feels out of place.\n" +
            "He throws the blanket aside and rushes to the window.\n" +
            "The curtains slide open, and the world outside hits him all at once.\n" +
            "Smoke rises between buildings. People are running—some shouting, some looking back in fear. A car is stopped in the middle of the street at an odd angle, its door left open. Somewhere in the distance, a loud bang echoes, followed by a wave of panic.",
        "He presses his hand against the glass, scanning the chaos below. This wasn’t normal—not just a random accident. It felt bigger. Wrong.\n" +
            "A group of people suddenly rush past his building, one of them yelling something he can’t quite make out—but the fear in their voice is unmistakable.\n" +
            "For a moment, he just stands there, caught between curiosity and dread.\n" +
            "Then his expression changes.\n" +
            "From shock… to decision.\n" +
            "He turns away from the window, mind racing. Whatever is happening out there, staying in bed is no longer an option.",
        " I grabbed my backpack from the chair, dumping out my useless school books. In their place, I shoved whatever I could find: a flashlight, a hoodie, and a single, half-empty water bottle. My hands shook so bad I could barely zip the bag.\n" +
            "\n" +
            "I looked at my shoes. It felt weird to be tying my laces while the world was ending, but every second counted.\n" +
            "\n" +
            "The voice of the news anchor was rising to a hysterical pitch.\n" +
            "\n" +
            "\"The coastal barriers have been breached!\" she screamed, her image flickering against the wall. \"If you are in Zone 4, you must—\"\n" +
            "\n" +
            "The TV went black.\n" +
            "\n" +
            "A silence so heavy it pressed against my eardrums filled the room. No more anchors. No more sirens. Just a low, vibrating thrum that seemed to be coming from the very center of the earth.\n" +
            "\n" +
            "Slowly, I stood up and forced my feet to move. Step by heavy step, I walked toward the window I had been avoiding. My hand, damp with cold sweat, reached for the curtain.\n" +
            "\n" +
            "I pulled it back, and my breath was stolen from my lungs.\n" +
            "\n" +
            "There was no more city lights. No familiar streets.",
        "He stood alone in his room, the early morning sun spilling across the walls like a quiet reminder that the world hadn’t completely fallen apart—yet.\n" +
            "\n" +
            "In front of him, the whiteboard was no longer blank.\n" +
            "\n" +
            "“I’m not just gonna survive… I’m gonna help others survive.”\n" +
            "\n" +
            "He read the words again, this time louder in his head. They didn’t sound like something he would normally say. Just hours ago, he was the same person who froze in fear, staring at the TV, unsure of what to do.\n" +
            "\n" +
            "But something changed.\n" +
            "\n" +
            "Maybe it was the chaos outside—the distant smoke rising beyond the window, the faint noise of people running, sirens echoing in the distance. Or maybe it was the realization that waiting wouldn’t save anyone.\n" +
            "\n" +
            "His grip tightened into a fist.\n" +
            "\n" +
            "The plan written in front of him wasn’t perfect. It was simple, almost too simple. Stay calm. Gather information. Prepare. Help others. Words anyone could write—but not everyone would act on.\n" +
            "\n" +
            "“That’s enough,” he whispered.\n" +
            "\n" +
            "Enough to start.\n" +
            "\n" +
            "He didn’t need to be the strongest. He didn’t need to have all the answers. He just needed to move—to choose courage over fear.\n" +
            "\n",
        "He stepped outside—not just to survive, but to help others survive.\n" +
            "\n" +
            "The world had turned into chaos. Buildings collapsed, fire spread, and a massive wave rose in the distance like the end of everything. People were running, scared and lost.\n" +
            "\n" +
            "For a second, fear hit him.\n" +
            "\n" +
            "But he didn’t stop.\n" +
            "\n" +
            "He tightened his grip on his bag and moved forward, helping the injured, calling out to strangers, guiding them to safety.\n" +
            "\n" +
            "“I’m not just surviving…” he said under his breath. “I’m helping others survive too.”\n" +
            "\n" +
            "And in the middle of destruction, he chose to stand—not alone, but for everyone."
    };

    public StoryScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        pages = new Array<>();
        for (int i = 1; i <= 8; i++) {
            pages.add(new Texture("page" + i + ".png"));
        }

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        TextButton nextButton = new TextButton("▶ Next", skin);
        TextButton prevButton = new TextButton("◀ Previous", skin);

        fullText = dialogues[0];
        dialogueLabel = new Label("", skin);
        dialogueLabel.setWrap(true);

        Table table = new Table();
        table.setFillParent(true);
        table.bottom();

        Table dialogueBox = new Table();
        dialogueBox.setBackground(skin.newDrawable("white", 0, 0, 0, 0.6f));
        dialogueBox.add(dialogueLabel).expandX().fillX().pad(10);

        table.add(dialogueBox).colspan(2).expandX().fillX().pad(10);
        table.row();

        table.add(prevButton).left().pad(10).width(150).height(50);
        table.add(nextButton).right().pad(10).width(150).height(50);

        stage.addActor(table);

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentPage < pages.size - 1) {
                    currentPage++;
                    fullText = dialogues[currentPage];
                    charsShown = 0;
                    dialogueLabel.setText("");
                } else {
                    game.setScreen(new MenuScreen(game));
                }
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentPage < pages.size - 1) {
                    currentPage++;
                    fullText = dialogues[currentPage];
                    charsShown = 0;
                    dialogueLabel.setText("");
                } else {
                    game.setScreen(new MenuScreen(game));
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        charTimer += delta;
        if (charTimer > 0.05f && charsShown < fullText.length()) {
            charsShown++;
            dialogueLabel.setText(fullText.substring(0, charsShown));
            charTimer = 0f;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(pages.get(currentPage), 0, 0,
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture t : pages) t.dispose();
        stage.dispose();
        skin.dispose();
    }
}
