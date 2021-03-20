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
import net.minecraft.util.math.vector.Vector3d;
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
		super(new Item.Properties().tab(AoAItemGroups.STAVES).durability(durability));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand == Hand.OFF_HAND) {
			Item mainItem = player.getItemInHand(Hand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseGun || mainItem instanceof BaseBlaster)
				return ActionResult.fail(stack);
		}

		if (player instanceof ServerPlayerEntity) {
			T preconditionResult = checkPreconditions(player, stack);

			if (preconditionResult == null)
				return ActionResult.fail(stack);

			if (!findAndConsumeRunes(getRunes(), (ServerPlayerEntity)player, true, stack))
				return ActionResult.fail(stack);

			if (getCastingSound() != null)
				world.playSound(null, player.getX(), player.getY(), player.getZ(), getCastingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

			player.getCooldowns().addCooldown(this, 12);
			player.awardStat(Stats.ITEM_USED.get(this));
			ItemUtil.damageItem(stack, player, hand);
			cast(world, stack, player, preconditionResult);
		}

		return ActionResult.success(stack);
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
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {return true;}

	@Override
	public Hand getWeaponHand(LivingEntity holder) {
		return holder.getMainHandItem().getItem() == this || holder.getOffhandItem().getItem() != this ? Hand.MAIN_HAND : Hand.OFF_HAND;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	public float getDmg() {
		return 0;
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (getDmg() > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.magic", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(getDmg())));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.staff.runesRequired", LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));

		for (Map.Entry<RuneItem, Integer> runeEntry : getRunes().entrySet()) {
			tooltip.add(LocaleUtil.getLocaleMessage("items.description.staff.runesRequired.specific", TextFormatting.WHITE, LocaleUtil.numToComponent(runeEntry.getValue()), LocaleUtil.getLocaleMessage(runeEntry.getKey().getDescriptionId())));
		}
	}
}
