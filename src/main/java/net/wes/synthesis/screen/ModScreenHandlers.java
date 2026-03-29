package net.wes.synthesis.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.wes.synthesis.Synthesis;
import net.wes.synthesis.screen.custom.KilnScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Synthesis.MOD_ID, "kiln_screen_handler"),
                    new ExtendedScreenHandlerType<>(KilnScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        Synthesis.LOGGER.info("Registering Screen Handlers for " + Synthesis.MOD_ID);
    }
}
