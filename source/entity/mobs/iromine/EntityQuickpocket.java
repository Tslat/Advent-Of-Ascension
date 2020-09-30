package net.tslat.aoa3.entity.mobs.iromine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.item.misc.CoinItem;
import net.tslat.aoa3.item.misc.DimensionalTokensItem;

import javax.annotation.Nullable;

public class EntityQuickpocket extends AoAMeleeMob {
	public static final float entityWidth = 0.375f;

	public EntityQuickpocket(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 75;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_QUICKPOCKET_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_QUICKPOCKET_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_QUICKPOCKET_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityQuickpocket;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer && getRevengeTarget() == null) {
			EntityPlayer pl = (EntityPlayer)target;
			
			for (ItemStack stack : pl.inventory.mainInventory) {
				if (stack.getItem() instanceof CoinItem || stack.getItem() == ItemRegister.CIRCUS_COIN || stack.getItem() instanceof DimensionalTokensItem) {
					int dropAmount = rand.nextInt(stack.getCount() + 1);

					entityDropItem(new ItemStack(stack.getItem(), dropAmount), 0);
					world.playSound(null, target.posX, target.posY, target.posZ, SoundsRegister.MOB_QUICKPOCKET_STEAL, SoundCategory.HOSTILE, 1f, 1f);
					stack.shrink(dropAmount);
					pl.inventoryContainer.detectAndSendChanges();

					return;
				}
			}
		}
	}
}
