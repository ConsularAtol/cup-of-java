package consular.coj.registry;

import consular.coj.CupOfJava;
import consular.coj.effect.CaffinatedEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect CAFFINATED = new CaffinatedEffect();

    public static void registerEffects(){
        Registry.register(Registries.STATUS_EFFECT, new Identifier(CupOfJava.MODID, "caffinated"), CAFFINATED);
    }
}
