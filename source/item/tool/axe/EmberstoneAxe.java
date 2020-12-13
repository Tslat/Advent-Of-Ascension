package net.tslat.aoa3.item.tool.axe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EmberstoneAxe extends BaseAxe implements SpecialHarvestTool {
	public EmberstoneAxe() {
		super(ItemUtil.customItemTier(2000, 10.0f, 5.5f, 5, 10, AoAItems.EMBERSTONE_INGOT));
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getState().getBlock().isIn(BlockTags.LOGS)) {
			if (e.getDrops().isEmpty())
				return;

			World world = (World)e.getWorld();
			BlockPos pos = e.getPos();

			if (!world.isRemote()) {
				e.getState().getBlock().dropXpOnBlockBreak(world, pos, 1 + random.nextInt(3));

				for (int i = 0; i < 5; i++) {
					((ServerWorld)world).spawnParticle(ParticleTypes.FLAME, pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), 1, 0, 0, 0, (double)0);
				}
			}

			if (e.getDrops().get(0).getItem() == Item.getItemFromBlock(e.getState().getBlock()))
				e.getDrops().remove(0);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
