package FCP;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class setTextTest {
    public static void main(String[] args) {

    	JFrame jf = new JFrame();
		JLabel jl = new JLabel();

		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		jf.setResizable(false);
		//화면 가운데 배치
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);
		
		jl.setText("남은 시간 : 20");
		//timeJl.setBounds(0, 0, 200, 30);
		jl.setLocation(0,0);
		//timeJl.setSize(200,30);
		jl.setFont(jl.getFont().deriveFont(20.0f));
		jf.getContentPane().add(jl);
		
		JButton jb = new JButton();
		//jb.setBackground(Color.RED);
		jb.setSize(100,100);
		jb.setLocation(10,10);
		jf.getContentPane().add(jb);
		
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 20;

            public void run() {

                jl.setText(""+countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}