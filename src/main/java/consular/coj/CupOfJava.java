package consular.coj;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import consular.coj.registry.ModRegistries;

public class CupOfJava implements ModInitializer {
    public static String MODID = "coj";
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    @Override
    public void onInitialize() {
        ModRegistries.registerAll();
    }
}