package consular.coj.world.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import consular.coj.CupOfJava;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> PATCH_COFFEE_COMMON = registerKey("patch_coffee_common");
    public static final RegistryKey<PlacedFeature> PATCH_COFFEE_RARE = registerKey("patch_coffee_rare");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry.Reference<ConfiguredFeature<?, ?>> registryEntry12 = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_COFFEE_PLANT);
        PlacedFeatures.register(context, PATCH_COFFEE_COMMON, registryEntry12, RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(context, PATCH_COFFEE_RARE, registryEntry12, RarityFilterPlacementModifier.of(384), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(CupOfJava.MODID, name));
    }

    //private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
    //                             List<PlacementModifier> modifiers) {
    //    context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    //}

    //private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
    //    register(context, key, configuration, List.of(modifiers));
    //}
}

