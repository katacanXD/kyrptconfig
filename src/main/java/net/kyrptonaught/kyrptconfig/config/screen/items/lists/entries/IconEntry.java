package net.kyrptonaught.kyrptconfig.config.screen.items.lists.entries;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.List;

public class IconEntry<E> extends ListStringEntry {
    protected boolean allowTags = false;
    protected List<E> enteredTag;
    int selectedTag = 0;
    float deltas;

    public IconEntry(String value, boolean allowTags) {
        super(value);
        this.allowTags = allowTags;
    }

    public ItemConvertible getItemToRender(float delta) {
        return Items.BARRIER;
    }

    public void tickTags(float delta) {
        deltas += delta;
        if (deltas > 45 && allowTags && enteredTag != null) {
            selectedTag++;
            deltas = 0;
        }
        if (enteredTag == null || selectedTag >= enteredTag.size()) selectedTag = 0;
    }

    @Override
    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        if (deleted) return;
        ItemConvertible item = getItemToRender(delta);
        renderGuiItemModel(context, new ItemStack(item), x, y);
    }

    protected void renderGuiItemModel(DrawContext context, ItemStack stack, int x, int y) {
        context.getMatrices().push();
        MinecraftClient.getInstance().getTextureManager().getTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).setFilter(false, false);
        RenderSystem.setShaderTexture(0, SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        context.getMatrices().translate(x, y, 100);
        context.getMatrices().translate(8.0, 8.0, 0.0);
        context.getMatrices().scale(1.0f, -1.0f, 1.0f);
        context.getMatrices().scale(16.0f, 16.0f, 16.0f);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        boolean bl = false;
        if (bl) {
            DiffuseLighting.disableGuiDepthLighting();
        }
        MinecraftClient.getInstance().getItemRenderer().renderItem(null, stack, ModelTransformationMode.GUI, false, context.getMatrices(), immediate, null, 0, OverlayTexture.DEFAULT_UV, 6);

        immediate.draw();
        if (bl) {
            DiffuseLighting.enableGuiDepthLighting();
        }
        context.getMatrices().pop();
    }
}
