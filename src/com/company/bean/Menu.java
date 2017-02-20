package com.company.bean;

import java.io.Serializable;

/**
 * Created by PC on 16.02.2017.
 */
public class Menu implements Serializable{
    private String typeMenu;
    private String url;
    private String name;
    private String explain;
    private String weight;
    private Price price;

    public Menu(){
    }

    public String getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(String typeMenu) {
        this.typeMenu = typeMenu;
    }

    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if(typeMenu != null){
           return typeMenu+": Menu{" +
                    "url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    ", explain='" + explain + '\'' +
                    ", weight='" + weight + '\'' +
                    ", price=" + price +
                    '}';
        }
        return "Menu{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", explain='" + explain + '\'' +
                ", weight='" + weight + '\'' +
                ", price=" + price +
                '}';
    }
}
