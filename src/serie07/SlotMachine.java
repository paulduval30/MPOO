package serie07;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class SlotMachine {

    // ATTRIBUTS

    private JFrame mainFrame;
    private JButton play;
    private JTextField result;
    private JLabel pertes;
    private JLabel gain;
    private JLabel lastGain;

    private StdSlotModel model;

    // CONSTRUCTEURS

    public SlotMachine(int[] credits) {
        createModel(credits);
        createView();
        placeComponents();
        createController();
    }

    // COMMANDES

    /**
     * Rend l'application visible au centre de l'�cran.
     */
    public void display() {
        refresh();
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // OUTILS

    private void createModel(int[] credits) {
        model = new StdSlotModel(credits);
    }

    private void createView() {
        final int frameWidth = 300;
        final int frameHeight = 300;

        mainFrame = new JFrame("SlotMachine");
        mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));

        this.play = new JButton("Tentez votre chance");
        this.gain = new JLabel("" + model.moneyWon());
        this.pertes = new JLabel("" + model.moneyLost());
        this.lastGain = new JLabel("" + model.lastPayout());

        this.result = new JTextField(model.result());
        this.result.setEditable(false);



    }

    private void placeComponents() {
        JPanel p = new JPanel(); {
            p.add(play);
            p.add(result);

            p.add(new JLabel("Money Won : "));
            p.add(gain);

            p.add(new JLabel("Money Lost : "));
            p.add(pertes);

            p.add(new JLabel("Last Gain : "));
            p.add(lastGain);
        }
        mainFrame.add(p);
    }

    private void createController() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gamble();
                refresh();
            }
        });
    }

    private void refresh() {
        result.setText(model.result());
        gain.setText(model.moneyWon() + "");
        pertes.setText(model.moneyLost() + "");
        lastGain.setText(model.lastPayout() + "");
    }

    // POINT D'ENTREE

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SlotMachine(new int[]{0,2,5,1000000}).display();
            }
        });
    }
}
