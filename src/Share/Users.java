/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Share;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Users implements Serializable {

    private String name;
    private int score;

    public Users() {

    }

    public Users(String name) {
        this.name = name;
        this.score = 1000;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
