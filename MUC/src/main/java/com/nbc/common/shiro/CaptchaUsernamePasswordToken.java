/**
 * 
 */
package com.nbc.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private String captcha;

	// 省略 getter 和 setter 方法

	public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host,
			String captcha) {
		super(username, password, rememberMe, host);
		this.setCaptcha(captcha);
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}