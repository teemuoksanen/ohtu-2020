package ohtu;

public class TennisGame {
    
    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    
    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName == this.player1Name) {
            player1Score++;
        } else if (playerName == this.player2Name) {
            player2Score++;
        } else {
            System.out.println("ERROR: Invalid player name");
        }
    }

    public String getScore() {
        String score;
        if (player1Score == player2Score) {
            if (player1Score < 4) {
                score = scoreName(player1Score) + "-All";
            } else {
                score = "Deuce";
            }
        } else if (player1Score >= 4 || player2Score >= 4) {
            int scoreDifference = Math.abs(player1Score - player2Score);
            String leadingPlayer = player1Name;
            if (player2Score > player1Score) {
                leadingPlayer = player2Name;
            }

            if (scoreDifference == 1) {
                score = "Advantage " + leadingPlayer;
            } else {
                score = "Win for " + leadingPlayer;
            }
        } else {
            score = scoreName(player1Score) + "-" + scoreName(player2Score);
        }
        return score;
    }

    private String scoreName(int score) {
        switch (score) {
            case 0: return "Love";
            case 1: return "Fifteen";
            case 2: return "Thirty";
            case 3: return "Forty";
            default: return null;
        }
    }
}