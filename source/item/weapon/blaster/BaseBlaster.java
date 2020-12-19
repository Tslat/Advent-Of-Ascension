package net.tslat.aoa3.item.weapon.blaster;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBlaster extends Item implements EnergyProjectileWeapon {
	protected final double baseDmg;
	protected final int firingDelay;
	protected final float energyCost;

	public BaseBlaster(Item.Properties properties, final double dmg, final int fireDelayTicks, final float energyCost) {
		super(properties);

		this.baseDmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.energyCost = energyCost;
	}

	public BaseBlaster(final double dmg, final int durability, final int fireDelayTicks, final float energyCost) {
		this(new Item.Properties().group(AoAItemGroups.BLASTERS).maxDamage(durability), dmg, fireDelayTicks, energyCost);
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
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.NONE;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getWeaponHand(player))
			return ActionResult.resultFail(stack);

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.resultFail(stack);

		if (player instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			int recharge = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack);
			int greed = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), stack);
			float energyConsumption = (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.GHOULISH)
				energyConsumption *= 0.7f;

			if (plData.stats().getResourceValue(Resources.ENERGY) < energyConsumption)
				return ActionResult.resultFail(stack);

			player.setActiveHand(hand);
		}

		return ActionResult.resultPass(stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (player instanceof ServerPlayerEntity) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
			int recharge = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack);
			int greed = EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), stack);
			float energyConsumption = ((PlayerEntity)player).isCreative() ? 0 : (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == AdventArmour.Type.GHOULISH)
				energyConsumption *= 0.7f;

			if (plData.stats().getResourceValue(Resources.ENERGY) >= energyConsumption) {
				if (count + firingDelay <= 72000 && count % firingDelay == 0) {
					if (consumeEnergy(plData, stack, energyConsumption)) {
						if (getFiringSound() != null)
							player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

						fire(stack, player);
						((PlayerEntity)player).addStat(Stats.ITEM_USED.get(this));

						if ((72000 - count) / firingDelay >= getMaxDamage(stack) - stack.getDamage())
							ItemUtil.damageItem(stack, player, (72000 - count) / firingDelay, EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? EquipmentSlotType.OFFHAND : EquipmentSlotType.MAINHAND);

					}
					else {
						player.stopActiveHand();
					}
				}
			}
			else {
				if (player.getActiveItemStack() != ItemStack.EMPTY)
					PlayerUtil.notifyPlayerOfInsufficientResources((ServerPlayerEntity)player, Resources.ENERGY, energyConsumption);

				player.stopActiveHand();
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity player, int useTicksRemaining) {
		ItemUtil.damageItem(stack, player, (72000 - useTicksRemaining - 1) / firingDelay, EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.BRACE.get(), stack) > 0 ? EquipmentSlotType.OFFHAND : EquipmentSlotType.MAINHAND);
	}

	public abstract void fire(ItemStack blaster, LivingEntity shooter);

	public boolean consumeEnergy(PlayerDataManager plData, ItemStack stack, float cost) {
		return plData.stats().consumeResource(Resources.ENERGY, cost, false);
	}

	@Override
	public Hand getWeaponHand(LivingEntity holder) {
		return Hand.MAIN_HAND;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, LivingEntity shooter) {}

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
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		if (baseDmg > 0)
			tooltip.add(1, LocaleUtil.getLocaleMessage("items.description.damage.blaster", TextFormatting.DARK_RED, NumberUtil.roundToNthDecimalPlace((float)baseDmg, 1)));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.fire", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.slowing", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.effect", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Double.toString((2000 / firingDelay) / 100d)));

		float energyConsumption = (1 + (0.3f * EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.GREED.get(), stack))) * getEnergyCost() * Math.max(0, (1 - 0.07f * EnchantmentHelper.getEnchantmentLevel(AoAEnchantments.RECHARGE.get(), stack)));

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, NumberUtil.roundToNthDecimalPlace(energyConsumption, 2), LocaleUtil.getLocaleString(LocaleUtil.Constants.ENERGY_RESOURCE)));
	}

	@Override
	public int getItemEnchantability() {
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
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND)
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", firingDelay < 20 ? AttackSpeed.THIRD : AttackSpeed.QUARTER, AttributeModifier.Operation.ADDITION));

		return multimap;
	}
}
