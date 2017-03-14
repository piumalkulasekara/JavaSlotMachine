/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SlotMachine;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.border.Border;


public class SlotMachineGui extends JFrame {

    Main main = new Main();
    event evntAddCoin = new event();
    event2 evntBetOne = new event2();
    event3 evntBetMax = new event3();
    event4 evntReset = new event4();
    event5 evntStats = new event5();
    event6 evntSpin = new event6();

    Reel reel1 = new Reel();
    Reel reel2 = new Reel();
    Reel reel3 = new Reel();
    Symbol symbol = new Symbol();

    ImageIcon image1, image2, image3;
    private int value1, value2, value3;
    private JButton btnSpin, btnBetOne, btnBetMax, btnReset, btnAddCoin, btnStat, btn7;
    private JLabel slotImg1, slotImg2, slotImg3, lblCredits, lblBets;
    private JLabel winLbl, lossesLbl, avgCreditsLbl;
    private JLabel txtLbl3, txtLbl4, txtLbl5;
    private JLabel lblStats, txtLbl7, txtLbl8;
    private static boolean mouse;
    Container contentPane;

    static int noOfWins = 0;
    static int noOfLose = 0;
    static int noOfSpin = 0;
    static int noOfFreeSpins = 0;

    public ArrayList<Symbol> spinValue;

    SlotMachineGui() {

        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.white);
        contentPane.setLayout(new FlowLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 5, 5);

        addSlotImages();
        addButton();
        evntAddCoin();
        evntBetOne();
        evntBetMax();
        evntReset();
        evntStats();
        evntSpin();

    }

    public void addButton() {
        Border border = BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(229, 229, 204));

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 5, 5);

        btnSpin = new JButton("SPIN");
        btnBetOne = new JButton("Bet One");
        btnBetMax = new JButton("Bet Max");
        btnReset = new JButton("Reset");
        btnAddCoin = new JButton("Add Coin");
        btnStat = new JButton("Statistics");

        btnSpin.setForeground(new Color(116, 37, 37));
        btnBetOne.setForeground(new Color(116, 37, 37));
        btnBetMax.setForeground(new Color(116, 37, 37));
        btnReset.setForeground(new Color(116, 37, 37));
        btnAddCoin.setForeground(new Color(116, 37, 37));
        btnStat.setForeground(new Color(116, 37, 37));

        btnSpin.setBackground(new Color(230, 230, 255));
        btnSpin.setSize(500, 500);
        
        //create JLabel to display credits
        lblCredits = new JLabel("      $ " + Integer.toString(main.credits));
        lblCredits.setBorder(BorderFactory.createTitledBorder(border, "CREDIT AREA"));
        
        //create JLabel to display bet credits
        lblBets = new JLabel("      $ " + Integer.toString(main.betCredits));
        lblBets.setBorder(BorderFactory.createTitledBorder(border, "BET AREA"));
        
        //create three JLabels to display current status of the slot game 
        lblStats = new JLabel("                      STATUS  >>>");
        txtLbl7 = new JLabel();
        txtLbl8 = new JLabel();
        
        //set font color of this JLabels
        txtLbl7.setForeground(new Color(255, 26, 26));
        txtLbl8.setForeground(new Color(255, 26, 26));

        //set location for buttons in grid layout
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPane.add(btnSpin, gbc);
        btnSpin.setEnabled(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(btnBetOne, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        contentPane.add(btnBetMax, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPane.add(btnReset, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        contentPane.add(btnAddCoin, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        contentPane.add(btnStat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        contentPane.add(lblCredits, gbc);

        gbc.gridx = 2;
        gbc.gridy = 10;
        contentPane.add(lblBets, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        contentPane.add(lblStats, gbc);

        gbc.gridx = 1;
        gbc.gridy = 13;
        contentPane.add(txtLbl7, gbc);

        gbc.gridx = 1;
        gbc.gridy = 14;
        contentPane.add(txtLbl8, gbc);
    }

    public void addSlotImages() {
        Border border = BorderFactory.createLineBorder(new Color(229, 229, 204), 5);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 2;
        gbc.ipady = 2;

        image1 = new ImageIcon(reel1.symbol.get(0).getImage());
        //create new JLabel to store slot images
        slotImg1 = new JLabel(image1);
        //  slotImg1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        slotImg1.setBorder(BorderFactory.createTitledBorder(border, "REEL01"));

        image2 = new ImageIcon(reel2.symbol.get(1).getImage());
        //create new JLabel to store slot images
        slotImg2 = new JLabel(image2);
        // slotImg2.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        slotImg2.setBorder(BorderFactory.createTitledBorder(border, "REEL02"));

        image3 = new ImageIcon(reel3.symbol.get(2).getImage());
        //create new JLabel to store slot images
        slotImg3 = new JLabel(image3);
        // slotImg3.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        slotImg3.setBorder(BorderFactory.createTitledBorder(border, "REEL03"));

        //set location for image labels in grid layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(slotImg1, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPane.add(slotImg2, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        contentPane.add(slotImg3, gbc);

    }

    //eventHandling for AddCoin button
    public void evntAddCoin() {
        btnAddCoin.addActionListener((ActionListener) evntAddCoin);

    }

    //eventHandling for BetOne button
    public void evntBetOne() {
        btnBetOne.addActionListener((ActionListener) evntBetOne);

    }

    //eventHandling for BetMax button
    public void evntBetMax() {
        btnBetMax.addActionListener((ActionListener) evntBetMax);

    }

    //eventHandling for Reset button
    public void evntReset() {
        btnReset.addActionListener((ActionListener) evntReset);

    }

    //eventHandling for Statistics button
    public void evntStats() {
        btnStat.addActionListener((ActionListener) evntStats);

    }

    //eventHandling for SPIN button
    public void evntSpin() {
        btnSpin.addActionListener((ActionListener) evntSpin);

    }

//create class in SlotMachineGui class for implements ActionListener for AddCoin button
    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (main.credits == 0) {
                txtLbl7.setText("You can't add more credits");
                txtLbl8.setText("");
            } else {
                main.credits++;
                lblCredits.setText("      $ " + Integer.toString(main.credits));
            }
        }

    }

    //create class in SlotMachineGui class for implements ActionListener for BetOne button
    public class event2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event2) {
            if (main.betCredits < 3) {
                if (main.credits > 0) {
                    main.betCredits++;
                    main.credits--;
                    lblCredits.setText("      $ " + Integer.toString(main.credits));
                    lblBets.setText("      $ " + Integer.toString(main.betCredits));
                    btnSpin.setEnabled(false);
                } else {
                    txtLbl7.setText("You have not enough credits to play");
                    txtLbl8.setText("");
                }
            } else {
                txtLbl7.setText("You can't bet that amount!");
                txtLbl8.setText("");
            }
        }
    }

    //create class in SlotMachineGui class for implements ActionListener for BetMax button
    public class event3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event3) {
            if (main.betCredits < 3) {
                if (main.credits >= 3) {
                    main.betCredits += 3;
                    main.credits -= 3;
                    lblCredits.setText("      $ " + Integer.toString(main.credits));
                    lblBets.setText("      $ " + Integer.toString(main.betCredits));
                } else {
                    txtLbl7.setText("You can't bet that amount!");
                    txtLbl8.setText("");
                }

            } else {
                txtLbl7.setText("You can't bet that amount!");
                txtLbl8.setText("");
            }
        }
    }

    //create class in SlotMachineGui class for implements ActionListener for Reset button
    public class event4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event4) {
            main.credits = main.credits + main.betCredits;
            main.betCredits = 0;
            lblCredits.setText("      $ " + Integer.toString(main.credits));
            lblBets.setText("      $ " + Integer.toString(main.betCredits));

        }
    }

    //create class in SlotMachineGui class for implements ActionListener for Statistics button
    public class event5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event5) {
            //create new JFrame for Statistics window 
            StackWindow stackWindow = new StackWindow(noOfWins, noOfLose, noOfSpin);
            stackWindow.setTitle("SLOT GAME/STATISTICS");
            stackWindow.setSize(600, 500);
            stackWindow.getContentPane().setBackground(new Color(178, 178, 102));
            stackWindow.setLocationRelativeTo(null);
            //  stackWindow.pack();
            stackWindow.setVisible(true);
        }

    }

    //create class in SlotMachineGui class for implements ActionListener for SPIN button
    public class event6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event6) {
            if (main.betCredits > 0) {
                if (main.betCredits <= 3) {

                    noOfSpin++;
                    reel1.Spin(reel1.symbol);
                    reel2.Spin(reel2.symbol);
                    reel3.Spin(reel3.symbol);
                    //thread 1
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            start(reel1, 10, image1, slotImg1);
                        }
                    });
                    //thread 2
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            start(reel2, 20, image2, slotImg2);
                        }
                    });
                    //thread 3
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            start(reel3, 30, image3, slotImg3);
                        }
                    });
                    
                    
                    spinValue = new ArrayList<Symbol>();
                } else {
                    txtLbl7.setText("Your BetCredit is High!");
                    txtLbl8.setText("");
                }
            } else {
                txtLbl7.setText("Sorry!! Your BetCredit is $0");
                txtLbl8.setText("");
            }

        }

        //get values of symbols
        public void setReelValue(Symbol s1, Symbol s2, Symbol s3) {
            value1 = (s1.getValue());
            value2 = (s2.getValue());
            value3 = (s3.getValue());

        }

        public void checkThreeSymbols(Symbol s1, Symbol s2, Symbol s3) {
            //  System.out.println(symbol.matchSymbols);
            if (symbol.checkThreeSymbols(s1, s2, s3) == 3) {
                noOfWins++;
                txtLbl7.setText("Congratulations!!YOU WON");
                int wonCredits = main.betCredits * value1;
                txtLbl8.setText("You Won $ " + wonCredits + " Credits");
                main.credits = main.credits + wonCredits;
                main.betCredits = 0;
                lblCredits.setText("      $ " + Integer.toString(main.credits));
                lblBets.setText("      $ " + Integer.toString(main.betCredits));

            } else if (symbol.checkThreeSymbols(s1, s2, s3) == 2) {
                noOfFreeSpins++;
                txtLbl7.setText("       YOU HAVE FREE SPIN");
                txtLbl8.setText("");
                // main.betCredits = 0;
                lblBets.setText("      $ " + Integer.toString(main.betCredits));

            } else if (symbol.checkThreeSymbols(s1, s2, s3) == 0) {
                noOfLose++;
                txtLbl7.setText("       Sorry!!  YOU LOSE");
                txtLbl8.setText("");
                main.betCredits = 0;
                lblBets.setText("      $ " + Integer.toString(main.betCredits));

            }

        }

        private void start(Reel reel, int sleepTime, ImageIcon icon, JLabel label) {
            SwingWorker<Boolean, Symbol> worker = new SwingWorker<Boolean, Symbol>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    // Simulate doing something useful.
                    int n = 0;
                    if (mouse == false) {
                        do {

                            // The type we pass to publish() is determined
                            // by the second template parameter.
                            Random Dice = new Random();
                            n = Dice.nextInt(5);

                            //publish(slot[n]);
                            publish(reel.symbol.get(n));
                            // setReelIcon();
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                                System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

                            }

                            events e = new events();
                            label.addMouseListener(e);

                        } while (mouse != true);
                 
                    }

                    if (mouse = true) {

                        System.out.println("The value of the Symbol " + reel.symbol.get(n).getValue() + " - " + "The index of the Symbol " + n);
                        System.out.println(reel.symbol.toString());
                        //assigning the symbols to an arraylist
                        spinValue.add(reel.symbol.get(n));
                        //setting the spinnned symbol values
                        setReelValue(spinValue.get(0), spinValue.get(1), spinValue.get(2));
                        //checking for any similarities
                        checkThreeSymbols(spinValue.get(0), spinValue.get(1), spinValue.get(2));
                        //  lblBetArea.setText(betString + Integer.toString(bet));

                    }
                   
                    return true;

                }
                
                //update the GUI from this method.
                protected void done() {
                    boolean status;
                    try {
                        // Retrieve the return value of doInBackground.
                        status = get();
                        mouse = false;
                        // statusLabel.setText("Completed with status: " + status);
                    } catch (InterruptedException e) {
                        // This is thrown if the thread's interrupted.
                    } catch (ExecutionException e) {
                        // This is thrown if we throw an exception
                        // from doInBackground.
                    }
                }

                @Override
                // Can safely update the GUI from this method.
                protected void process(java.util.List<Symbol> chunks) {
                    // Here we receive the values that we publish().
                    // They may come grouped in chunks.
                    Symbol mostRecentValue = chunks.get(chunks.size() - 1);
                    //countLabel1.setText(Integer.toString(mostRecentValue.getValue()));
                    //countLabel1.setText((mostRecentValue.getImage()));
                    try {
                        //icon = new ImageIcon(mostRecentValue.getImage());
                        label.setIcon(new ImageIcon(mostRecentValue.getImage()));
                    } catch (Exception e) {
                    }
                    //System.out.println();
                }
            };
            worker.execute();
        }

        public class events implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                //mouse = true;
                //checkThreeSymbols(reel1.symbol.get(0), reel2.symbol.get(0), reel3.symbol.get(0));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouse = true;
            }

            public void mouseReleased(MouseEvent e) {
                //mouse = true;
            }

            public void mouseEntered(MouseEvent e) {
                ///mouse = true;
            }

            public void mouseExited(MouseEvent e) {
                //mouse = true;
            }
        }

    }
}
