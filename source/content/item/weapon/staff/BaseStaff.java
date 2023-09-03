package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.AoASoundBuilderPacket;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.content.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseStaff<T> extends Item implements EnergyProjectileWeapon {
	protected final HashMap<Item, Integer> runes = new HashMap<Item, Integer>(2);

	public BaseStaff(int durability) {
		super(new Item.Properties().durability(durability));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand == InteractionHand.OFF_HAND) {
			Item mainItem = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseGun || mainItem instanceof BaseBlaster)
				return InteractionResultHolder.fail(stack);
		}

		if (player instanceof ServerPlayer) {
			T preconditionResult = checkPreconditions(player, stack);

			if (preconditionResult == null)
				return InteractionResultHolder.fail(stack);

			if (!findAndConsumeRunes(getRunes(), (ServerPlayer)player, true, stack))
				return InteractionResultHolder.fail(stack);

			if (getCastingSound() != null)
				AoAPackets.messageAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(getCastingSound()).isPlayer().followEntity(player)), player);

			player.getCooldowns().addCooldown(this, 24);
			player.awardStat(Stats.ITEM_USED.get(this));
			ItemUtil.damageItem(stack, player, hand);
			cast(world, stack, player, preconditionResult);
		}

		return InteractionResultHolder.success(stack);
	}

	public boolean findAndConsumeRunes(HashMap<Item, Integer> runes, ServerPlayer player, boolean allowBuffs, ItemStack staff) {
		return ItemUtil.findAndConsumeRunes(runes, player, allowBuffs, staff);
	}

	@Nullable
	public T checkPreconditions(LivingEntity caster, ItemStack staff) {
		return (T)new Object();
	}

	public HashMap<Item, Integer> getRunes() {
		if (runes.isEmpty())
			populateRunes(runes);

		return runes;
	}

	@Nullable
	public SoundEvent getCastingSound() {
		return null;
	}

	protected abstract void populateRunes(HashMap<Item, Integer> runes);

	public abstract void cast(Level world, ItemStack staff, LivingEntity caster, T args);

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {return true;}

	@Override
	public InteractionHand getWeaponHand(LivingEntity holder) {
		return holder.getMainHandItem().getItem() == this || holder.getOffhandItem().getItem() != this ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
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
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		if (getDmg() > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.magic", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(getDmg())));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.staff.runesRequired", LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));

		for (Map.Entry<Item, Integer> runeEntry : getRunes().entrySet()) {
			tooltip.add(LocaleUtil.getLocaleMessage("items.description.staff.runesRequired.specific", ChatFormatting.WHITE, LocaleUtil.numToComponent(runeEntry.getValue()), LocaleUtil.getLocaleMessage(runeEntry.getKey().getDescriptionId())));
		}
	}
}
