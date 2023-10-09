package consular.coj.registry;

import com.google.common.collect.ImmutableSet;

import consular.coj.CupOfJava;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final RegistryKey<PointOfInterestType> COFFEE_POI_KEY = poiKey("coffeepoi");
    public static final PointOfInterestType COFFEE_POI = registerPoi("coffeepoi", ModBlocks.COFFEE_MAKER);

    public static final VillagerProfession COFFEE_MAKER = registerProfession("coffee_maker", COFFEE_POI_KEY);


    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(CupOfJava.MODID, name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_CLERIC));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(CupOfJava.MODID, name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(CupOfJava.MODID, name));
    }

    public static void registerVillagers() {
        CupOfJava.LOGGER.info("Registering Villagers " + CupOfJava.MODID);
    }
}
