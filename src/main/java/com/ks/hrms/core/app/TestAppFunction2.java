package com.ks.hrms.core.app;

import com.ks.hrms.core.app.AppFunction;
import com.ks.hrms.core.app.AppFunctionMain;
import com.ks.hrms.core.app.TestAppFunctionMain2;
import com.ks.hrms.core.context.HRMSAppFunctionContext;

public class TestAppFunction2 extends AppFunction {

    @Override
    public void init(HRMSAppFunctionContext context) {
        super.init(context);
        setText("Tsd");
    }

    @Override
    public AppFunctionMain createMain() {
        TestAppFunctionMain2 main2 = new TestAppFunctionMain2();
        main2.setAppManager(this);
        return main2;
    }
}
