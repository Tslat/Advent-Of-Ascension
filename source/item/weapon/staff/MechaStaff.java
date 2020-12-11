package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectile.staff.LyonicShotEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
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
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 2);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 1);
		runes.put(AoAItems.POWER_RUNE.get(), 1);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Object args) {
		world.addEntity(new LyonicShotEntity(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (target instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity)target;
			IAttributeInstance armour = entity.getAttribute(SharedMonsterAttributes.ARMOR);

			if (armour != null && armour.getValue() > 0 && !armour.hasModifier(DEBUFF)) {
				EntityUtil.applyAttributeModifierSafely(entity, SharedMonsterAttributes.ARMOR, DEBUFF);

				if (!entity.world.isRemote) {
					AxisAlignedBB bounds = entity.getBoundingBox();

					for (int i = 0; i < 8; i++) {
						((ServerWorld)entity.world).spawnParticle(ParticleTypes.TOTEM_OF_UNDYING, bounds.minX + RandomUtil.randomValueUpTo(entity.getWidth()), bounds.maxY + 0.1d, bounds.minZ + RandomUtil.randomValueUpTo(entity.getWidth()), 1, 0, 0, 0, 0);
					}
				}
			}

			return true;
		}

		return false;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
