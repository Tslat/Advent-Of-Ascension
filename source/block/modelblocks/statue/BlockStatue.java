package net.nevermine.block.modelblocks.statue;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.nevermine.block.modelblocks.BlockMod;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import net.nevermine.izer.Itemizer;

public class BlockStatue extends BlockMod {
	public ModelEternalBlock model;
	public ResourceLocation texture;

	public BlockStatue(final ResourceLocation texture, final ModelEternalBlock model) {
		super(Material.rock);
		this.texture = texture;
		this.model = model;
		setHardness(25.0f);
		setResistance(2000.0f);
		setCreativeTab(Itemizer.FurnitureTab);
	}

	public boolean hasTileEntity(final int metadata) {
		return true;
	}

	public TileEntity createTileEntity(final World world, final int metadata) {
		return new TileEntityStatue(texture, model);
	}

	public int getRenderType() {
		return -1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockPlacedBy(final World w, final int x, final int y, final int z, final EntityLivingBase entity, final ItemStack item) {
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
		final int i1 = w.getBlockMetadata(x, y, z) >> 2;
		l = ++l % 4;

		if (l == 0) {
			w.setBlockMetadataWithNotify(x, y, z, 0x3 | i1 << 2, 2);
		}
		if (l == 1) {
			w.setBlockMetadataWithNotify(x, y, z, 0x2 | i1 << 2, 2);
		}
		if (l == 2) {
			w.setBlockMetadataWithNotify(x, y, z, 0x1 | i1 << 2, 2);
		}
		if (l == 3) {
			w.setBlockMetadataWithNotify(x, y, z, 0x0 | i1 << 2, 2);
		}
	}

	public boolean onBlockActivated(final World w, final int x, final int y, final int z, final EntityPlayer p, final int var6, final float var7, final float var8, final float var9) {
		if (p.isSneaking())
			return true;

		String sound = null;

		switch (w.getBlock(x, y, z).getUnlocalizedName()) {
			case "tile.SmashStatue":
			case "tile.SmashStatueGold":
				sound = "nevermine:SmashLiving1";
				break;
			case "tile.RockriderStatue":
			case "tile.RockriderStatueGold":
				sound = "nevermine:RockriderSwitch";
				break;
			case "tile.KingBamBamBamStatue":
			case "tile.KingBamBamBamStatueGold":
				sound = "nevermine:KingBamBamBamLiving1";
				break;
			case "tile.SkeletorStatue":
			case "tile.SkeletorStatueGold":
				sound = "nevermine:SkeletronLiving";
				break;
			case "tile.SkeleelderStatue":
			case "tile.SkeleelderStatueGold":
				sound = "mob.skeleton.say";
				break;
			case "tile.SkelepigStatue":
			case "tile.SkelepigStatueGold":
				sound = "mob.skeleton.say";
				break;
			case "tile.SkelemanStatue":
			case "tile.SkelemanStatueGold":
				sound = "mob.skeleton.say";
				break;
			case "tile.SkeleHopperStatue":
			case "tile.SkeleHopperStatueGold":
				sound = "mob.skeleton.say";
				break;
			case "tile.GuardianStatueBlue":
			case "tile.GuardianStatueBlueGold":
				sound = "nevermine:GuardianDeath";
				break;
			case "tile.GuardianStatueGreen":
			case "tile.GuardianStatueGreenGold":
				sound = "nevermine:GuardianDeath";
				break;
			case "tile.GuardianStatueYellow":
			case "tile.GuardianStatueYellowGold":
				sound = "nevermine:GuardianDeath";
				break;
			case "tile.GuardianStatueRed":
			case "tile.GuardianStatueRedGold":
				sound = "nevermine:GuardianDeath";
				break;
			case "tile.ShadowlordStatue":
			case "tile.ShadowlordStatueGold":
				sound = "nevermine:ShadowlordLiving";
				break;
			case "tile.ElusiveStatue":
			case "tile.ElusiveStatueGold":
				sound = "nevermine:ElusiveLiving1";
				break;
			case "tile.NethengeicWitherStatue":
			case "tile.NethengeicWitherStatueGold":
				sound = "nevermine:NethengeicWitherLiving1";
				break;
			case "tile.CorallusStatue":
			case "tile.CorallusStatueGold":
				sound = "nevermine:CorallusLiving";
				break;
			case "tile.HarkosStatue":
			case "tile.HarkosStatueGold":
				sound = "nevermine:PrimordialLiving";
				break;
			case "tile.KajarosStatue":
			case "tile.KajarosStatueGold":
				sound = "nevermine:PrimordialLiving";
				break;
			case "tile.OkazorStatue":
			case "tile.OkazorStatueGold":
				sound = "nevermine:PrimordialLiving";
				break;
			case "tile.MiskelStatue":
			case "tile.MiskelStatueGold":
				sound = "nevermine:PrimordialLiving";
				break;
			case "tile.RaxxanStatue":
			case "tile.RaxxanStatueGold":
				sound = "nevermine:PrimordialLiving";
				break;
			case "tile.DracyonStatue":
			case "tile.DracyonStatueGold":
				sound = "nevermine:DracyonLiving";
				break;
			case "tile.KrorStatue":
			case "tile.KrorStatueGold":
				sound = "nevermine:KrorLiving";
				break;
			case "tile.VoxxulonStatue":
			case "tile.VoxxulonStatueGold":
				sound = "nevermine:VoxxulonLiving";
				break;
			case "tile.HiveKingStatue":
			case "tile.HiveKingStatueGold":
				sound = "nevermine:HiveKingLiving";
				break;
			case "tile.VinocorneStatue":
			case "tile.VinocorneStatueGold":
				sound = "nevermine:EntLiving";
				break;
			case "tile.ClunkheadStatue":
			case "tile.ClunkheadStatueGold":
				sound = "nevermine:ClunkheadDeath";
				break;
			case "tile.KingShroomusStatue":
			case "tile.KingShroomusStatueGold":
				sound = "nevermine:FungiLiving";
				break;
			case "tile.BaneStatue":
			case "tile.BaneStatueGold":
				sound = "nevermine:BaneLiving";
				break;
			case "tile.VisualentStatue":
			case "tile.VisualentStatueGold":
				sound = "nevermine:VisularLiving";
				break;
			case "tile.SilverfootStatue":
			case "tile.SilverfootStatueGold":
				sound = "random.anvil_land";
				break;
			case "tile.GoldorthStatue":
			case "tile.GoldorthStatueGold":
				sound = "nevermine:GoldorthLiving";
				break;
			case "tile.HoronStatue":
			case "tile.HoronStatueGold":
				sound = "nevermine:HoronLiving";
				break;
			case "tile.PenumbraStatue":
			case "tile.PenumbraStatueGold":
				sound = "nevermine:PenumbraLiving";
				break;
			case "tile.ConiferonStatue":
			case "tile.ConiferonStatueGold":
				sound = "nevermine:ConiferonLiving";
				break;
			case "tile.GrawStatue":
			case "tile.GrawStatueGold":
				sound = "nevermine:GrawLiving";
				break;
			case "tile.GyroStatue":
			case "tile.GyroStatueGold":
				sound = "nevermine:GyroLiving";
				break;
			case "tile.CrystocoreStatue":
			case "tile.CrystocoreStatueGold":
				sound = "nevermine:CrystalConstructLiving1";
				break;
			case "tile.CottonCandorStatue":
			case "tile.CottonCandorStatueGold":
				sound = "nevermine:CottonCandorLiving";
				break;
			case "tile.CreepStatue":
			case "tile.CreepStatueGold":
				sound = "nevermine:CreepoidLiving";
				break;
			case "tile.CraexxeusStatue":
			case "tile.CraexxeusStatueGold":
				sound = "nevermine:CraexxeusLiving1";
				break;
			case "tile.XxeusStatue":
			case "tile.XxeusStatueGold":
				sound = "nevermine:XxeusLiving";
				break;
			default:
				break;
		}

		if (sound != null)
			w.playSoundAtEntity(p, sound, 1.0f, 1.0f);

		return true;
	}

	@Override
	public BlockStatue setName(final String name) {
		setBlockTextureName("cobblestone");
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
