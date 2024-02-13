package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.ShyreShotEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.MagicTeleportEvent;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class ShyreStaff extends BaseStaff<Object> {
	public ShyreStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_SHYRE_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 3);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 3);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new ShyreShotEntity(caster, this, 120));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter) {
		Level world = shooter.level();
		BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos(hitPos.x(), hitPos.y(), hitPos.z());
		BlockState state = world.getBlockState(testPos);
		Vec3 shotMotion = shot.getDeltaMovement();
		Vec3 testVec = hitPos;
		int tests = 0;

		while (tests <= 10 && !(state = world.getBlockState(testPos)).isAir()) {
			testVec = testVec.subtract(shotMotion.x() * 0.15f, shotMotion.y() * 0.15f, shotMotion.z() * 0.15f);
			testPos.set(testVec.x() + shooter.getBbWidth(), testVec.y(), testVec.z() + shooter.getBbWidth());
			tests++;
		}

		if (state.isAir()) {
			MagicTeleportEvent event = AoAEvents.magicalTeleport(shooter, null, null, testVec);

			if (event.isCanceled())
				return;

			testVec = event.getTarget();
		}

		shooter.teleportTo(testVec.x(), testVec.y(), testVec.z());

		if (shooter instanceof ServerPlayer && WorldUtil.isWorld(shooter.level(), AoADimensions.LUNALUS))
			AdvancementUtil.completeAdvancement((ServerPlayer)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus/200_iq"), "lunalus_shyre_staff_travel");
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		MagicTeleportEvent event = AoAEvents.magicalTeleport(shooter, null, null, new Vec3((target.getX() + shot.getX()) / 2d, (target.getY() + shot.getY()) / 2d, (target.getZ() + shot.getZ()) / 2d));

		if (event.isCanceled())
			return false;

		shooter.teleportTo(event.getTargetX(), event.getTargetY(), event.getTargetZ());

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
