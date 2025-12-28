package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.boss.tyrosaur.TyrosaurEntity;
import net.tslat.aoa3.content.entity.boss.tyrosaur.WoundedTyrosaurEntity;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class BoneHorn extends BossSpawningItem<TyrosaurEntity> {
	public BoneHorn() {
		super(0, new Properties().rarity(Rarity.RARE).durability(3).setNoRepair());
	}

	@Override
	public TyrosaurEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack, int playerCount) {
		TyrosaurEntity tyrosaur = EntitySpawningUtil.spawnEntity(level, AoAMonsters.TYROSAUR.get(), position, MobSpawnType.TRIGGERED);

		applyMultiplayerHealthBoost(tyrosaur, playerCount);

		return tyrosaur;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 63;
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		return !enchantment.is(Enchantments.MENDING) && super.supportsEnchantment(stack, enchantment);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (player.getItemInHand(hand).getDamageValue() > 1)
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		player.startUsingItem(hand);
		SoundBuilder.following(AoASounds.ITEM_BONE_HORN_CALL, player).play();

		return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
		if (!stack.isDamaged())
			super.onUseTick(level, livingEntity, stack, remainingUseDuration);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return stack.isDamaged() ? UseAnim.TOOT_HORN : super.getUseAnimation(stack);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (stack.isDamaged()) {
			if (stack.getDamageValue() == 1) {
				if (level.dimension() == AoADimensions.PRECASIA && level instanceof ServerLevel serverLevel) {
					BlockPos spawnPos = RandomUtil.positionWithinRange(entity.blockPosition(), 30, 10, 30, 10, 0, 10, true, level, 10, (state, pos) ->
							level.getBlockState(pos.below()).isValidSpawn(level, pos.below(), AoAMonsters.WOUNDED_TYROSAUR.get()) && level.noCollision(AoAMonsters.WOUNDED_TYROSAUR.get().getSpawnAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d)));

					if (spawnPos != entity.blockPosition()) {
						WoundedTyrosaurEntity tyrosaur = EntitySpawningUtil.spawnEntity(serverLevel, AoAMonsters.WOUNDED_TYROSAUR.get(), spawnPos, MobSpawnType.TRIGGERED);

						if (tyrosaur != null) {
							BrainUtils.setTargetOfEntity(tyrosaur, entity);
							SoundBuilder.following(AoASounds.ENTITY_TYROSAUR_HURT, tyrosaur).play();
						}
					}
				}

				if (entity instanceof Player pl)
					pl.getCooldowns().addCooldown(this, 20);
			}

			return stack;
		}

		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeUsed) {
		SoundBuilder.stopSound(AoASounds.ITEM_BONE_HORN_CALL, level).play();
	}

	@Override
	public EntityType<TyrosaurEntity> getEntityType(ItemStack stack) {
		return stack.isDamaged() ? null : AoAMonsters.TYROSAUR.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		if (!stack.isDamaged()) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
		}
		else {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 5 - stack.getDamageValue()));
		}
	}
}
