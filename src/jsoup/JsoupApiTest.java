package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 介绍jsoup的常用类
 *
 * @author CPF cpfprogrammer@163.com
 * @version 2021/9/22 23:04
 * @since JDK8
 */
public class JsoupApiTest{

	@Test
	public void testJsoup() throws IOException{
		/*1.根据文件来解析html/xml，得到dom树*/
		File file = new File("src/jsoup/student.xml");
		Document document = Jsoup.parse(file, "utf-8");
		System.out.println(document);
		System.out.println("==============================================");

		/*2.根据文本内容解析dom树*/
		String fileString = document.toString();
		Document document1 = Jsoup.parse(fileString);
		System.out.println(document1);
		System.out.println("=================================================");

		/*3.根据网络url来解析html，可以做一些爬虫类的小程序*/
		URL url = new URL("http://www.jjckb.cn/yw.htm");
		Document document2 = Jsoup.parse(url, 10000);
		System.out.println(document2);
	}

	@Test
	public void testDocument() throws IOException{
		File xmlFile = new File("src/jsoup/student.xml");
		Document document = Jsoup.parse(xmlFile, "utf-8");
		/*一个document就是一棵dom树*/
		/*1.根据标签tag来获取elements*/
		Elements students = document.getElementsByTag("student");
		students.forEach(student -> System.out.println(student.text()));
		System.out.println("================================================");

		/*2.根据属性attribute来获取elements*/
		Elements elements = document.getElementsByAttribute("number");
		elements.forEach(element -> System.out.println(element.text()));
		System.out.println("==================================================");

		/*3.根据<属性名，属性值>来获取elements,双管齐下，很容易只得到一个element*/
		Elements students1 = document.getElementsByAttributeValue("number", "001");
		System.out.println(students1.size());
		students1.forEach(student -> System.out.println(student));
		System.out.println("=============================================");

		/*根据id属性值来获取element,id值都是唯一的，因此只能得到一个元素*/
		Element element = document.getElementById("name");
		System.out.println(element);
	}

	@Test
	public void testElements() throws IOException{
		File xmlFile = new File("src/jsoup/student.xml");
		Document document = Jsoup.parse(xmlFile, "utf-8");
		Elements student = document.getElementsByTag("student");
		/*elements这里就是一个几个，内置很多element*/
		Element element = student.get(0);
		System.out.println(element);
	}

	@Test
	public void testElement() throws IOException{
		File xmlFile = new File("src/jsoup/student.xml");
		Document document = Jsoup.parse(xmlFile, "utf-8");
		Elements student = document.getElementsByTag("student");
		Element element = student.get(1);
		/*获得元素属性名对应的属性值，属性名不区分大小写*/
		String number = element.attr("Number");
		System.out.println(number);
		/*element的子类是Document，所以很多方法是一样的*/
		System.out.println(element);
		System.out.println("========================================================");
		Elements names = element.getElementsByTag("name");
		names.forEach(name -> System.out.println(name));
		/*其他的跟第一个测试方法内的Document类差不多*/
	}


}




























