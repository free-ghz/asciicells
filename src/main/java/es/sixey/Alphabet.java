package es.sixey;

public class Alphabet {

    public String forState(int state) {
        if (state == 1) return "~";
        if (state == 2) return "@";
        return " ";
    }
}
