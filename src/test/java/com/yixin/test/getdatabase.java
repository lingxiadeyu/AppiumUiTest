package com.yixin.test;


import com.yixin.Util.DatabaseUtil;
import com.yixin.model.ElementDetail;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class getdatabase {
    @Test
    public void getdatabase() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getsqlsession();
//        List<ElementDetail> name = sqlSession.selectList("Getelementbyname",64);
//        System.out.println(name.get(0).toString());


        ElementDetail elementDetail = sqlSession.selectOne("GetElements","btn_login");
        System.out.println(elementDetail.getBy_name().toString());
        System.out.println(elementDetail.getBy_xpath().toString());
        System.out.println(elementDetail.getBy_classname().toString());
        System.out.println(elementDetail.getBy_resourceid().toString());
        System.out.println(elementDetail.getIndexs());
        System.out.println(elementDetail.getContextdesc().toString());
        System.out.println(elementDetail.getX_start_point());
        System.out.println(elementDetail.getX_end_point());
        System.out.println(elementDetail.getY_start_point());
        System.out.println(elementDetail.getY_end_point());
    }
}

