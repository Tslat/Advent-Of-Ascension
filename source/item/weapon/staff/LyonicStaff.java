package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityLyonicShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class LyonicStaff extends BaseStaff {
	public LyonicStaff(int durability) {
		super(durability);
		setTranslationKey("LyonicStaff");
		setRegistryName("aoa3:lyonic_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.BASIC_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.ENERGY_RUNE, 1);
		runes.put(ItemRegister.WIND_RUNE, 1);
		runes.put(ItemRegister.WITHER_RUNE, 2);
		runes.put(ItemRegister.STRIKE_RUNE, 1);
	}

	@Nullable
	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		List<EntityLivingBase> targets = caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(10, 1, 10), entity -> entity instanceof IMob && entity.isEntityAlive());

		return targets.isEmpty() ? null : targets;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		for (float x = -1; x <= 1; x += 0.125f) {
			for (float z = -1; z <= 1; z += 0.125f) {
				world.spawnEntity(new EntityLyonicShot(caster, this, 1, x, 0, z));
			}
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target instanceof IMob) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1, false, true));

			if (itemRand.nextInt(150) == 0)
				target.world.addWeatherEffect(new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, false));

			return true;
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyonicStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyonicStaff.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyonicStaff.desc.3", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
