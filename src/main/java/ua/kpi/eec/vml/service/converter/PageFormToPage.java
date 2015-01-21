package ua.kpi.eec.vml.service.converter;

import org.springframework.core.convert.converter.Converter;

import ua.kpi.eec.vml.model.entity.I18n;
import ua.kpi.eec.vml.model.entity.Page;
import ua.kpi.eec.vml.model.form.PageForm;

import static ua.kpi.eec.vml.model.entity.LanguageCode.*;

public class PageFormToPage implements Converter<PageForm, Page> {

	@Override
	public Page convert(PageForm form) {
		Page page = new Page();
		page.setId(form.getId());
		page.setUrlSuffix(form.getSuffix());
		
		I18n name = new I18n();
		name.setContentByLanguage(EN, form.getNameEn());
		name.setContentByLanguage(UK, form.getNameUk());
		page.setName(name);
		
		I18n content = new I18n();
		content.setContentByLanguage(EN, form.getContentEn());
		content.setContentByLanguage(UK, form.getContentUk());
		page.setContent(content);
		
		return page;
	}

}
