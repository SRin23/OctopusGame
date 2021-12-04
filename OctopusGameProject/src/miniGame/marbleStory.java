package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import RPS.rps;


public class marbleStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;
    JLabel img;
    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));

    int chkEnter = 0;
    boolean first;
    private void startRspGame(String playerNo){
        setVisible(false);
        rps r = new rps();
        boolean first = r.startGame();
    }

    //story�� �ѱ��ھ� ����
    private void talking(String s, String s2){
        String str="";
        try {
            for(int i=6; i<s.length(); i++){
                if(chkEnter == 2)
                    break;
                if(chkEnter == 1) 
                    talk.setText(s);
                
                Thread.sleep(110);
                str+=s.charAt(i);
                talk.setText("<html>"+str);
            }			
		}catch(Exception e) {
			System.out.println(e);
		}

        str="<html>";
        talk.setText(str);

        try {
            for(int i=6; i<s2.length(); i++){
                if(chkEnter == 4) {
                    return ;
                }
                if(chkEnter == 3) {
                    talk.setText(s2);
                    break;
                }
                Thread.sleep(110);
                str+=s2.charAt(i);
                talk.setText(str);
            }
            Thread.sleep(3000);

        }catch(Exception e) {
            System.out.println(e);
        }
        return;
    }

    public marbleStory(String s, String play,String playerNo){
        setTitle("����");
		//â�� ���� �� ���α׷� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//������ â ����
		setResizable(false);
		setSize(1200,900);
        c1.setBackground(Color.BLACK); 
		//������(���� x �ִ� ��) ���̰� ����
		setVisible(true);

        //���� ��� �̹���
        img = new JLabel(sMan);
        img.setBounds(0, 100, 300, 400);
        c1.add(img);

        //story �ѱ��
        nextBtn = new JButton("> SKIP");
        nextBtn.setBounds(1030, 30, 150, 30);
        nextBtn.setBorderPainted(false);
		nextBtn.setContentAreaFilled(false);
        nextBtn.setFont(nextBtn.getFont().deriveFont(30.0f));
        nextBtn.setForeground(Color.WHITE);
		nextBtn.setFocusPainted(false);
        nextBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //story skip
                setVisible(false);
                startRspGame(playerNo);
            }
        });

        //story ������ ������ִ� �ڽ�
        talk = new JLabel();
        talk.setBounds(350,400,800,420);
        talk.setOpaque(true); //���� ����� �⺻������ �����̱� ������ ������ �ȸ���
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(30.0f));
        talk.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
        talk.setForeground(Color.BLACK);
        c1.add(talk);
        c1.add(nextBtn);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter++; 
                }
            }
        });
        talking(s,play);
        startRspGame(playerNo);
    }

    public static void main(String[] args){
        String playerNo="123";
        String firstStory = "���ݱ��� ����ִ� �����<br>�ſ� ���� ���ų� �Ƿ��� ���� ���Դϴ�. <br>�׷� ������ ������ �����ϰڽ��ϴ�."+"</html>";
        String howGame = "<html>������ ������ Ȧ¦ �����Դϴ�.<br>Ȧ¦ ������ ������ ���ϱ� ���� ������������ �����մϴ�.<br>"+
        "Ȧ¦ ������ �ϸ��� ������ ����˴ϴ�.<br>���� ������ �ڽ��� �տ� �� ������ �����մϴ�.<br>"+
        "����� ������ �� ������ ���� Ȧ������ ¦������ ���߰� �˴ϴ�.<br>"+ 
        "�̶� ���� ���� ��� ������ ������ ���񿡰� �Ѿ��,<br>������ ���� ��� ������ �Ѱ��ݴϴ�.<br>"+
        "���������� ������ �������� ����� ���� �˴ϴ�.<br>"+
        "�׷� ������ ���ϴ�.</html>";
        new panStory(firstStory,howGame,playerNo);
    }
}

