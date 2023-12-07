import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Pokemon> pokemonList = getPokemons();

        TeamManager playerTeam = new TeamManager(pokemonList);
        playerTeam.generateRandomTeam();
        TeamManager computerTeam = new TeamManager(pokemonList);
        computerTeam.generateRandomTeam();


        System.out.println("Welcome to the Pokemon League \nPlease select your actions with the number keys!");

        Scanner pokeScan = new Scanner(System.in);

        System.out.println("Your Team:\n");
        playerTeam.displayTeam();
        do {
            System.out.println("Choose your pokemon!");
            while (!pokeScan.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                pokeScan.next(); // Consume the invalid input
            }
            int pokeChoice = pokeScan.nextInt() - 1;
            while (pokeChoice >= 3) {
                System.out.println("Pick a number between 1 and 3 please.");
                pokeChoice = pokeScan.nextInt() - 1;
            }
            int compChoice = computerChoice(computerTeam);
            faintCheck(playerTeam, pokeChoice);

            System.out.println("Go " + playerTeam.get(pokeChoice).getName());

            System.out.println("Computer sent out " + computerTeam.get(compChoice).getName());
            while (computerTeam.get(compChoice).getStatus() && playerTeam.get(pokeChoice).getStatus()) {
                combat(playerTeam, pokeChoice, computerTeam, compChoice);
            }
        } while (((!computerTeam.get(0).getStatus() || !computerTeam.get(1).getStatus() || !computerTeam.get(2).getStatus())
                && (!playerTeam.get(0).getStatus() || !playerTeam.get(1).getStatus() || !playerTeam.get(2).getStatus())));
    }

    private static List<Pokemon> getPokemons() {
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 110, 25);
        bulbasaur.setNewMoves("Tackle", "Vine Whip", "Razor Leaf", "Solar Beam");

        Pokemon pikachu = new Pokemon("Pikachu", 80, 20);
        pikachu.setNewMoves("Tackle", "Thundershock", "Quick Attack", "Thunder");

        Pokemon charmander = new Pokemon("Charmander", 90, 35);
        charmander.setNewMoves("Scratch", "Ember", "Cut", "Fire Blast");

        Pokemon squirtle = new Pokemon("Squirtle", 100, 30);
        squirtle.setNewMoves("Tackle", "Water gun", "Head Bash", "Hydro Pump");

        Pokemon ekans = new Pokemon("Ekans", 75, 25);
        ekans.setNewMoves("Bite", "Wrap", "Poison Spit", "Tail Slap");

        Pokemon koffing = new Pokemon("Koffing", 90, 30);
        koffing.setNewMoves("Tackle", "Poison Gas", "Obnouxious Laugh", "Explosion");

        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(bulbasaur);
        pokemonList.add(pikachu);
        pokemonList.add(charmander);
        pokemonList.add(squirtle);
        pokemonList.add(ekans);
        pokemonList.add(koffing);
        return pokemonList;
    }

    public static void faintCheck(TeamManager playerTeam, int pokeChoice) {
        if (!playerTeam.get(pokeChoice).getStatus()) {
            System.out.println("That pokemon has fainted, choose another!");
            System.out.println(playerTeam.get(0).getName() + "\n");
            System.out.println(playerTeam.get(0).getStatus() + "\n");
            System.out.println("\n");
            System.out.println(playerTeam.get(1).getName() + "\n");
            System.out.println(playerTeam.get(1).getStatus() + "\n");
            System.out.println("\n");
            System.out.println(playerTeam.get(2).getName() + "\n");
            System.out.println(playerTeam.get(2).getStatus() + "\n");
        }
    }

    public static int computerChoice(TeamManager compTeam) {
        Random compRand = new Random();
        int compChoice = compRand.nextInt(3);

        while (!compTeam.get(compChoice).getStatus()) {
            compChoice = compRand.nextInt(3);
        }
        return compChoice;
    }

    public static void combat(TeamManager playerTeam, int pokeChoice, TeamManager computerTeam, int compChoice) {
        while (playerTeam.get(pokeChoice).getStatus() && computerTeam.get(compChoice).getStatus()) {
            playerTeam.get(pokeChoice).displayInfo();
            System.out.println("What do you want to do?");

            Scanner moveScan = new Scanner(System.in);
            while (!moveScan.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                moveScan.next(); // Consume the invalid input
            }
            int moveChoice = moveScan.nextInt();
            int damage;

            switch (moveChoice) {
                case 1:
                    damage = playerTeam.get(pokeChoice).move1();
                    break;
                case 2:
                    damage = playerTeam.get(pokeChoice).move2();
                    break;
                case 3:
                    damage = playerTeam.get(pokeChoice).move3();
                    break;
                case 4:
                    damage = playerTeam.get(pokeChoice).move4();
                    break;
                default:
                    System.out.println("Please pick a number between 1 and 4");
                    continue; // Go to the next iteration of the loop
            }

            System.out.println(playerTeam.get(pokeChoice).getName() + " used " + playerTeam.get(pokeChoice).getMoves(moveChoice));
            computerTeam.get(compChoice).setHP(damage);


            if (computerTeam.get(compChoice).getHP() <= 0) {
                System.out.println(computerTeam.get(compChoice).getName() + " has fainted!");
                computerTeam.get(compChoice).setStatus();
                do {
                    compChoice = computerChoice(computerTeam);
                } while (!computerTeam.get(compChoice).getStatus());
                System.out.println("Computer sent out " + computerTeam.get(compChoice).getName());
                continue;
            } else {
                System.out.println(computerTeam.get(compChoice).getName() + " was hit! They have " + computerTeam.get(compChoice).getHP() + " left!\n");
            }

            // Computer's turn
            int compMove = new Random().nextInt(4) + 1;
            damage = switch (moveChoice) {
                case 1 -> computerTeam.get(compChoice).move1();
                case 2 -> computerTeam.get(compChoice).move2();
                case 3 -> computerTeam.get(compChoice).move3();
                case 4 -> computerTeam.get(compChoice).move4();
                default -> damage;
            };
            System.out.println(computerTeam.get(compChoice).getName() + " used " + computerTeam.get(compChoice).getMoves(compMove));
            playerTeam.get(pokeChoice).setHP(damage);

            if (playerTeam.get(pokeChoice).getHP() <= 0) {
                System.out.println(playerTeam.get(pokeChoice).getName() + " has fainted!");
                playerTeam.get(pokeChoice).setStatus();
                System.out.println("Your Team:\n");
                playerTeam.displayTeam();
                Scanner pokeScan = new Scanner(System.in);
                    System.out.println("Choose your pokemon!");
                    pokeScan.nextInt();
                while (!pokeScan.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    pokeScan.next(); // Consume the invalid input
                }
                    pokeChoice = pokeScan.nextInt() - 1;
                    while (pokeChoice >= 3) {
                        System.out.println("Pick a number between 1 and 3 please.");
                        pokeChoice = pokeScan.nextInt() -1;
                    }
                if (!playerTeam.get(pokeChoice).getStatus()){
                    System.out.println("That pokemon has fainted, select another\n");
                    playerTeam.displayTeam();
                    System.out.println("Pick a different Pokemon");
                    pokeChoice = pokeScan.nextInt() - 1;
                }
                faintCheck(playerTeam, pokeChoice); // Choose a new Pokemon
                    System.out.println("Go " + playerTeam.get(pokeChoice).getName());
                if (!computerTeam.get(0).getStatus() && !computerTeam.get(1).getStatus() && !computerTeam.get(2).getStatus()) {
                    System.out.println("You win!");
                } else if (!playerTeam.get(0).getStatus() && !playerTeam.get(1).getStatus() && !playerTeam.get(2).getStatus()) {
                    System.out.println("You lose, try again next time!");
                }
            } else {
                System.out.println(playerTeam.get(pokeChoice).getName() + " was hit! They have " + playerTeam.get(pokeChoice).getHP() + " left!\n");
            }
        }
    }
}