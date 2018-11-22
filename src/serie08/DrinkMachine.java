package serie08;

import serie06.DrinkTypes;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DrinkMachine
{
    private final static DrinkTypes[] drinkTypes = DrinkTypes.values();
    private JFrame mainFrame;
    private JLabel monaie;
    private JLabel credit;
    private HashMap<DrinkTypes, JButton> drinks;
    private JTextField insert;
    private JTextField rendu;
    private JTextField drink;



    public DrinkMachine() {
        createModel();
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

    private void createModel() {
    }

    private void createView() {
        final int frameWidth = 800;
        final int frameHeight = 300;


        mainFrame = new JFrame("Distributeur de boisson");
        mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));

        monaie = new JLabel("Cet appareil rend la monnaie", JLabel.CENTER);
        credit = new JLabel("Vous disposez d'un crédit de 0 cent", JLabel.CENTER);
        drinks = new HashMap<DrinkTypes, JButton>();
        for(DrinkTypes d : drinkTypes)
        {
            drinks.put(d, new JButton(d.toString()));
        }

        insert = new JTextField();
        insert.setColumns(5);
        rendu = new JTextField();
        rendu.setColumns(5);
        drink = new JTextField();
        drink.setColumns(5);
    }

    private void placeComponents() {
        JPanel p = new JPanel(new GridLayout(2,1)); {
            p.add(monaie);
            p.add(credit);
        }
        mainFrame.add(p, BorderLayout.NORTH);

        p = new JPanel(new GridLayout(3,2));{
            for(DrinkTypes d : drinkTypes)
            {
                p.add(drinks.get(d));
                p.add(new JLabel(d.getPrice() + " cent", JLabel.CENTER));
            }
        }
        mainFrame.add(p, BorderLayout.WEST);

        p = new JPanel(new GridLayout(3,2));{
            p.add(new JButton("Insérer"));
            p.add(insert);
            p.add(new JButton("Annuler"));
            p.add(new JPanel());
            p.add(new JPanel());
            p.add(new JPanel());
        }
        mainFrame.add(p, BorderLayout.EAST);

        p = new JPanel(new GridLayout(2,1));{
            JPanel q = new JPanel(new GridLayout(1,2));{
                JPanel r = new JPanel();{
                    r.add(new JLabel("Boisson"));
                    r.add(drink);
                    drink.setEditable(false);
                }
                q.add(r);

                r = new JPanel();{
                    r.add(new JLabel("Monnaie"));
                    r.add(rendu);
                    rendu.setEditable(false);
                }
                q.add(r);
            }
            p.add(q);

            q = new JPanel();{
                q.add(new JButton("Prenez votre boisson et/ou monnaie"));
            }
            p.add(q);
            mainFrame.add(p, BorderLayout.SOUTH);

        }
    }

    private void createController() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void refresh() {
        Container contentPane = mainFrame.getContentPane();
    }

    // POINT D'ENTREE

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrinkMachine().display();
            }
        });
    }
}
