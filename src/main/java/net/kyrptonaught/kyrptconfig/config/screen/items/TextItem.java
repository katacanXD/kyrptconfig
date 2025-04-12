package net.kyrptonaught.kyrptconfig.config.screen.items;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class TextItem extends ConfigItem<String> {

    TextFieldWidget valueEntry;

    public TextItem(Text name, String value, String defaultValue) {
        super(name, value, defaultValue);
        useDefaultResetBTN();
        valueEntry = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 96, 18, Text.literal("Text Entry"));
        setMaxLength(256);
        valueEntry.setText(value);
        valueEntry.setChangedListener(this::setValue);
    }

    public TextItem setMaxLength(int length) {
        valueEntry.setMaxLength(length);
        return this;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    @Override
    public void resetToDefault() {
        setValue(defaultValue);
        valueEntry.setText(value);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        return valueEntry.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return valueEntry.charTyped(chr, modifiers);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        valueEntry.setFocused(valueEntry.mouseClicked(mouseX, mouseY, button));
    }

    @Override
    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);

        if (valueEntry.isFocused())
            this.valueEntry.setWidth(150);
        else
            this.valueEntry.setWidth(96);

        this.valueEntry.setY(y + 1);
        this.valueEntry.setX(resetButton.getX() - (valueEntry.getWidth()) - 7);

        valueEntry.render(context, mouseX, mouseY, delta);
    }
}