package ua.kpi.eec.vml.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.I18n;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.model.form.ModuleForm;

import static ua.kpi.eec.vml.model.entity.LanguageCode.*;

public class ModuleFormToModule implements Converter<ModuleForm, Module> {

	@Autowired
	private RoomDao roomDao;
	
	@Override
	public Module convert(ModuleForm form) {
		Module module = new Module();
		module.setId(form.getId());
		module.setCode(form.getShortName());
		module.setEmbedHeight(form.getEmbedHeight());
		module.setEmbedWidth(form.getEmbedWidth());
		module.setEmbedCode(form.getEmbedCode());
		module.setRoom(roomDao.find(form.getRoomId()));
		module.setImageUrl(form.getImageUrl());
		I18n name = new I18n();
		I18n description = new I18n();
		I18n content = new I18n();
		name.setContentByLanguage(EN , form.getNameEn());
		name.setContentByLanguage(UK, form.getNameUk());
		System.out.println(form.getNameUk());
		description.setContentByLanguage(EN, form.getDescriptionEn());
		description.setContentByLanguage(UK, form.getDescriptionUk());
		content.setContentByLanguage(EN, form.getContentEn());
		content.setContentByLanguage(UK, form.getContentUk());
		module.setName(name);
		module.setDescription(description);
		module.setPageContent(content);
		return module;
	}

}
