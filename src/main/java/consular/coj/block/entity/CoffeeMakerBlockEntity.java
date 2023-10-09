package consular.coj.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.potion.PotionUtil;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import consular.coj.block.CoffeeMakerBlock;
import consular.coj.item.CoffeeItem;
import consular.coj.registry.ModBlockEntities;
import consular.coj.registry.ModItems;
import consular.coj.screen.CoffeeMakerScreenHandler;
import consular.coj.util.ImplementedInventory;

public class CoffeeMakerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT = 1;
    private static final int BEAN_SLOT = 0;
    private static final int MILK_SLOT = 2;
    private static final int POTION_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public CoffeeMakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COFFEE_MAKER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoffeeMakerBlockEntity.this.progress;
                    case 1 -> CoffeeMakerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CoffeeMakerBlockEntity.this.progress = value;
                    case 1 -> CoffeeMakerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Coffee Maker");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("coffee_maker.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("coffee_maker.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CoffeeMakerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        world.setBlockState(pos, state.with(CoffeeMakerBlock.LIT, !inventory.get(OUTPUT_SLOT).isEmpty()));

        if(!isOutputSlotEmptyOrReceivable()) {
            if(isReady()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    System.out.println("All done");
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private boolean isReady(){
        return (this.inventory.get(0).getItem() == ModItems.COFFEE_BEAN || this.inventory.get(0).getItem() == ModItems.GROUND_COFFEE_BEANS) && (this.inventory.get(1).getItem() == ModItems.MUG_OF_WATER || (this.inventory.get(1).getItem() instanceof CoffeeItem && this.inventory.get(POTION_SLOT).getItem() == Items.POTION));
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        ItemConvertible coffee = getResult();
        ItemStack coffeeStack = coffee.asItem().getDefaultStack();
        if (inventory.get(POTION_SLOT).getItem() == Items.POTION){
            PotionUtil.setPotion(coffeeStack, PotionUtil.getPotion(inventory.get(POTION_SLOT)));
            PotionUtil.setCustomPotionEffects(coffeeStack, PotionUtil.getCustomPotionEffects(inventory.get(POTION_SLOT)));
            setStack(POTION_SLOT, Items.GLASS_BOTTLE.getDefaultStack());
        }
        this.setStack(OUTPUT_SLOT, coffeeStack);
        this.inventory.get(BEAN_SLOT).decrement(1);
        if (inventory.get(MILK_SLOT).getItem() == Items.MILK_BUCKET)
            setStack(MILK_SLOT, Items.BUCKET.getDefaultStack());
    }

    private Item getResult(){
        if (inventory.get(OUTPUT_SLOT).getItem() == ModItems.MUG_OF_WATER){
            if (inventory.get(BEAN_SLOT).getItem() == ModItems.COFFEE_BEAN){
                if (inventory.get(MILK_SLOT).getItem() == Items.MILK_BUCKET)
                    return ModItems.CAFÉ_AU_LAIT;
                else
                    return ModItems.BLACK_COFFEE;
            } else {
                if (inventory.get(MILK_SLOT).getItem() == Items.MILK_BUCKET)
                    return ModItems.LATTE;
                else
                    return ModItems.ESPRESSO;
            }
        }
        if (inventory.get(POTION_SLOT).getItem() == Items.POTION){
            return inventory.get(OUTPUT_SLOT).getItem();
        }
        return null;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    //private boolean canInsertItemIntoOutputSlot(Item item) {
    //    return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    //}

    //private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
    //    return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    //}

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty();
    }
}
