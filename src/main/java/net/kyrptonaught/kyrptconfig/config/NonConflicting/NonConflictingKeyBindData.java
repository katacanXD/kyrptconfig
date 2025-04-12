package net.kyrptonaught.kyrptconfig.config.NonConflicting;

import net.minecraft.client.util.InputUtil;

import java.util.function.Consumer;

@Deprecated
public class NonConflictingKeyBindData {
    public String name, category;
    public InputUtil.Type inputType;
    public int keyCode;
    public Consumer<InputUtil.Key> keySetEvent;
    public String defaultKey;

    @Deprecated
    public NonConflictingKeyBindData(String Name, String Category, InputUtil.Type type, int KeyCode, Consumer<InputUtil.Key> keySetEvent) {
        this.name = Name;
        this.category = Category;
        this.inputType = type;
        this.keyCode = KeyCode;
        this.keySetEvent = keySetEvent;
    }

    @Deprecated
    public NonConflictingKeyBindData(String Name, String Category, InputUtil.Key boundKey, String defaultKey, Consumer<InputUtil.Key> keySetEvent) {
        this(Name, Category, boundKey.getCategory(), boundKey.getCode(), keySetEvent);
        this.defaultKey = defaultKey;
    }

    @Deprecated
    public InputUtil.Key getDefaultKey() {
        if (defaultKey == null || defaultKey.isEmpty())
            return InputUtil.UNKNOWN_KEY;
        try {
            return InputUtil.fromTranslationKey(defaultKey);
        } catch (IllegalArgumentException e) {
            return InputUtil.UNKNOWN_KEY;
        }
    }
}
