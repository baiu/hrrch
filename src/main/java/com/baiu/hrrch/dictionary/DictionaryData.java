package com.baiu.hrrch.dictionary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "dictionary_data")
public class DictionaryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Справочник
     */
    @ManyToOne
    private Dictionary dic;

    /**
     * Значение
     */
    private String value;

    /**
     * Значение по умолчанию
     */
    private Boolean asDefault;

    public DictionaryData() {

    }

    public DictionaryData(Long id) {
        this.id = id;
    }

    public Dictionary getDic() {
        return dic;
    }

    public void setDic(Dictionary dic) {
        this.dic = dic;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getAsDefault() {
        return asDefault;
    }

    public void setAsDefault(Boolean asDefault) {
        this.asDefault = asDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
