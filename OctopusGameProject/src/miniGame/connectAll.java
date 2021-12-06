package miniGame;

import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FCP.panGame;
import RSP.rspGame;
import mainMenu.GameEnding_fail;
import mainMenu.GameEnding_success;
import marbleOddEven.MarbleGameGUI;

public class connectAll {
    final int FRAME_CNT=4;
    public JFrame panJf;
    public JFrame marbleJf;
    public JFrame catchMoleJf;
    public JFrame rspJf;

    JButton nextBtn[] = new JButton[FRAME_CNT];
    JLabel talk[] = new JLabel[FRAME_CNT];
    JLabel img[] = new JLabel[FRAME_CNT];

    ImageIcon sMan = new ImageIcon(connectAll.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(connectAll.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(connectAll.class.getResource("../img/circleManager.png"));

    boolean chkEnter = false;
    boolean start;
    public static int heart = 2;
    
    public connectAll(String pNum){

    	Font font = new Font("���ﳲ�� ��üB",Font.PLAIN,20);

        panJf = new JFrame();
        rspJf = new JFrame();
        marbleJf = new JFrame();
        catchMoleJf = new JFrame();

        Container c1 = panJf.getContentPane();
        Container c2 = catchMoleJf.getContentPane();
        Container c3 = rspJf.getContentPane();
        Container c4 = marbleJf.getContentPane();

        panJf.setTitle("pan");
		//â�� ���� �� ���α׷� ����
		panJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		panJf.setResizable(false);
		panJf.setSize(1200,900);
        c1.setBackground(Color.BLACK); 
		//������(���� x �ִ� ��) ���̰� ����
		panJf.setVisible(true);

        rspJf.setTitle("rsp");
		//â�� ���� �� ���α׷� ����
		rspJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		rspJf.setResizable(false);
		rspJf.setSize(1200,900);
        c3.setBackground(Color.BLACK); 
		//������(���� x �ִ� ��) ���̰� ����
		rspJf.setVisible(false);

        marbleJf.setTitle("marble");
		//â�� ���� �� ���α׷� ����
		marbleJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		marbleJf.setResizable(false);
		marbleJf.setSize(1200,900);
        c4.setBackground(Color.BLACK); 
		//������(���� x �ִ� ��) ���̰� ����
		marbleJf.setVisible(false);

        catchMoleJf.setTitle("catchMole");
		//â�� ���� �� ���α׷� ����
		catchMoleJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		catchMoleJf.setResizable(false);
		catchMoleJf.setSize(1200,900);
        c2.setBackground(Color.BLACK); 
		//������(���� x �ִ� ��) ���̰� ����
		catchMoleJf.setVisible(false);

        panJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                	
                	chkEnter = true;
                    System.out.println(chkEnter);
                    if(chkEnter) {
                        panJf.dispose();
                        new panGame(pNum).startGame();
                        chkEnter = false;
                    }
                }
            }
        });

        marbleJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter = true;
                    if(chkEnter) {
                        marbleJf.dispose();
                       new MarbleGameGUI(pNum).startGame(start);
                        chkEnter =false;
                    	
                    }
                }
            }
        });

        catchMoleJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter = true;
                    if(chkEnter) {
                        catchMoleJf.dispose();
                        new catchMole.CatchMoleGUI(pNum).startGame();
                        chkEnter = false;
                        
                    }
                }
            }
        });

        rspJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                	chkEnter = true;
                    if(chkEnter) {
                        rspJf.dispose();
                        start = new rspGame(pNum).startGame();
                        chkEnter = false;
                    }
                }
            }
        });

        talk[0] = new JLabel("<html>������ȣ "+pNum+", �Ͼ�ñ� �ٶ��ϴ�.<br>"
        		+ "���� ����� �޲ٹ� ���̿� �����߽��ϴ�.<br> "
        		+ "�ּ��� ���� ������ �����Ͽ� ��� ����� �޾ư��ñ� �ٶ��ϴ�.<br><br>" 
        		+"ù��° ������ �� ������ �Դϴ�.<br>"
        		+ "�� ������� ���ο��� �־��� ���� ���� �� ���� ������ ����� �̱�� �����Դϴ�.<br>"  
        		+"���� ������ ���ؼ� ���콺�� Ŭ���� �ϸ� �˴ϴ�.<br>"
        		+ "�־��� �ð��� ���� �� ������ ���� �� ���� ���̸� �̱� �� �ֽ��ϴ�.<br><br>"  
        		+"�׷� ������ ���ϴ�.</html>" );
        talk[1] = new JLabel("<html>���� ������ �δ��� ����Դϴ�.<br>"
        		+ "������ �ſ� �����մϴ�.<br>���� ���̴� �δ����� ������ Ŭ���Ͽ� ������ ������ �˴ϴ�.<br><br>"
        		+ "�׷� ������ ���ϴ�.</html>");
        talk[2] = new JLabel("<html>���ݱ��� ����ִ� �����<br>"
        		+ "�ſ� ���� ���ų� �Ƿ��� ���� ���Դϴ�.<br><br>"+
        		"������ ������ Ȧ¦ �����Դϴ�.<br>"
        		+ "Ȧ¦ ������ ������ ���ϱ� ���� ������������ �����մϴ�.<br>"
        		+"������������ �ڽ��� �� �������� ���� �˴ϴ�.<br>"+
        		"�̱�� �ʷϻ�, ���� ������, ���� ������Դϴ�.<br>"+
        		"�� �＼�� �� �� ������ �̱�� �˴ϴ�.<br><br>"+
        		"�׷� ������ ���ϴ�.</html>");
        talk[3] = new JLabel("<html>Ȧ¦ ������ �ϸ��� ������ ����˴ϴ�.<br>" + 
        		"���� ������ �տ� �� ������ �����մϴ�.<br>" + 
        		"����� ������ �� ������ ���� Ȧ���� ¦���� ���߰� �˴ϴ�.<br>" + 
        		"���� ���� ��� ������ ������ " + 
        		"���񿡰� �Ѿ��," + 
        		"������ ���� ��� ������ �Ѱ��ݴϴ�.<br>" + 
        		"���������� ������ ���� ����� ���� �˴ϴ�.<br>"
        		+ "���������� Ȧ¦ ������ �����غ��ڽ��ϴ�.<br><br>"
        		+ "�׷� ������ ���ϴ�.</html>");
        
        for(int i=0; i<FRAME_CNT; i++){
        	talk[i].setFont(font.deriveFont(30.0f));
            talk[i].setBounds(350,400,800,420);
            talk[i].setOpaque(true); //���� ����� �⺻������ �����̱� ������ ������ �ȸ���
            talk[i].setBackground(Color.WHITE);
            talk[i].setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
            talk[i].setForeground(Color.BLACK);
        }
        c1.add(talk[0]);
        c2.add(talk[1]);
        c3.add(talk[2]);
        c4.add(talk[3]);

        //���� ��� �̹���
        img[0] = new JLabel(sMan);
        img[0].setBounds(0, 500, 300, 400);
        c1.add(img[0]);

        img[1] = new JLabel(tMan);
        img[1].setBounds(0, 500, 300, 400);
        c2.add(img[1]);

        img[2] = new JLabel(cMan);
        img[2].setBounds(0, 500, 300, 400);
        c3.add(img[2]);

        img[3] = new JLabel(cMan);
        img[3].setBounds(0, 500, 300, 400);
        c4.add(img[3]);

        //story �ѱ��
        for(int i=0; i<FRAME_CNT; i++){
            nextBtn[i] = new JButton("ENTER �Ǵ� �̰��� ������ �Ѿ�ϴ�.");
            nextBtn[i].setBounds(800, 30, 400, 30);
            nextBtn[i].setBorderPainted(false);
            nextBtn[i].setContentAreaFilled(false);
            nextBtn[i].setFont(font.deriveFont(20.0f));
            nextBtn[i].setForeground(Color.WHITE);
            nextBtn[i].setFocusPainted(false);

            switch(i){
                case 0:        
                    nextBtn[i].setText("ENTER �Ǵ� �̰��� ������ �Ѿ�ϴ�.");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panJf.dispose();
                            new panGame(pNum).startGame();
                            System.out.println(heart);
                        }
                    });
                    c1.add(nextBtn[i]);
                    break;
                case 1:
                nextBtn[i].setText("ENTER �Ǵ� �̰��� ������ �Ѿ�ϴ�.");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	catchMoleJf.dispose();
                        	new catchMole.CatchMoleGUI(pNum).startGame();
                        }
                    });
                    c2.add(nextBtn[i]);
                    break;
                case 2:
                nextBtn[i].setText("ENTER �Ǵ� �̰��� ������ �Ѿ�ϴ�.");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rspJf.dispose();
                            start = new rspGame(pNum).startGame();
                        }
                    });
                    c3.add(nextBtn[i]);
                    break;
                case 3:
                nextBtn[i].setText("ENTER �Ǵ� �̰��� ������ �Ѿ�ϴ�.");
                nextBtn[i].addActionListener(new ActionListener() {
            
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        marbleJf.dispose();
                        new MarbleGameGUI(pNum).startGame(start);
                    }
                });
                    c4.add(nextBtn[i]);
                    break;
            }
        }
        JLabel jl[] = new JLabel[FRAME_CNT];
        for(int i=0; i<FRAME_CNT; i++)
            jl[i] = new JLabel();
        c1.add(jl[0]);
        c2.add(jl[1]);
        c3.add(jl[2]);
        c4.add(jl[3]);
    }
}
