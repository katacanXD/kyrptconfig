package net.kyrptonaught.kyrptconfig.config.screen.items.number;

import net.kyrptonaught.kyrptconfig.config.screen.items.ConfigItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class NumberItem<T extends Number> extends ConfigItem<T> {

    protected T min, max;
    TextFieldWidget valueEntry;
    boolean lastInputFixed = false;

    public NumberItem(Text name, T value, T defaultValue) {
        super(name, value, defaultValue);
        useDefaultResetBTN();
        valueEntry = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 96, 17, Text.literal("Number Entry"));
        valueEntry.setText(value.toString());
        valueEntry.setChangedListener(this::onTyped);
    }

    public NumberItem setMinMax(T min, T max) {
        this.min = min;
        this.max = max;
        valueEntry.setTooltip(Tooltip.of(Text.literal(min + " - " + max)));
        return this;
    }

    @Override
    public void setValue(T value) {
        valueEntry.setText(fixInput(value).toString());
    }

    public T fixInput(T value) {
        BigDecimal big = new BigDecimal(value.toString());
        if (min != null && big.compareTo(new BigDecimal(min.toString())) < 0)
            return min;
        if (max != null && big.compareTo(new BigDecimal(max.toString())) > 0)
            return max;

        return value;
    }

    public abstract T parseValue(String value);

    public void onTyped(String s) {
        boolean isValid = isValid(s);
        if (isValid) {
            valueEntry.setEditableColor(0xE0E0E0);
        } else {
            valueEntry.setEditableColor(DyeColor.RED.getSignColor());
        }
        lastInputFixed = false;
    }

    public boolean isValid(String s) {
        try {
            T parsed = parseValue(s);
            if (Objects.equals(fixInput(parsed), parsed))
                return true;
        } catch (NumberFormatException ignored) {

        }
        return false;
    }

    @Override
    public void tick() {
        if (!valueEntry.isFocused() && !lastInputFixed) {
            fixLastInput();
        }
    }

    @Override
    public void save() {
        fixLastInput();
        super.save();
    }

    public void fixLastInput() {
        valueEntry.setText(fixInput(parseValue(valueEntry.getText())).toString());
        value = parseValue(valueEntry.getText());
        lastInputFixed = true;
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
        this.valueEntry.setY(y + 2);
        this.valueEntry.setX(resetButton.getX() - resetButton.getWidth() - (valueEntry.getWidth() / 2) - 20);

        valueEntry.render(context, mouseX, mouseY, delta);
    }
}