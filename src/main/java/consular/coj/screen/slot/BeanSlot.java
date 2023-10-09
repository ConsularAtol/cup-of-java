package consular.coj.screen.slot;

import consular.coj.registry.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BeanSlot extends Slot{

    public BeanSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean canInsert(ItemStack stack) {
        return stack.getItem() == ModItems.COFFEE_BEAN || stack.getItem() == ModItems.GROUND_COFFEE_BEANS;
    }
    
}
