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
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.tslat.aoa3.common.registration.AoABlocks;
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
import java.util.Locale;

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
			entityIn.setSlot(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		if (context.getPlayer() == null)
			return super.onItemUseFirst(stack, context);

		Block block = context.getLevel().getBlockState(context.getClickedPos()).getBlock();

		if (block == AoABlocks.CHARGING_TABLE.get()) {
			if (this == AoAItems.FLESHY_BONES.get()) {
				context.getPlayer().setItemInHand(context.getHand(), ((ReservedItem)AoAItems.DARK_BONES.get()).newValidStack());


				return ActionResultType.SUCCESS;
			}
		}
		else if (block == AoABlocks.MINERALIZATION_STATION.get()) {
			if (this == AoAItems.DARK_BONES.get()) {
				context.getPlayer().setItemInHand(context.getHand(), ((ReservedItem)AoAItems.ROCK_BONES.get()).newValidStack());


				return ActionResultType.SUCCESS;
			}
		}

		return super.onItemUseFirst(stack, context);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!target.level.isClientSide) {
			if (stack.getItem() == AoAItems.MILLENNIUM_UPGRADER.get()) {
				if (target.isInLava() && attacker.isInLava()) {
					attacker.setItemInHand(Hand.MAIN_HAND, ((ReservedItem)AoAItems.MOLTEN_UPGRADER.get()).newValidStack());

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

	public static void handlePlayerToss(ItemTossEvent ev) {
		World world = ev.getEntityItem().level;

		if (!world.isClientSide()) {
			if (ev.getEntityItem().getItem().getItem() == AoAItems.MOLTEN_UPGRADER.get() && world.getMoonBrightness() == 1)
				ev.getEntityItem().setItem(((ReservedItem)AoAItems.MOONSTONE.get()).newValidStack());
		}
	}

	public static void handleArcworm(ArcwormEntity arcworm) {
		if (arcworm.getY() > 275 && arcworm.getKillCredit() instanceof PlayerEntity) {
			if (arcworm.getKillCredit().getMainHandItem().getItem() == AoAItems.MOONSTONE.get()) {
				arcworm.spawnAtLocation(((ExperimentW801)AoAWeapons.EXPERIMENT_W_801.get()).newValidStack(), 0);
				arcworm.getKillCredit().setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
				arcworm.remove();
			}
		}
	}

	public static void handlePlayerDeath(PlayerEntity pl) {
		if (pl.getHealth() > 0 && !pl.level.getLevelData().isHardcore())
			return;

		if (!pl.getMainHandItem().getItem().getRegistryName().getPath().toLowerCase(Locale.ENGLISH).startsWith("a"))
			return;

		NonNullList<ItemStack> armour = pl.inventory.armor;

		if (!armour.get(3).getItem().getRegistryName().getPath().toLowerCase(Locale.ENGLISH).startsWith("l"))
			return;

		if (!armour.get(2).getItem().getRegistryName().getPath().toLowerCase(Locale.ENGLISH).startsWith("i"))
			return;

		if (!armour.get(1).getItem().getRegistryName().getPath().toLowerCase(Locale.ENGLISH).startsWith("e"))
			return;

		if (!armour.get(0).getItem().getRegistryName().getPath().toLowerCase(Locale.ENGLISH).startsWith("n"))
			return;

		if (ItemUtil.findInventoryItem(pl, new ItemStack(AoAItems.ALIEN_ORB.get()), false, 1))
			return;

		pl.spawnAtLocation(((ReservedItem)AoAItems.ALIEN_ORB.get()).newValidStack(), 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (stack.getItem() == AoAItems.ALIEN_ORB.get())
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
