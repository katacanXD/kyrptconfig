package net.kyrptonaught.kyrptconfig.example;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.kyrptonaught.kyrptconfig.config.screen.ConfigScreen;
import net.kyrptonaught.kyrptconfig.config.screen.ConfigSection;
import net.kyrptonaught.kyrptconfig.config.screen.items.BooleanItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.ButtonItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.KeybindItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.TextItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.BlockIconList;
import net.kyrptonaught.kyrptconfig.config.screen.items.lists.StringList;
import net.kyrptonaught.kyrptconfig.config.screen.items.number.FloatItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.number.IntegerItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ExampleConfig implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return (screen) -> {
            if (!FabricLoader.getInstance().isDevelopmentEnvironment())
                return null;

            // regex replace (\.setSaveConsumer\()([\S\h])*val\) & (options\.)([^\,])+
            ConfigScreen configScreen = new ConfigScreen(screen, Text.translatable("key.kyrptconfig.exampleconfig"));

            ConfigSection displaySection = new ConfigSection(configScreen, Text.translatable("Display"));
            displaySection.addConfigItem(new BooleanItem(Text.translatable("key.kyrptconfig.exampleconfig.displaysort"), true, true));
            displaySection.addConfigItem(new IntegerItem(Text.translatable("key.diggusmaximus.config.maxmine"), 40, 40).setMinMax(1, 2048));
            displaySection.addConfigItem(new KeybindItem(Text.translatable("key.inventorysorter.sort"), "key.keyboard.l", "key.keyboard.p"));

            FloatItem armorHudScale = (FloatItem) displaySection.addConfigItem(new FloatItem(Text.translatable("key.lemclienthelper.clientgui.armourscale"), 1f, 1f));
            armorHudScale.setMinMax(1f, 4f);
            armorHudScale.setSaveConsumer(System.out::println);
            armorHudScale.setToolTipWithNewLine("key.lemclienthelper.clientgui.armourscale.tooltip");

            for (int i = 0; i < 3; i++)
                displaySection.addConfigItem(new BooleanItem(Text.translatable("key.kyrptconfig.exampleconfig.displaytooltip"), true, true));

            ConfigSection blackListSection = new ConfigSection(configScreen, Text.translatable("Blacklist"));

            blackListSection.addConfigItem(new BooleanItem(Text.translatable("key.kyrptconfig.exampleconfig.showdebug"), true, false).setToolTipWithNewLine("key.kyrptconfig.exampleconfig.debugtooltip"));

            String DOWNLOAD_URL = "https://raw.githubusercontent.com/kyrptonaught/Inventory-Sorter/1.19/DownloadableBlacklist.json5";
            TextItem blackListURL = new TextItem(Text.translatable("key.kyrptconfig.exampleconfig.blacklistURL"), DOWNLOAD_URL, DOWNLOAD_URL).setMaxLength(1024);
            blackListSection.addConfigItem(blackListURL);

            StringList hideList = (StringList) new StringList(Text.translatable("key.kyrptconfig.exampleconfig.hidesort"), new ArrayList<>(), new ArrayList<>()).setToolTipWithNewLine("key.kyrptconfig.exampleconfig.hidetooltip");

            blackListSection.addConfigItem(new ButtonItem(Text.translatable("key.kyrptconfig.exampleconfig.downloadListButton")).setClickEvent(() -> {
                SystemToast.add(MinecraftClient.getInstance().getToastManager(), SystemToast.Type.NARRATOR_TOGGLE, Text.translatable("key.inventorysorter.toast.error"), Text.translatable("key.inventorysorter.toast.error2"));
            }));

            blackListSection.addConfigItem(hideList);

            blackListSection.addConfigItem(new BlockIconList(Text.translatable("key.diggusmaximus.config.blacklist"), new ArrayList<>(), new ArrayList<>(), true));

            new ConfigSection(configScreen, Text.literal("Tab Test 1"));
            new ConfigSection(configScreen, Text.literal("Tab Test 2"));
            new ConfigSection(configScreen, Text.literal("Tab Test 3"));
            new ConfigSection(configScreen, Text.literal("Tab Test 4"));
            new ConfigSection(configScreen, Text.literal("Tab Test 5"));
            new ConfigSection(configScreen, Text.literal("Tab Test 6"));
            return configScreen;
        };
    }
}
