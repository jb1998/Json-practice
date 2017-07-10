package me.jaxbot.jsonpractice;

/**
 * Created by pc on 3/14/2017.
 */

public class Items {
    String name;
    String symbol;

    String percentagechange;
    Items(String name,String percentagechange,String symbol)
    {
        this.symbol=symbol;
        this.name=name;

        this.percentagechange=percentagechange;
    }
}
