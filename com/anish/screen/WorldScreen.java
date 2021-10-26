package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Location;
import com.anish.calabashbros.QuickSorter;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash[][] bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new Calabash[8][8];

        // bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        // world.put(bros[0], 10, 10);
        // int a=0,b=0;
        Random r=new Random();
        for (int i = 0; i < bros.length; i++) {
            // a=6+i*2;
            for (int j = 0; j < bros[0].length; j++) {
                // b= 6+j*2;
                bros[i][j] = new Calabash(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)),Location.get(i*8+j),world);
                        // Location.get(i * 8 + j) + 1, world);
                world.put(bros[i][j], 6 + j * 2, 6 + i * 2);
            }
        }

        // BubbleSorter<Calabash> b = new BubbleSorter<>();
        QuickSorter<Calabash> b = new QuickSorter<>();
        b.load(bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[][] bros2, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros2, Integer.parseInt(couple[0])).swap(getBroByRank(bros2, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[][] bros2, int rank) {
        // for (Calabash[] bro : bros2) {
        // if (bro.getRank() == rank) {
        // return bro;
        // }
        // }
        for (int i = 0; i < bros2.length; i++) {
            for (int j = 0; j < bros2[0].length; j++)
                if (bros2[i][j].getRank() == rank)
                    return bros2[i][j];
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        
        while (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
