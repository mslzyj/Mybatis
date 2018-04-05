package com.zyj.mybaits01.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MybatisTest {
	/**
	 * �������棺
	 * һ�����棺�����ػ��棩��sqlSession����Ļ��档һ��������һֱ�����ģ�SqlSession�����һ��Map
	 * 		�����ݿ�ͬһ�λỰ�ڼ��ѯ�������ݻ���ڱ��ػ����С�
	 * 		�Ժ������Ҫ��ȡ��ͬ�����ݣ�ֱ�Ӵӻ������ã�û��Ҫ��ȥ��ѯ���ݿ⣻
	 * 
	 * 		һ������ʧЧ�����û��ʹ�õ���ǰһ������������Ч�����ǣ�����Ҫ�������ݿⷢ����ѯ����
	 * 		1��sqlSession��ͬ��
	 * 		2��sqlSession��ͬ����ѯ������ͬ.(��ǰһ�������л�û���������)
	 * 		3��sqlSession��ͬ�����β�ѯ֮��ִ������ɾ�Ĳ���(�����ɾ�Ŀ��ܶԵ�ǰ������Ӱ��)
	 * 		4��sqlSession��ͬ���ֶ������һ�����棨������գ�
	 * 
	 * �������棺��ȫ�ֻ��棩������namespace����Ļ��棺һ��namespace��Ӧһ���������棺
	 * 		�������ƣ�
	 * 		1��һ���Ự����ѯһ�����ݣ�������ݾͻᱻ���ڵ�ǰ�Ự��һ�������У�
	 * 		2������Ự�رգ�һ�������е����ݻᱻ���浽���������У��µĻỰ��ѯ��Ϣ���Ϳ��Բ��ն��������е����ݣ�
	 * 		3��sqlSession===EmployeeMapper==>Employee
	 * 						DepartmentMapper===>Department
	 * 			��ͬnamespace��������ݻ�����Լ���Ӧ�Ļ����У�map��
	 * 			Ч�������ݻ�Ӷ��������л�ȡ
	 * 				��������ݶ��ᱻĬ���ȷ���һ�������С�
	 * 				ֻ�лỰ�ύ���߹ر��Ժ�һ�������е����ݲŻ�ת�Ƶ�����������
	 * 		ʹ�ã�
	 * 			1��������ȫ�ֶ����������ã�<setting name="cacheEnabled" value="true"/>
	 * 			2����ȥmapper.xml������ʹ�ö������棺
	 * 				<cache></cache>
	 * 			3�������ǵ�POJO��Ҫʵ�����л��ӿ�
	 * 	
	 * �ͻ����йص�����/���ԣ�
	 * 			1����cacheEnabled=true��false���رջ��棨��������رգ�(һ������һֱ���õ�)
	 * 			2����ÿ��select��ǩ����useCache="true"��
	 * 					false����ʹ�û��棨һ��������Ȼʹ�ã��������治ʹ�ã�
	 * 			3������ÿ����ɾ�ı�ǩ�ģ�flushCache="true"����һ�����������������
	 * 					��ɾ��ִ����ɺ�ͻ�������棻
	 * 					���ԣ�flushCache="true"��һ�����������ˣ�����Ҳ�ᱻ�����
	 * 					��ѯ��ǩ��flushCache="false"��
	 * 						���flushCache=true;ÿ�β�ѯ֮�󶼻���ջ��棻������û�б�ʹ�õģ�
	 * 			4����sqlSession.clearCache();ֻ�������ǰsession��һ�����棻
	 * 			5����localCacheScope�����ػ��������򣺣�һ������SESSION������ǰ�Ự���������ݱ����ڻỰ�����У�
	 * 								STATEMENT�����Խ���һ�����棻		
	 * 				
	 *�������������ϣ�
	 *		1���������������������ɣ�
	 *		2����������������������ϵ���������ٷ��У�
	 *		3����mapper.xml��ʹ���Զ��建��
	 *		<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
	 *
	 * @throws IOException 
	 * 
	 */
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

}