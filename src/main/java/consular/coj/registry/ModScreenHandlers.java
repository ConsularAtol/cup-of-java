package consular.coj.registry;

import consular.coj.CupOfJava;
import consular.coj.screen.CoffeeMakerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CoffeeMakerScreenHandler> COFFEE_MAKER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(CupOfJava.MODID, "coffee_maker"),
                    new ExtendedScreenHandlerType<>(CoffeeMakerScreenHandler::new));

    public static void registerScreenHandlers() {
        CupOfJava.LOGGER.info("Registering Screen Handlers for " + CupOfJava.MODID);
    }
}
