package com.yixin.BusinessLayer;

import com.yixin.Operation.ElementOperation;
import com.yixin.Util.Application_Locator;
import com.yixin.Util.DatabaseUtil;
import com.yixin.model.ElementDetail;
import io.appium.java_client.android.AndroidDriver;
import org.apache.ibatis.session.SqlSession;

public class Infrastructure_Login {
    Application_Locator locator = new Application_Locator();

    public void run(AndroidDriver androidDriver) throws Exception {
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
        ElementDetail elementDetail = sqlSession.selectOne("GetElements","loginbuttonentrance");
        System.out.println(elementDetail.getBy_xpath().toString());
        ElementDetail elementDetail1 = sqlSession.selectOne("GetElements","username");
        ElementDetail elementDetail2 = sqlSession.selectOne("GetElements","userpwd");
        ElementDetail elementDetail3 = sqlSession.selectOne("GetElements","btn_login");


        locator.getLocator(androidDriver, ElementOperation.byxpath(elementDetail.getBy_xpath())).Click();
        Thread.sleep(2000);
        locator.getLocator(androidDriver,ElementOperation.byname(elementDetail1.getBy_name())).Send_keys("18600228767");
        locator.getLocator(androidDriver,ElementOperation.byname(elementDetail2.getBy_name())).Send_keys("123456");
        locator.getLocator(androidDriver,ElementOperation.byname(elementDetail3.getBy_resourceid())).Click();
        Thread.sleep(2000);
    }

}

