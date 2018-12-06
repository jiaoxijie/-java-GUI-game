import java.util.Random;

public class BattleField{
	/**
	 * 战场构造二维数组。
	 */
	static char [][] A = new char [20][20];
	/**
	 * 初始化战场
	 */
	public int m, k;
	static public void Initiate() {
		Random rand = new Random();
		int i, j, n;
		for(i = 0 ;i < 20; i++)
			for(j = 0 ; j < 20; j++)
				A[i][j] = '0';
		n = rand.nextInt(100);
		int m ,k;
		for(i = 0; i < n; i++)
		{
			m = rand.nextInt(20);
			k = rand.nextInt(20);
			A[m][k] = '*';
		}
	}
	public int get_x()
	{
		Random rand = new Random();
		m = rand.nextInt(20);
		return m;
	}
	public int get_y()
	{
		Random rand = new Random();
		k = rand.nextInt(20);
		return k;
	}
}