package IHM.serie01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EventTester
{
    private JFrame mainFrame;
    private JFrame testFrame;
    private JButton createFrame;

    private JButton razButton;

    private JTextArea mlArea;
    private JTextArea wflArea;
    private JTextArea wlArea;
    private JTextArea klArea;
    private JTextArea wslArea;
    private JTextArea mwlArea;
    private JTextArea mmlArea;

    private ArrayList<JTextArea> areaList;

    public static int eventCount = 0;
    private static int RAZ_count = 0;


    public EventTester(){
        this.createModel();
        this.createView();
        this.placeComponents();
        this.createController();

    }

    private void createController()
    {
        ActionListener aL = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createTestFrame();

            }
        };
        this.createFrame.addActionListener(aL);

        aL = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for(JTextArea j : areaList)
                    writeInArea(j, "_____RAZ " + ++EventTester.RAZ_count + "_______");
                EventTester.eventCount = 0;
            }
        };
        this.razButton.addActionListener(aL);
    }

    private void createTestFrame()
    {
        if(testFrame != null)
            this.testFrame.dispose();
        this.testFrame = new JFrame();
        this.testFrame.setVisible(true);
        this.testFrame.setSize(300,200);
        MouseListener ml = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                writeInArea(mlArea, ++EventTester.eventCount + e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                writeInArea(mlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                writeInArea(mlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                writeInArea(mlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();

            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                writeInArea(mlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();

            }
        };
        this.testFrame.addMouseListener(ml);

        WindowFocusListener wfl = new WindowFocusListener()
        {
            @Override
            public void windowGainedFocus(WindowEvent e)
            {
                writeInArea(wflArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowLostFocus(WindowEvent e)
            {
                writeInArea(wflArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }
        };
        this.testFrame.addWindowFocusListener(wfl);

        KeyListener kl = new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                writeInArea(klArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                writeInArea(klArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                writeInArea(klArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }
        };
        this.testFrame.addKeyListener(kl);

        WindowListener wl = new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowClosed(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowIconified(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowDeiconified(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowActivated(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void windowDeactivated(WindowEvent e)
            {
                writeInArea(wlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }
        };
        this.testFrame.addWindowListener(wl);

        WindowStateListener wsl = new WindowStateListener()
        {
            @Override
            public void windowStateChanged(WindowEvent e)
            {
                writeInArea(wslArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }
        };

        this.testFrame.addWindowStateListener(wsl);

        MouseWheelListener mwl = new MouseWheelListener()
        {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e)
            {
                writeInArea(mwlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }
        };
        this.testFrame.addMouseWheelListener(mwl);

        MouseMotionListener mml = new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                writeInArea(mmlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }

            @Override
            public void mouseMoved(MouseEvent e)
            {
                writeInArea(mmlArea, ++EventTester.eventCount + " " +  e.paramString().split(",")[0]);
                refresh();
            }
        };

        this.testFrame.addMouseMotionListener(mml);
    }

    private void placeComponents()
    {
        JPanel p = new JPanel();{
            p.add(this.createFrame);
            p.add(this.razButton);
        }

        this.mainFrame.add(p, BorderLayout.NORTH);

        p = new JPanel(new GridLayout(3,3));
        JScrollPane q = new JScrollPane(mlArea);
        q.setBorder(BorderFactory.createTitledBorder("Mouse Listener"));
        p.add(q);

        JScrollPane wflScroll = new JScrollPane(wflArea);
            wflScroll.setBorder(BorderFactory.createTitledBorder("Window Focus Listener"));
        p.add(wflScroll);

        JScrollPane wlScroll = new JScrollPane(wlArea);
        wlScroll.setBorder(BorderFactory.createTitledBorder("Window Listener"));
        p.add(wlScroll);

        JScrollPane klScroll = new JScrollPane(klArea);
        klScroll.setBorder(BorderFactory.createTitledBorder("Key Listener"));
        p.add(klScroll);

        JScrollPane wslScroll = new JScrollPane(wslArea);
        wslScroll.setBorder(BorderFactory.createTitledBorder("Window State Listener"));
        p.add(wslScroll);

        JScrollPane mwlScroll = new JScrollPane(mwlArea);
        mwlScroll.setBorder(BorderFactory.createTitledBorder("Mouse wheel Listener"));
        p.add(mwlScroll);

        JScrollPane mmlScroll = new JScrollPane(mmlArea);
        mmlScroll.setBorder(BorderFactory.createTitledBorder("Mouse Motion Listener"));
        p.add(mmlScroll);

        this.mainFrame.add(p, BorderLayout.CENTER);

    }

    private void createView()
    {
        this.mainFrame = new JFrame("Main Frame");
        this.createFrame = new JButton("Create Frame");
        this.razButton = new JButton("RAZ Compteur");
        this.testFrame = new JFrame();

        this.klArea = new JTextArea();
        this.mlArea = new JTextArea();
        this.wflArea = new JTextArea();
        this.mmlArea = new JTextArea();
        this.wlArea = new JTextArea();
        this.wslArea = new JTextArea();
        this.mwlArea = new JTextArea();

        this.areaList = new ArrayList<>();
        areaList.add(klArea);
        areaList.add(mlArea);
        areaList.add(wflArea);
        areaList.add(mmlArea);
        areaList.add(wlArea);
        areaList.add(wslArea);
        areaList.add(mwlArea);

    }

    private void writeInArea(JTextArea area, String text)
    {
        area.setText(area.getText() + "\n" + text);
    }

    private void createModel()
    {
    }

    private void display() {
        refresh();
        mainFrame.setLocation(20,20);
        this.mainFrame.setSize(800, 800);
        mainFrame.setVisible(true);
    }

    private void refresh()
    {
        this.mainFrame.repaint();
    }

    public static void main(String[] argv)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventTester().display();
            }
        });
    }
}
