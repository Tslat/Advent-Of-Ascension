package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseStaff<T> extends Item implements EnergyProjectileWeapon {
	protected final HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>(2);

	public BaseStaff(int durability) {
		super(new Item.Properties().group(AoAItemGroups.STAVES).maxDamage(durability));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand == Hand.OFF_HAND) {
			Item mainItem = player.getHeldItem(Hand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseGun || mainItem instanceof BaseBlaster)
				return ActionResult.resultFail(stack);
		}

		if (player instanceof ServerPlayerEntity) {
			T preconditionResult = checkPreconditions(player, stack);

			if (preconditionResult == null)
				return ActionResult.resultFail(stack);

			if (!findAndConsumeRunes(getRunes(), (ServerPlayerEntity)player, true, stack))
				return ActionResult.resultFail(stack);

			if (getCastingSound() != null)
				world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), getCastingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			player.getCooldownTracker().setCooldown(this, 12);
			player.addStat(Stats.ITEM_USED.get(this));
			ItemUtil.damageItem(stack, player, hand);
			cast(world, stack, player, preconditionResult);
		}

		return ActionResult.resultSuccess(stack);
	}

	public boolean findAndConsumeRunes(HashMap<RuneItem, Integer> runes, ServerPlayerEntity player, boolean allowBuffs, ItemStack staff) {
		return ItemUtil.findAndConsumeRunes(runes, player, allowBuffs, staff);
	}

	@Nullable
	public T checkPreconditions(LivingEntity caster, ItemStack staff) {
		return (T)new Object();
	}

	public HashMap<RuneItem, Integer> getRunes() {
		if (runes.isEmpty())
			populateRunes(runes);

		return runes;
	}

	@Nullable
	public SoundEvent getCastingSound() {
		return null;
	}

	protected abstract void populateRunes(HashMap<RuneItem, Integer> runes);

	public abstract void cast(World world, ItemStack staff, LivingEntity caster, T args);

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, LivingEntity shooter) {}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {return true;}

	@Override
	public Hand getWeaponHand(LivingEntity holder) {
		return holder.getHeldItemMainhand().getItem() == this || holder.getHeldItemOffhand().getItem() != this ? Hand.MAIN_HAND : Hand.OFF_HAND;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	public float getDmg() {
		return 0;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (getDmg() > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.magic", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Float.toString(getDmg())));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.staff.runesRequired", LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));

		for (Map.Entry<RuneItem, Integer> runeEntry : getRunes().entrySet()) {
			tooltip.add(LocaleUtil.getLocaleMessage("items.description.staff.runesRequired.specific", TextFormatting.WHITE, Integer.toString(runeEntry.getValue()), LocaleUtil.getItemName(runeEntry.getKey())));
		}
	}
}
