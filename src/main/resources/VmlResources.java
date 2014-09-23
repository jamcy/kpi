package resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class VmlResources extends PropertyResourceBundle {

	public static final String GV_SITE_NAME = "site_name";

	public static final String LF_VALIDATION_FAILED = "validation.failed";

	public static final String FM_VALIDATION_FAILED = "form.messages.validation_failed";
	public static final String FM_REQUIRED_VALUE = "form.messages.required_value";
	public static final String FM_UNIQUE_VALUE = "form.messages.unique_value";
	public static final String FM_BUTTON_CONFIRM = "form.button.confirm";

	/******************* MODULE FORM CONSTANTS ************************/
	public static final String FM_MODULE_NAME_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_DESCRIPTION_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_EMBED_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_CONTENTS_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_FILES_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_SHORT_NAME_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_PICTURE_LBL = "form.module.lbl.name";
	public static final String FM_MODULE_ROOM_LBL = "form.module.lbl.name";

	public static final String RM_ROOM_NOT_SELECTED = "rm.no_room_not_selected";

	public static final String MD_NO_MODULE = "md.no_module";

	public VmlResources(InputStream stream) throws IOException {
		super(stream);
		// TODO Auto-generated constructor stub
	}

}
