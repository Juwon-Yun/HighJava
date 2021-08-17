package kr.or.ddit.basic.excoding;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser03 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		// XML 문서 파싱
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse("sample.xml");
		
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();
		
		// XPath를 컴파일
		XPathExpression expr = xpath.compile("//teacher");
		// 특정 요소만 검색
		XPathExpression expr2 = xpath.compile("//teacher[@name='t2']");
		// XPath에서 XML 문서를 검색
		Object result = expr.evaluate(document, XPathConstants.NODESET);
		Object result2 = expr2.evaluate(document, XPathConstants.NODESET);
		
		NodeList nodes = (NodeList)result;
		for(int i = 0; i < nodes.getLength(); i++){
			Element element = (Element)nodes.item(i);
			System.out.println("att1: " + element.getAttribute("name"));
		}
		System.out.println();
		nodes = (NodeList)result2;
		for(int i = 0; i < nodes.getLength(); i++){
			Element element = (Element)nodes.item(i);
			System.out.println("att2: " + element.getAttribute("name"));
		}
	}
}