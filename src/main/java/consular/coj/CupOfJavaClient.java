package consular.coj;

import consular.coj.registry.ModBlocks;
import consular.coj.registry.ModScreenHandlers;
import consular.coj.screen.CoffeeMakerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class CupOfJavaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.COFFEE_MAKER_SCREEN_HANDLER, CoffeeMakerScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COFFEE_PLANT, RenderLayer.getCutout());
    }
}