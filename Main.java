public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Player1", "Player2", "Player3", "Player4");
        Game game = new Game(names);
        game.start();
    }
}
