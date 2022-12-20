package me.jonasjones.microcraft.gui.screens;

import me.jonasjones.microcraft.gui.widget.BoardConfigwidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class BoardScreen extends Screen {
    private final Screen parent;

    public BoardScreen (Screen parent) {
        super(Text.of("Microcraft Configuration"));
        this.parent = parent;
    }
    protected void init() {


        this.addDrawableChild(ButtonWidget.builder(Text.of("Configure Microcontrollers"), (button) -> {
            this.client.setScreen(new SelectDevice(this));
        }).dimensions(this.width / 2 - 155, this.height / 6 + 12, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("Configure Microcraft"), (button) -> {
            this.client.setScreen(new ModSettings(this));
        }).dimensions(this.width / 2 + 5, this.height / 6 + 12, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.client.setScreen(this.parent);
        }).dimensions(this.width / 2 - 100, this.height / 6 + 168, 200, 20).build());
        this.client.setScreen(new BoardConfigwidget(this.parent));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}