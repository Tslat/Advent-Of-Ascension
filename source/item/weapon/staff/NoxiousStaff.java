package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectile.staff.NoxiousShotEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class NoxiousStaff extends BaseStaff<Object> {
	public NoxiousStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_NOXIOUS_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 2);
		runes.put(AoAItems.POISON_RUNE.get(), 2);
		runes.put(AoAItems.STORM_RUNE.get(), 2);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new NoxiousShotEntity(caster, this, 60, 0, 0, 0));
		world.addFreshEntity(new NoxiousShotEntity(caster, this, 60, 0.075f, 0.075f, 0));
		world.addFreshEntity(new NoxiousShotEntity(caster, this, 60, -0.075f, 0, 0.075f));
		world.addFreshEntity(new NoxiousShotEntity(caster, this, 60, 0, -0.075f, -0.075f));
		world.addFreshEntity(new NoxiousShotEntity(caster, this, 60, -0.075f, 0.075f, -0.075f));
		world.addFreshEntity(new NoxiousShotEntity(caster, this, 60, -0.075f, -0.075f, 0.075f));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (DamageUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 100).level(3));

			return true;
		}

		return false;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d pos, LivingEntity shooter) {
		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(shot.level, shot.getX(), shot.getY(), shot.getZ());

		cloud.setRadius(3);
		cloud.setPotion(Potions.STRONG_POISON);
		cloud.addEffect(new EffectInstance(Effects.POISON, 100, 2, true, true));
		cloud.setDuration(3);
		cloud.setFixedColor(NumberUtil.RGB(51, 102, 0));
		cloud.setOwner(shooter);

		shot.level.addFreshEntity(cloud);
	}

	@Override
	public float getDmg() {
		return 3.5f;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
