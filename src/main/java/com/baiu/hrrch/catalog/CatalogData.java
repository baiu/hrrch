package com.baiu.hrrch.catalog;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CatalogData  implements Serializable {
    private List<CatalogDto> data;
    private int totalCount=0;
    public CatalogData(){}
    public CatalogData(CatalogDto catalog){
        data=new LinkedList<CatalogDto>();
        data.add(catalog);
        if(!CollectionUtils.isEmpty(catalog.getChildren())){
            data.addAll(catalog.getChildren());
        }
        totalCount=data.size();
    }

    public List<CatalogDto> getData() {
        return data;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
