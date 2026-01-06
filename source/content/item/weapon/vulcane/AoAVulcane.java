package net.tslat.aoa3.content.item.weapon.vulcane;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.datacomponent.VulcaneStats;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.List;

public abstract class AoAVulcane extends Item {
	public AoAVulcane(Item.Properties properties) {
		super(properties);
	}

	public VulcaneStats getVulcaneStats(ItemStack stack) {
		return stack.get(AoADataComponents.VULCANE_STATS.get());
	}

	public float getVulcaneDamage(ItemStack stack) {
		return getVulcaneStats(stack).damage();
	}

	public float getRageCost(ItemStack stack) {
		return getVulcaneStats(stack).rageCost();
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	protected void onDamageEntity(Level level, LivingEntity user, LivingEntity hitEntity, ItemStack vulcane, float damage) {}
	public void doActivationFx(ServerLevel level, LivingEntity user, LivingEntity hitEntity, ItemStack vulcane) {
		SoundBuilder.following(AoASounds.ITEM_VULCANE_USE, user).play();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		AoAResource.Instance rageResource = PlayerUtil.getResource(player, AoAResources.RAGE.get());
		float rageCost = getRageCost(stack);

		if (!player.hasInfiniteMaterials() && !rageResource.hasAmount(rageCost))
			return InteractionResultHolder.fail(stack);

		if (level instanceof ServerLevel serverLevel) {
			LivingEntity target = player.getLastHurtByMob();

			if (target == null)
				return InteractionResultHolder.fail(stack);

			if (activate(serverLevel, player, target, stack, 1 + ((rageResource.getCurrentValue() - getRageCost(stack)) / rageResource.getMaxValue()))) {
				doActivationFx(serverLevel, player, target, stack);
				ItemUtil.damageItemForUser(player, stack, hand);
				rageResource.consume(rageResource.getCurrentValue(), true);

				return InteractionResultHolder.success(stack);
			}
		}

		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
	}

	public boolean activate(ServerLevel level, LivingEntity user, LivingEntity target, ItemStack stack, float damageMod) {
		if (DamageUtil.doVulcaneAttack(user, target, getVulcaneDamage(stack) * damageMod)) {
			onDamageEntity(level, user, target, stack, getVulcaneDamage(stack) * damageMod);

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.VULCANE_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(getVulcaneDamage(stack))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.VULCANE_COST, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, LocaleUtil.numToComponent(getRageCost(stack))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.VULCANE_GRACE_PERIOD, LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
