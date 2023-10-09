package consular.coj.registry;

import consular.coj.world.gen.ModWorldGeneration;

public class ModRegistries {
    public static void registerAll(){
        ModItems.registerItems();
        ModEffects.registerEffects();
        ModBlocks.registerModBlocks();
        ModWorldGeneration.generateWorldGen();
        ModScreenHandlers.registerScreenHandlers();
        ModBlockEntities.registerBlockEntities();
        ModItemGroups.registerItemGroups();
        ModVillagers.registerVillagers();
        ModTrades.registerCustomTrades();
    }
}
