package com.yixin.BusinessLayer;

import com.yixin.Operation.ElementOperation;
import com.yixin.Util.Application_Locator;
import com.yixin.Util.DatabaseUtil;
import com.yixin.model.ElementDetail;
import io.appium.java_client.android.AndroidDriver;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Infrastructure_ClickAllCategory {

    Application_Locator locator = new Application_Locator();

    public void run(AndroidDriver androidDriver) throws Exception {
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        List<ElementDetail> elementDetails = sqlSession.selectList("GetElements","navigationbar");
        for (ElementDetail s : elementDetails){
            System.out.println(s.getBy_name());
        }


        locator.getLocator(androidDriver, ElementOperation.byname(elementDetails.get(0).getBy_name())).Click();
        locator.getLocator(androidDriver, ElementOperation.byname(elementDetails.get(1).getBy_name())).Click();
        locator.getLocator(androidDriver, ElementOperation.byname(elementDetails.get(2).getBy_name())).Click();
        locator.getLocator(androidDriver, ElementOperation.byname(elementDetails.get(3).getBy_name())).Click();
        Thread.sleep(2000);

    }

}
