package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用dom4j解析xml文档
 * @author Administrator
 *
 */
public class ParseXmlDemo {
	public static void main(String[] args) {
	/*
	 * 使用DOM4J解析XML的大致步骤：
	 * 1.创建SAXReader
	 * 2.使用SAXReader读取XML文档，并生成Document对象
	 * 3.通过Document对象获取根元素
	 * 4.从根元素开始逐级获取子元素以达到便利XML文档数据的目的
	 */
	/*
	 * 将emplist.xml文档中的所有员工信息读取
	 */
	List<Emp> empList = new ArrayList<>();
	try {
		//1
		SAXReader reader = new SAXReader();
		//2
		Document doc = reader.read(new File("emplist.xml"));
		//3
		/*
		 * Document提供了获取根元素的方法：
		 * Element getRootElement()
		 * 
		 * Element的每一个实例用于表示XML文档中的一个元素（标签）
		 * 
		 * 其提供了获取标签相关信息的一组方法：
		 * String getName()
		 * 获取标签的名字
		 * 
		 * String getText()
		 * 获取标签文本(开始与结束标签中间的文本)
		 * 
		 * Element element(String name)
		 * 获取当前标签下指定名字的子标签
		 * 
		 * List elements()
		 * 获取当前标签下所有子标签
		 * 
		 * List elements(String name)
		 * 获取当前标签下所有同名字标签(指定的名字)
		 */
		Element root = doc.getRootElement();
//		String name = root.getName();
//		System.out.println(name);
		
		//获取根标签下所有的<emps>标签
		List<Element> list = root.elements("emps");
		System.out.println(list.size());
		
		/*
		 * 遍历每个<emp>标签，用于获取该员工信息
		 */
		for(Element empEle : list) {
			//获取员工名字
			//1 获取<name>标签
			Element nameEle = empEle.element("name");
			//2获取<name>标签中间的文本
			String name = nameEle.getText().trim();
			//获取年龄
			Element ageEle  = empEle.element("age");
			int age = Integer.parseInt(ageEle.getText());
			//获取性别
			String gender = empEle.elementText("gender");
			//获取工资
			int salary = Integer.parseInt(empEle.elementText("salary"));
			//获取ID--属性
			int id = Integer.parseInt(empEle.attributeValue("id"));
			Emp emp = new Emp(id,name,age,gender,salary);
			empList.add(emp);
		}//遍历完毕
		
		System.out.println("解析完毕！");
		for (Emp emp : empList) {
			System.out.println(emp);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
}
}
