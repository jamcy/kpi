package ua.kpi.eec.vml.model.form;

import ua.kpi.eec.vml.common.RequestData;

public interface FormData {

    public boolean validate();

    public void init(RequestData rd);
}
