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
    private ButtonGroup grp;
    private GraphicSpeedometer speed;
    private JButton moins;
    private JButton plus;
    private JButton turn;

    JFrame main;

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

    public void display() {
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

        main = new JFrame();
        grp = new ButtonGroup();

        km = new JRadioButton("km/h");
        km.addActionListener(act);

        mi = new JRadioButton("mi/h");
        mi.addActionListener(act);

        grp.add(km);
        grp.add(mi);

        km.setSelected(true);

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
        this.moins = new JButton("moins");
        moins.addActionListener(act);
        moins.setEnabled(false);

        this.plus = new JButton("plus");
        plus.addActionListener(act);
        plus.setEnabled(false);

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
        this.turn = new JButton("Turn on");
        turn.addActionListener(act);

        this.speed = new GraphicSpeedometer(this.model, new Dimension(500,500));
    }

    private void createModel()
    {
        this.model = new StdSpeedometerModel(10, 150);
        model.addObserver(new Observer()
        {
            @Override
            public void update(Observable o, Object arg)
            {
                refresh();
            }
        });
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
