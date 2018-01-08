package common.src.main.cards;

public class BlackCard {

    private String[] sentences;
    private int blanks;

    public BlackCard(int n, String[] s) {
        this.sentences = new String[n+1];
        this.blanks = n;

        for (int i = 1; i < n; i++) {
            if (s.length <= i) {
                this.sentences[i] = "";
            }
            else {
                this.sentences[i] = s[i];
            }
        }
    }

    public BlackCard(String s1, String s2) {
        this.blanks = 1;
        this.sentences = new String[2];

        this.sentences[0] = s1;
        this.sentences[1] = s2;
    }

    public BlackCard(int n, String s1, String s2) {
        this.blanks = n;
        this.sentences = new String[n+1];

        this.sentences[0] = s1;
        this.sentences[1] = s2;

        if (n > 2) {
            for (int i = 2; i < n; i++) {
                this.sentences[i] = "";
            }
        }
    }

    public BlackCard(int n, String s) {
        this.blanks = n;
        this.sentences = new String[n+1];

        this.sentences[0] = s;

        if (n > 1) {
            for (int i = 1; i < n; i++) {
                this.sentences[i] = "";
            }
        }
    }

    public BlackCard(String s) {
        this.blanks = 1;
        this.sentences = new String[2];

        this.sentences[0] = s;
        this.sentences[1] = "";
    }

    public BlackCard(String s1, String s2, String s3) {
        this.blanks = 2;
        this.sentences = new String[3];

        this.sentences[0] = s1;
        this.sentences[1] = s2;
        this.sentences[2] = s3;
    }

    public BlackCard(String[] s) {
        this.blanks = s.length-1;
        this.sentences = s;
    }

    // Not used
    /*
    public void setSentenceAt(int i, String s) {
        this.sentences[i] = s;
    }

    public void setSentences(String[] s) {
        this.blanks = s.length;
    }
    */

    public String getSentence() {
        String result = "";
        for(int i = 0; i < this.blanks; i++) {
            result = result + this.sentences[i];
        }
        return result;
    }

    public int getBlanks() {
        return this.blanks;
    }




}
