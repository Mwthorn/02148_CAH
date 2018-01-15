package common.src.main.server.utilities;

public class BlackCard {

    private String sentence;
    private int blanks;

    public BlackCard(int b, String s) {
        this.sentence = s;
        if (b <= 0) {
            this.blanks = 1;
        }
        else {
            this.blanks = b;
        }
    }
    
    public String getSentence() {
        return this.sentence;
    }

    public int getBlanks() {
        return this.blanks;
    }
}

