package ru.medvedev.pract16;

import ru.medvedev.pract16.background.InternetOrdersManager;
import ru.medvedev.pract16.background.TableOrdersManager;

public class DataContainer {
    public static InternetOrdersManager internetOrdersManager = new InternetOrdersManager();
    public static TableOrdersManager tableOrdersManager = new TableOrdersManager();
}
