package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.blaster.ArcwormShotEntity;
import net.tslat.aoa3.content.item.misc.SequenceVerifiedItem;
import org.jetbrains.annotations.Nullable;

public class ExperimentW801 extends AoABlaster<WeaponProjectile> implements SequenceVerifiedItem {
	public ExperimentW801(Item.Properties properties) {
		super(properties);
	}

	@Override
	public String getSequenceName() {
		return "alien_orb";
	}

	@Override
	public WeaponFiringContext.Builder createFiringContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return super.createFiringContext(stack, shooter, hand).lifespan(120);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBlasterProjectile(level, context, ArcwormShotEntity::new);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);

			if (entity instanceof ServerPlayer pl)
				pl.getInventory().setItem(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.discard();
		}

		return false;
	}
}
