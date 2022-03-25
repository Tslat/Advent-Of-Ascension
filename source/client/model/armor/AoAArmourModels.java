package net.tslat.aoa3.client.model.armor;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nullable;
import java.util.HashMap;

public final class AoAArmourModels {
	private static final HashMap<AoASkill, Pair<? extends SkillHelmetModel, ? extends SkillHelmetModel>> skillHelmetModels = new HashMap<AoASkill, Pair<? extends SkillHelmetModel, ? extends SkillHelmetModel>>(10);

	@Nullable
	public static SkillHelmetModel getSkillHelmetModel(AoASkill skill, boolean trim) {
		if (skillHelmetModels.isEmpty()) {
			skillHelmetModels.put(AoASkills.ALCHEMY.get(), Pair.of(new HelmOfTheAlchemistModel(), new HelmOfTheAlchemistTrimModel()));
			skillHelmetModels.put(AoASkills.CREATION.get(), Pair.of(new HelmOfTheCreatorModel(), new HelmOfTheCreatorTrimModel()));
			skillHelmetModels.put(AoASkills.DEXTERITY.get(), Pair.of(new HelmOfTheDextrousModel(), new HelmOfTheDextrousTrimModel()));
			skillHelmetModels.put(AoASkills.ENGINEERING.get(), Pair.of(new HelmOfTheTinkererModel(), new HelmOfTheTinkererTrimModel()));
			skillHelmetModels.put(AoASkills.EXTRACTION.get(), Pair.of(new HelmOfTheTreasurerModel(), new HelmOfTheTreasurerTrimModel()));
			skillHelmetModels.put(AoASkills.FARMING.get(), Pair.of(new HelmOfTheDryadModel(), new HelmOfTheDryadTrimModel()));
			skillHelmetModels.put(AoASkills.FAUNAMANCY.get(), Pair.of(new HelmOfTheOccultistModel(), new HelmOfTheOccultistTrimModel()));
			skillHelmetModels.put(AoASkills.HAULING.get(), Pair.of(new HelmOfTheTrawlerModel(), new HelmOfTheTrawlerTrimModel()));
			skillHelmetModels.put(AoASkills.IMBUING.get(), Pair.of(new HelmOfTheRitualistModel(), new HelmOfTheRitualistTrimModel()));
			skillHelmetModels.put(AoASkills.INNERVATION.get(), Pair.of(new HelmOfTheWarriorModel(), new HelmOfTheWarriorTrimModel()));
		}

		if (!skillHelmetModels.containsKey(skill))
			return null;

		return trim ? skillHelmetModels.get(skill).getSecond() : skillHelmetModels.get(skill).getFirst();
	}

	@Nullable
	public static ResourceLocation getSkillHelmetTexture(AoASkill skill, boolean trim) {
		SkillHelmetModel model = getSkillHelmetModel(skill, trim);

		return model == null ? null : model.getTexturePath();
	}
}
