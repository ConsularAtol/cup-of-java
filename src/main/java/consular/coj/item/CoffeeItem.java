package consular.coj.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import consular.coj.registry.ModEffects;
import consular.coj.registry.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class CoffeeItem extends Item {

    private String type;

    public CoffeeItem(Settings settings, String type) {
        super(settings);
        this.type = type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

   @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (!world.isClient) {
            List<StatusEffectInstance> list = PotionUtil.getPotionEffects(stack);
            for (StatusEffectInstance statusEffectInstance : list) {
                if (statusEffectInstance.getEffectType().isInstant()) {
                    statusEffectInstance.getEffectType().applyInstantEffect(user, user, user, statusEffectInstance.getAmplifier(), 1.0);
                    continue;
                }
                user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
            }
            if (shouldCaffinate())
                user.addStatusEffect(new StatusEffectInstance(ModEffects.CAFFINATED, 180 * 20, 0));
        }
        if (stack.isEmpty()) {
            return new ItemStack(ModItems.MUG);
        }
        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(ModItems.MUG);
            PlayerEntity playerEntity = (PlayerEntity)user;
            if (!playerEntity.getInventory().insertStack(itemStack)) {
                playerEntity.dropItem(itemStack, false);
            }
        }
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    private boolean shouldCaffinate(){
        return type == "espresso" || type == "latte";
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Formatting formatting = Formatting.GRAY;
        if (shouldCaffinate())
            formatting = Formatting.GOLD;
        tooltip.add(Text.translatable("coj.coffee." + type).formatted(formatting));
        PotionUtil.buildTooltip(stack, tooltip, 1.0f);
    }

    @Override
    public String getTranslationKey() {
        return "item.coj.coffee";
    }
}
