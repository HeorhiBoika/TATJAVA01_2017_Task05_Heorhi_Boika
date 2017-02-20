package com.company.dao.impl;

import com.company.dao.impl.stax.MenuStaXTagName;
import com.company.dao.impl.stax.XMLStreamConstansStAX;
import com.company.bean.Menu;
import com.company.bean.Price;
import com.company.dao.StAXParser_Dao;
import com.company.dao.exception.DAOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public class StAXParser_DaoImpl implements StAXParser_Dao {

    private static final String path = "C:\\Users\\PC\\IdeaProjects\\Task5\\src\\com\\company\\menu.xml";

    public StAXParser_DaoImpl() {

    }

    @Override
    public List stax_Parser() throws DAOException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        List<Menu> foodList;
        try {
            InputStream inputStream = new FileInputStream(path);
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            foodList = process(xmlStreamReader);

        } catch (FileNotFoundException | XMLStreamException | NumberFormatException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return foodList;
    }

    private static List process(XMLStreamReader reader) throws XMLStreamException {
        List<Menu> foodList = new ArrayList<>();
        Menu menu = null;
        MenuStaXTagName menuStaXTagName = null;
        StringBuilder textExplain = null;
        StringBuilder textPrice = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstansStAX.START_ELEMENT:
                    menuStaXTagName = MenuStaXTagName.getElementTagName(reader.getLocalName());
                    switch (menuStaXTagName) {
                        case coldSnacks:
                            menu = new Menu();
                            textExplain = new StringBuilder();
                            textPrice = new StringBuilder();
                            break;
                        case hotSnacks:
                            menu = new Menu();
                            textExplain = new StringBuilder();
                            textPrice = new StringBuilder();
                            break;
                        case breakfast:
                            menu = new Menu();
                            textExplain = new StringBuilder();
                            textPrice = new StringBuilder();
                            break;
                    }
                    break;
                case XMLStreamConstansStAX.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (menuStaXTagName) {
                        case photo:
                            menu.setUrl(text);
                            break;
                        case name:
                            menu.setName(text);
                            break;
                        case explain:
                            textExplain.append(text);
                            break;
                        case explainFirst:
                            textExplain.append(text).append("\n");
                            break;
                        case explainSecond:
                            textExplain.append(text).append("\n");
                            break;
                        case explainThird:
                            textExplain.append(text);
                            break;
                        case weight:
                            menu.setWeight(text);
                            break;
                        case price:
                            textPrice.append(text);
                            break;

                        case priceFirst:
                            textPrice.append(text).append(" ");
                            break;
                        case priceSecond:
                            textPrice.append(text).append(" ");
                            break;
                        case priceThird:
                            textPrice.append(text);
                            break;


                    }
                    break;
                case XMLStreamConstansStAX.END_ELEMENT:
                    menuStaXTagName = MenuStaXTagName.getElementTagName(reader.getLocalName());
                    switch (menuStaXTagName) {
                        case coldSnacks:
                            menu.setTypeMenu("coldSnacks");
                            foodList.add(menu);
                            break;
                        case hotSnacks:
                            menu.setTypeMenu("hotSnacks");
                            foodList.add(menu);
                            break;
                        case breakfast:
                            menu.setTypeMenu("breakfast");
                            foodList.add(menu);
                            break;
                        case explain:
                            menu.setExplain(textExplain.toString());
                            textExplain = null;
                            break;
                        case price:
                            parsePriceFromString(textPrice.toString(), menu);
                            textPrice = null;
                            break;
                    }
            }

        }
        return foodList;
    }

    private static void parsePriceFromString(String price, Menu menu) {
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
