package net.tslat.aoa3.client.model.armor;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.library.object.CachedFunction;
import net.tslat.aoa3.player.skill.AoASkill;

import javax.annotation.Nonnull;
import java.util.HashMap;

public final class AoAArmourModels {
	private static final HashMap<AoASkill, Pair<CachedFunction<EntityModelSet, ? extends SkillHelmetModel>, CachedFunction<EntityModelSet, ? extends SkillHelmetModel>>> skillHelmetModels = new HashMap<>(10);

	public static void init(EntityRenderersEvent.RegisterLayerDefinitions ev) {
		ev.registerLayerDefinition(HelmOfTheAlchemistModel.LAYER_LOCATION, HelmOfTheAlchemistModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheAlchemistTrimModel.LAYER_LOCATION, HelmOfTheAlchemistTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheCreatorModel.LAYER_LOCATION, HelmOfTheCreatorModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheCreatorTrimModel.LAYER_LOCATION, HelmOfTheCreatorTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheDextrousModel.LAYER_LOCATION, HelmOfTheDextrousModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheDextrousTrimModel.LAYER_LOCATION, HelmOfTheDextrousTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheTinkererModel.LAYER_LOCATION, HelmOfTheTinkererModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheTinkererTrimModel.LAYER_LOCATION, HelmOfTheTinkererTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheTreasurerModel.LAYER_LOCATION, HelmOfTheTreasurerModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheTreasurerTrimModel.LAYER_LOCATION, HelmOfTheTreasurerTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheDryadModel.LAYER_LOCATION, HelmOfTheDryadModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheDryadTrimModel.LAYER_LOCATION, HelmOfTheDryadTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheOccultistModel.LAYER_LOCATION, HelmOfTheDryadTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheOccultistTrimModel.LAYER_LOCATION, HelmOfTheOccultistTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheTrawlerModel.LAYER_LOCATION, HelmOfTheTrawlerModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheTrawlerTrimModel.LAYER_LOCATION, HelmOfTheTrawlerTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheRitualistModel.LAYER_LOCATION, HelmOfTheRitualistModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheRitualistTrimModel.LAYER_LOCATION, HelmOfTheRitualistTrimModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheWarriorModel.LAYER_LOCATION, HelmOfTheWarriorModel::createLayerDefinition);
		ev.registerLayerDefinition(HelmOfTheWarriorTrimModel.LAYER_LOCATION, HelmOfTheWarriorTrimModel::createLayerDefinition);
	}

	public static void generateFactories() {
		skillHelmetModels.clear();

		skillHelmetModels.put(AoASkills.ALCHEMY.get(), Pair.of(CachedFunction.of(HelmOfTheAlchemistModel.modelFactory()), CachedFunction.of(HelmOfTheAlchemistTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.CREATION.get(), Pair.of(CachedFunction.of(HelmOfTheCreatorModel.modelFactory()), CachedFunction.of(HelmOfTheCreatorTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.DEXTERITY.get(), Pair.of(CachedFunction.of(HelmOfTheDextrousModel.modelFactory()), CachedFunction.of(HelmOfTheDextrousTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.ENGINEERING.get(), Pair.of(CachedFunction.of(HelmOfTheTinkererModel.modelFactory()), CachedFunction.of(HelmOfTheTinkererTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.EXTRACTION.get(), Pair.of(CachedFunction.of(HelmOfTheTreasurerModel.modelFactory()), CachedFunction.of(HelmOfTheTreasurerTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.FARMING.get(), Pair.of(CachedFunction.of(HelmOfTheDryadModel.modelFactory()), CachedFunction.of(HelmOfTheDryadTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.FAUNAMANCY.get(), Pair.of(CachedFunction.of(HelmOfTheOccultistModel.modelFactory()), CachedFunction.of(HelmOfTheOccultistTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.HAULING.get(), Pair.of(CachedFunction.of(HelmOfTheTrawlerModel.modelFactory()), CachedFunction.of(HelmOfTheTrawlerTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.IMBUING.get(), Pair.of(CachedFunction.of(HelmOfTheRitualistModel.modelFactory()), CachedFunction.of(HelmOfTheRitualistTrimModel.modelFactory())));
		skillHelmetModels.put(AoASkills.INNERVATION.get(), Pair.of(CachedFunction.of(HelmOfTheWarriorModel.modelFactory()), CachedFunction.of(HelmOfTheWarriorTrimModel.modelFactory())));
	}

	@Nonnull
	public static Model getSkillHelmetModel(AoASkill skill, boolean trim, HumanoidModel<?> base) {
		if (!skillHelmetModels.containsKey(skill))
			return base;

		EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
		SkillHelmetModel model = trim ? skillHelmetModels.get(skill).getSecond().apply(modelSet) : skillHelmetModels.get(skill).getFirst().apply(modelSet);

		model.setProperties(base);

		return model;
	}
}