package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;


import net.kyrptonaught.kyrptconfig.TagHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

import java.util.List;

public class ItemIconEntry extends IconEntry<Item> {
    public ItemIconEntry(String value, boolean allowTags) {
        super(value, allowTags);
    }

    @Override
    public ItemConvertible getItemToRender(float delta) {
        try {
            String entered = getValue();
            if (entered == null) return Items.BARRIER;

            if (entered.startsWith("#") && allowTags) {
                entered = entered.replaceAll("#", "");
                List<Item> items = TagHelper.getItemsInTag(Identifier.of(entered));
                if (items.size() > 0)
                    enteredTag = items;
                else enteredTag = null;
                if (enteredTag != null) {
                    tickTags(delta);
                    return enteredTag.get(selectedTag).asItem();
                }
            }
            if (entered.startsWith("#"))
                return Items.BARRIER;
            return Registries.ITEM.getOrEmpty(Identifier.of(entered)).orElse(Items.BARRIER);
        } catch (InvalidIdentifierException ignored) {
        }
        return Items.BARRIER;
    }
}
