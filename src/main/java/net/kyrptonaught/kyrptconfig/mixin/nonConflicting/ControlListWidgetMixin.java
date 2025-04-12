package net.kyrptonaught.kyrptconfig.mixin.nonConflicting;

import net.kyrptonaught.kyrptconfig.config.NonConflicting.NonConflictingKeyBinding;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ControlsListWidget.KeyBindingEntry.class, priority = -1)
public class ControlListWidgetMixin {

    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;equals(Lnet/minecraft/client/option/KeyBinding;)Z"), require = 0)
    public boolean dontConflict(KeyBinding instance, KeyBinding other) {
        if (instance instanceof NonConflictingKeyBinding || other instanceof NonConflictingKeyBinding)
            return false;
        return instance.equals(other);
    }
}
