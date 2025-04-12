package net.kyrptonaught.kyrptconfig.config.NonConflicting;

import net.kyrptonaught.kyrptconfig.keybinding.CustomKeyBinding;
import net.kyrptonaught.kyrptconfig.keybinding.DisplayOnlyKeyBind;
import net.minecraft.client.util.InputUtil;

import java.util.function.Consumer;

public class NonConflictingKeyBinding extends DisplayOnlyKeyBind {

    public NonConflictingKeyBinding(String name, InputUtil.Type type, int code, String category) {
        super(name, type, code, category);
    }

    public NonConflictingKeyBinding(String translationKey, String category, CustomKeyBinding customKeyBinding, Consumer<InputUtil.Key> keySet) {
        super(translationKey, category, customKeyBinding, keySet);
    }
}