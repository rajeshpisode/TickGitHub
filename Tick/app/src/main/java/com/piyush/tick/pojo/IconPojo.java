package com.piyush.tick.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 7/24/2015.
 */


public class IconPojo {

    private String name;

    private List<Arg> args = new ArrayList<Arg>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The args
     */
    public List<Arg> getArgs() {
        return args;
    }

    /**
     *
     * @param args
     * The args
     */
    public void setArgs(List<Arg> args) {
        this.args = args;
    }

}

