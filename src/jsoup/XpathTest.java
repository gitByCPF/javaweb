package jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 另一种解析html的语言Xpath，用来定位dom中的元素;
 * 虽然Xpath是基于Jsoup的，但是要使用Xpath技术还必须额外引入xpath的依赖（jar包）
 *
 * @author CPF cpfprogrammer@163.com
 * @version 2021/9/23 11:54
 * @since JDK8
 */
public class XpathTest{

	public Document getDocument(){
		File xmlFile = new File("src/jsoup/student.xml");
		Document document = null;
		try{
			document = Jsoup.parse(xmlFile, "utf-8");
		}catch(IOException e){
			System.out.println("文件解析有误：\n" + e );
		}
		return document;
	}

	@Test
	public void testXml() throws XpathSyntaxErrorException{
		Document document = getDocument();
		//1.根据jsoup的document对象，创建xpath自己的JXDocument;
		JXDocument jxDocument = new JXDocument(document);
		//2.遵循Xpath的语法来查询DOM中的元素
		/*2.1 从当前节点（dom根部）开始选择所有名为age的节点，不考虑位置*/
		List<JXNode> jxNodes1 = jxDocument.selN("//age");
		System.out.println(jxNodes1);
		System.out.println("================================================");
		/*2.2 选取student节点下的所有age元素，不管他在什么位置 */
		List<JXNode> jxNodes2 = jxDocument.selN("//student//age");
		System.out.println(jxNodes2);
		System.out.println("================================================");
		/*2.3 选取student节点下的所有直接子元素age */
		List<JXNode> jxNodes3 = jxDocument.selN("//student/age");
		System.out.println(jxNodes3);
		System.out.println("================================================");
		/*2.4 选取文档中的所有元素：含有html格式的头部身体尾巴*/
		List<JXNode> jxNodes4 = jxDocument.selN("//*");
		System.out.println(jxNodes4);
		System.out.println("================================================");
	}

	/**
	 * 爬取【经济参考万-要闻】网页的每日新闻标题及其链接.
	 * @throws IOException 爬出超时、网页不存在.
	 * @throws XpathSyntaxErrorException 获取页面元素失败.
	 */
	@Test
	public void testUrl() throws IOException, XpathSyntaxErrorException{
		URL url = new URL("http://www.jjckb.cn/yw.htm");
		Document document = Jsoup.parse(url, 10000);
		JXDocument jxDocument = new JXDocument(document);
		/*/html/body/div[1]/div[3]/ul/li[1]/a*/
		List<JXNode> newses = jxDocument.selN("//ul[@class='xpage-content-list']//a");
		int count = 1;
		for(JXNode news : newses){
			System.out.println("新闻" + count + ": " + news.getElement().text());
			System.out.println("新闻" + count + "链接: " + news.getElement().attr("href"));
			count++;
			System.out.println();
		}
	}

	/**
	 * 想爬取东方财富的股票信息，但是数据都是动态的，没有在页面呈现，目前不知道怎么爬取？
	 * @throws IOException
	 * @throws XpathSyntaxErrorException
	 */
	@Test
	public void testStock() throws IOException, XpathSyntaxErrorException{
		URL url = new URL("http://quote.eastmoney.com/center/gridlist.html#sh_a_board");
		Document document = Jsoup.parse(url, 10000);
			JXDocument jxDocument = new JXDocument(document);
		//	<a class="paginate_button current" data-index="1">1</a>
		List<JXNode> jxNodes = jxDocument.selN("//a");
		for(JXNode jxNode : jxNodes){
			System.out.println(jxNode);
		}
	}
}



















