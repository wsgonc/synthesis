package net.wes.synthesis.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wes.synthesis.Synthesis;
import net.wes.synthesis.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SYNTHESIS_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Synthesis.MOD_ID, "synthesis_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.KERNITE_ORE))
                    .displayName(Text.translatable("itemgroup.synthesis.synthesis_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.KERNITE_ORE);
                        entries.add(ModBlocks.KILN);
                    }).build());

    public static final ItemGroup SYNTHESIS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Synthesis.MOD_ID, "synthesis_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.TEST_TUBE))
                    .displayName(Text.translatable("itemgroup.synthesis.synthesis_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TEST_TUBE);
                        entries.add(ModItems.MORTAR_AND_PESTLE);
                        entries.add(ModItems.KERNITE);
                        entries.add(ModItems.SILICA_DUST);
                        entries.add(ModItems.KERNITE_DUST);
                    }).build());

    public static void registerItemGroups() {
        Synthesis.LOGGER.info("Registering mod items for " + Synthesis.MOD_ID);
    }
}
