package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.container.CorruptedTravellerContainer;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;

import javax.annotation.Nullable;

public class CorruptedTravellerEntity extends AoATrader {
	public CorruptedTravellerEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);

		setGlowing(AoAConfig.SERVER.easyCorruptedTravellers.get());
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected void openGui(PlayerEntity player) {
		NetworkHooks.openGui((ServerPlayerEntity)player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return CorruptedTravellerEntity.this.getDisplayName();
			}

			@Nullable
			@Override
			public Container createMenu(int screenId, PlayerInventory inv, PlayerEntity player) {
				return new CorruptedTravellerContainer(screenId, player.inventory, CorruptedTravellerEntity.this);
			}
		}, buffer -> buffer.writeInt(getEntityId()));
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {}
}
