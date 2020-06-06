package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityNoxiousShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class NoxiousStaff extends BaseStaff {
	public NoxiousStaff(int durability) {
		super(durability);
		setTranslationKey("NoxiousStaff");
		setRegistryName("aoa3:noxious_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.NOXIOUS_STAFF_CAST;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.WIND_RUNE, 2);
		runes.put(ItemRegister.POISON_RUNE, 2);
		runes.put(ItemRegister.STORM_RUNE, 2);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, 0, 0, 0));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, 0.075f, 0.075f, 0));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, -0.075f, 0, 0.075f));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, 0, -0.075f, -0.075f));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, -0.075f, 0.075f, -0.075f));
		world.spawnEntity(new EntityNoxiousShot(caster, this, 60, -0.075f, -0.075f, 0.075f));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (EntityUtil.dealMagicDamage(shot, shooter, target, getDmg(), false)) {
			if (target instanceof EntityLivingBase)
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 2, true, true));

			return true;
		}

		return false;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos pos, EntityLivingBase shooter) {
		EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(shot.world, shot.posX, shot.posY, shot.posZ);

		cloud.setRadius(3);
		cloud.setPotion(PotionTypes.STRONG_POISON);
		cloud.addEffect(new PotionEffect(MobEffects.POISON, 100, 2, true, true));
		cloud.setDuration(3);
		cloud.setColor(Enums.RGBIntegers.TOXIC_GREEN);
		cloud.setOwner(shooter);

		shot.world.spawnEntity(cloud);
	}

	@Override
	public float getDmg() {
		return 3.5f;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.NoxiousStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
