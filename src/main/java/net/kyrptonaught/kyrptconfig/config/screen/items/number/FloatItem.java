package net.kyrptonaught.kyrptconfig.config.screen.items.number;

import net.minecraft.text.Text;

public class FloatItem extends NumberItem<Float> {
    public FloatItem(Text name, Float value, Float defaultValue) {
        super(name, value, defaultValue);
        setMinMax(Float.MIN_VALUE, Float.MAX_VALUE);
    }

    @Override
    public Float parseValue(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException ignored) {
        }
        return 0f;
    }
}