package com.company.dao.impl;

import com.company.bean.Menu;
import com.company.bean.Price;
import com.company.dao.DOMParser_Dao;
import com.company.dao.exception.DAOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public class DOMParser_DaoImpl implements DOMParser_Dao {

    private final static String path = "C:\\Users\\PC\\IdeaProjects\\Task5\\src\\com\\company\\menu.xml";

    public DOMParser_DaoImpl() {

    }

    @Override
    public List domParser(String tagname) throws DAOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(path);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
        document.getDocumentElement().normalize();
        System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());

        NodeList nodeList = document.getElementsByTagName(tagname);

        List<Menu> menuList = new ArrayList<Menu>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            menuList.add(getMenu(nodeList.item(i)));
        }
        return menuList;
    }

    // создаем из узла документа объект
    private static Menu getMenu(Node node) throws DAOException {
        Menu menu = new Menu();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            menu.setUrl(getTagValue("photo", element));
            menu.setName(getTagValue("name", element));
            menu.setExplain(getChild(element, "explain").getTextContent());
            menu.setWeight(getTagValue("weight", element));
            try {
                parsePriceFromString(getChild(element, "price").getTextContent(), menu);
            } catch (NullPointerException e) {
                throw new DAOException("Error, price doesn't find");
            }
        }

        return menu;
    }

    private static void parsePriceFromString(String price, Menu menu) throws DAOException {
        String[] prices = price.trim().split(" ");
        String finalPrice = "";
        for (int i = 0; i < prices.length; i++) {
            if (!prices[i].equals("")) {
                finalPrice += prices[i];
            }
        }

        finalPrice = finalPrice.replace("\n", ",");
        int countComma = 0;
        for (int i = 0; i < finalPrice.length(); i++) {
            if (finalPrice.charAt(i) == ',') {
                countComma++;
            }
        }

        if (countComma == 1) {
            String firstPrice = finalPrice.substring(0, finalPrice.indexOf(","));
            String secondPrice = finalPrice.substring(finalPrice.indexOf(",") + 1);

            Price pr = new Price();
            try {
                pr.setPriceFirst(Double.parseDouble(firstPrice));
                pr.setPriceSecond(Double.parseDouble(secondPrice));
            } catch (NumberFormatException e) {
                throw new DAOException(e.getMessage(), e);
            }
            menu.setPrice(pr);
        } else {
            if (countComma == 2) {
                String firstPrice = finalPrice.substring(0, finalPrice.indexOf(","));
                String secondPrice = finalPrice.substring(finalPrice.indexOf(",") + 1, finalPrice.lastIndexOf(","));
                String thirdPrice = finalPrice.substring(finalPrice.lastIndexOf(",") + 1);

                Price pr = new Price();
                try {
                    pr.setPriceFirst(Double.parseDouble(firstPrice));
                    pr.setPriceSecond(Double.parseDouble(secondPrice));
                    pr.setPriceThird(Double.parseDouble(thirdPrice));
                } catch (NumberFormatException e) {
                    throw new DAOException(e.getMessage(), e);
                }
                menu.setPrice(pr);
            } else {

                Price pr = new Price();

                try {
                    pr.setPriceFirst(Double.parseDouble(finalPrice));
                } catch (NumberFormatException e) {
                    throw new DAOException(e.getMessage(), e);
                }
                menu.setPrice(pr);
            }
        }

    }

    private static Element getChild(Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
