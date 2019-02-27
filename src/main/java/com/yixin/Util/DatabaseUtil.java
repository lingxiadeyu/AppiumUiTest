package com.yixin.Util;

/*
利用mybatis从mysql数据库中获取session数据信息
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {
    public static SqlSession getsqlsession() throws IOException {

        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

}
