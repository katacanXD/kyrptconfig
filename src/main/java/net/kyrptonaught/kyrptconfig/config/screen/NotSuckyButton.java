package net.kyrptonaught.kyrptconfig.config.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class NotSuckyButton extends ButtonWidget {
    int buttonColor = 16777215;
    public boolean disableHover = false;
    private static final ButtonTextures TEXTURES = new ButtonTextures(Identifier.of("widget/button"), Identifier.of("widget/button_disabled"), Identifier.of("widget/button_highlighted"));

    public NotSuckyButton(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION_SUPPLIER);
    }

    public void setButtonColor(int color) {
        this.buttonColor = color;
    }

    public boolean detectHover(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (disableHover) hovered = false;

        // Получаем цвет с учетом альфа-канала
        int color = ColorHelper.Argb.getArgb((int) this.alpha, 255, 255, 255);

        // Рисуем текстуру кнопки
        context.drawGuiTexture(
                TEXTURES.get(this.active, this.isSelected()),
                this.getX(),
                this.getY(),
                this.getWidth(),
                this.getHeight(),
                color
        );

        // Рендерим текст
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int textColor = this.active ? buttonColor : 0xA0A0A0;
        drawMessage(context, textRenderer, textColor);
    }
}
