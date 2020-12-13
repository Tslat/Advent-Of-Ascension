package net.tslat.aoa3.item.misc;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.mob.shyrelands.ArcwormEntity;
import net.tslat.aoa3.item.weapon.blaster.ExperimentW801;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ReservedItem extends Item {
	private final String sequenceId;

	public ReservedItem(String sequenceName) {
		super(new Item.Properties().rarity(Rarity.EPIC));

		this.sequenceId = sequenceName;
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);
			entityIn.replaceItemInInventory(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		if (context.getPlayer() == null)
			return super.onItemUseFirst(stack, context);

		Block block = context.getWorld().getBlockState(context.getPos()).getBlock();

		if (block == AoABlocks.CHARGING_TABLE.get()) {
			if (this == AoAItems.FLESHY_BONES.get()) {
				context.getPlayer().setHeldItem(context.getHand(), ((ReservedItem)AoAItems.DARK_BONES.get()).newValidStack());


				return ActionResultType.SUCCESS;
			}
		}
		else if (block == AoABlocks.MINERALIZATION_STATION.get()) {
			if (this == AoAItems.DARK_BONES.get()) {
				context.getPlayer().setHeldItem(context.getHand(), ((ReservedItem)AoAItems.ROCK_BONES.get()).newValidStack());


				return ActionResultType.SUCCESS;
			}
		}

		return super.onItemUseFirst(stack, context);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!target.world.isRemote) {
			if (stack.getItem() == AoAItems.MILLENNIUM_UPGRADER.get()) {
				if (target.isInLava() && attacker.isInLava()) {
					attacker.setHeldItem(Hand.MAIN_HAND, ((ReservedItem)AoAItems.MOLTEN_UPGRADER.get()).newValidStack());

					return true;
				}
			}
			else if (stack.getItem() == AoAItems.MOLTEN_UPGRADER.get()) {
				if (target.world.getCurrentMoonPhaseFactor() == 1 && target.getHealth() <= 0 &&
						(target.getType() == AoAEntities.Mobs.DARK_BEAST.get() ||
						target.getType() == AoAEntities.Mobs.IRKLING.get() ||
						target.getType() == AoAEntities.Mobs.NIGHT_WATCHER.get() ||
						target.getType() == AoAEntities.Mobs.SCRUBBY.get() ||
						target.getType() == AoAEntities.Mobs.SKELLOX.get())) {
					attacker.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
					target.entityDropItem(((ReservedItem)AoAItems.MOONSTONE.get()).newValidStack(), 0);

					return true;
				}
			}
			else if (stack.getItem() == AoAItems.MOONSTONE.get()) {
				if (target instanceof ArcwormEntity)
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.LEVITATION, 200).level(11).hideParticles());

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.remove();
		}

		return false;
	}

	public ItemStack newValidStack() {
		ItemStack stack = new ItemStack(this);
		CompoundNBT tag = new CompoundNBT();

		tag.putBoolean(sequenceId, true);
		stack.setTag(tag);

		return stack;
	}

	private boolean verifyStack(ItemStack stack) {
		if (stack.isEmpty())
			return false;

		if (!stack.hasTag())
			return false;

		CompoundNBT tag = stack.getTag();

		if (!tag.contains(sequenceId))
			return false;

		return tag.getBoolean(sequenceId);
	}

	public static void handleArcworm(ArcwormEntity arcworm) {
		if (arcworm.getPosY() > 275 && arcworm.getAttackingEntity() instanceof PlayerEntity) {
			if (arcworm.getAttackingEntity().getHeldItemMainhand().getItem() == AoAItems.MOONSTONE.get()) {
				arcworm.entityDropItem(((ExperimentW801)AoAWeapons.EXPERIMENT_W_801.get()).newValidStack(), 0);
				arcworm.getAttackingEntity().setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
				arcworm.remove();
			}
		}
	}

	public static void handlePlayerDeath(PlayerEntity pl) {
		if (pl.getHealth() > 0 && !pl.world.getWorldInfo().isHardcore())
			return;

		if (!pl.getHeldItemMainhand().getItem().getRegistryName().getPath().toLowerCase().startsWith("a"))
			return;

		NonNullList<ItemStack> armour = pl.inventory.armorInventory;

		if (!armour.get(3).getItem().getRegistryName().getPath().toLowerCase().startsWith("l"))
			return;

		if (!armour.get(2).getItem().getRegistryName().getPath().toLowerCase().startsWith("i"))
			return;

		if (!armour.get(1).getItem().getRegistryName().getPath().toLowerCase().startsWith("e"))
			return;

		if (!armour.get(0).getItem().getRegistryName().getPath().toLowerCase().startsWith("n"))
			return;

		if (!ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.ALIEN_ORB.get()), false, 1))
			return;

		pl.entityDropItem(((ReservedItem)AoAItems.ALIEN_ORB.get()).newValidStack(), 0);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (stack.getItem() == AoAItems.ALIEN_ORB.get())
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
