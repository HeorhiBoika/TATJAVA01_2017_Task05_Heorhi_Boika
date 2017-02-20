package com.company.bean.response;

import com.company.bean.Menu;
import com.company.bean.Response;

import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public class ResponseListMenu extends Response{
    List<Menu> listFood;

    public ResponseListMenu(){

    }

    public List<Menu> getListFood() {
        return listFood;
    }

    public void setListFood(List<Menu> listFood) {
        this.listFood = listFood;
    }
}
