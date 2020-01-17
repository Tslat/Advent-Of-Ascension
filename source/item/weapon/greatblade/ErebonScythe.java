package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class ErebonScythe extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public ErebonScythe(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("ErebonScythe");
		setRegistryName("aoa3:erebon_scythe");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		float damagePercent = (float)dmg / dmgDealt;

		for (EntityLivingBase entity : target.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(target.posX, target.getEntityBoundingBox().minY, target.posZ, target.posX - 2, target.getEntityBoundingBox().minY + 1, target.posZ + 2), PredicateUtil.IS_HOSTILE_MOB)) {
			entity.setFire((int)(5 * damagePercent));
		}

		if (attacker instanceof EntityPlayerMP)
			PlayerUtil.addResourceToPlayer((EntityPlayer)attacker, Enums.Resources.SOUL, 1 * damagePercent);

		if (target instanceof EntityPlayerMP)
			PlayerUtil.consumeResource((EntityPlayer)target, Enums.Resources.SOUL, 1 * damagePercent, true);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ErebonScythe.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.scythe", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
