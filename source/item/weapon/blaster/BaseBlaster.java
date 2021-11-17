package net.tslat.aoa3.item.weapon.blaster;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.misc.AttackSpeed;

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

		attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", firingDelay < 20 ? AttackSpeed.THIRD : AttackSpeed.QUARTER, AttributeModifier.Operation.ADDITION));
	}

	public BaseBlaster(final double dmg, final int durability, final int fireDelayTicks, final float energyCost) {
		this(new Item.Properties().tab(AoAItemGroups.BLASTERS).durability(durability), dmg, fireDelayTicks, energyCost);
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
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getWeaponHand(player))
			return ActionResult.fail(stack);

		if (player.getAttackStrengthScale(0.0f) < 1)
			return ActionResult.fail(stack);

		if (player instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			int recharge = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack);
			int greed = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), stack);
			float energyConsumption = (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.GHOULISH)
				energyConsumption *= 0.7f;

			if (!player.isCreative() && plData.getResource(AoAResources.SPIRIT.get()).getCurrentValue() < energyConsumption)
				return ActionResult.fail(stack);

			player.startUsingItem(hand);
		}

		return ActionResult.pass(stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (player instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			int recharge = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack);
			int greed = EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), stack);
			float energyConsumption = ((PlayerEntity)player).isCreative() ? 0 : (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.GHOULISH)
				energyConsumption *= 0.7f;

			if (((ServerPlayerEntity)player).isCreative() || plData.getResource(AoAResources.SPIRIT.get()).hasAmount(energyConsumption)) {
				if (count + firingDelay <= 72000 && count % firingDelay == 0) {
					if (consumeEnergy(plData, stack, energyConsumption)) {
						if (getFiringSound() != null)
							player.level.playSound(null, player.getX(), player.getY(), player.getZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

						fire(stack, player);
						((PlayerEntity)player).awardStat(Stats.ITEM_USED.get(this));

						if ((72000 - count) / firingDelay >= getMaxDamage(stack) - stack.getDamageValue())
							ItemUtil.damageItem(stack, player, (72000 - count) / firingDelay, EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? EquipmentSlotType.OFFHAND : EquipmentSlotType.MAINHAND);

					}
					else {
						player.releaseUsingItem();
					}
				}
			}
			else {
				if (player.getUseItem() != ItemStack.EMPTY)
					PlayerUtil.notifyPlayerOfInsufficientResources((ServerPlayerEntity)player, AoAResources.SPIRIT.get(), energyConsumption);

				player.releaseUsingItem();
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, World world, LivingEntity player, int useTicksRemaining) {
		ItemUtil.damageItem(stack, player, (72000 - useTicksRemaining - 1) / firingDelay, EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? EquipmentSlotType.OFFHAND : EquipmentSlotType.MAINHAND);
	}

	public abstract void fire(ItemStack blaster, LivingEntity shooter);

	public boolean consumeEnergy(PlayerDataManager plData, ItemStack stack, float cost) {
		return plData.getResource(AoAResources.SPIRIT.get()).consume(cost, false);
	}

	@Override
	public Hand getWeaponHand(LivingEntity holder) {
		return Hand.MAIN_HAND;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (DamageUtil.dealBlasterDamage(shooter, target, shot, (float)baseDmg, false)) {
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

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
		CompoundNBT tag = stack.getOrCreateTag();

		tag.putByte("HideFlags", (byte)2);

		return null;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		return attributeModifiers;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (baseDmg > 0)
			tooltip.add(1, LocaleUtil.getLocaleMessage("items.description.damage.blaster", TextFormatting.DARK_RED, new StringTextComponent(NumberUtil.roundToNthDecimalPlace((float)baseDmg, 1))));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.fire", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.slowing", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.effect", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(20 / (float)getFiringDelay(), 2))));

		float energyConsumption = (1 + (0.3f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.GREED.get(), stack))) * getEnergyCost() * Math.max(0, (1 - 0.07f * EnchantmentHelper.getItemEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack)));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(energyConsumption, 2)), AoAResources.SPIRIT.get().getName()));
	}
}
