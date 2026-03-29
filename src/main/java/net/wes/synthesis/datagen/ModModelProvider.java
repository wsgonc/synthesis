package net.wes.synthesis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.wes.synthesis.block.ModBlocks;
import net.wes.synthesis.block.custom.KilnBlock;
import net.wes.synthesis.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static VariantSettings.Rotation getRotation(Direction direction) {
        return switch (direction) {
            case NORTH -> VariantSettings.Rotation.R0;
            case EAST -> VariantSettings.Rotation.R90;
            case SOUTH -> VariantSettings.Rotation.R180;
            case WEST -> VariantSettings.Rotation.R270;
            default -> VariantSettings.Rotation.R0;
        };
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.KILN)
                        .coordinate(
                                BlockStateVariantMap.create(KilnBlock.FACING, KilnBlock.LIT)
                                        .register((facing, lit) -> {
                                            Identifier model = lit
                                                    ? Identifier.of("synthesis", "block/kiln_on")
                                                    : Identifier.of("synthesis", "block/kiln");

                                            return BlockStateVariant.create()
                                                    .put(VariantSettings.MODEL, model)
                                                    .put(VariantSettings.Y, getRotation(facing));
                                        })
                        )
        );

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.KERNITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.KERNITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KERNITE_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILICA_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.MORTAR_AND_PESTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TEST_TUBE, Models.GENERATED);
    }
}
