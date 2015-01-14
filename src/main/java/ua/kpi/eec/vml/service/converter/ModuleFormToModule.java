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
	public Module convert(ModuleForm mf) {
		Module m = new Module();
		m.setId(mf.getId());
		m.setCode(mf.getShortName());
		m.setEmbed(mf.getEmbedCode());
		m.setRoom(roomDao.find(mf.getRoomId()));
		m.setImageUrl(mf.getImageUrl());
		I18n name = new I18n();
		I18n description = new I18n();
		I18n content = new I18n();
		name.setContentByLanguage(EN , mf.getNameEn());
		name.setContentByLanguage(UK, mf.getContentUk());
		description.setContentByLanguage(EN, mf.getDescriptionEn());
		description.setContentByLanguage(UK, mf.getDescriptionUk());
		content.setContentByLanguage(EN, mf.getContentEn());
		content.setContentByLanguage(UK, mf.getContentUk());
		m.setName(name);
		m.setDescription(description);
		m.setPageContent(content);
		return m;
	}

}
