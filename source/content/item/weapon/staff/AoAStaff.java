package net.tslat.aoa3.content.item.weapon.staff;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.content.item.datacomponent.ItemUseSound;
import net.tslat.aoa3.content.item.datacomponent.StaffStats;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.StreamCodecUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public abstract class AoAStaff<T> extends Item implements ProjectileFiringWeapon {
	public AoAStaff(Item.Properties properties) {
		super(properties);
	}

	public StaffStats getStaffStats() {
		return getStaffStats(getDefaultInstance());
	}

	public StaffStats getStaffStats(ItemStack stack) {
		return stack.get(AoADataComponents.STAFF_STATS);
	}

	public Reference2IntMap<Item> getRuneCost(ItemStack stack) {
		return getStaffStats(stack).runeCosts();
	}

	public float getMagicDamage(ItemStack stack) {
		return getStaffStats(stack).damage();
	}

	public ItemUseSound getUseSound(ItemStack stack) {
		final ItemUseSound stats = stack.get(AoADataComponents.ITEM_USE_SOUND);

		return stats != null ? stats : ItemUseSound.none();
	}

	public Optional<Holder<SoundEvent>> getCastingSound(ItemStack stack) {
		return getUseSound(stack).sound();
	}

	public float getCastingSoundPitch(ItemStack stack) {
		return getUseSound(stack).pitch();
	}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	public abstract void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, T args);
	protected void onCast(ServerLevel level, ItemStack staff, LivingEntity caster, T args) {}
	public void doCastFx(ServerLevel level, ItemStack staff, LivingEntity caster, T args) {
		getCastingSound(staff).ifPresent(sound ->
												 SoundBuilder.following(sound, caster)
														 .pitch(getCastingSoundPitch(staff))
														 .varyPitch(0.075f)
														 .play());
	}
	protected void onDamageEntity(ServerLevel level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<?> rayTrace, Entity hitEntity, float damage) {}
	protected void onHitBlock(Level level, @Nullable WeaponProjectile projectile, @Nullable WeaponFiringContext context, @Nullable RayTrace<Void> rayTrace, BlockState hitBlock) {}

	public Optional<T> checkPreconditions(LivingEntity caster, ItemStack staff) {
		return Optional.of((T)Void.TYPE);
	}

	protected void modifyImpactDamage(ServerLevel level, WeaponProjectile projectile, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, DamageSource source, MutableFloat damage) {
		damage.setValue(EnchantmentHelper.modifyDamage(level, context.weaponStack(), hitEntity, source, damage.floatValue()));
	}

	public WeaponFiringContext.Builder createProjectileContext(ItemStack stack, @Nullable Entity shooter, InteractionHand hand) {
		return WeaponFiringContext.Builder.of(stack, shooter, hand).degreesInaccuracy(1f).damage(getMagicDamage(stack)).lifespan(60);
	}

	protected <P extends NonPhysicalWeaponProjectile> void fireProjectile(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand,
																			 DeferredHolder<EntityType<?>, EntityType<P>> projectileType) {
		fireProjectile(level, caster, staff, hand, (level2, context) -> new NonPhysicalWeaponProjectile(projectileType.get(), level2, context));
	}

	protected void fireProjectile(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, BiFunction<ServerLevel, WeaponFiringContext, WeaponProjectile> projectileFactory) {
		fireProjectile(level, caster, staff, hand, projectileFactory,
							  (context, projectile) -> projectile.fromArmPos().shootingAtTarget(context.velocity(), context.inaccuracy()));
	}

	protected void fireProjectile(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand,
									 BiFunction<ServerLevel, WeaponFiringContext, WeaponProjectile> projectileFactory, BiConsumer<WeaponFiringContext, WeaponProjectile> shootFunction) {
		fireProjectile(level, createProjectileContext(staff, caster, hand).build(), projectileFactory, shootFunction);
	}

	protected void fireProjectile(ServerLevel level, WeaponFiringContext context,
									 BiFunction<ServerLevel, WeaponFiringContext, WeaponProjectile> projectileFactory, BiConsumer<WeaponFiringContext, WeaponProjectile> shootFunction) {
		WeaponProjectile projectile = projectileFactory.apply(level, context);

		if (projectile == null || !(projectile.asEntity() instanceof Entity staffProjectile))
			return;

		shootFunction.accept(context, projectile);

		level.addFreshEntity(staffProjectile);
	}

	@Override
	public boolean doEntityImpact(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity) {
		if (level instanceof ServerLevel serverLevel) {
			WeaponFiringContext context = projectile.getShotContext();
			MutableFloat damage = new MutableFloat(context.damage());
			ToFloatFunction<DamageSource> damageCalculator = source -> {
				modifyImpactDamage(serverLevel, projectile, context, rayTrace, hitEntity, source, damage);

				return damage.floatValue();
			};

			if (getMagicDamage(context.weaponStack()) == 0 || DamageUtil.doMagicProjectileAttack(context.getShooter(), projectile.asEntity(), hitEntity, damageCalculator)) {
				onDamageEntity(serverLevel, projectile, context, rayTrace, hitEntity, damage.floatValue());

				return true;
			}

			return false;
		}

		return true;
	}

	@Override
	public boolean doBlockImpact(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		onHitBlock(level, projectile, projectile.getShotContext(), rayTrace, hitBlock);

		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand == InteractionHand.OFF_HAND) {
			Item mainItem = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();

			if (mainItem instanceof ProjectileFiringWeapon)
				return InteractionResultHolder.fail(stack);
		}

		if (player instanceof ServerPlayer pl) {
			return checkPreconditions(pl, stack).filter(data -> findAndConsumeRunes(getRuneCost(stack), pl, true, stack)).map(data -> {
				activate(pl.serverLevel(), pl, stack, hand, data);

				return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
			}).orElseGet(() -> InteractionResultHolder.fail(stack));
		}

		return InteractionResultHolder.success(stack);
	}

	protected void activate(ServerLevel level, LivingEntity caster, ItemStack stack, InteractionHand hand, T args) {
		cast(level, caster, stack, hand, args);

        if (caster instanceof ServerPlayer pl) {
            pl.getCooldowns().addCooldown(this, 24);
            pl.awardStat(Stats.ITEM_USED.get(this));

            ItemUtil.damageItemForUser(pl, stack, hand);
        }

        onCast(level, stack, caster, args);
        doCastFx(level, stack, caster, args);
    }

	public int getStoredCharges(ItemStack stack) {
		return stack.has(AoADataComponents.STORED_SPELL_CASTS) ? stack.get(AoADataComponents.STORED_SPELL_CASTS).stored() : -1;
	}

	public boolean findAndConsumeRunes(Reference2IntMap<Item> runes, ServerPlayer player, boolean allowBuffs, ItemStack staff) {
		if (player.hasInfiniteMaterials())
			return true;

		StoredCasts storedCasts = staff.has(AoADataComponents.STORED_SPELL_CASTS) ? staff.get(AoADataComponents.STORED_SPELL_CASTS) : null;

		if (storedCasts != null && storedCasts.stored() > 0) {
			staff.set(AoADataComponents.STORED_SPELL_CASTS, StoredCasts.decrement(storedCasts));

			return true;
		}

		return ItemUtil.findAndConsumeRunes(runes, player, allowBuffs, staff);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		float damage = getMagicDamage(stack);
		if (damage > 0)
			tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.MAGIC_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(damage)));

		int storedCharges = getStoredCharges(stack);

		if (storedCharges > 0) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.STAFF_STORED_CHARGES, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, Component.literal(String.valueOf(storedCharges))));
		}
		else if (storedCharges == 0) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.STAFF_ADD_CHARGE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));
		}

		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.STAFF_RUNE_COST, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST));

		for (Reference2IntMap.Entry<Item> runeEntry : getRuneCost(stack).reference2IntEntrySet()) {
			tooltip.add(LocaleUtil.getLocaleMessage(LocaleUtil.Keys.STAFF_RUNE_COST_LINE, ChatFormatting.GRAY, LocaleUtil.numToComponent(runeEntry.getIntValue()), LocaleUtil.getLocaleMessage(runeEntry.getKey().getDescriptionId())));
		}
	}

	public record StoredCasts(int stored, OptionalInt max) {
		public static final Codec<StoredCasts> CODEC = RecordCodecBuilder.create(builder -> builder.group(
				Codec.INT.fieldOf("stored")
						.forGetter(StoredCasts::stored),
				Codec.INT.optionalFieldOf("max")
						.xmap(optional -> optional.map(
										OptionalInt::of).orElseGet(OptionalInt::empty),
								optionalInt -> optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.<Integer>empty())
						.forGetter(StoredCasts::max)
		).apply(builder, StoredCasts::new));
		public static final StreamCodec<FriendlyByteBuf, StoredCasts> STREAM_CODEC = StreamCodec.composite(
				ByteBufCodecs.INT, StoredCasts::stored,
				StreamCodecUtil.OPTIONALINT, StoredCasts::max,
				StoredCasts::new);
		public static final StoredCasts DISABLED = new StoredCasts(-1, OptionalInt.of(-1));

		public static StoredCasts decrement(StoredCasts original) {
			return new StoredCasts(original.stored - 1, original.max);
		}

		public static StoredCasts increment(StoredCasts original) {
			return new StoredCasts(original.stored + 1, original.max);
		}

		public static Optional<StoredCasts> getIfPresent(ItemStack stack) {
			if (!stack.has(AoADataComponents.STORED_SPELL_CASTS))
				return Optional.empty();

			return Optional.of(stack.get(AoADataComponents.STORED_SPELL_CASTS));
		}
	}
}
