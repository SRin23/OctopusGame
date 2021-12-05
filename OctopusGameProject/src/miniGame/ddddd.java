package miniGame;

import java.awt.Container;
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

public class ddddd {
    final int FRAME_CNT=4;
    public JFrame panJf;
    public JFrame marbleJf;
    public JFrame catchMoleJf;
    public JFrame rspJf;

    JButton nextBtn[] = new JButton[FRAME_CNT];
    JLabel talk[] = new JLabel[FRAME_CNT];
    JLabel img[] = new JLabel[FRAME_CNT];

    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));

    boolean chkEnter = false;

    public ddddd(){
        panJf = new JFrame();
        rspJf = new JFrame();
        marbleJf = new JFrame();
        catchMoleJf = new JFrame();

        Container c1 = panJf.getContentPane();
        Container c2 = catchMoleJf.getContentPane();
        Container c3 = rspJf.getContentPane();
        Container c4 = marbleJf.getContentPane();

        panJf.setTitle("pan");
		//창을 닫을 시 프로그램 종료
		panJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		panJf.setResizable(false);
		panJf.setSize(1200,900);
        c1.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		panJf.setVisible(true);

        rspJf.setTitle("rsp");
		//창을 닫을 시 프로그램 종료
		rspJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		rspJf.setResizable(false);
		rspJf.setSize(1200,900);
        c3.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		rspJf.setVisible(false);

        marbleJf.setTitle("marble");
		//창을 닫을 시 프로그램 종료
		marbleJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		marbleJf.setResizable(false);
		marbleJf.setSize(1200,900);
        c4.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		marbleJf.setVisible(false);

        catchMoleJf.setTitle("catchMole");
		//창을 닫을 시 프로그램 종료
		catchMoleJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		catchMoleJf.setResizable(false);
		catchMoleJf.setSize(1200,900);
        c2.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		catchMoleJf.setVisible(false);

        panJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter=true;
                    System.out.println(chkEnter);
                }
            }
        });

        marbleJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter=true;
                    System.out.println(chkEnter);
                }
            }
        });

        catchMoleJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter=true;
                    System.out.println(chkEnter);
                }
            }
        });

        rspJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter=true;
                    System.out.println(chkEnter);
                }
            }
        });

        for(int i=0; i<FRAME_CNT; i++){
            talk[i] = new JLabel();
            talk[i].setBounds(350,400,800,420);
            talk[i].setOpaque(true); //라벨의 배경은 기본적으로 투명이기 때문에 배경색이 안먹힘
            talk[i].setBackground(Color.WHITE);
            //talk[i].setHorizontalAlignment(SwingConstants.TOP);
            talk[i].setFont(talk[i].getFont().deriveFont(30.0f));
            talk[i].setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
            talk[i].setForeground(Color.BLACK);
        }
        c1.add(talk[0]);
        c2.add(talk[1]);
        c3.add(talk[2]);
        c4.add(talk[3]);

        //진행 요원 이미지
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

        //story 넘기기
        for(int i=0; i<FRAME_CNT; i++){
            nextBtn[i] = new JButton("> SKIP");
            nextBtn[i].setBounds(1030, 30, 150, 30);
            nextBtn[i].setBorderPainted(false);
            nextBtn[i].setContentAreaFilled(false);
            //nextBtn[i].setBackground(Color.BLACK);
            nextBtn[i].setFont(nextBtn[i].getFont().deriveFont(30.0f));
            nextBtn[i].setForeground(Color.WHITE);
            nextBtn[i].setFocusPainted(false);

            switch(i){
                case 0:        
                    nextBtn[i].setText("> SKIP1");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panJf.dispose();
                            new panGame("111").startGame();
                        }
                    });
                    c1.add(nextBtn[i]);
                    break;
                case 1:
                nextBtn[i].setText("> SKIP2");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            catchMoleJf.dispose();
                            //catchMole.start
                        }
                    });
                    c2.add(nextBtn[i]);
                    break;
                case 2:
                nextBtn[i].setText("> SKIP3");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rspJf.dispose();
                            new rspGame().startGame();
                        }
                    });
                    c3.add(nextBtn[i]);
                    break;
                case 3:
                nextBtn[i].setText("> SKIP4");
                nextBtn[i].addActionListener(new ActionListener() {
            
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        marbleJf.dispose();
                        //marble.start
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
