public class Character{
	/**Name 英雄编号
	 */
	public char Name;
	/** Hp 生命值
	 */
	public int Hp = 100;
	 /** Mp 魔法值
	  */
	public int Mp = 100;
	 /** Exp 经验值
	  */
	public int Exp = 0;
	/**英雄等级
	 */
	public int level = 0;
	/**  x轴坐标 
	 */
	public int x;
	/**  y轴坐标
	 */
	public int y;
	/**
	 控制人物移动。
	 @param num 控制英雄选择。
	 @param direction 控制英雄移动方向。
	 */
	public void move(int num, char direction) {
		switch(direction)
		{
		case 'a':
			
			x--;
			if(x<0||BattleField.A[y][x] != '0')
			{
				Hp -= 10;
				x++;
			}
			else
			{
				BattleField.A[y][x+1] = '0';
				BattleField.A[y][x] = (char)(num + 64);
			}
			break;
			
		case 's':
			y++;
			if(y>19||BattleField.A[y][x] != '0')
			{
				Hp -= 10;
				y--;
			}
			else
			{
	    		BattleField.A[y-1][x] = '0';
	    		BattleField.A[y][x] = (char)(num + 64);
			}
			break;
			
		case 'd':
			x++;
			if(x>19||BattleField.A[y][x] != '0')
			{
				Hp -= 10;
				x--;
			}
			else
			{
	    		BattleField.A[y][x-1] = '0';
	    		BattleField.A[y][x] = (char)(num + 64);
			}
			break;
			
		case 'w':
			y--;
			if(y<0||BattleField.A[y][x] != '0')
			{
				Hp -= 10;
				y++;
			}
			else
			{
	    		BattleField.A[y+1][x] = '0';
	    		BattleField.A[y][x] = (char)(num + 64);
			}
			break;
		}
	}
	/**
	 * @return x坐标
	 */
	public int ret_x(){
		return x;
	}
	/**
	 * @return y坐标
	 */
	public int ret_y(){
		return y;
	}/**
	 * @return 生命值
	 */
	public int ret_Hp()
	{
		return Hp;
	}
	/**
	 * @return 魔法值
	 */
	public int ret_Mp()
	{
		return Mp;
	}/**
	 * @return 经验值
	 */
	public int ret_Exp()
	{
		return Exp;
	}
}

class typeone extends Character{	
	public int flag = 1;
	/**
	 * 技能：发射子弹。
	 * @param num 控制英雄选择。
	 * @param direction 控制子弹发射方向。
	 */
	public synchronized void skill(int num, char direction){
		int x1 = x, y1 = y;
		int t = 1;
		if(Mp < 10) 
			return;
		else
			Mp -= 10;
		switch(direction)
		{
		case 'a':
			while(x1 > 0)
			{
				if(t > 1)
					BattleField.A[y][x1] = '0';
				if(BattleField.A[y][x1-1] == '*')
				{
					Exp += 10;
					if(Exp >= 100)
					{
						level++;
						Hp = 100;
						Mp = 100;
						Exp = 0;
					}
				}
				BattleField.A[y][--x1] = '-';
				t++;
			}
			break;
		case 's':
			while(y1 <= 18)
			{
				if(t > 1)
					BattleField.A[y1][x] = '0';
				if(BattleField.A[y1+1][x] == '*')
				{
					Exp += 10;
					if(Exp >= 100)
					{
						level++;
						Hp = 100;
						Mp = 100;
						Exp = 0;
					}
				}
				BattleField.A[++y1][x] = '-';
				t++;
			}
			break;
		case 'w':
			while(y1 > 0)
			{
				if(t > 1)
					BattleField.A[y1][x] = '0';
				if(BattleField.A[y1-1][x] == '*')
				{
					Exp += 10;
					if(Exp >= 100)
					{
						level++;
						Hp = 100;
						Mp = 100;
						Exp = 0;
					}
				}
				BattleField.A[--y1][x] = '-';
				t++;
				
			}
			break;
		case 'd':
			while(x1 <= 18)
			{
				if(t > 1)
					BattleField.A[y][x1] = '0';
				if(BattleField.A[y][x1+1] == '*')
				{
					Exp += 10;
					if(Exp >= 100)
					{
						level++;
						Hp = 100;
						Mp = 100;
						Exp = 0;
					}
				}
				BattleField.A[y][++x1] = '-';
				t++;
			}
			break;
		}
	}
}
class typetwo extends Character{
	/**
	 * 技能：清除障碍物。
	 * @param num 控制英雄选择。
	 * @param direction 控制技能使用方向。
	 */
	public void skill(int num, char direction){
		switch(direction)
		{
		case 'a':
			if(BattleField.A[y][x-1] == '*')
			{
				BattleField.A[y][x-1] = '0';
				Exp += 10;
				if(Exp >= 100)
				{
					level++;
					Hp = 100;
					Mp = 100;
					Exp = 0;
				}
			}
			else 
			break;
		case 's':
			if(BattleField.A[y+1][x-1] == '*')
			{
				BattleField.A[y+1][x-1] = '0';
				Exp += 10;
				if(Exp >= 100)
				{
					level++;
					Hp = 100;
					Mp = 100;
					Exp = 0;
				}
			}
			break;
		case 'w':
			if(BattleField.A[y-1][x] == '*')
			{
				BattleField.A[y-1][x] = '0';
				Exp += 10;
				if(Exp >= 100)
				{
					level++;
					Hp = 100;
					Mp = 100;
					Exp = 0;
				}
			}
			break;
		case 'd':
			if(BattleField.A[y][x+1] == '*')
			{
				BattleField.A[y][x+1] = '0';
				Exp += 10;
				if(Exp >= 100)
				{
					level++;
					Hp = 100;
					Mp = 100;
					Exp = 0;
				}
			}			
			break;
		}
	}
}


