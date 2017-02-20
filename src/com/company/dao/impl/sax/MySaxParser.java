package com.company.dao.impl.sax;

import com.company.bean.Menu;
import com.company.bean.Price;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 18.02.2017.
 */
public class MySaxParser extends DefaultHandler {
    private List<Menu> foodList = new ArrayList<>();
    private Menu menu;
    private StringBuilder text;
    private StringBuilder explainText;
    private StringBuilder priceText;

    public MySaxParser() {

    }

    public List getListFood() {
        return foodList;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (qName.equals("coldSnacks")) {
            menu = new Menu();
            explainText = new StringBuilder();
            priceText = new StringBuilder();
        }
        if (qName.equals("hotSnacks")) {
            menu = new Menu();
            explainText = new StringBuilder();
            priceText = new StringBuilder();
        }
        if (qName.equals("breakfast")) {
            menu = new Menu();
            explainText = new StringBuilder();
            priceText = new StringBuilder();
        }


    }

    @Override
    public void characters(char[] c, int start, int length)
            throws SAXException {
        text.append(c, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("mn:menu")) {
            qName = qName.replace(":", "_");
        }
        MenuTagNameSAX tagName = MenuTagNameSAX.valueOf(qName);
        switch (tagName) {
            case photo:
                menu.setUrl(text.toString());
                break;
            case name:
                menu.setName(text.toString());
                break;
            case explain:
                if (explainText.toString().equals("")) {
                    explainText = text;
                }
                menu.setExplain(explainText.toString());
                break;
            case weight:
                menu.setWeight(text.toString());
                break;
            case explainFirst:
                explainText.append(text.toString()).append("\n");
                break;
            case explainSecond:
                explainText.append(text.toString()).append("\n");
                break;
            case explainThird:
                explainText.append(text.toString());
                break;
            case priceFirst:
                priceText.append(text.toString()).append(" ");
                break;
            case priceSecond:
                priceText.append(text.toString()).append(" ");
                break;
            case priceThird:
                priceText.append(text.toString());
                break;
            case price:
                parsePriceFromString(priceText.toString(), menu);
                break;
            case coldSnacks:
                menu.setTypeMenu("coldSnacks");
                foodList.add(menu);
                menu = null;
                break;
            case hotSnacks:
                menu.setTypeMenu("hotSnacks");
                foodList.add(menu);
                menu = null;
                break;
            case breakfast:
                menu.setTypeMenu("breakfast");
                foodList.add(menu);
                menu = null;
                break;
            case mn_menu:
                break;
        }
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Начало разбора документа!");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Разбор документа окончен!");

    }

    private void parsePriceFromString(String price, Menu menu) {
        if (price.isEmpty()) {
            price = text.toString();
        }
        String finalPrice;
        finalPrice = price.trim().replace(" ", ",");
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

            pr.setPriceFirst(Double.parseDouble(firstPrice));
            pr.setPriceSecond(Double.parseDouble(secondPrice));

            menu.setPrice(pr);
        } else {
            if (countComma == 2) {
                String firstPrice = finalPrice.substring(0, finalPrice.indexOf(","));
                String secondPrice = finalPrice.substring(finalPrice.indexOf(",") + 1, finalPrice.lastIndexOf(","));
                String thirdPrice = finalPrice.substring(finalPrice.lastIndexOf(",") + 1);
                Price pr = new Price();
                pr.setPriceFirst(Double.parseDouble(firstPrice));
                pr.setPriceSecond(Double.parseDouble(secondPrice));
                pr.setPriceThird(Double.parseDouble(thirdPrice));
                menu.setPrice(pr);
            } else {
                Price pr = new Price();
                pr.setPriceFirst(Double.parseDouble(finalPrice));
                menu.setPrice(pr);
            }
        }

    }
}
