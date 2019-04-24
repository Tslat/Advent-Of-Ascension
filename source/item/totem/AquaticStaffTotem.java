package net.tslat.aoa3.item.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.misc.EntityLottoIdol;

public class AquaticStaffTotem extends TotemItem {
	public AquaticStaffTotem() {
		super("AquaticStaffTotem", "aquatic_staff_totem");
	}

	@Override
	protected void summonTotemEntity(World world, EntityPlayer player, BlockPos pos) {
		EntityLottoIdol idolEntity = new EntityLottoIdol(world, new ItemStack(WeaponRegister.staffAquatic), new ItemStack(ItemRegister.tokensBorean));

		idolEntity.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
		world.spawnEntity(idolEntity);
	}
}
