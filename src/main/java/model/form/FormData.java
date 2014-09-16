package model.form;

import common.RequestData;

public interface FormData {

    public boolean validate();

    public void init(RequestData rd);
}
