package com.company;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{
    String[] questions = 	{
            "Which company created Java?",
            "Which year was Java created?",
            "What was Java originally called?",
            "Who is credited with creating Java?"
    };
    String[][] options = 	{
            {"Sun Microsystems","Starbucks","Microsoft","Alphabet"},
            {"1989","1996","1972","1492"},
            {"Apple","Latte","Oak","Koffing"},
            {"Steve Jobs","Bill Gates","James Gosling","Mark Zuckerburg"}
    };
    char[] answers = 		{
            'A',
            'B',
            'C',
            'C'
    };

    char guess;
    char answer;
    int index;
    int correctguesses=0;
    int totalquestions= questions.length;
    int result;
    int sec=10;

    JFrame Frame=new JFrame();
    JTextField TextField=new JTextField();
    JTextArea Textarea=new JTextArea();
    JButton buttonA =new JButton();
    JButton buttonB =new JButton();
    JButton buttonC =new JButton();
    JButton buttonD =new JButton();
    JLabel answer_labelA=new JLabel();
    JLabel answer_labelB=new JLabel();
    JLabel answer_labelC=new JLabel();
    JLabel answer_labelD=new JLabel();
    JLabel time_label=new JLabel();
    JLabel seconds_left=new JLabel();
    JTextField numbers_right=new JTextField();
    JTextField percentage=new JTextField();

    Timer timer=new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sec--;
            seconds_left.setText(String.valueOf(sec));
            if(sec<=0){
                displayanswer();
            }
        }
    });

    public Quiz(){
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setSize(650,650);
        Frame.getContentPane().setBackground(new Color(50,50,50));
        Frame.setLayout(null);
        Frame.setResizable(false);

        TextField.setBounds(0,0,650,50);
        TextField.setBackground(new Color(25,25,25));
        TextField.setForeground(new Color(25,255,0));
        TextField.setFont(new Font("Ink Free",Font.BOLD,30));
        TextField.setBorder(BorderFactory.createBevelBorder(1));
        TextField.setHorizontalAlignment(JTextField.CENTER);
        TextField.setEditable(false);

        Textarea.setBounds(0,50,650,50);
        Textarea.setLineWrap(true);
        Textarea.setWrapStyleWord(true);
        Textarea.setBackground(new Color(25,25,25));
        Textarea.setForeground(new Color(25,255,0));
        Textarea.setFont(new Font("MV Boli",Font.BOLD,25));
        Textarea.setBorder(BorderFactory.createBevelBorder(1));
        Textarea.setEditable(false);

        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(25,25,25));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(25,25,25));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(25,25,25));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(25,25,25));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));
        answer_labelD.setText("helo");

        seconds_left.setBounds(535,510,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setOpaque(true);
        seconds_left.setText(String.valueOf(sec));

        numbers_right.setBounds(225,255,200,100);
        numbers_right.setBackground(new Color(25,25,25));
        numbers_right.setForeground(new Color(25,255,0));
        numbers_right.setFont(new Font("Ink Free",Font.BOLD,50));
        numbers_right.setBorder(BorderFactory.createBevelBorder(1));
        numbers_right.setHorizontalAlignment(JTextField.CENTER);
        numbers_right.setEditable(false);

        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Ink Free",Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        Frame.add(seconds_left);
        Frame.add(answer_labelA);
        Frame.add(answer_labelB);
        Frame.add(answer_labelC);
        Frame.add(answer_labelD);
        Frame.add(buttonA);
        Frame.add(buttonB);
        Frame.add(buttonC);
        Frame.add(buttonD);
        Frame.add(Textarea);
        Frame.add(TextField);
        Frame.setVisible(true);

        nextquestion();

    }

    public void nextquestion(){
        if(index>=totalquestions){
            result();

        }else {
            TextField.setText("Question"+(index+1));
            Textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA){
            answer='A';
            if(answer==answers[index]) correctguesses++;
        }
        if(e.getSource()==buttonB){
            answer='B';
            if(answer==answers[index]) correctguesses++;
        }
        if(e.getSource()==buttonC){
            answer='C';
            if(answer==answers[index]) correctguesses++;
        }
        if(e.getSource()==buttonD){
            answer='D';
            if(answer==answers[index]) correctguesses++;
        }
        displayanswer();

    }

    public void displayanswer(){
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        if(answers[index] != 'A'){
            answer_labelA.setForeground(new Color(255,0,0));
        }
        if(answers[index] != 'B'){
            answer_labelB.setForeground(new Color(255,0,0));
        }
        if(answers[index] != 'C'){
            answer_labelC.setForeground(new Color(255,0,0));
        }
        if(answers[index] != 'D'){
            answer_labelD.setForeground(new Color(255,0,0));
        }

        Timer pause=new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));

                sec=10;
                answer=' ';
                seconds_left.setText(String.valueOf(sec));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextquestion();
            }
        });
        pause.setRepeats(false);
        pause.start();


    }

    public void result(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
         result=(int) ((correctguesses/(double)totalquestions)*100);

         TextField.setText("Results!");
         Textarea.setText("");
         answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        numbers_right.setText("("+correctguesses+"/"+totalquestions+")");
        percentage.setText(result+"%");

        Frame.add(numbers_right);
        Frame.add(percentage);



    }
}
