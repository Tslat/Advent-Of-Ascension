package net.tslat.aoa3.content.entity.npc.banker;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.container.BankerContainer;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class PrimordialBankerEntity extends AoABanker {
	public PrimordialBankerEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.73125f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !WorldUtil.isWorld(level, AoADimensions.DUSTOPIA.key);
	}

	@Override
	protected void openScreen(Player player) {
		NetworkHooks.openScreen((ServerPlayer)player, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return PrimordialBankerEntity.this.getDisplayName();
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int screenId, Inventory inv, Player player) {
				return new BankerContainer(screenId, player.getInventory(), PrimordialBankerEntity.this);
			}
		}, buffer -> buffer.writeInt(getId()));
	}
}
