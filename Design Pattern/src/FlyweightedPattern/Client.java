package FlyweightedPattern;

public class Client {
    public static void main(String[] args) {
        Chess chess1 = ChessFactory.getChess("RED");
        chess1.setX(1);chess1.setY(2);
        chess1.print();
        Chess chess2 = ChessFactory.getChess("GREEN");
        chess2.setX(1);chess2.setY(2);
        chess2.print();
        Chess chess3 = ChessFactory.getChess("BLUE");
        chess3.setX(1);chess3.setY(2);
        chess3.print();
        Chess chess4 = ChessFactory.getChess("RED");
        chess4.setX(1);chess4.setY(3);
        chess4.print();
        System.out.println("chess1.hashcode() = " + chess1.hashCode());

        System.out.println("chess4.hashcode() = " + chess4.hashCode());

    }
}
