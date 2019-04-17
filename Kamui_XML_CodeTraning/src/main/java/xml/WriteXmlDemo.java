package xml;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 写出xml文档
 * @author Administrator
 *
 */
public class WriteXmlDemo {
	public static void main(String[] args) {
		List<Emp> empList = new ArrayList<>();
		empList.add(new Emp(1,"Kamui",25,"男",5000));
		empList.add(new Emp(2,"Subaru",26,"男",6000));
		empList.add(new Emp(1,"Luna",17,"femal",7000));
		empList.add(new Emp(1,"Diva",20,"femal",8000));
		empList.add(new Emp(1,"Also",28,"male",9000));
		/*
		 * 大致步骤：
		 * 1.创建Document对象表示一个空白文档
		 * 2.向Document中添加根元素
		 * 3.从根元素开始逐级添加子元素以形成XML文档的树结构
		 * 4.创建XmlWriter
		 * 5.通过XmlWriter将Document写出以生成XML
		 */
		//1
		Document doc = DocumentHelper.createDocument();
		//2
		/*
		 * Document提供的方法：
		 * Element addElement(String name)
		 * 向当前文档中添加给定名字的根元素，并以Element实例形式将其返回，以便继续对根元素追加操作。
		 * 注意，Document的这个方法只能调用一次，
		 * 因为一个文档只能有一个根元素。
		 */
		Element root = doc.addElement("list");
		
		for (Emp emp : empList) {
			//像根元素中添加<emp>
			Element empEle = root.addElement("emp");
			
			//添加名字
			Element nameEle = empEle.addElement("name");
			nameEle.addText(emp.getName());
			
			//添加年龄
			empEle.addElement("age").addText(emp.getAge()+"");

			//添加性别
			empEle.addElement("grender").addText(emp.getGender());

			//添加工资
			empEle.addElement("salary").addText(emp.getGender());
			
			//添加id--属性
			empEle.addAttribute("id", emp.getId()+"");
		}
		
		
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileOutputStream("myemp.xml"),OutputFormat.createPrettyPrint());
			writer.write(doc);
			System.out.println("写出完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
