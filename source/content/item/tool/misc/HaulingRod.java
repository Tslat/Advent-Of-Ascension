package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.event.custom.events.HaulingRodPullEntityEvent;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;

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
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!level.isClientSide()) {
			if (player.fishing != null && player.fishing.level == player.level) {
				if (player.fishing instanceof HaulingFishingBobberEntity) {
					HaulingFishingBobberEntity bobber = (HaulingFishingBobberEntity)player.fishing;

					if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_FISH) {
						reelIn((ServerPlayer)player, bobber, stack, hand);
						player.startUsingItem(hand);
					}
					else if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY) {
						landEntity(player, stack, hand, bobber);
						player.startUsingItem(hand);
					}
					else {
						bobber.discard();
						player.fishing = null;
					}
				}
				else {
					player.fishing.discard();
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

		return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
	}

	protected void reelIn(ServerPlayer player, HaulingFishingBobberEntity bobber, ItemStack stack, InteractionHand hand) {
		if (bobber.distanceToSqr(player) <= 9) {
			List<ItemStack> loot = landEntity(player, stack, hand, bobber);
			int xp = RandomUtil.randomNumberBetween(2, 10);
			HaulingItemFishedEvent event = AoAEvents.haulingItemFished(bobber.getHookedIn(), stack, loot, xp, 1, bobber);

			if (!event.isCanceled()) {
				handleLureRetrieval(player, stack, bobber, loot);

				for (ItemStack lootStack : loot) {
					ItemEntity entity = new ItemEntity(player.level, bobber.getX(), bobber.getY(), bobber.getZ(), lootStack);
					double velX = player.getX() - bobber.getX();
					double velY = player.getY() - bobber.getY();
					double velZ = player.getZ() - bobber.getZ();

					Vec3 pullVec = new Vec3(velX, velY + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)), velZ).scale(0.1d);

					entity.setDeltaMovement(pullVec);
					player.level.addFreshEntity(entity);

					if (!player.isOnGround())
						player.setDeltaMovement(pullVec.reverse());

					if (lootStack.is(ItemTags.FISHES))
						player.awardStat(Stats.FISH_CAUGHT, 1);
				}
			}

			if (!player.isCreative())
				ItemUtil.damageItem(stack, player, hand, event.getRodDamage());

			player.level.addFreshEntity(new ExperienceOrb(player.level, player.getX() + 0.5d, player.getY() + 0.5d, player.getZ() + 0.5d, event.getXp()));
			bobber.discard();
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

	protected List<ItemStack> landEntity(Player player, ItemStack stack, InteractionHand hand, HaulingFishingBobberEntity bobber) {
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

			return player instanceof ServerPlayer ? getLootForHauledEntity((ServerPlayer)player, stack, bobber, hookedEntity) : Collections.emptyList();
		}

		return Collections.emptyList();
	}

	protected List<ItemStack> getLootForHauledEntity(ServerPlayer player, ItemStack stack, HaulingFishingBobberEntity bobber, Entity hookedEntity) {
		if (bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
			return Collections.emptyList();

		ArrayList<ItemStack> loot = new ArrayList<ItemStack>();

		if (hookedEntity instanceof ItemEntity) {
			loot.add(((ItemEntity)hookedEntity).getItem());

			return loot;
		}
		else if (hookedEntity instanceof LivingEntity livingEntity) {
			DamageSource damageSource = killHaulingEntity(bobber, player, livingEntity);
			LootContext.Builder lootContext = new LootContext.Builder((ServerLevel)player.level)
					.withParameter(LootContextParams.ORIGIN, bobber.position())
					.withParameter(LootContextParams.DAMAGE_SOURCE, damageSource)
					.withParameter(LootContextParams.TOOL, stack)
					.withParameter(LootContextParams.THIS_ENTITY, bobber)
					.withParameter(LootContextParams.KILLER_ENTITY, player)
					.withParameter(LootContextParams.DIRECT_KILLER_ENTITY, bobber)
					.withParameter(LootContextParams.LAST_DAMAGE_PLAYER, player)
					.withRandom(livingEntity.getRandom())
					.withLuck(bobber.getLuck());
			loot.addAll(player.getServer().getLootTables().get(livingEntity.getLootTable()).getRandomItems(lootContext.create(LootContextParamSets.ENTITY)));

			return loot;
		}

		return Collections.emptyList();
	}

	protected void handleLureRetrieval(ServerPlayer player, ItemStack stack, HaulingFishingBobberEntity bobber, Collection<ItemStack> loot) {
		playRetrievalSound(player, bobber, stack);
		CriteriaTriggers.FISHING_ROD_HOOKED.trigger(player, stack, bobber, loot);
		player.getCooldowns().addCooldown(this, 5);
	}

	protected void playRetrievalSound(Player player, HaulingFishingBobberEntity bobber, ItemStack stack) {
		player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1, 0.4f / (float)RandomUtil.randomValueBetween(0.8f, 1.2f));
	}

	protected void playCastSound(Player player, HaulingFishingBobberEntity bobber, ItemStack stack) {
		player.level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (float)RandomUtil.randomValueBetween(0.8f, 1.2f));
	}

	protected HaulingFishingBobberEntity getNewBobber(Player player, ItemStack stack, int lureMod, int luckMod) {
		return new HaulingFishingBobberEntity(player, player.level, stack, luckMod, lureMod);
	}

	public float getHaulStrengthMod(Player player, ItemStack stack, HaulingFishingBobberEntity bobber) {
		return 1;
	}

	public int getLureMod(Player player, ItemStack stack) {
		return EnchantmentHelper.getFishingSpeedBonus(stack);
	}

	public int getLuckMod(Player player, ItemStack stack) {
		return EnchantmentHelper.getFishingLuckBonus(stack);
	}

	private DamageSource killHaulingEntity(FishingHook bobber, Player player, LivingEntity target) {
		DamageSource damageSource = new IndirectEntityDamageSource("hauling", bobber, player).bypassArmor().bypassMagic().bypassInvul();

		target.invulnerableTime = 0;
		target.hurt(damageSource, target.getHealth() - 0.01f);
		target.discard();

		return damageSource;
	}
}
