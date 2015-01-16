package ua.kpi.eec.vml.service.converter;

import org.springframework.core.convert.converter.Converter;

import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.model.form.ModuleForm;

import static ua.kpi.eec.vml.model.entity.LanguageCode.*;


public class ModuleToModuleForm implements Converter<Module, ModuleForm> {

	@Override
	public ModuleForm convert(Module module) {
		ModuleForm form = new ModuleForm();
		form.setId(module.getId());
		form.setFolder(module.getFolder());
		form.setNameEn(module.getName().getContentByLanguage(EN));
		form.setNameUk(module.getName().getContentByLanguage(UK));
		form.setDescriptionEn(module.getDescription().getContentByLanguage(EN));
		form.setDescriptionUk(module.getDescription().getContentByLanguage(UK));
		form.setContentEn(module.getPageContent().getContentByLanguage(EN));
		form.setContentUk(module.getPageContent().getContentByLanguage(UK));
		form.setRoomId(module.getRoom().getId());
		form.setImageUrl(module.getImageUrl());
		form.setEmbedCode(module.getEmbedCode());
		form.setEmbedHeight(module.getEmbedHeight());
		form.setEmbedWidth(module.getEmbedWidth());
		return form;
	}

}
