package com.company.dao.impl.stax;

/**
 * Created by PC on 18.02.2017.
 */
public enum MenuStaXTagName {
    photo, name, explain, weight, price, coldSnacks, explainFirst, explainSecond, explainThird, priceFirst, priceSecond,
    priceThird, hotSnacks, breakfast, mn_menu;

    public static MenuStaXTagName getElementTagName(String element) {
        switch (element) {
            case "coldSnacks":
                return coldSnacks;
            case "hotSnacks":
                return hotSnacks;
            case "breakfast":
                return breakfast;
            case "photo":
                return photo;
            case "name":
                return name;
            case "explain":
                return explain;
            case "explainFirst":
                return explainFirst;
            case "explainSecond":
                return explainSecond;
            case "explainThird":
                return explainThird;
            case "weight":
                return weight;
            case "price":
                return price;
            case "priceFirst":
                return priceFirst;
            case "priceSecond":
                return priceSecond;
            case "priceThird":
                return priceThird;
            case "menu":
                return mn_menu;
            default:
                throw new EnumConstantNotPresentException(MenuStaXTagName.class, element);
        }
    }
}
