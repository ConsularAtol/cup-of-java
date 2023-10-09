package consular.coj.world.gen;

import consular.coj.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class CoffeePlantGeneration {
    public static void generateCoffeePlant(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST), GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.PATCH_COFFEE_COMMON);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST), GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.PATCH_COFFEE_RARE);
    }
}
