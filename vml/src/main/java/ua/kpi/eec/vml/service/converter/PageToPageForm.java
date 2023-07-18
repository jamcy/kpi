package ua.kpi.eec.vml.service.converter;

import org.springframework.core.convert.converter.Converter;

import ua.kpi.eec.vml.model.entity.Page;
import ua.kpi.eec.vml.model.form.PageForm;

import static ua.kpi.eec.vml.model.entity.LanguageCode.*;

public class PageToPageForm implements Converter<Page, PageForm> {

	@Override
	public PageForm convert(Page page) {
		PageForm form = new PageForm();
		form.setId(page.getId());
		form.setSuffix(page.getUrlSuffix());
		form.setNameEn(page.getName().getContentByLanguage(EN));
		form.setNameUk(page.getName().getContentByLanguage(UK));
		form.setContentEn(page.getContent().getContentByLanguage(EN));
		form.setContentUk(page.getContent().getContentByLanguage(UK));
		return form;
	}

}
