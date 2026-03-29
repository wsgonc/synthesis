package net.wes.synthesis.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.wes.synthesis.block.ModBlocks;
import net.wes.synthesis.item.ModItems;
import net.minecraft.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> KERNITE_SMELTABLES = List.of(ModBlocks.KERNITE_ORE);

        offerSmelting(exporter, KERNITE_SMELTABLES, RecipeCategory.MISC, ModItems.KERNITE, 0.25f, 200, "kernite");
        offerBlasting(exporter, KERNITE_SMELTABLES, RecipeCategory.MISC, ModItems.KERNITE, 0.25f, 100, "kernite");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.KERNITE_DUST)
                .pattern("$")
                .pattern("#")
                .input('$', ModItems.KERNITE)
                .input('#', ModItems.MORTAR_AND_PESTLE)
                .criterion(hasItem(ModItems.MORTAR_AND_PESTLE), conditionsFromItem(ModItems.MORTAR_AND_PESTLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SILICA_DUST)
                .pattern("$")
                .pattern("#")
                .input('$', Items.SAND)
                .input('#', ModItems.MORTAR_AND_PESTLE)
                .criterion(hasItem(ModItems.MORTAR_AND_PESTLE), conditionsFromItem(ModItems.MORTAR_AND_PESTLE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MORTAR_AND_PESTLE)
                .pattern("#$#")
                .pattern("###")
                .input('#', Items.COBBLED_DEEPSLATE)
                .input('$', Items.IRON_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);

    }
}
