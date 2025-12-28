package net.tslat.aoa3.common.registration.item;

import com.mojang.serialization.Codec;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.block.functional.misc.TrophyBlock;
import net.tslat.aoa3.content.item.datacomponent.*;
import net.tslat.aoa3.content.item.weapon.staff.AoAStaff;
import net.tslat.aoa3.library.object.container.AmmoVoidPouchComponent;
import net.tslat.aoa3.library.object.container.CachedEntity;
import net.tslat.aoa3.library.object.extension.MutableFloat;

import java.util.Optional;
import java.util.UUID;
import java.util.function.UnaryOperator;

public final class AoADataComponents {
	public static void init() {}

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> CHARGE = register("charge", builder -> builder.persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Optional<UUID>>> LAST_TARGET = register("last_target", builder -> builder.networkSynchronized(UUIDUtil.STREAM_CODEC.apply(ByteBufCodecs::optional)));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Float>> DAMAGE_SCALING = register("damage_scaling", builder -> builder.networkSynchronized(ByteBufCodecs.FLOAT));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<MutableFloat>> MELEE_SWING_STRENGTH = register("melee_swing_strength", builder -> builder.networkSynchronized(MutableFloat.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> RESERVED_ITEM_STAGE = register("reserved_item_stage", builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<AoAStaff.StoredCasts>> STORED_SPELL_CASTS = register("stored_spell_casts", builder -> builder.persistent(AoAStaff.StoredCasts.CODEC).networkSynchronized(AoAStaff.StoredCasts.STREAM_CODEC));

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlasterStats>> BLASTER_STATS = register("blaster_stats", builder -> builder.persistent(BlasterStats.CODEC).networkSynchronized(BlasterStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<SniperScope>> SNIPER_SCOPE = register("sniper_scope", builder -> builder.persistent(SniperScope.CODEC).networkSynchronized(SniperScope.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<BowStats>> BOW_STATS = register("bow_stats", builder -> builder.persistent(BowStats.CODEC).networkSynchronized(BowStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<CrossbowStats>> CROSSBOW_STATS = register("crossbow_stats", builder -> builder.persistent(CrossbowStats.CODEC).networkSynchronized(CrossbowStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<GunStats>> GUN_STATS = register("gun_stats", builder -> builder.persistent(GunStats.CODEC).networkSynchronized(GunStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ShotgunStats>> SHOTGUN_STATS = register("shotgun_stats", builder -> builder.persistent(ShotgunStats.CODEC).networkSynchronized(ShotgunStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<VulcaneStats>> VULCANE_STATS = register("vulcane_stats", builder -> builder.persistent(VulcaneStats.CODEC).networkSynchronized(VulcaneStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ThrownWeaponStats>> THROWN_WEAPON_STATS = register("thrown_weapon_stats", builder -> builder.persistent(ThrownWeaponStats.CODEC).networkSynchronized(ThrownWeaponStats.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<StaffStats>> STAFF_STATS = register("staff_stats", builder -> builder.persistent(StaffStats.CODEC).networkSynchronized(StaffStats.STREAM_CODEC));

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<CachedEntity<?>>> STORED_ENTITY = register("stored_entity", builder -> builder.persistent(CachedEntity.CODEC).networkSynchronized(CachedEntity.STREAM_CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<AmmoVoidPouchComponent>> AMMO_VOID_POUCH = register("ammo_void_pouch", builder -> builder.persistent(AmmoVoidPouchComponent.CODEC).networkSynchronized(AmmoVoidPouchComponent.STREAM_CODEC));

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<CompressedItemData>> COMPRESSED_ITEM_DATA = register("compressed_item_data", builder -> builder.persistent(CompressedItemData.CODEC).networkSynchronized(CompressedItemData.STREAM_CODEC));

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<TrophyBlock.TrophyData>> TROPHY_DATA = register("trophy_data", builder -> builder.persistent(TrophyBlock.TrophyData.CODEC).networkSynchronized(TrophyBlock.TrophyData.STREAM_CODEC));

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemUseSound>> ITEM_USE_SOUND = register("item_use_sound", builder -> builder.persistent(ItemUseSound.CODEC).networkSynchronized(ItemUseSound.STREAM_CODEC));

	private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
		return AoARegistries.DATA_COMPONENTS.register(id, () -> builder.apply(DataComponentType.builder()).build());
	}
}
