package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Gravitator extends SimpleItem {
	public Gravitator() {
		super("Gravitator", "gravitator");
		setMaxDamage(1500);
		setMaxStackSize(1);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity holder, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, holder, itemSlot, isSelected);

		if (isSelected && holder instanceof EntityLivingBase) {
			if (holder.motionY < -0.079) {
				holder.motionY *= 0.8f;
				holder.fallDistance = -0.5f;

				if (itemRand.nextInt(15) == 0 && (!(holder instanceof EntityPlayer) || !((EntityPlayer)holder).capabilities.isCreativeMode))
					stack.damageItem(1, (EntityLivingBase)holder);
			}

			if (!world.isRemote) {
				((EntityLivingBase)holder).addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, -1, 5, true, false));

				if (world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.haven && !holder.onGround && world.getTotalWorldTime() % 5 == 0 && holder instanceof EntityPlayer) {
					EntityPlayer pl = (EntityPlayer)holder;

					if (pl.isCreative())
						return;

					BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(holder.getPosition());

					for (ItemStack invStack : pl.inventory.mainInventory) {
						if (invStack.getItem() == ItemRegister.BLANK_REALMSTONE) {
							for (int i = 0; i < holder.posY; i++) {
								if (!world.isAirBlock(pos.setPos(pos.getX(), pos.getY() - i, pos.getZ())))
									return;
							}

							invStack.shrink(1);
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(ItemRegister.LUNALUS_REALMSTONE));

							break;
						}
					}
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Gravitator.desc.1", Enums.ItemDescriptionType.UNIQUE));
	}
}
