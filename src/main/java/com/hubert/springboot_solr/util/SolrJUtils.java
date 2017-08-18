package com.hubert.springboot_solr.util;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Component;

import com.hubert.springboot_solr.bean.SolrUser;

@Component
public class SolrJUtils {
	
	private static String solrCoreUrl = "http://127.0.0.1:8080/solr/mysql_Core";
	
//	@Value("${spring.solr.host}")
//	public void setSolrCoreUrl(String solrCoreUrl){
//		SolrJUtils.solrCoreUrl = solrCoreUrl;
//	}
	
	private static HttpSolrClient solrClient = new HttpSolrClient(solrCoreUrl);
	
	/**
	 * 添加一个索引对象
	 * @param solrUser
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void addOne(SolrUser solrUser) throws IOException, SolrServerException{
		solrClient.addBean(solrUser);
		solrClient.commit();
		solrClient.close();
	}
	
	/**
	 * 添加多个索引
	 * @param solrUserList
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void addList(List<SolrUser> solrUserList) throws SolrServerException, IOException{
		solrClient.addBeans(solrUserList);
		solrClient.commit();
		solrClient.close();
	}
	
	/**
	 * 删除索引
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void deleteIndex(List<String> ids) throws SolrServerException, IOException {
		// //1.删除一个
		// client.deleteById("zxj1");

		// //2.删除多个
		// ids.add("1");
		// ids.add("2");
		solrClient.deleteById(ids);

		// //3.根据查询条件删除数据,这里的条件只能有一个，不能以逗号相隔
		// client.deleteByQuery("id:zxj1");
		//
		// //4.删除全部，删除不可恢复
		// client.deleteByQuery("*:*");

		// 记得提交，否则不起作用
		solrClient.commit();
		solrClient.close();
	}

	/**
	 * 删除指定条件
	 * 
	 * @param key
	 * @param value
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void deleteIndex(String key, String value) throws SolrServerException, IOException {
		String queryMap = key + ":" + value;
		solrClient.deleteByQuery(queryMap);
		solrClient.commit();
		solrClient.close();
	}

	/**
	 * 删除所有索引
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void deleteIndexAll() throws SolrServerException, IOException {
		solrClient.deleteByQuery("*:*");
		solrClient.commit();
		solrClient.close();
	}
	
	/**
	 * 查询
	 * @param queryString
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static List<SolrUser> query(String queryString) throws SolrServerException, IOException{
		//构造查询参数
        SolrQuery query = new SolrQuery(queryString);//查询姓名包含土豆的User
        query.setSort("id", SolrQuery.ORDER.asc);//按id升序
        query.setHighlight(true);                //开启高亮
        query.setHighlightFragsize(10);          //返回的字符个数
        query.setHighlightRequireFieldMatch(true);
        query.setHighlightSimplePre("<font color=\"red\">");    //前缀
        query.setHighlightSimplePost("</font>");    //后缀
        query.setParam("hl.fl", "name");      //高亮字段
        query.setStart(0);  //分页参数
        query.setRows(10); //分页参数
        
        //获得查询结果
        QueryResponse response = solrClient.query(query);
        System.out.println(response);
        //转换为Java Bean
        List<SolrUser> userList = response.getBeans(SolrUser.class);
        
        for(SolrUser user : userList){
        	System.out.println("查询到：id=" + user.getId() + ", name=" + user.getName());
        }

        return userList;
		
	}
	
	
	
	
	
	
}
