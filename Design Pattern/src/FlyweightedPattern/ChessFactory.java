package FlyweightedPattern;

import java.util.HashMap;

public class ChessFactory {
    public static final HashMap<String, Chess> hashMap = new HashMap<>();
    public static Chess getChess(String color){
        Chess res = hashMap.get(color);
        if(res == null){
            res = new ColorChess(color);
            hashMap.put(color, res);
            System.out.println("Create " + color + " chess;");
        }
        return res;
    }

}
