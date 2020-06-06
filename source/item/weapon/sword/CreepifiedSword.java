package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.minions.EntityFriendlyCreeper;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class CreepifiedSword extends BaseSword {
	public CreepifiedSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("CreepifiedSword");
		setRegistryName("aoa3:creepified_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (!attacker.world.isRemote && itemRand.nextInt(10) == 0 && (!(attacker instanceof EntityPlayer) || attackCooldown > 0.75f)) {
			final EntityFriendlyCreeper creeper = new EntityFriendlyCreeper(target.world);

			creeper.setLocationAndAngles(target.posX, target.posY, target.posZ, itemRand.nextFloat() * 360.0f, 0.0f);
			creeper.setAttackTarget(target);
			target.world.spawnEntity(creeper);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CreepifiedSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
