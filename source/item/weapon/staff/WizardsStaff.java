package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectiles.staff.EntityPolymorphShot;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

public class WizardsStaff extends BaseStaff {
	private static HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>();

	static {
		runes.put(ItemRegister.runeWind, 2);
		runes.put(ItemRegister.runeDistortion, 2);
		runes.put(ItemRegister.runeLife, 2);
	}

	public WizardsStaff(SoundEvent sound, int durability) {
		super(sound, durability);
		setUnlocalizedName("WizardsStaff");
		setRegistryName("aoa3:wizards_staff");
	}

	@Override
	public HashMap<RuneItem, Integer> getRunes() {
		return runes;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		world.spawnEntity(new EntityPolymorphShot(caster, this, 60));

		if (itemRand.nextInt(50) == 0) {
			switch (itemRand.nextInt(7)) {
				case 0:
					world.createExplosion(null, caster.posX, caster.posY + caster.height + 0.1f, caster.posZ, 2.0f, false);
					break;
				case 1:
					staff.damageItem(20, caster);
					break;
				case 2:
					caster.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200, 0, true, false));
					break;
				case 3:
					for (int i = 0; i < 5; i++) {
						EntityChicken chicken = new EntityChicken(caster.world);

						chicken.setPosition(caster.posX, caster.posY, caster.posZ);
						caster.world.spawnEntity(chicken);
					}
					break;
				case 4:
					EntityZombie zombie = new EntityZombie(caster.world);

					zombie.setPosition(caster.posX, caster.posY, caster.posZ);
					caster.world.spawnEntity(zombie);
					break;
				case 5:
					caster.addVelocity(0, 2, 0);
					break;
				case 6:
					caster.setFire(10);
					break;
			}
		}
	}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target instanceof EntityLivingBase && !EntityUtil.isSpecExempt(target, shooter) && itemRand.nextInt(10) == 0) {
			EntityChicken chicken = new EntityChicken(shooter.world);

			chicken.setPosition(target.posX, target.posY, target.posZ);
			shooter.world.spawnEntity(chicken);
			target.setDead();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.WizardsStaff.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.specImmune", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.WizardsStaff.desc.2", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
