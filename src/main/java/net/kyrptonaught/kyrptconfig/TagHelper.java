package net.kyrptonaught.kyrptconfig;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TagHelper {

    public static List<Identifier> getBlockIDsInTag(Identifier blockTagKey) {
        List<Identifier> blocks = new ArrayList<>();
        List<TagKey<Block>> tags = Registries.BLOCK.streamTags().toList();

        for (TagKey<Block> tagKey : tags) {
            if (tagKey.id().equals(blockTagKey)) {
                Registries.BLOCK.iterateEntries(tagKey).forEach(registryEntry -> {
                    registryEntry.getKey().ifPresent(registryEntry2 -> blocks.add(registryEntry2.getValue()));
                });
                break;
            }
        }
        return blocks;
    }

    public static List<Block> getBlocksInTag(Identifier blockTagKey) {
        List<Block> blocks = new ArrayList<>();
        List<TagKey<Block>> tags = Registries.BLOCK.streamTags().toList();

        for (TagKey<Block> tagKey : tags) {
            if (tagKey.id().equals(blockTagKey)) {
                Registries.BLOCK.iterateEntries(tagKey).forEach(registryEntry -> {
                    blocks.add(registryEntry.value());
                });
                break;
            }
        }
        return blocks;
    }

    public static List<Identifier> getItemsIDsInTag(Identifier blockTagKey) {
        List<Identifier> items = new ArrayList<>();
        List<TagKey<Item>> tags = Registries.ITEM.streamTags().toList();

        for (TagKey<Item> tagKey : tags) {
            if (tagKey.id().equals(blockTagKey)) {
                Registries.ITEM.iterateEntries(tagKey).forEach(registryEntry -> {
                    registryEntry.getKey().ifPresent(registryEntry2 -> items.add(registryEntry2.getValue()));
                });
                break;
            }
        }
        return items;
    }

    public static List<Item> getItemsInTag(Identifier blockTagKey) {
        List<Item> items = new ArrayList<>();
        List<TagKey<Item>> tags = Registries.ITEM.streamTags().toList();

        for (TagKey<Item> tagKey : tags) {
            if (tagKey.id().equals(blockTagKey)) {
                Registries.ITEM.iterateEntries(tagKey).forEach(registryEntry -> {
                    items.add(registryEntry.value());
                });
                break;
            }
        }
        return items;
    }
}