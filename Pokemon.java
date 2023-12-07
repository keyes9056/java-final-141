import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    protected String name;
    protected int baseAttackPower;
    protected int hp;
    private boolean status = true;

    private List<String> moves;

    public Pokemon(String name, int hp, int baseAttackPower) {
        this.name = name;
        this.hp = hp;
        this.baseAttackPower = baseAttackPower;
        this.moves = new ArrayList<>();
        setDefaultMoves();
    }
    public void setDefaultMoves() {
        moves.add("Tackle");
        moves.add("Default Move 2");
        moves.add("Default Move 3");
        moves.add("Default Move 4");
    }

    public void setStatus(){
        this.status = false;
    }

    public boolean getStatus(){
        return this.status;
    }

    public String getName(){
        return this.name;
    }

    public void setHP(int results){
        this.hp = this.hp - results;
    }

    public int  getHP() {
        return this.hp;
    }


    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Attack Power: " + baseAttackPower);
        System.out.println("HP: " + hp);
        System.out.println("Moves: ");
        for(int i = 0; i< moves.size(); i++){
            System.out.print(moves.get(i) + "\n");
        }
    }

    public String getMoves(int choice) {
        return moves.get(choice-1);
    }

    public int move1() {
        return baseAttackPower+5;
    }
    public int move2() {
        return baseAttackPower+10;
    }
    public int move3() {return baseAttackPower+15;}
    public int move4() {
        return baseAttackPower+20;
    }

    public void setNewMoves(String move1, String move2, String move3, String move4) {
        moves.clear();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);
        moves.add(move4);
    }
}
