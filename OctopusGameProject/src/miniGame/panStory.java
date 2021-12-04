package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.event.KeyAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FCP.fcp;

//���� ����� �����ϴ°� 

public class panStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;
    JLabel img;
    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));

    boolean chkEnter = false;

    private void startPanGame(String playerNo){
        fcp f = new fcp(playerNo);
        f.startGame();
    }

    //story�� �ѱ��ھ� ����
    private void talking(String s){
        String str="";
        try {
            for(int i=0; i<s.length(); i++){
                if(chkEnter) {
                    return;
                }
                Thread.sleep(300);
                str+=s.charAt(i);
                talk.setText(str);
            }			
		}catch(Exception e) {
			System.out.println(e);
		}
    }

    public panStory(String s,String playerNo){
        setTitle("����");
		//â�� ���� �� ���α׷� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//������ â ����
		setResizable(false);
		setSize(1200,900);
        c1.setBackground(Color.BLACK);
		//������(���� x �ִ� ��) ���̰� ����
		setVisible(true);

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
                startPanGame(playerNo);
            }
        });

        //story ������ ������ִ� �ڽ�
        talk = new JLabel();
        talk.setBounds(400,400,1000,420);
        talk.setOpaque(true); //���� ����� �⺻������ �����̱� ������ ������ �ȸ���
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(30.0f));
        c1.add(talk);
        c1.add(nextBtn);
        talking(s);

        c1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(chkEnter) startPanGame(playerNo); //����Ű�� �ι� ������ �Ѿ
                    chkEnter = true; 
                }
            }
        });
    }

    public static void main(String[] args){
        String story = "����� ����Ѵ�.";
        new panStory(story,"123");
    }
}

