package net.tslat.aoa3.content.item.weapon.blaster;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.content.item.armour.AdventArmour;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBlaster extends Item implements EnergyProjectileWeapon {
	private final Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();

	protected final double baseDmg;
	protected final int firingDelay;
	protected final float energyCost;

	public BaseBlaster(Item.Properties properties, final double dmg, final int fireDelayTicks, final float energyCost) {
		super(properties);

		this.baseDmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.energyCost = energyCost;

		attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", AttackSpeed.forAttacksPerSecond(1.2f), AttributeModifier.Operation.ADDITION));
	}

	public BaseBlaster(final double dmg, final int durability, final int fireDelayTicks, final float energyCost) {
		this(new Item.Properties().durability(durability), dmg, fireDelayTicks, energyCost);
	}

	public double getDamage() {
		return baseDmg;
	}

	public int getFiringDelay() {
		return firingDelay;
	}

	public float getEnergyCost() {
		return energyCost;
	}

	@Nullable
	public SoundEvent getFiringSound() {
		return null;
	}

	@Override
	public ItemStack getDefaultInstance() {
		return super.getDefaultInstance();
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getWeaponHand(player))
			return InteractionResultHolder.fail(stack);

		if (player.getAttackStrengthScale(0.0f) < 1)
			return InteractionResultHolder.fail(stack);

		if (player instanceof ServerPlayer) {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)player);
			int recharge = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack);
			int greed = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), stack);
			float energyConsumption = (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.GHOULISH)
				energyConsumption *= 0.7f;

			if (!player.isCreative() && plData.getResource(AoAResources.SPIRIT.get()).getCurrentValue() < energyConsumption)
				return InteractionResultHolder.fail(stack);

			player.startUsingItem(hand);
		}

		return InteractionResultHolder.pass(stack);
	}

	@Override
	public void onUseTick(Level level, LivingEntity player, ItemStack stack, int count) {
		if (player instanceof ServerPlayer) {
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)player);
			int recharge = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack);
			int greed = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), stack);
			float energyConsumption = ((Player)player).isCreative() ? 0 : (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.GHOULISH)
				energyConsumption *= 0.7f;

			if (((ServerPlayer)player).isCreative() || plData.getResource(AoAResources.SPIRIT.get()).hasAmount(energyConsumption)) {
				if (count + firingDelay <= 72000 && count % firingDelay == 0) {
					if (consumeEnergy(plData, stack, energyConsumption)) {
						if (getFiringSound() != null)
							player.level().playSound(null, player.getX(), player.getY(), player.getZ(), getFiringSound(), SoundSource.PLAYERS, 1.0f, 1.0f);

						fire(stack, player);
						((Player)player).awardStat(Stats.ITEM_USED.get(this));

						if ((72000 - count) / firingDelay >= getMaxDamage(stack) - stack.getDamageValue())
							ItemUtil.damageItem(stack, player, (72000 - count) / firingDelay, EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND);

					}
					else {
						player.releaseUsingItem();
					}
				}
			}
			else {
				if (player.getUseItem() != ItemStack.EMPTY)
					PlayerUtil.notifyPlayerOfInsufficientResources((ServerPlayer)player, AoAResources.SPIRIT.get(), energyConsumption);

				player.releaseUsingItem();
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity player, int useTicksRemaining) {
		ItemUtil.damageItem(stack, player, (72000 - useTicksRemaining - 1) / firingDelay, EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND);
	}

	public abstract void fire(ItemStack blaster, LivingEntity shooter);

	public boolean consumeEnergy(ServerPlayerDataManager plData, ItemStack stack, float cost) {
		return plData.getResource(AoAResources.SPIRIT.get()).consume(cost, false);
	}

	@Override
	public int getDefaultTooltipHideFlags(@NotNull ItemStack stack) {
		return ItemStack.TooltipPart.MODIFIERS.getMask();
	}

	@Override
	public InteractionHand getWeaponHand(LivingEntity holder) {
		return InteractionHand.MAIN_HAND;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (DamageUtil.doEnergyProjectileAttack(shooter, shooter, target, (float)baseDmg)) {
			doImpactEffect(shot, target, shooter);

			return true;
		}

		return false;
	}

	protected void doImpactEffect(BaseEnergyShot shot, Entity target, LivingEntity shooter) {}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		if (slot == EquipmentSlot.MAINHAND)
			return attributeModifiers;

		return super.getAttributeModifiers(slot, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		if (getDamage() > 0)
			tooltip.add(1, LocaleUtil.getLocaleMessage("items.description.damage.energy", ChatFormatting.DARK_RED, Component.literal(NumberUtil.roundToNthDecimalPlace((float)getDamage(), 1))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.fire", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.effect", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(NumberUtil.roundToNthDecimalPlace(20 / (float)getFiringDelay(), 2))));

		float energyConsumption = (1 + (0.3f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), stack))) * getEnergyCost() * Math.max(0, (1 - 0.07f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack)));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Component.literal(NumberUtil.roundToNthDecimalPlace(energyConsumption, 2)), AoAResources.SPIRIT.get().getName()));
	}
}
