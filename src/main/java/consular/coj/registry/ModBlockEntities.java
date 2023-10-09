package consular.coj.registry;

import consular.coj.CupOfJava;
import consular.coj.block.entity.CoffeeMakerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CoffeeMakerBlockEntity> COFFEE_MAKER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(CupOfJava.MODID, "coffee_maker"),
                    FabricBlockEntityTypeBuilder.create(CoffeeMakerBlockEntity::new,
                            ModBlocks.COFFEE_MAKER).build());

    public static void registerBlockEntities() {
        CupOfJava.LOGGER.info("Registering Block Entities for " + CupOfJava.MODID);
    }
}
