package consular.coj.screen.slot;

import consular.coj.item.CoffeeItem;
import consular.coj.registry.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MugSlot extends Slot{

    public MugSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean canInsert(ItemStack stack) {
        return stack.getItem() == ModItems.MUG_OF_WATER || stack.getItem() instanceof CoffeeItem;
    }
    
}
