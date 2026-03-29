package net.wes.synthesis.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.wes.synthesis.Synthesis;
import net.wes.synthesis.item.custom.KerniteItem;
import net.wes.synthesis.item.custom.MortarItem;
import net.wes.synthesis.item.custom.SilicaItem;

public class ModItems {
    public static final Item TEST_TUBE = registerItem("test_tube", new Item(new Item.Settings()));
    public static final Item MORTAR_AND_PESTLE = registerItem("mortar_and_pestle", new MortarItem(new Item.Settings().maxCount(1).maxDamage(64)));
    public static final Item KERNITE = registerItem("kernite", new KerniteItem(new Item.Settings()));
    public static final Item KERNITE_DUST = registerItem("kernite_dust", new KerniteItem(new Item.Settings()));
    public static final Item SILICA_DUST = registerItem("silica_dust", new SilicaItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Synthesis.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Synthesis.LOGGER.info("Registering mod items for " + Synthesis.MOD_ID);
    }
}

