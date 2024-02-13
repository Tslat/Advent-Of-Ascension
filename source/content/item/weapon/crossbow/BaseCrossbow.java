package net.tslat.aoa3.content.item.weapon.crossbow;

import com.google.common.collect.Lists;
import com.mojang.math.Constants;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class BaseCrossbow extends CrossbowItem {
	protected double damage;

	public BaseCrossbow(double damage, int durability) {
		super(new Item.Properties().durability(durability));

		this.damage = damage;
	}

	public double getDamage() {
		return damage;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack crossbowStack = player.getItemInHand(hand);

		if (isCharged(crossbowStack)) {
			fireProjectiles(player, hand, crossbowStack, getShotVelocity(crossbowStack), 1.0F);
			setCharged(crossbowStack, false);

			return InteractionResultHolder.consume(crossbowStack);
		}
		else if (!findAmmo(crossbowStack, player, player.isCreative()).isEmpty()) {
			if (!isCharged(crossbowStack)) {
				startSoundPlayed = false;
				midLoadSoundPlayed = false;

				player.startUsingItem(hand);
			}

			return InteractionResultHolder.consume(crossbowStack);
		}
		else {
			return InteractionResultHolder.fail(crossbowStack);
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.CROSSBOW;
	}

	@Override
	public void onUseTick(Level world, LivingEntity shooter, ItemStack stack, int count) {
		if (!world.isClientSide) {
			int quickCharge = stack.getEnchantmentLevel(Enchantments.QUICK_CHARGE);
			SoundEvent chargeSound = getChargeSound(quickCharge);
			SoundEvent middleChargeSound = quickCharge == 0 ? SoundEvents.CROSSBOW_LOADING_MIDDLE : null;
			float chargePercent = (stack.getUseDuration() - count) / (float)getChargeDuration(stack);

			if (chargePercent < 0.2F) {
				startSoundPlayed = false;
				midLoadSoundPlayed = false;
			}

			if (chargePercent >= 0.2F && !startSoundPlayed) {
				startSoundPlayed = true;

				world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), chargeSound, SoundSource.PLAYERS, 0.5F, 1.0F);
			}

			if (chargePercent >= 0.5F && middleChargeSound != null && !midLoadSoundPlayed) {
				midLoadSoundPlayed = true;

				world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), middleChargeSound, SoundSource.PLAYERS, 0.5F, 1.0F);
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack crossbowStack, Level world, LivingEntity shooter, int timeLeft) {
		int useTicks = getUseDuration(crossbowStack) - timeLeft;
		float charge = getCharge(crossbowStack, useTicks);

		if (charge >= 1.0F && !isCharged(crossbowStack) && hasAmmo(shooter, crossbowStack)) {
			setCharged(crossbowStack, true);
			world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_LOADING_END, shooter instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE, 1.0F, 1.0F / (float)RandomUtil.randomValueBetween(1, 1.5f) + 0.2F);
		}
	}

	protected ItemStack findAmmo(ItemStack crossbowStack, LivingEntity player, boolean infiniteAmmo) {
		return player.getProjectile(crossbowStack);
	}

	protected boolean hasAmmo(LivingEntity user, ItemStack crossbowStack) {
		int multishot = crossbowStack.getEnchantmentLevel(Enchantments.MULTISHOT);
		boolean infiniteAmmo = user instanceof Player && ((Player)user).isCreative();
		ItemStack ammoStack = findAmmo(crossbowStack, user, infiniteAmmo);
		ItemStack ammoStackCopy = ammoStack.copy();

		for(int i = 0; i < 1 + multishot * 2; i++) {
			if (i > 0)
				ammoStack = ammoStackCopy.copy();

			if (ammoStack.isEmpty() && infiniteAmmo) {
				ammoStack = new ItemStack(Items.ARROW);
				ammoStackCopy = ammoStack.copy();
			}

			if (!chargeShot(user, crossbowStack, ammoStack, i > 0, infiniteAmmo))
				return false;
		}

		return true;
	}

	protected boolean chargeShot(LivingEntity shooter, ItemStack crossbowStack, ItemStack ammoStack, boolean isMultishotProjectile, boolean infiniteAmmo) {
		if (ammoStack.isEmpty())
			return false;

		boolean canUseAmmo = infiniteAmmo && ammoStack.getItem() instanceof ArrowItem;
		ItemStack itemstack;

		if (!canUseAmmo && !infiniteAmmo && !isMultishotProjectile) {
			itemstack = ammoStack.split(1);

			if (ammoStack.isEmpty() && shooter instanceof Player)
				((Player)shooter).getInventory().removeItem(ammoStack);
		}
		else {
			itemstack = ammoStack.copy();
		}

		addChargedProjectile(crossbowStack, itemstack);

		return true;
	}

	protected void addChargedProjectile(ItemStack crossbow, ItemStack projectile) {
		CompoundTag tag = crossbow.getOrCreateTag();
		ListTag projectilesNbt;

		if (tag.contains("ChargedProjectiles", Tag.TAG_LIST)) {
			projectilesNbt = tag.getList("ChargedProjectiles", Tag.TAG_COMPOUND);
		}
		else {
			projectilesNbt = new ListTag();
		}

		CompoundTag projectileTag = new CompoundTag();

		projectile.save(projectileTag);
		projectilesNbt.add(projectileTag);
		tag.put("ChargedProjectiles", projectilesNbt);
	}

	protected void fireProjectiles(LivingEntity shooter, InteractionHand hand, ItemStack crossbowStack, float baseVelocity, float baseInaccuracy) {
		List<ItemStack> projectiles = getChargedProjectiles(crossbowStack);

		if (projectiles.isEmpty())
			return;

		float[] soundPitches = getRandomSoundPitches(RandomUtil.RANDOM.getSource(), projectiles.size());
		boolean creativeMode = shooter instanceof Player && ((Player)shooter).isCreative();
		float spreadModifier = -10f;

		fireProjectile(shooter, hand, crossbowStack, projectiles.get(0), soundPitches[0], creativeMode, baseVelocity, baseInaccuracy, 0);

		for (int i = 1; i < projectiles.size(); i++) {
			ItemStack projectile = projectiles.get(i);

			fireProjectile(shooter, hand, crossbowStack, projectile, soundPitches[i], creativeMode, baseVelocity, baseInaccuracy, spreadModifier);

			spreadModifier = spreadModifier < 0 ? spreadModifier * -1 : spreadModifier / -2f;
		}

		if (shooter instanceof ServerPlayer) {
			ServerPlayer player = (ServerPlayer)shooter;

			CriteriaTriggers.SHOT_CROSSBOW.trigger(player, crossbowStack);
			player.awardStat(Stats.ITEM_USED.get(crossbowStack.getItem()));
		}

		clearProjectiles(crossbowStack);
	}

	protected void fireProjectile(LivingEntity shooter, InteractionHand hand, ItemStack crossbowStack, ItemStack projectileStack, float soundPitch, boolean isCreative, float velocity, float inaccuracy, float projectileAngle) {
		Level world = shooter.getCommandSenderWorld();

		if (!world.isClientSide) {
			boolean isFirework = projectileStack.getItem() == Items.FIREWORK_ROCKET;
			Projectile projectile;

			if (isFirework) {
				projectile = new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - 0.15F, shooter.getZ(), true);
			}
			else {
				projectile = createArrow(shooter, crossbowStack, projectileStack);

				if (isCreative || projectileAngle != 0.0F)
					((AbstractArrow)projectile).pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
			}

			if (shooter instanceof CrossbowAttackMob crossbowUser) {
				crossbowUser.shootCrossbowProjectile(crossbowUser.getTarget(), crossbowStack, projectile, projectileAngle);
			}
			else {
				Vec3 vecUp = shooter.getUpVector(1);
				Quaternionf angle = new Quaternionf().setAngleAxis(projectileAngle * Constants.DEG_TO_RAD, vecUp.x, vecUp.y, vecUp.z);
				Vector3f lookVec = shooter.getViewVector(1).toVector3f().rotate(angle);

				projectile.shoot(lookVec.x(), lookVec.y(), lookVec.z(), velocity, inaccuracy);
			}

			if (projectile instanceof CustomArrowEntity)
				doArrowMods((CustomArrowEntity)projectile, shooter, 0);

			world.addFreshEntity(projectile);
			crossbowStack.hurtAndBreak(isFirework ? 3 : 1, shooter, (user) -> user.broadcastBreakEvent(hand));
			world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, soundPitch);
		}
	}

	protected CustomArrowEntity createArrow(LivingEntity shooter, ItemStack crossbowStack, ItemStack ammoStack) {
		ArrowItem arrowItem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
		CustomArrowEntity arrow = CustomArrowEntity.fromArrow(arrowItem.createArrow(shooter.level(), ammoStack, shooter), this, shooter, getDamage());

		if (shooter instanceof Player)
			arrow.setCritArrow(true);

		arrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
		arrow.setShotFromCrossbow(true);
		int piercing = crossbowStack.getEnchantmentLevel(Enchantments.PIERCING);

		if (piercing > 0)
			arrow.setPierceLevel((byte)piercing);

		return arrow;
	}

	protected float getShotVelocity(ItemStack crossbowStack) {
		return crossbowStack.getItem() instanceof BaseCrossbow && containsChargedProjectile(crossbowStack, Items.FIREWORK_ROCKET) ? 1.6f : 3.15f;
	}

	protected float getCharge(ItemStack crossbowStack, int useTime) {
		return Math.min(useTime / (float)getChargeDuration(crossbowStack), 1);
	}

	protected List<ItemStack> getChargedProjectiles(ItemStack crossbowStack) {
		List<ItemStack> projectiles = Lists.newArrayList();
		CompoundTag tag = crossbowStack.getTag();

		if (tag != null && tag.contains("ChargedProjectiles", Tag.TAG_LIST)) {
			ListTag projectileNbt = tag.getList("ChargedProjectiles", Tag.TAG_COMPOUND);

			for (int i = 0; i < projectileNbt.size(); ++i) {
				projectiles.add(ItemStack.of(projectileNbt.getCompound(i)));
			}
		}

		return projectiles;
	}

	protected void clearProjectiles(ItemStack crossbowStack) {
		CompoundTag tag = crossbowStack.getTag();

		if (tag != null) {
			ListTag projectilesNbt = tag.getList("ChargedProjectiles", Tag.TAG_LIST);

			projectilesNbt.clear();
			tag.put("ChargedProjectiles", projectilesNbt);
		}
	}

	protected float[] getRandomSoundPitches(RandomSource rand, int amount) {
		float[] pitches = new float[amount];

		for (int i = 0; i < amount; i++) {
			pitches[i] = 1 / (rand.nextFloat() * 0.5f + 1.8f) + (rand.nextBoolean() ? 0.63f : 0.43f);
		}

		return pitches;
	}

	protected SoundEvent getChargeSound(int quickCharge) {
		return switch (quickCharge) {
			case 1 -> SoundEvents.CROSSBOW_QUICK_CHARGE_1;
			case 2 -> SoundEvents.CROSSBOW_QUICK_CHARGE_2;
			case 3 -> SoundEvents.CROSSBOW_QUICK_CHARGE_3;
			default -> SoundEvents.CROSSBOW_LOADING_START;
		};
	}

	@Override
	public boolean useOnRelease(ItemStack stack) {
		return true;
	}

	public void doArrowMods(CustomArrowEntity arrow, Entity shooter, int useTicksRemaining) {}

	public void onEntityHit(CustomArrowEntity arrow, Entity target, Entity shooter, double damage, float drawStrength) {}

	public void onBlockHit(CustomArrowEntity arrow, BlockHitResult rayTrace, Entity shooter) {}

	public void onArrowTick(CustomArrowEntity arrow, Entity shooter) {}

	public double getArrowDamage(CustomArrowEntity arrow, Entity target, double currentDamage, float drawStrength, boolean isCritical) {
		double damage = currentDamage * 0.5d * (drawStrength / 3f);

		if (isCritical)
			damage += damage + (damage * RandomUtil.randomScaledGaussianValue(0.35f));

		return damage;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getDamage()))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.AMMO_ITEM, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Component.translatable(Items.ARROW.getDescriptionId())));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
