import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang. *;
/**
 * Single players graphical version of tic Tac Toe with 
 * the ability to select stratagies to play against
 * 
 * @author Denis Atikpladza
 * @version: 2.0
 */
public class GraphicalTicTacToe extends TicTacToe implements ActionListener
{
    private JFrame gameFrame;
    private JButton[][] buttons;
    private JPanel gridPanel;
    private Move nextPlayerMove;
    
        
    public GraphicalTicTacToe()
    {
        ///Constructor that makes the initial frame for the game
        super();
            
        buttons = new JButton[SIZE][SIZE];
        gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        gridPanel.setPreferredSize(new Dimension (250, 250));
            
        for (int i = 0; i < SIZE; i += 1) 
        {
                for (int j = 0; j < SIZE; j += 1) 
                {
                    buttons[i][j] = new JButton();
                    
                    buttons[i][j].addActionListener(this);
                    
                    buttons [i][j].setActionCommand("" + i + j);
                    
                    gridPanel.add(buttons[i][j]);
                }
        }
        
        print();
        
        gameFrame = new JFrame();
        gameFrame.add(gridPanel, BorderLayout.NORTH);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);
        
        JButton b1 = new JButton("First move");
        JButton b2 = new JButton("Random");
        JButton b3 = new JButton("Greedy");
        
        ///these new buttons are the buttons to be added to the frame that hold the stratagies
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setMoveStrategy(new FirstAvailableMove());
                JOptionPane.showMessageDialog(null, "computer is on First legal move mode");
            }
        });
        
        b2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setMoveStrategy(new RandomMove());
                JOptionPane.showMessageDialog(null, "computer is on Random move mode");
            }   
        });
        
        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setMoveStrategy(new GreedyMove());
                JOptionPane.showMessageDialog(null, "computer is on Greedy move mode");
            }
        });
        
        
        JPanel p = new JPanel();
        p.add(b1);
        p.add(b2);
        p.add(b3);
        gameFrame.add(p,BorderLayout.SOUTH);
        gameFrame.pack();
    }
    
    ///Prints the board in its current state
    public void print ()
    {
            for (int i = 0; i < SIZE; i += 1) 
        {
                for (int j = 0; j < SIZE; j += 1) 
                {
                    buttons[i][j].setText("" + grid[i][j]);
                    if (grid[i][j] != '_')
                    { 
                        buttons[i][j].setEnabled(false);
                }
        }
    }
    }
     
    ///This loop is reused from the TTT loop given with some changes to take onto account the GUI 
    public void loop() {
            int status = ONGOING;
            Move move = null;
            boolean hasPassed = false;
            
            nextPlayerMove = null;
            while (status == ONGOING) {
               print();
               if (!(generateMoves().isEmpty())) {
                   do {///This program doesnt like to be in a loop with notihng in it so
                       ///I filled it with a arbitrary wait function so it wouldnt error
                       try 
                       {
                           Thread.sleep(10);
                       }
                       catch(InterruptedException ie) {}
                       
                       if(nextPlayerMove != null)
                       {
                           move = nextPlayerMove;
                           nextPlayerMove = null;
                        }
                    }
                   while (move == null || !(canPlay(move)));
                   status = play(move);
                   move = null;
                   hasPassed = false;
               }
               
               else {
                   if (hasPassed) status = GAME_OVER;
                   else {
                       hasPassed = true;
                       System.out.println("No available move for you!");
                   }
               }
               
               if (status == ONGOING) {
                   print();
                   System.out.println("Computer's turn: ");
                   toggleTurn();
                   if (!(generateMoves().isEmpty())) {
                       status = machinePlay();
                       hasPassed = false;
                    }
                   else {
                       if (hasPassed) status = GAME_OVER;
                       else {
                           hasPassed = true;
                           System.out.println("No available move for me!");
                       }
                    }
               }           
               toggleTurn();
            }
            
            determineWinner();
            displayStatus(status);
            System.out.println("Bye bye!");
               
        }  
        
    public void actionPerformed(ActionEvent event)
    {
             
            JButton button = (JButton) event.getSource();
               
            String command = event.getActionCommand();
            
            int row = Integer.parseInt(command.substring(0, 1));
            int col = Integer.parseInt(command.substring(1, 2));        
            nextPlayerMove = new Move(row, col);
            System.out.println("Sent Move");
            
    }
    
    public static void main(String [ ] args)
    {
        (new GraphicalTicTacToe()).loop();
    }
}
