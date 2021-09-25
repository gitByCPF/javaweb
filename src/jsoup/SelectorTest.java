package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 传统标签只能一层层来获取信息，太死板，使用快捷查询方式，能迅速定位需要的标签内容
 *
 * @author CPF cpfprogrammer@163.com
 * @version 2021/9/23 10:59
 * @since JDK8
 */
public class SelectorTest{
	public Document getDocument(){
		File xmlFile = new File("src/jsoup/student.xml");
		Document document = null;
		try{
			document = Jsoup.parse(xmlFile, "utf-8");
		}catch(IOException e){
			System.out.println("打开文件有误：\n" + e);
		}
		return document;
	}

	@Test
	public void testSelect(){
		Document document = getDocument();
		/*select最大的好处就是：选择器描述，有点像sql语句的感觉,
		* 注意：此处语法非常严格，不能多添加空格
		*       属性值若是双引号，需要用到转义右斜杠\;但是属性值用单引号包裹也是允许的，建议使用单引号
		* cssQuery语法内容就css选择器的内容，JQuery查找DOM元素时，也采用了CSS选择器语法；
		* 详情请见：org.jsoup.select.Selector
		* */
		/*获取指定属性值的标签*/
		Elements element1 = document.select("student[number='002']");
		System.out.println(element1);
		System.out.println("===================================================");
		/*获取子标签，子孙都要获取到*/
		Elements element2 = document.select("student[number='002'] age");
		System.out.println(element2);
		System.out.println("=====================================================");
		/*获取直接子标签，只要儿子，不要孙子*/
		Elements element3 = document.select("student[number='002'] > age");
		System.out.println(element3);
		System.out.println("=====================================================");


	}
}






















