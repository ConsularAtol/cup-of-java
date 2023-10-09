package consular.coj.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;

public class CaffinatedEffect extends StatusEffect {
  public CaffinatedEffect() {
    super(StatusEffectCategory.BENEFICIAL,0x98D982);
  }
 
  @Override
  public boolean canApplyUpdateEffect(int duration, int amplifier) {
    return true;
  }
 
  @Override
  public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    if (entity instanceof ServerPlayerEntity) {
      ServerPlayerEntity serverPlayer = (ServerPlayerEntity)entity;
      serverPlayer.increaseStat(Stats.TIME_SINCE_REST, -1); // Essentially freezes insomnia ticks
    }
  }
}