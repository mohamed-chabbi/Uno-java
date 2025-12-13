import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("human", "bot1", "bot2", "bot3");
        Game game = new Game(names);
        game.start();
    }
}