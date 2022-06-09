package com.solvd.internet_store.utils.builders;

import com.solvd.internet_store.enums.ModelType;
import com.solvd.internet_store.models.*;

public class Builder {
    public static AbstractModel createModel(ModelType type){
        switch (type){
            case USER:
                return new User("Ivan", "ivan@gmail.com", (short) 24);
            case COSTUMER:
                return new Costumer("+380 066 876 56 46", new User(1));
            case DRIVER:
                return new Driver("experienced", new User(2));
            case VEHICLE_LICENCE:
                return new VehicleLicence(12314, "12/04/2023", new Driver(1));
            case ORDER:
                return new Order("regular", "07/07/2022", 1, 1);
            case VEHICLE:
                return new Vehicle("car", 1, 1);
            default:
                return null;
        }
    }
}
