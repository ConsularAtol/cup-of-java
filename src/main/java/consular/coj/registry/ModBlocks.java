package consular.coj.registry;

import consular.coj.CupOfJava;
import consular.coj.block.CoffeeMakerBlock;
import consular.coj.block.CoffeePlantBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block COFFEE_PLANT = registerBlockWithoutItem("coffee_plant", new CoffeePlantBlock(FabricBlockSettings.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block COFFEE_MAKER = registerBlock("coffee_maker", new CoffeeMakerBlock(FabricBlockSettings.copy(Blocks.BREWING_STAND)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(CupOfJava.MODID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(CupOfJava.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(CupOfJava.MODID, name), 
            new BlockItem(block, new FabricItemSettings()));
    }
    
    public static void registerModBlocks(){
        CupOfJava.LOGGER.info("Registering blocks");;
    }
}
