package com.github.gxtexpander.demo.client.models;

import java.util.Arrays;

import com.extjs.gxt.ui.client.store.ListStore;

public class DemoDataFactory {

    private DemoDataFactory() {

    }

    public static ListStore<User> createDemoDataListStore() {
        final ListStore<User> retval = new ListStore<User>();
        retval.add(Arrays.asList(
                new User("Adam", Arrays.asList("C++", "C#", "F#")),
                new User("Brian", Arrays.asList("Java", "Scala", "Groovy")),
                new User("Chris", Arrays.asList("Python", "Groovy")),
                new User("Devon", Arrays.asList("Scala", "Clojure")),
                new User("Ethan", Arrays.asList("F#", "Clojure", "Haskell", "Prolog")),
                new User("Frank", Arrays.asList("Ruby", "C", "C++")),
                new User("Grant", Arrays.asList("Java", "Ruby", "Groovy")),
                new User("Hazel", Arrays.asList("Java", "C++", "C", "Haskell", "Scheme", "Erlang", "Clojure", "Scala")),
                new User("Ivy", Arrays.asList("Java", "Erlang", "Cobol")),
                new User("James", Arrays.asList("C#", "Visual Basic", "Logo"))));
        return retval;
    }

}
