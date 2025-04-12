package net.kyrptonaught.kyrptconfig.config.screen.items.lists;


import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.kyrptonaught.kyrptconfig.config.screen.items.ConfigItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.SubItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries.ListStringEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class StringList extends SubItem<List<String>> {
    protected NotSuckyButton addButton, clearButton;

    public StringList(Text name, List<String> value, List<String> defaultValue) {
        this(name, value, defaultValue, true);
    }

    public StringList(Text name, List<String> value, List<String> defaultValue, Boolean autoPop) {
        super(name, false);
        this.value = value;
        this.defaultValue = defaultValue;
        if (autoPop) populateFromList();
        this.addButton = new NotSuckyButton(0, 0, 35, 20, Text.translatable("key.kyrptconfig.config.add"), widget -> {
            addConfigItem(createNewEntry(""));
        });
        this.clearButton = new NotSuckyButton(0, 0, 35, 20, Text.translatable("key.kyrptconfig.config.clear"), widget -> {
            setValue(new ArrayList<>());
        });
        useDefaultResetBTN();
    }

    public StringList setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    @Override
    public void setValue(List<String> value) {
        super.setValue(value);
        configs.forEach(configItem -> {
            if (configItem instanceof ListStringEntry stringListEntry)
                stringListEntry.setDeleted(true);
        });
        populateFromList();
    }

    public void populateFromList() {
        for (String s : value) {
            addConfigItem(createNewEntry(s));
        }
    }

    public ListStringEntry createNewEntry(String string) {
        return new ListStringEntry(string);
    }

    public List<String> getNewValues() {
        List<String> newValues = new ArrayList<>();
        configs.forEach(configItem -> {
            if (configItem instanceof ListStringEntry stringListEntry) {
                String result = stringListEntry.getValue();
                if (result != null) newValues.add(result);
            }
        });
        return newValues;
    }

    @Override
    public boolean isValueDefault() {
        return getNewValues().equals(defaultValue);
    }

    @Override
    protected void runSaveConsumer(List<String> value) {
        super.runSaveConsumer(getNewValues());
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (expanded && (addButton.mouseClicked(mouseX, mouseY, button) || clearButton.mouseClicked(mouseX, mouseY, button)) || resetButton.mouseClicked(mouseX, mouseY, button))
            return;
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        context.drawText(MinecraftClient.getInstance().textRenderer, expanded ? "-" : "+", x - 10, y + 5, 16777215, false);
        subStart = y;
        if (expanded) {
            this.clearButton.setY(y);
            this.clearButton.setX(resetButton.getX() - (clearButton.getWidth() / 2) - 20);
            this.clearButton.render(context, mouseX, mouseY, delta);
            this.addButton.setY(y);
            this.addButton.setX(clearButton.getX() - (addButton.getWidth() / 2) - 20);
            this.addButton.render(context, mouseX, mouseY, delta);
            int runningY = subStart + 23;
            for (ConfigItem<?> item : configs) {
                item.render(context, 30, runningY, mouseX, mouseY, delta);
                runningY += item.getSize() + 3;
            }
        }
    }
}
