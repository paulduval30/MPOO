package serie09;

import org.jmock.api.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Speedometer
{
    private SpeedometerModel model;
    private JRadioButton km;
    private JRadioButton mi;
    private GraphicSpeedometer speed;
    private JButton moins;
    private JButton plus;
    private JButton turn;

    private JFrame main;

    public Speedometer()
    {
        createModel();
        createView();
        placeComponents();
        createController();
    }

    private void createController()
    {
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ActionListener act = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == km){
                    model.setUnit(SpeedometerModel.SpeedUnit.KMH);
                }
                else
                    model.setUnit(SpeedometerModel.SpeedUnit.MIH);
            }
        };
        km.addActionListener(act);
        mi.addActionListener(act);

        act = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource() == moins)
                    model.slowDown();
                else
                    model.speedUp();
            }
        };
        moins.addActionListener(act);
        plus.addActionListener(act);

        act = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(model.isOn())
                {
                    turn.setText("Turn on");
                    model.turnOff();
                    moins.setEnabled(false);
                    plus.setEnabled(false);
                }
                else
                {
                    turn.setText("Turn Off");
                    model.turnOn();
                    moins.setEnabled(true);
                    plus.setEnabled(true);
                }
            }
        };
        turn.addActionListener(act);
        model.addObserver(new Observer()
        {
            @Override
            public void update(Observable o, Object arg)
            {
                refresh();
            }
        });
    }

    private void placeComponents()
    {
        JPanel p = new JPanel(new GridLayout(0, 1));{
            JPanel q = new JPanel();{
                JPanel r = new JPanel(new GridLayout(0,1));{
                    r.add(km);
                    r.add(mi);
                }
                q.add(r);
            }
            p.add(q);

            q = new JPanel();{
                JPanel r = new JPanel(new GridLayout(0,1));{
                    r.add(moins);
                    r.add(plus);
                }
                q.add(r);
                q.add(turn);
            }
            p.add(q);
        }
        main.add(p, BorderLayout.WEST);
        main.add(speed, BorderLayout.EAST);

    }

    private void display() {
        refresh();
        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }

    private void refresh()
    {
        main.repaint();
    }

    private void createView()
    {


        main = new JFrame();
        ButtonGroup grp = new ButtonGroup();
        km = new JRadioButton("km/h");
        mi = new JRadioButton("mi/h");
        grp.add(km);
        grp.add(mi);
        km.setSelected(true);


        this.moins = new JButton("moins");
        moins.setEnabled(false);

        this.plus = new JButton("plus");
        plus.setEnabled(false);


        this.turn = new JButton("Turn on");

        this.speed = new GraphicSpeedometer(this.model, new Dimension(500,500));
    }

    private void createModel()
    {
        this.model = new StdSpeedometerModel(10, 150);

    }

    public static void main(String[] argv)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Speedometer().display();
            }
        });
    }
}
