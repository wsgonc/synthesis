package net.wes.synthesis;

import net.fabricmc.api.ModInitializer;

import net.wes.synthesis.block.ModBlocks;
import net.wes.synthesis.block.entity.ModBlockEntities;
import net.wes.synthesis.item.ModItemGroups;
import net.wes.synthesis.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Synthesis implements ModInitializer {
	public static final String MOD_ID = "synthesis";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
	}
}