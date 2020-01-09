package com.anma.comercial.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("anma")
public class AnmaApiProperty {
	
	private String originPermitida = "http://anma.herokuapp.com";
	
	private final Seguranca seguranca = new Seguranca();
	
	public String getOriginPermitida() {
		return originPermitida;
	}


	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}


	public Seguranca getSeguranca() {
		return seguranca;
	}


	public static class Seguranca{
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		
	}
}
