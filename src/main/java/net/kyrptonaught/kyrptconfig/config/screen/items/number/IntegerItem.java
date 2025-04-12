package net.kyrptonaught.kyrptconfig.config.screen.items.number;

import net.minecraft.text.Text;

public class IntegerItem extends NumberItem<Integer> {
    public IntegerItem(Text name, int value, int defaultValue) {
        super(name, value, defaultValue);
        setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public Integer parseValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
        }
        return 0;
    }
}
