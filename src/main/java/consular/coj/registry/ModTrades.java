package consular.coj.registry;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.village.TradeOffer;

public class ModTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(ModItems.MUG, 2),
                            24, 2, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.COFFEE_BEAN, 1),
                            24, 3, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 4),
                            new ItemStack(ModItems.GROUND_COFFEE_BEANS, 1),
                            54, 4, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.BLACK_COFFEE, 1),
                            6, 6, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.CAFÉ_AU_LAIT, 1),
                            6, 6, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(ModItems.ESPRESSO, 1),
                            6, 8, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(ModItems.LATTE, 1),
                            6, 8, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            PotionUtil.setPotion(new ItemStack(ModItems.CAFÉ_AU_LAIT), getRandomPotion()),
                            6, 12, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            PotionUtil.setPotion(new ItemStack(ModItems.BLACK_COFFEE), getRandomPotion()),
                            6, 12, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            PotionUtil.setPotion(new ItemStack(ModItems.LATTE), getRandomPotion()),
                            6, 12, 0.075f));
                });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.COFFEE_MAKER, 5,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            PotionUtil.setPotion(new ItemStack(ModItems.ESPRESSO), getRandomPotion()),
                            6, 12, 0.075f));
                });
    }

    public static Potion getRandomPotion() {
        Iterable<Potion> potions = Registries.POTION;
        
        List<Potion> potionList = Lists.newArrayList(potions);
        for (int i = 0; i < potionList.size(); i++){
                if (potionList.get(i) == Potions.EMPTY || potionList.get(i) == Potions.WATER || potionList.get(i) == Potions.MUNDANE || potionList.get(i) == Potions.THICK || potionList.get(i) == Potions.AWKWARD){
                        potionList.remove(i);
                        i--;
                }
        }
        
        int randomIndex = new Random().nextInt(potionList.size());
        
        return potionList.get(randomIndex);
    }
}
