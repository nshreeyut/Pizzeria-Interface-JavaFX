package com.example.project4.management;

import com.example.project4.pizzeria.Pizza;

public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
