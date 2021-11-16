import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LunchChoice extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton button;
    private JPanel textPanel, buttonPanel;
    private ArrayList<String> bobList = new ArrayList<String>();

    private boolean startCheck = false;

    private int count = 0;

    public static void main(String[] args) {
        LunchChoice lunchChoice = new LunchChoice();
        lunchChoice.dataSet();
        lunchChoice.createWindow();
    }

    private void dataSet() {
        try {
            FileReader fr = new FileReader("D:\\DevSpace\\javaTest\\src\\lunch.dat");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
                bobList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void createWindow() {
        Container container = this.getContentPane();

        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(new Font("", Font.BOLD, 100));
        textArea.setForeground(Color.green);
        textArea.setBackground(Color.WHITE);
        textPanel.add(textArea);

        buttonPanel = new JPanel();
        button = new JButton("Click");
        button.addActionListener(this);
        buttonPanel.add(button);

        container.add(textPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        this.setBounds(600, 600, 1300, 300);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("개발팀 점심메뉴");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        button.requestFocus();
    }

    private boolean toggle() {
        return startCheck = !startCheck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(toggle()) {
            Loop loop = new Loop();
            loop.start();
        }
        button.requestFocus();
    }

    class Loop extends Thread {
        @Override
        public void run() {
            while(startCheck == true) {
                textArea.setText(bobList.get(count));
                if(++count == bobList.size()) {
                    count = 0;
                }
                try {
                    Loop.sleep(18);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


