package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;

import net.kyrptonaught.kyrptconfig.TagHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class BlockIconEntry extends IconEntry<Block> {
    public BlockIconEntry(String value, boolean allowTags) {
        super(value, allowTags);
    }

    @Override
    public ItemConvertible getItemToRender(float delta) {
        try {
            String entered = getValue();

            if (entered == null) return Items.BARRIER;

            if (entered.startsWith("#") && allowTags) {
                entered = entered.replaceAll("#", "");

                List<Block> blocks = TagHelper.getBlocksInTag(Identifier.of(entered));
                if (blocks.size() > 0)
                    enteredTag = blocks;
                else enteredTag = null;

                if (enteredTag != null) {
                    tickTags(delta);
                    return enteredTag.get(selectedTag).asItem();
                }
            }
            if (entered.startsWith("#"))
                return Items.BARRIER;
            return Registries.BLOCK.getOrEmpty(Identifier.of(entered)).orElse(Blocks.BARRIER);
        } catch (Exception ignored) {
        }
        return Items.BARRIER;
    }
}