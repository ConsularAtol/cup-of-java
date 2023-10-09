package consular.coj.registry;

import consular.coj.CupOfJava;
import consular.coj.item.CoffeeItem;
import consular.coj.item.MugItem;
import consular.coj.item.WaterMugItem;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MUG = registerItem("mug", new MugItem(new Item.Settings().maxCount(16)));

    public static final Item MUG_OF_WATER = registerItem("mug_of_water", new WaterMugItem(new Item.Settings().maxCount(1)));

    public static final Item COFFEE_BEAN = registerItem("coffee_bean", new AliasedBlockItem(ModBlocks.COFFEE_PLANT, new Item.Settings()));

    public static final Item GROUND_COFFEE_BEANS = registerItem("ground_coffee_beans", new Item(new Item.Settings()));

    public static final Item BLACK_COFFEE = registerCoffee("black_coffee", "black");

    public static final Item CAFÉ_AU_LAIT = registerCoffee("cafe_au_lait", "café_au_lait");

    public static final Item ESPRESSO = registerCoffee("espresso", "espresso");

    public static final Item LATTE = registerCoffee("latte", "latte");

    private static Item registerCoffee(String name, String type){
        return registerItem(name, new CoffeeItem(new Item.Settings().food(FoodComponents.HONEY_BOTTLE).maxCount(1), type));
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(CupOfJava.MODID, name), item);
    }

    public static void registerItems(){
        CupOfJava.LOGGER.info("Registering items");
    }
}
