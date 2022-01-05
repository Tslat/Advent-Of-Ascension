package net.tslat.aoa3.object.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Gravitator extends Item {
	public Gravitator() {
		super(new Item.Properties().tab(AoAItemGroups.MISC_ITEMS).durability(1500));
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (isSelected && entity instanceof LivingEntity) {
			if (entity.getDeltaMovement().y() < -0.079) {
				entity.setDeltaMovement(entity.getDeltaMovement().multiply(1, 0.8f, 1));
				entity.fallDistance *= 0.8f;

				if (RandomUtil.oneInNChance(15) && (!(entity instanceof PlayerEntity) || !((PlayerEntity)entity).isCreative()))
					ItemUtil.damageItem(stack, (LivingEntity)entity, 1, EquipmentSlotType.MAINHAND);
			}

			if (!world.isClientSide) {
				if (WorldUtil.isWorld(world, AoADimensions.HAVEN.key) && !entity.onGround && world.getGameTime() % 5 == 0 && entity instanceof PlayerEntity) {
					PlayerEntity pl = (PlayerEntity)entity;

					if (pl.isCreative())
						return;

					BlockPos.Mutable pos = new BlockPos.Mutable(entity.blockPosition().getX(), entity.blockPosition().getY(), entity.blockPosition().getZ());

					for (ItemStack invStack : pl.inventory.items) {
						if (invStack.getItem() == AoAItems.BLANK_REALMSTONE.get()) {
							for (int i = 0; i < entity.getY(); i++) {
								if (!world.isEmptyBlock(pos.set(pos.getX(), pos.getY() - i, pos.getZ())))
									return;
							}

							invStack.shrink(1);
							ItemUtil.givePlayerItemOrDrop(pl, new ItemStack(AoAItems.LUNALUS_REALMSTONE.get()));

							break;
						}
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
