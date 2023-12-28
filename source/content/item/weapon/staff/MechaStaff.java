package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.LyonicShotEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MechaStaff extends BaseStaff<Object> {
	private static final AttributeModifier DEBUFF = new AttributeModifier(UUID.fromString("3a1413c7-055b-405c-8d89-d3270c94f133"), "AoAMechaStaffDebuff", -0.5, AttributeModifier.Operation.MULTIPLY_BASE);

	public MechaStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 2);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 1);
		runes.put(AoAItems.POWER_RUNE.get(), 1);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new LyonicShotEntity(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (target instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity)target;
			AttributeInstance armour = entity.getAttribute(Attributes.ARMOR);

			if (armour != null && armour.getValue() > 0 && !armour.hasModifier(DEBUFF)) {
				EntityUtil.applyAttributeModifierSafely(entity, Attributes.ARMOR, DEBUFF, false);

				if (!entity.level().isClientSide) {
					AABB bounds = entity.getBoundingBox();

					for (int i = 0; i < 8; i++) {
						((ServerLevel)entity.level()).sendParticles(ParticleTypes.TOTEM_OF_UNDYING, bounds.minX + RandomUtil.randomValueUpTo(entity.getBbWidth()), bounds.maxY + 0.1d, bounds.minZ + RandomUtil.randomValueUpTo(entity.getBbWidth()), 1, 0, 0, 0, 0);
					}
				}
			}

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
