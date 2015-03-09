package com.util;

import java.io.IOException;

public class TurnOffPC {
	private final int second = 60;
	/*DOS关机命令如下，怎么用你自己填吧
	shutdown [/i | /l | /s | /r | /a | /p | /h | /e] [/
	[/m \\computer][/t xxx][/d [p:]xx:yy [/c "comment"]]

	没有参数   显示帮助。这与键入 /? 是一样的
	/?         显示帮助。这与不键入任何选项是一样的
	/i         显示图形用户界面(GUI)。
	           这必须是第一个选项
	/l         注销。这不能与 /m 或 /d 选项一起使用
	/s         关闭计算机
	/r         关闭并重启动计算机
	/a         放弃系统关闭。
	           这只能在超时过程中使用
	/p         关闭本地计算机，没有超时或警告。
	           这只能与 /d 选项一起使用
	/h         休眠本地计算机。
	           这只能与 /f 选项一起使用
	/e         将计算机的意外关闭原因记入文档
	/m \\computer 指定目标计算机
	/t xxx     设置关闭前的超时为 xxx 秒。
	           有效范围是 0-600，默认为 30
	/c "comment" 重启动或关闭的原因的注释。
	           最大允许 127 个字符
	/f         强制正在运行的应用程序关闭而不事先警告用户
	/d [p:]xx:yy  提供重启动或关闭的原因
	           p 表明重启动或关闭是计划内的
	           xx 是主要原因号(小于 256 的正整数)
	           yy 是次要原因号(小于 65536 的正整数)*/
	public void turnOffPC(int time){
		Runtime run = Runtime.getRuntime();
		try {
			int temp = second * time;
		
			run.exec("shutdown.exe -s -t "+temp);
System.out.println("time:"+temp+"分钟后关机");				
			 //rt.exec("shutdown.exe -s -t 40");
			
			/*40的单位为秒，可以改成你想要的任何数字。
			如果是想定时关机，可用这句：rt.exec("at 19：00 shutdown.exe -s");19：00可以换成你想要的时间*/
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
