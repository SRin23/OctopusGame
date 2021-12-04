package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FCP.fcp;
import RPS.rps;
import marbleOddEven.MarbleGameMain;

//���� ����� �����ϴ°� 
public class marbleStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;

    private void startMarbleGame(String playerNo){
        rps r = new rps();
    }


    marbleStory(String s){
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
                startMarbleGame("123");
            }

        });

        //story ������ ������ִ� �ڽ�
        talk = new JLabel(s);
        talk.setBounds(400,400,1000,420);
        talk.setOpaque(true); //���� ����� �⺻������ �����̱� ������ ������ �ȸ���
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(30.0f));
        c1.add(talk);
        c1.add(nextBtn);
    }
    public static void main(String[] args){
        String story = "����� ����Ѵ�.";
        new marbleStory(story);
    }
}
