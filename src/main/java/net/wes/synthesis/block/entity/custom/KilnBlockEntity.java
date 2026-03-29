package net.wes.synthesis.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.wes.synthesis.block.ModBlocks;
import net.wes.synthesis.block.entity.ImplementedInventory;
import net.wes.synthesis.block.entity.ModBlockEntities;
import net.wes.synthesis.item.ModItems;
import net.wes.synthesis.screen.custom.KilnScreenHandler;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.windows.INPUT;

public class KilnBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int FUEL_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 300;

    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KILN_BE, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> KilnBlockEntity.this.progress;
                    case 1 -> KilnBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: KilnBlockEntity.this.progress = value;
                    case 1: KilnBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size () {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.synthesis.kiln");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KilnScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = 300;
    }

    private void craftItem() {
        ItemStack output = new ItemStack(ModItems.KERNITE_DUST, 6);
        this.removeStack(INPUT_SLOT_1, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(), this.getStack(OUTPUT_SLOT).getCount() + output.getCount()));
    }


    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Item input = ModItems.KERNITE;
        ItemStack output = new ItemStack(ModItems.KERNITE_DUST, 1);

        return this.getStack(INPUT_SLOT_1).isOf(input) &&
                canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);

    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("kiln.progress", progress);
        nbt.putInt("kiln.maxProgress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        progress = nbt.getInt("kiln.progress");
        maxProgress = nbt.getInt("kiln.maxProgress");
        super.readNbt(nbt, registryLookup);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
