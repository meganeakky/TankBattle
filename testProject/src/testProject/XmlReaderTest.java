package testProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlReaderTest {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			FileInputStream is = new FileInputStream(Paths.get("C:/temp/TankConfig.xml").toFile());
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);

			Element element = document.getDocumentElement();

			while(element.getFirstChild().getNodeName().equals("Tank")) {

			}

//			XPath xPath = XPathFactory.newInstance().newXPath();

//			XPathExpression expression = xPath.compile("TankBattleConfig/Tank");

//			NodeList nList = (NodeList) expression.evaluate(document, XPathConstants.NODESET);


//			System.out.println("nodeName:" + element.getNodeName());
//			System.out.println("nodeValue:" + element.getNodeValue());

//			NodeList list = element.getChildNodes();

//			for(int n = 0; n < nList.getLength(); n++) {
//				Element element2 = (Element)nList.item(n);
//
//				System.out.println((n+1) + "台目");
//				System.out.println("name:" + element2.getAttribute("name"));
//				System.out.println("攻撃性:" + element2.getAttribute("offensive"));
//			}

		} catch (ParserConfigurationException | IOException | SAXException  e ) {
			// TODO 自動生成された catch ブロック
			System.out.println("エラーが発生");
			e.printStackTrace();
		}
	}

}
