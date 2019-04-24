package net.nevermine.assist;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.izer.equipment.Armorizer;

public class ArmorUtil {
	public static boolean checkHelmetSetMatch(final Item helmet, final Item setHelmet) {
		return helmet == setHelmet || helmet == Armorizer.SealordHelmet || helmet == Armorizer.OceanusHelmet || helmet == Armorizer.AchelosHelmet || helmet == Armorizer.FaceMask || helmet == Armorizer.NightVisionGoggles;
	}

	public static boolean isAlacrityArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.AlacrityBoots && legs == Armorizer.AlacrityLeggings & chest == Armorizer.AlacrityChestplate && checkHelmetSetMatch(helmet, Armorizer.AlacrityHelmet);
	}

	public static boolean isAmethindArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.AmethindBoots && legs == Armorizer.AmethindLeggings & chest == Armorizer.AmethindChestplate && checkHelmetSetMatch(helmet, Armorizer.AmethindHelmet);
	}

	public static boolean isAnimaArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.AnimaBoots && legs == Armorizer.AnimaLeggings & chest == Armorizer.AnimaChestplate && checkHelmetSetMatch(helmet, Armorizer.AnimaHelmet);
	}

	public static boolean isArchaicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ArchaicBoots && legs == Armorizer.ArchaicLeggings & chest == Armorizer.ArchaicChestplate && checkHelmetSetMatch(helmet, Armorizer.ArchaicHelmet);
	}

	public static boolean isAuguryArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.AuguryBoots && legs == Armorizer.AuguryLeggings & chest == Armorizer.AuguryChestplate && checkHelmetSetMatch(helmet, Armorizer.AuguryHelmet);
	}

	public static boolean isBaronArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.BaronBoots && legs == Armorizer.BaronLeggings & chest == Armorizer.BaronChestplate && checkHelmetSetMatch(helmet, Armorizer.BaronHelmet);
	}

	public static boolean isBiogenicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.BiogenicBoots && legs == Armorizer.BiogenicLeggings & chest == Armorizer.BiogenicChestplate && checkHelmetSetMatch(helmet, Armorizer.BiogenicHelmet);
	}

	public static boolean isBoreicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.BoreicBoots && legs == Armorizer.BoreicLeggings & chest == Armorizer.BoreicChestplate && checkHelmetSetMatch(helmet, Armorizer.BoreicHelmet);
	}

	public static boolean isButcheryArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ButcheryBoots && legs == Armorizer.ButcheryLeggings & chest == Armorizer.ButcheryChestplate && checkHelmetSetMatch(helmet, Armorizer.ButcheryHelmet);
	}

	public static boolean isCandyArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.CandyBoots && legs == Armorizer.CandyLeggings & chest == Armorizer.CandyChestplate && checkHelmetSetMatch(helmet, Armorizer.CandyHelmet);
	}

	public static boolean isCommanderArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.CommanderBoots && legs == Armorizer.CommanderLeggings & chest == Armorizer.CommanderChestplate && checkHelmetSetMatch(helmet, Armorizer.CommanderHelmet);
	}

	public static boolean isCreationArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.CreationBoots && legs == Armorizer.CreationLeggings & chest == Armorizer.CreationChestplate && checkHelmetSetMatch(helmet, Armorizer.CreationHelmet);
	}

	public static boolean isCrystallisArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.CrystallisBoots && legs == Armorizer.CrystallisLeggings & chest == Armorizer.CrystallisChestplate && checkHelmetSetMatch(helmet, Armorizer.CrystallisHelmet);
	}

	public static boolean isDemonicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.DemonicBoots && legs == Armorizer.DemonicLeggings & chest == Armorizer.DemonicChestplate && checkHelmetSetMatch(helmet, Armorizer.DemonicHelmet);
	}

	public static boolean isElecanyteArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ElecanyteBoots && legs == Armorizer.ElecanyteLeggings & chest == Armorizer.ElecanyteChestplate && checkHelmetSetMatch(helmet, Armorizer.ElecanyteHelmet);
	}

	public static boolean isEmbrodiumArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.EmbrodiumBoots && legs == Armorizer.EmbrodiumLeggings & chest == Armorizer.EmbrodiumChestplate && checkHelmetSetMatch(helmet, Armorizer.EmbrodiumHelmet);
	}

	public static boolean isExoplateArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ExoplateBoots && legs == Armorizer.ExoplateLeggings & chest == Armorizer.ExoplateChestplate && checkHelmetSetMatch(helmet, Armorizer.ExoplateHelmet);
	}

	public static boolean isExpeditionArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ExpeditionBoots && legs == Armorizer.ExpeditionLeggings & chest == Armorizer.ExpeditionChestplate && checkHelmetSetMatch(helmet, Armorizer.ExpeditionHelmet);
	}

	public static boolean isExplosiveArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ExplosiveBoots && legs == Armorizer.ExplosiveLeggings & chest == Armorizer.ExplosiveChestplate && checkHelmetSetMatch(helmet, Armorizer.ExplosiveHelmet);
	}

	public static boolean isForagingArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ForagingBoots && legs == Armorizer.ForagingLeggings & chest == Armorizer.ForagingChestplate && checkHelmetSetMatch(helmet, Armorizer.ForagingHelmet);
	}

	public static boolean isFungalArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.FungalBoots && legs == Armorizer.FungalLeggings & chest == Armorizer.FungalChestplate && checkHelmetSetMatch(helmet, Armorizer.FungalHelmet);
	}

	public static boolean isGhastlyArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.GhastlyBoots && legs == Armorizer.GhastlyLeggings & chest == Armorizer.GhastlyChestplate && checkHelmetSetMatch(helmet, Armorizer.GhastlyHelmet);
	}

	public static boolean isGhoulishArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.GhoulishBoots && legs == Armorizer.GhoulishLeggings & chest == Armorizer.GhoulishChestplate && checkHelmetSetMatch(helmet, Armorizer.GhoulishHelmet);
	}

	public static boolean isHaulingArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.HaulingBoots && legs == Armorizer.HaulingLeggings & chest == Armorizer.HaulingChestplate && checkHelmetSetMatch(helmet, Armorizer.HaulingHelmet);
	}

	public static boolean isHazmatArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.HazmatBoots && legs == Armorizer.HazmatLeggings & chest == Armorizer.HazmatChestplate && checkHelmetSetMatch(helmet, Armorizer.HazmatHelmet);
	}

	public static boolean isHermetismArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.HermetismBoots && legs == Armorizer.HermetismLeggings & chest == Armorizer.HermetismChestplate && checkHelmetSetMatch(helmet, Armorizer.HermetismHelmet);
	}

	public static boolean isHunterArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.HunterBoots && legs == Armorizer.HunterLeggings & chest == Armorizer.HunterChestplate && checkHelmetSetMatch(helmet, Armorizer.HunterHelmet);
	}

	public static boolean isHydrangicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.HydrangicBoots && legs == Armorizer.HydrangicLeggings & chest == Armorizer.HydrangicChestplate && checkHelmetSetMatch(helmet, Armorizer.HydrangicHelmet);
	}

	public static boolean isHydroplateArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.HydroplateBoots && legs == Armorizer.HydroplateLeggings & chest == Armorizer.HydroplateChestplate && checkHelmetSetMatch(helmet, Armorizer.HydroplateHelmet);
	}

	public static boolean isIceArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.IceBoots && legs == Armorizer.IceLeggings & chest == Armorizer.IceChestplate && checkHelmetSetMatch(helmet, Armorizer.IceHelmet);
	}

	public static boolean isInfernalArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.InfernalBoots && legs == Armorizer.InfernalLeggings & chest == Armorizer.InfernalChestplate && checkHelmetSetMatch(helmet, Armorizer.InfernalHelmet);
	}

	public static boolean isInfusionArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.InfusionBoots && legs == Armorizer.InfusionLeggings & chest == Armorizer.InfusionChestplate && checkHelmetSetMatch(helmet, Armorizer.InfusionHelmet);
	}

	public static boolean isInnervationArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.InnervationBoots && legs == Armorizer.InnervationLeggings & chest == Armorizer.InnervationChestplate && checkHelmetSetMatch(helmet, Armorizer.InnervationHelmet);
	}

	public static boolean isLoggingArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.LoggingBoots && legs == Armorizer.LoggingLeggings & chest == Armorizer.LoggingChestplate && checkHelmetSetMatch(helmet, Armorizer.LoggingHelmet);
	}

	public static boolean isLunarArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.LunarBoots && legs == Armorizer.LunarLeggings & chest == Armorizer.LunarChestplate && checkHelmetSetMatch(helmet, Armorizer.LunarHelmet);
	}

	public static boolean isLyndamyteArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.LyndamyteBoots && legs == Armorizer.LyndamyteLeggings & chest == Armorizer.LyndamyteChestplate && checkHelmetSetMatch(helmet, Armorizer.LyndamyteHelmet);
	}

	public static boolean isLyonicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.LyonicBoots && legs == Armorizer.LyonicLeggings & chest == Armorizer.LyonicChestplate && checkHelmetSetMatch(helmet, Armorizer.LyonicHelmet);
	}

	public static boolean isMercurialArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.MercurialBoots && legs == Armorizer.MercurialLeggings & chest == Armorizer.MercurialChestplate && checkHelmetSetMatch(helmet, Armorizer.MercurialHelmet);
	}

	public static boolean isNecroArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.NecroBoots && legs == Armorizer.NecroLeggings & chest == Armorizer.NecroChestplate && checkHelmetSetMatch(helmet, Armorizer.NecroHelmet);
	}

	public static boolean isNethengeicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.NethengeicBoots && legs == Armorizer.NethengeicLeggings & chest == Armorizer.NethengeicChestplate && checkHelmetSetMatch(helmet, Armorizer.NethengeicHelmet);
	}

	public static boolean isNightmareArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.NightmareBoots && legs == Armorizer.NightmareLeggings & chest == Armorizer.NightmareChestplate && checkHelmetSetMatch(helmet, Armorizer.NightmareHelmet);
	}

	public static boolean isOmniArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.OmniBoots && legs == Armorizer.OmniLeggings & chest == Armorizer.OmniChestplate && checkHelmetSetMatch(helmet, Armorizer.OmniHelmet);
	}

	public static boolean isPhantasmArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.PhantasmBoots && legs == Armorizer.PhantasmLeggings & chest == Armorizer.PhantasmChestplate && checkHelmetSetMatch(helmet, Armorizer.PhantasmHelmet);
	}

	public static boolean isPoisonArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.PoisonBoots && legs == Armorizer.PoisonLeggings & chest == Armorizer.PoisonChestplate && checkHelmetSetMatch(helmet, Armorizer.PoisonHelmet);
	}

	public static boolean isPredatiousArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.PredatiousBoots && legs == Armorizer.PredatiousLeggings & chest == Armorizer.PredatiousChestplate && checkHelmetSetMatch(helmet, Armorizer.PredatiousHelmet);
	}

	public static boolean isPrimordialArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.PrimordialBoots && legs == Armorizer.PrimordialLeggings & chest == Armorizer.PrimordialChestplate && checkHelmetSetMatch(helmet, Armorizer.PrimordialHelmet);
	}

	public static boolean isPurityArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.PurityBoots && legs == Armorizer.PurityLeggings & chest == Armorizer.PurityChestplate && checkHelmetSetMatch(helmet, Armorizer.PurityHelmet);
	}

	public static boolean isRockboneArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.RockboneBoots && legs == Armorizer.RockboneLeggings & chest == Armorizer.RockboneChestplate && checkHelmetSetMatch(helmet, Armorizer.RockboneHelmet);
	}

	public static boolean isRosidianArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.RosidianBoots && legs == Armorizer.RosidianLeggings & chest == Armorizer.RosidianChestplate && checkHelmetSetMatch(helmet, Armorizer.RosidianHelmet);
	}

	public static boolean isRunationArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.RunationBoots && legs == Armorizer.RunationLeggings & chest == Armorizer.RunationChestplate && checkHelmetSetMatch(helmet, Armorizer.RunationHelmet);
	}

	public static boolean isRunicArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.RunicBoots && legs == Armorizer.RunicLeggings & chest == Armorizer.RunicChestplate && checkHelmetSetMatch(helmet, Armorizer.RunicHelmet);
	}

	public static boolean isSharpshotArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.SharpshotBoots && legs == Armorizer.SharpshotLeggings & chest == Armorizer.SharpshotChestplate && checkHelmetSetMatch(helmet, Armorizer.SharpshotHelmet);
	}

	public static boolean isSkeletalArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.SkeletalBoots && legs == Armorizer.SkeletalLeggings & chest == Armorizer.SkeletalChestplate && checkHelmetSetMatch(helmet, Armorizer.SkeletalHelmet);
	}

	public static boolean isSpaceKingArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.SpaceKingBoots && legs == Armorizer.SpaceKingLeggings & chest == Armorizer.SpaceKingChestplate && checkHelmetSetMatch(helmet, Armorizer.SpaceKingHelmet);
	}

	public static boolean isSpeedArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.SpeedBoots && legs == Armorizer.SpeedLeggings & chest == Armorizer.SpeedChestplate && checkHelmetSetMatch(helmet, Armorizer.SpeedHelmet);
	}

	public static boolean isSubterraneanArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.SubterraneanBoots && legs == Armorizer.SubterraneanLeggings & chest == Armorizer.SubterraneanChestplate && checkHelmetSetMatch(helmet, Armorizer.SubterraneanHelmet);
	}

	public static boolean isThermalArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ThermalBoots && legs == Armorizer.ThermalLeggings & chest == Armorizer.ThermalChestplate && checkHelmetSetMatch(helmet, Armorizer.ThermalHelmet);
	}

	public static boolean isUtopianArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.UtopianBoots && legs == Armorizer.UtopianLeggings & chest == Armorizer.UtopianChestplate && checkHelmetSetMatch(helmet, Armorizer.UtopianHelmet);
	}

	public static boolean isVitalityArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.VitalityBoots && legs == Armorizer.VitalityLeggings & chest == Armorizer.VitalityChestplate && checkHelmetSetMatch(helmet, Armorizer.VitalityHelmet);
	}

	public static boolean isVoidArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.VoidBoots && legs == Armorizer.VoidLeggings & chest == Armorizer.VoidChestplate && checkHelmetSetMatch(helmet, Armorizer.VoidHelmet);
	}

	public static boolean isVulcanismArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.VulcanismBoots && legs == Armorizer.VulcanismLeggings & chest == Armorizer.VulcanismChestplate && checkHelmetSetMatch(helmet, Armorizer.VulcanismHelmet);
	}

	public static boolean isWeakenArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.WeakenBoots && legs == Armorizer.WeakenLeggings & chest == Armorizer.WeakenChestplate && checkHelmetSetMatch(helmet, Armorizer.WeakenHelmet);
	}

	public static boolean isWitherArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.WitherBoots && legs == Armorizer.WitherLeggings & chest == Armorizer.WitherChestplate && checkHelmetSetMatch(helmet, Armorizer.WitherHelmet);
	}

	public static boolean isZargoniteArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.ZargoniteBoots && legs == Armorizer.ZargoniteLeggings & chest == Armorizer.ZargoniteChestplate && checkHelmetSetMatch(helmet, Armorizer.ZargoniteHelmet);
	}

	public static boolean isKnightArmor(final Item boots, final Item legs, final Item chest, final Item helmet) {
		return boots == Armorizer.KnightBoots && legs == Armorizer.KnightLeggings & chest == Armorizer.KnightChestplate && checkHelmetSetMatch(helmet, Armorizer.KnightHelmet);
	}

	public static boolean isSharpshotArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return isSharpshotArmor(boots, legs, body, helmet);
	}

	public static boolean isPurityArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return isPurityArmor(boots, legs, body, helmet);
	}

	public static boolean isNightmareArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return isNightmareArmor(boots, legs, body, helmet);
	}

	public static boolean isGhoulishArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return isGhoulishArmor(boots, legs, body, helmet);
	}
}
