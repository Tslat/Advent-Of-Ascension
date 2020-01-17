package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.BlockLog;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EmberstoneAxe extends BaseAxe implements SpecialHarvestTool {
	public EmberstoneAxe() {
		super("EmberstoneAxe", "emberstone_axe", MaterialsRegister.TOOL_EMBERSTONE);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getState().getBlock() instanceof BlockLog) {
			World world = e.getWorld();
			BlockPos pos = e.getPos();

			if (!world.isRemote) {
				e.getState().getBlock().dropXpOnBlockBreak(world, pos, 1 + itemRand.nextInt(3));

				for (int i = 0; i < 5; i++) {
					((WorldServer)world).spawnParticle(EnumParticleTypes.FLAME, pos.getX() + itemRand.nextFloat(), pos.getY() + itemRand.nextFloat(), pos.getZ() + itemRand.nextFloat(), 1, 0, 0, 0, (double)0);
				}
			}

			if (e.getDrops().get(0).getItem() == Item.getItemFromBlock(e.getState().getBlock()))
				e.getDrops().remove(0);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.EmberstoneAxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
