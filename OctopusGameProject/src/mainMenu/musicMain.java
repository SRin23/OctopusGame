package mainMenu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

import mainMenu.GameMainGUI;

class musicMain extends Thread{
	private Player player;
	private boolean isLoop;//무한반복 확인
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public musicMain(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(GameMainGUI.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if(player==null) {
			return 0;
		}
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();	//스레드를 중지상태로 만듦(음악 실행 종료)
	}

	@Override
	public void run() {
		try {
			do {
				GameMainGUI.chkPlay = true;
					player.play();
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis);

			}while(isLoop);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}

