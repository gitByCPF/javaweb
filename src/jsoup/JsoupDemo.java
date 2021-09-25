package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 测试使用Jsoup解析xml文件
 *
 * @author CPF cpfprogrammer@163.com
 * @version 2021/9/22 18:40
 * @since JDK8
 */
public class JsoupDemo{
	public static void main(String[] args) throws IOException{
		//1.获得xml的字节输入流
		String path = JsoupDemo.class.getResource("student.xml").getPath();
		System.out.println("path = " + path);
		//2.使用jsoup解析xml文件
		Document parse = Jsoup.parse(new File(path), "utf-8");
		//3.获取元素element的值组成的集合
		Elements names = parse.getElementsByTag("name");
		//4.输出标签的文本内容
		names.forEach(name -> System.out.println(name.text()));
	}
}
