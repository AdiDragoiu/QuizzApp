package com.example.proiectfinal;

public class Persoane implements Comparable<Persoane>{

    String username;
    int scor;

    public Persoane(String username ,int scor) {
        this.username = username;
        this.scor = scor;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScor() {
        return scor;
    }

    public void setScor(int scor) {
        this.scor = scor;
    }

    @Override
    public int compareTo(Persoane o) {
        if(scor > o.scor)
            return -1;
        else if(o.scor > scor)
            return 1;
        else
            return 0;

    }
}
