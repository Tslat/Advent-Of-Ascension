package net.tslat.aoa3.content.item.tool.misc;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LootUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HaulingRod extends FishingRodItem {
	public HaulingRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	public int getUseDuration(ItemStack pStack, LivingEntity user) {
		return 72000;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (player.fishing != null && player.fishing.level() == player.level()) {
			if (player.fishing instanceof HaulingFishingBobberEntity bobber) {
				if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_FISH) {
					if (!level.isClientSide) {
						SoundBuilder.following(AoASounds.ITEM_HAULING_ROD_REEL_IN, player).play();
						ParticleBuilder.forRandomPosInEntity(ParticleTypes.BUBBLE, player.fishing).sendToAllPlayersTrackingEntity(player.fishing);
						ParticleBuilder.forRandomPosInEntity(ParticleTypes.SPLASH, player.fishing).sendToAllPlayersTrackingEntity(player.fishing);
					}

					reelIn(player, bobber, stack, hand);

					if (!player.isUsingItem())
						player.startUsingItem(hand);

					return InteractionResultHolder.fail(stack);
				}
				else if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY) {
					landEntity(player, stack, hand, bobber);

					if (!player.isUsingItem())
						player.startUsingItem(hand);
				}
				else if (!level.isClientSide) {
					bobber.discard();
					player.fishing = null;
				}
			}
			else if (!level.isClientSide) {
				player.fishing.discard();
				player.fishing = null;
			}
		}
		else if (player instanceof ServerPlayer pl) {
			HaulingFishingBobberEntity bobber = getNewBobber(player, stack, getLureMod(pl, stack), getLuckMod(pl, stack));

			if (bobber != null) {
				level.addFreshEntity(bobber);
				player.awardStat(Stats.ITEM_USED.get(this));
				playCastSound(player, bobber, stack);
			}
		}

		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	protected void reelIn(Player player, HaulingFishingBobberEntity bobber, ItemStack stack, InteractionHand hand) {
		if (bobber.distanceToSqr(player) <= 9) {
			if (player instanceof ServerPlayer pl) {
				List<ItemStack> loot = landEntity(pl, stack, hand, bobber);
				int xp = RandomUtil.numberBetween(2, 10);
				HaulingItemFishedEvent event = AoAEvents.fireHaulingItemFished(bobber.getHookedIn(), stack, loot, xp, 1, bobber);

				if (!event.isCanceled()) {
					handleLureRetrieval(pl, stack, bobber, loot);

					for (ItemStack lootStack : loot) {
						ItemEntity entity = new ItemEntity(pl.level(), bobber.getX(), bobber.getY(), bobber.getZ(), lootStack);
						double velX = pl.getX() - bobber.getX();
						double velY = pl.getY() - bobber.getY();
						double velZ = pl.getZ() - bobber.getZ();

						Vec3 pullVec = new Vec3(velX, velY + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)), velZ).scale(0.1d);

						entity.setDeltaMovement(pullVec);
						pl.level().addFreshEntity(entity);

						if (!pl.onGround())
							pl.setDeltaMovement(pullVec.reverse());

						if (lootStack.is(ItemTags.FISHES))
							pl.awardStat(Stats.FISH_CAUGHT, 1);
					}
				}

				ItemUtil.damageItemForUser(pl, stack, event.getRodDamage(), hand);
				pl.level().addFreshEntity(new ExperienceOrb(pl.level(), pl.getX() + 0.5d, pl.getY() + 0.5d, pl.getZ() + 0.5d, event.getXp()));
				bobber.discard();
			}
		}
		else {
			Entity hookedEntity = bobber.getHookedIn();

			if (hookedEntity != null) {
				float pullStrength = getHaulStrengthMod(player, stack, bobber);

				EntityUtil.pullEntityIn(player, hookedEntity, 0.25f * pullStrength, true);

				hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().multiply(1, 0.5f, 1));

				if (!player.onGround() && bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
					EntityUtil.pullEntityIn(hookedEntity, player, 0.25f * pullStrength, true);
			}
		}
	}

	protected List<ItemStack> landEntity(Player player, ItemStack stack, InteractionHand hand, HaulingFishingBobberEntity bobber) {
		Entity hookedEntity = bobber.getHookedIn();

		if (hookedEntity != null && hookedEntity.isAlive()) {
			float pullStrength = getHaulStrengthMod(player, stack, bobber);

			if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY) {
				HaulingRodPullEntityEvent event = AoAEvents.fireHaulingRodPullEntity(player, stack, bobber, hookedEntity, 0, pullStrength);

				if (event.isCanceled())
					return Collections.emptyList();

				pullStrength = event.getPullStrength();

				if (event.getAdditionalRodDamage() > 0 && !player.level().isClientSide)
					ItemUtil.damageItemForUser(player, stack, event.getAdditionalRodDamage(), hand);
			}

			EntityUtil.pullEntityIn(player, hookedEntity, pullStrength, true);

			hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().multiply(1, 0.25f, 1));

			if (!player.onGround() && bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
				EntityUtil.pullEntityIn(hookedEntity, player, 0.25f * pullStrength, true);

			player.getCooldowns().addCooldown(this, 10);

			return player instanceof ServerPlayer ? getLootForHauledEntity((ServerPlayer)player, stack, bobber, hookedEntity) : Collections.emptyList();
		}

		return Collections.emptyList();
	}

	protected List<ItemStack> getLootForHauledEntity(ServerPlayer player, ItemStack stack, HaulingFishingBobberEntity bobber, Entity hookedEntity) {
		if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
			return Collections.emptyList();

		return switch (hookedEntity) {
			case ItemEntity itemEntity -> ObjectArrayList.of(itemEntity.getItem());
			case LivingEntity livingEntity -> ObjectArrayList.of(LootUtil.generateLoot(livingEntity.getLootTable(), new LootParams.Builder(player.serverLevel())
                    .withParameter(LootContextParams.ORIGIN, bobber.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, killHaulingEntity(bobber, player, livingEntity))
                    .withParameter(LootContextParams.TOOL, stack)
                    .withParameter(LootContextParams.THIS_ENTITY, bobber)
                    .withParameter(LootContextParams.ATTACKING_ENTITY, player)
                    .withParameter(LootContextParams.DIRECT_ATTACKING_ENTITY, bobber)
                    .withParameter(LootContextParams.LAST_DAMAGE_PLAYER, player)
                    .withLuck(bobber.getLuck())
                    .create(LootContextParamSets.ENTITY)).toArray(new ItemStack[0]));
			case null, default -> Collections.emptyList();
		};
	}

	protected void handleLureRetrieval(ServerPlayer player, ItemStack stack, HaulingFishingBobberEntity bobber, Collection<ItemStack> loot) {
		playRetrievalSound(player, bobber, stack);
		CriteriaTriggers.FISHING_ROD_HOOKED.trigger(player, stack, bobber, loot);
		player.getCooldowns().addCooldown(this, 5);
	}

	protected void playRetrievalSound(Player player, HaulingFishingBobberEntity bobber, ItemStack stack) {
		player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1, 0.4f / (float)RandomUtil.valueBetween(0.8f, 1.2f));
	}

	protected void playCastSound(Player player, HaulingFishingBobberEntity bobber, ItemStack stack) {
		player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (float)RandomUtil.valueBetween(0.8f, 1.2f));
	}

	protected HaulingFishingBobberEntity getNewBobber(Player player, ItemStack stack, float lureMod, float luckMod) {
		return new HaulingFishingBobberEntity(player, player.level(), stack, luckMod, lureMod);
	}

	public float getHaulStrengthMod(Player player, ItemStack stack, HaulingFishingBobberEntity bobber) {
		return 1;
	}

	public float getLureMod(ServerPlayer player, ItemStack stack) {
		return EnchantmentHelper.getFishingTimeReduction(player.serverLevel(), stack, player);
	}

	public int getLuckMod(ServerPlayer player, ItemStack stack) {
		return EnchantmentHelper.getFishingLuckBonus(player.serverLevel(), stack, player);
	}

	private DamageSource killHaulingEntity(FishingHook bobber, Player player, LivingEntity target) {
		DamageSource damageSource = DamageUtil.indirectEntityDamage(AoADamageTypes.HAULING, player, bobber);

		target.lastDamageSource = damageSource;
		target.setLastHurtByMob(player);
		target.setLastHurtByPlayer(player);
		target.setHealth(0);
		player.awardKillScore(target, 1, damageSource);
		target.discard();

		return damageSource;
	}
}
