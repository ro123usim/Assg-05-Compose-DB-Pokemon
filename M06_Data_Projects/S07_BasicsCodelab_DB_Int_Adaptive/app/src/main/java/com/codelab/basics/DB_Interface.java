package com.codelab.basics;

import java.util.List;

public interface DB_Interface {

    int count();

    int save(DataModel dataModel);

    int update(DataModel dataModel);

    int deleteById(Long id);

    List<DataModel> findAll();

    String getNameById(Long id);

    DataModel getMax();

    void incAccessCount(long id);

    long getMostAccessed();
}