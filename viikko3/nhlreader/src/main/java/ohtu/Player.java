
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setNationality(String nat) {
        this.nationality = nat;
    }

    public String getNationality() {
        return nationality;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public int points() {
        return goals+assists;
    }

    @Override
    public int compareTo(Player p) {
        if (this.points() == p.points()) {
            return this.goals - p.goals;
        }
        return this.points() - p.points();
    }

    @Override
    public String toString() {
        String data = name + "\t";
        if (name.length() < 16) {
            data += "\t";
        }
        data += team + "\t";
        if (goals < 10) {
            data += " ";
        }
        data += goals + " + ";
        if (assists < 10) {
            data += " ";
        }
        data += assists + " = ";
        if (points() < 10) {
            data += " ";
        }
        data += points();
        return data;
    }
      
}
