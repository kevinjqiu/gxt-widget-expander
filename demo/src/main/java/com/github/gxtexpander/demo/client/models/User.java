package com.github.gxtexpander.demo.client.models;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;

public class User extends BaseModel {

    public User(final String name, final List<String> languages) {
        setName(name);
        setLanguages(languages);
    }

    public String getName() {
        return (String) get("name");
    }

    @SuppressWarnings("unchecked")
    public List<String> getLanguages() {
        return (List<String>) get("languages");
    }

    public void setName(final String val) {
        set("name", val);
    }

    public void setLanguages(final List<String> val) {
        set("languages", val);
    }


}
