package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;

import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.kyrptonaught.kyrptconfig.config.screen.items.ConfigItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class ListStringEntry extends ConfigItem<String> {
    boolean deleted = false;
    TextFieldWidget valueEntry;
    protected NotSuckyButton delButton;

    public ListStringEntry(String value) {
        super(Text.literal(""), value, value);
        valueEntry = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 125, 18, Text.literal("Text Entry"));
        valueEntry.setMaxLength(256);
        valueEntry.setText(value);
        this.delButton = new NotSuckyButton(0, 0, 35, 20, Text.translatable("key.kyrptconfig.config.delete"), widget -> {
            setDeleted(true);
        });
    }

    public void setDeleted(boolean isDeleted) {
        this.deleted = isDeleted;
        if (deleted) valueEntry.setText("");
    }

    public String getValue() {
        if (deleted || valueEntry.getText() == null) return null;
        if (valueEntry.getText().isEmpty() || valueEntry.getText().isBlank()) return null;
        return valueEntry.getText();
    }

    @Override
    public int getContentSize() {
        if (deleted) return -23;
        return super.getContentSize();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (deleted) return false;
        return valueEntry.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (deleted) return false;
        return valueEntry.charTyped(chr, modifiers);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        if (deleted) return;
        delButton.mouseClicked(mouseX, mouseY, button);
        valueEntry.setFocused(valueEntry.mouseClicked(mouseX, mouseY, button));
    }

    @Override
    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        if (deleted) return;
        super.render(context, x, y, mouseX, mouseY, delta);
        this.delButton.setY(y);
        this.delButton.setX(MinecraftClient.getInstance().getWindow().getScaledWidth() - delButton.getWidth() - 20);
        this.delButton.render(context, mouseX, mouseY, delta);

        if (valueEntry.isFocused())
            this.valueEntry.setWidth(175);
        else
            this.valueEntry.setWidth(125);

        this.valueEntry.setY(y + 1);
        this.valueEntry.setX(delButton.getX() - (valueEntry.getWidth()) - 7);
        this.valueEntry.render(context, mouseX, mouseY, delta);
    }
}