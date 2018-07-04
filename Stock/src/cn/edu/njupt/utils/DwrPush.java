package cn.edu.njupt.utils;

import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;


/**
 * 推送消息
 * @author admin
 *
 */
public class DwrPush {
	
	public void send(String msg) {
		WebContext webContext = WebContextFactory.get();
		
		Collection<ScriptSession> sessions = webContext.getAllScriptSessions();
		
//		构建发送所需的js脚本
		ScriptBuffer scriptBuffer = new ScriptBuffer();
		
//		调用客服端的js脚本函数
		scriptBuffer.appendScript("callback(");
		scriptBuffer.appendData(msg);
		scriptBuffer.appendScript(")");
		
//		为所有的用户服务
		Util util = new Util(sessions);
		util.addScript(scriptBuffer);
	}
}
