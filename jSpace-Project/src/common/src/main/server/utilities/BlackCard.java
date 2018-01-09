package common.src.main.server.utilities;

public class BlackCard {

    private String sentence;
    private int blanks;

    public BlackCard(int b, String s) {
        this.sentence = s;
        this.blanks = b; 
    }
    
    public String getSentence() {
        return this.sentence;
    }

    public int getBlanks() {
        return this.blanks;
    }
}

