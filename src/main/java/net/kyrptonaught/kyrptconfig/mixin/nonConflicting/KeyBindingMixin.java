package net.kyrptonaught.kyrptconfig.mixin.nonConflicting;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.kyrptonaught.kyrptconfig.config.NonConflicting.NonConflictingKeyBinding;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @WrapWithCondition(method = "<init>(Ljava/lang/String;Lnet/minecraft/client/util/InputUtil$Type;ILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    public <K, V> boolean dontRegister(Map<K, V> instance, K k, V v) {
        if (((Object) this instanceof NonConflictingKeyBinding))
            return false;

        return true;
    }
}
