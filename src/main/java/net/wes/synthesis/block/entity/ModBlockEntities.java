package net.wes.synthesis.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wes.synthesis.Synthesis;
import net.wes.synthesis.block.ModBlocks;
import net.wes.synthesis.block.entity.custom.KilnBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<KilnBlockEntity> KILN_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Synthesis.MOD_ID, "kiln_be"),
                    BlockEntityType.Builder.create(KilnBlockEntity::new, ModBlocks.KILN).build(null));

    public static void registerBlockEntities() {
        Synthesis.LOGGER.info("Registering mod blocks for " + Synthesis.MOD_ID);
    }
}
