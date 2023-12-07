import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamManager {
    private final List<Pokemon> allPokemon;
    public List<Pokemon> team = new ArrayList<>(3);

    public TeamManager(List<Pokemon> allPokemon) {
        this.allPokemon = allPokemon;
    }

    public void generateRandomTeam(){
        Random random = new Random();
        while(team.size()!= 3) {
            int randomIndex = random.nextInt(allPokemon.size());
            Pokemon randomPokemon = allPokemon.get(randomIndex);

            if (!team.contains(randomPokemon)) {
                team.add(randomPokemon);
            }
        }
    }

    public void displayTeam(){
        for (Pokemon pokemon : this.team) {
            System.out.println(pokemon.getName());
        }
    }

    public Pokemon get(int choice){
        return team.get(choice);
    }
}
