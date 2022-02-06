package net.tslat.aoa3.object.item.tool.misc;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.object.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HaulingRod extends FishingRodItem {
	public HaulingRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!level.isClientSide()) {
			if (player.fishing != null && player.fishing.level == player.level) {
				if (player.fishing instanceof HaulingFishingBobberEntity) {
					HaulingFishingBobberEntity bobber = (HaulingFishingBobberEntity)player.fishing;

					if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_FISH) {
						reelIn((ServerPlayerEntity)player, bobber, stack, hand);
						player.startUsingItem(hand);
					}
					else if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY) {
						landEntity(player, stack, hand, bobber);
						player.startUsingItem(hand);
					}
					else {
						bobber.remove();
						player.fishing = null;
					}
				}
				else {
					player.fishing.remove();
					player.fishing = null;
				}
			}
			else {
				HaulingFishingBobberEntity bobber = getNewBobber(player, stack, getLureMod(player, stack), getLuckMod(player, stack));

				if (bobber != null) {
					level.addFreshEntity(bobber);
					player.awardStat(Stats.ITEM_USED.get(this));
					playCastSound(player, bobber, stack);
				}
			}
		}

		return ActionResult.sidedSuccess(stack, level.isClientSide());
	}

	protected void reelIn(ServerPlayerEntity player, HaulingFishingBobberEntity bobber, ItemStack stack, Hand hand) {
		if (bobber.distanceToSqr(player) <= 9) {
			List<ItemStack> loot = landEntity(player, stack, hand, bobber);
			int xp = random.nextInt(6) + 1;
			HaulingItemFishedEvent event = AoAEvents.haulingItemFished(bobber.getHookedIn(), stack, loot, xp, 1, bobber);

			if (!event.isCanceled()) {
				handleLureRetrieval(player, stack, bobber, loot);

				for (ItemStack lootStack : loot) {
					ItemEntity entity = new ItemEntity(player.level, bobber.getX(), bobber.getY(), bobber.getZ(), lootStack);
					double velX = player.getX() - bobber.getX();
					double velY = player.getY() - bobber.getY();
					double velZ = player.getZ() - bobber.getZ();

					Vector3d pullVec = new Vector3d(velX, velY + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)), velZ).scale(0.1d);

					entity.setDeltaMovement(pullVec);
					player.level.addFreshEntity(entity);

					if (!player.isOnGround())
						player.setDeltaMovement(pullVec.reverse());

					if (lootStack.getItem().is(ItemTags.FISHES))
						player.awardStat(Stats.FISH_CAUGHT, 1);
				}
			}

			if (!player.isCreative())
				ItemUtil.damageItem(stack, player, hand, event.getRodDamage());

			player.level.addFreshEntity(new ExperienceOrbEntity(player.level, player.getX() + 0.5d, player.getY() + 0.5d, player.getZ() + 0.5d, event.getXp()));
			bobber.remove();
		}
		else {
			Entity hookedEntity = bobber.getHookedIn();

			if (hookedEntity != null) {
				float pullStrength = getHaulStrengthMod(player, stack, bobber);

				EntityUtil.pullEntityIn(player, hookedEntity, 0.25f * pullStrength, true);

				hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().multiply(1, 0.5f, 1));

				if (!player.isOnGround() && bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
					EntityUtil.pullEntityIn(hookedEntity, player, 0.25f * pullStrength, true);
			}
		}
	}

	protected List<ItemStack> landEntity(PlayerEntity player, ItemStack stack, Hand hand, HaulingFishingBobberEntity bobber) {
		Entity hookedEntity = bobber.getHookedIn();

		if (hookedEntity != null && hookedEntity.isAlive()) {
			float pullStrength = getHaulStrengthMod(player, stack, bobber);

			if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY) {
				HaulingRodPullEntityEvent event = AoAEvents.haulingRodPullEntity(player, stack, bobber, hookedEntity, 0, pullStrength);

				if (event.isCanceled())
					return Collections.emptyList();

				pullStrength = event.getPullStrength();

				if (event.getAdditionalRodDamage() > 0 && !player.isCreative())
					ItemUtil.damageItem(stack, player, hand, event.getAdditionalRodDamage());
			}

			EntityUtil.pullEntityIn(player, hookedEntity, pullStrength, true);

			hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().multiply(1, 0.25f, 1));

			if (!player.isOnGround() && bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
				EntityUtil.pullEntityIn(hookedEntity, player, 0.25f * pullStrength, true);

			player.getCooldowns().addCooldown(this, 10);

			return player instanceof ServerPlayerEntity ? getLootForHauledEntity((ServerPlayerEntity)player, stack, bobber, hookedEntity) : Collections.emptyList();
		}

		return Collections.emptyList();
	}

	protected List<ItemStack> getLootForHauledEntity(ServerPlayerEntity player, ItemStack stack, HaulingFishingBobberEntity bobber, Entity hookedEntity) {
		if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
			return Collections.emptyList();

		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();

		if (hookedEntity instanceof ItemEntity) {
			loot.add(((ItemEntity)hookedEntity).getItem());

			return loot;
		}
		else if (hookedEntity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity)hookedEntity;
			DamageSource damageSource = killHaulingEntity(bobber, player, livingEntity);
			LootContext.Builder lootContext = new LootContext.Builder((ServerWorld)player.level)
					.withParameter(LootParameters.ORIGIN, bobber.position())
					.withParameter(LootParameters.DAMAGE_SOURCE, damageSource)
					.withParameter(LootParameters.TOOL, stack)
					.withParameter(LootParameters.THIS_ENTITY, bobber)
					.withParameter(LootParameters.KILLER_ENTITY, player)
					.withParameter(LootParameters.DIRECT_KILLER_ENTITY, bobber)
					.withParameter(LootParameters.LAST_DAMAGE_PLAYER, player)
					.withRandom(random)
					.withLuck(bobber.getLuck());
			loot.addAll(player.getServer().getLootTables().get(livingEntity.getLootTable()).getRandomItems(lootContext.create(LootParameterSets.ENTITY)));

			return loot;
		}

		return Collections.emptyList();
	}

	protected void handleLureRetrieval(ServerPlayerEntity player, ItemStack stack, HaulingFishingBobberEntity bobber, Collection<ItemStack> loot) {
		playRetrievalSound(player, bobber, stack);
		CriteriaTriggers.FISHING_ROD_HOOKED.trigger(player, stack, bobber, loot);
		player.getCooldowns().addCooldown(this, 5);
	}

	protected void playRetrievalSound(PlayerEntity player, HaulingFishingBobberEntity bobber, ItemStack stack) {
		player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1, 0.4f / (random.nextFloat() * 0.4f + 0.8f));
	}

	protected void playCastSound(PlayerEntity player, HaulingFishingBobberEntity bobber, ItemStack stack) {
		player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (random.nextFloat() * 0.4f + 0.8f));
	}

	protected HaulingFishingBobberEntity getNewBobber(PlayerEntity player, ItemStack stack, int lureMod, int luckMod) {
		return new HaulingFishingBobberEntity(player, player.level, stack, luckMod, lureMod);
	}

	public float getHaulStrengthMod(PlayerEntity player, ItemStack stack, HaulingFishingBobberEntity bobber) {
		return 1;
	}

	public int getLureMod(PlayerEntity player, ItemStack stack) {
		return EnchantmentHelper.getFishingSpeedBonus(stack);
	}

	public int getLuckMod(PlayerEntity player, ItemStack stack) {
		return EnchantmentHelper.getFishingLuckBonus(stack);
	}

	private DamageSource killHaulingEntity(FishingBobberEntity bobber, PlayerEntity player, LivingEntity target) {
		DamageSource damageSource = new IndirectEntityDamageSource("hauling", bobber, player).bypassArmor().bypassMagic().bypassInvul();

		target.invulnerableTime = 0;
		target.hurt(damageSource, target.getHealth() - 0.01f);
		target.remove();

		return damageSource;
	}
}
