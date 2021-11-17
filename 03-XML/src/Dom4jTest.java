import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {

    public static void main(String[] args) throws DocumentException {
        //1.先创建一个 SAXReader 对象，用于获取document对象
        SAXReader saxReader = new SAXReader();

        //2.使用SAXReader对象 的read()方法 获取 document对象
        //注意：这里的 根目录还是 工程名：JavaWeb
        // 根目录 都不用写
        Document document = saxReader.read("03-XML/xml/books.xml");
        //工程中，工程名就是任何文件当前所在目录。所以 以工程名作为根目录的方式就是相对路径。
        //而真正的绝对路径，是从系统盘C\D\E开始的
        //Document document2 = saxReader.read("../xml/books.xml");
        System.out.println(document);
    }
    /**
     * dom4j 获取 document对象
     */
    @Test
    public void getDocument() throws DocumentException {
        //1.先创建一个 SAXReader 对象，用于获取document对象
        SAXReader saxReader = new SAXReader();
        //2.使用SAXReader对象 的read()方法 获取 document对象
        //注意：junit测试的，module项目下的方法，根目录是module
        Document document = saxReader.read("xml/books.xml");
        System.out.println(document);
    }

    /**
     * 遍历标签，获取标签中的所有内容
     */
    @Test
    public void readXML() throws DocumentException {
        //1.获取document对象
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("xml/books.xml");

        //2.通过 document 对象 获取根元素对象
        Element root = document.getRootElement();
        /*
        asXML()方法 将当前元素转换成 String对象
        System.out.println(root.asXML());
         */

        //3.通过 根元素对象 获取 子元素
        // Element.elements(标签名) 得到当前元素下的指定子元素集合
        List<Element> books = root.elements("book");

        //4.遍历 子元素集合 获取每个 book标签对象 中的每个 元素
        for (Element element : books) {
            //还是通过 Element.element(标签名) 获取子元素(标签)对象
            Element nameElement = element.element("name");
            //Element.getText() 获取标签之间的文本内容
            String name = nameElement.getText();
            //通过 Element.elementText(标签名)直接获取标签之间的文本内容
            String  author = element.elementText("author");
            String price = element.elementText("price");

            //通过 Element.attributeValue(属性名)获取属性值
            String id = element.attributeValue("id");
            try {
                // getText() 获取标签之间的文本内容
                System.out.println("id：" + id + ", 书名" + nameElement.getText() + " , 价格:"
                        + price+ ", 作者：" + author);
            } catch(NullPointerException e) {
                System.out.println(e);
            }
        }
    }
}
