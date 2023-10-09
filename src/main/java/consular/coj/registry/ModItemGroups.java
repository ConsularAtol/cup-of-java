package consular.coj.registry;

import consular.coj.CupOfJava;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    private static final RegistryKey<ItemGroup> CUP_OF_JAVA = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(CupOfJava.MODID, "coj"));

    private static void register(){
        Registry.register(Registries.ITEM_GROUP, CUP_OF_JAVA, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.LATTE))
            .displayName(Text.translatable("itemGroup.coj"))
            .build());
    }

    public static void registerItemGroups(){
        register();
        ItemGroupEvents.modifyEntriesEvent(CUP_OF_JAVA).register(content -> {
            content.add(ModBlocks.COFFEE_MAKER);
            content.add(ModItems.COFFEE_BEAN);
            content.add(ModItems.GROUND_COFFEE_BEANS);
            content.add(ModItems.MUG);
            content.add(ModItems.MUG_OF_WATER);
            content.add(ModItems.BLACK_COFFEE);
            content.add(ModItems.CAFÉ_AU_LAIT);
            content.add(ModItems.ESPRESSO);
            content.add(ModItems.LATTE);
        });
    }
}
