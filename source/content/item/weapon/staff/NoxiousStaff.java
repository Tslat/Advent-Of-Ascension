package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.NoxiousShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

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
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.WIND_RUNE.get(), 2);
		runes.put(AoAItems.POISON_RUNE.get(), 2);
		runes.put(AoAItems.STORM_RUNE.get(), 2);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, Object args) {
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
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.POISON, 100).level(3));

			return true;
		}

		return false;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 pos, LivingEntity shooter) {
		AreaEffectCloud cloud = new AreaEffectCloud(shot.level, shot.getX(), shot.getY(), shot.getZ());

		cloud.setRadius(3);
		cloud.setPotion(Potions.STRONG_POISON);
		cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2, true, true));
		cloud.setDuration(3);
		cloud.setFixedColor(ColourUtil.RGB(51, 102, 0));
		cloud.setOwner(shooter);

		shot.level.addFreshEntity(cloud);
	}

	@Override
	public float getDmg() {
		return 7f;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
