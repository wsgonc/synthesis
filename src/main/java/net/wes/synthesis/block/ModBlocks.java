package net.wes.synthesis.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.wes.synthesis.Synthesis;
import net.wes.synthesis.block.custom.KilnBlock;

public class ModBlocks {

    public static final Block KERNITE_ORE = registerBlock("kernite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.STONE)));

    public static final Block KILN = registerBlock("kiln", new KilnBlock(
            AbstractBlock.Settings.create().strength(3f).requiresTool().sounds(BlockSoundGroup.STONE).nonOpaque()));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Synthesis.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Synthesis.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Synthesis.LOGGER.info("Registering mod blocks for " + Synthesis.MOD_ID);
    }
}
