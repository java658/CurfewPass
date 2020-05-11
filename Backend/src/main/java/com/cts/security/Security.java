package com.cts.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class Security {
	
		
		@Value("${authUsername}")
		private String authUsername;
		
		@Value("${authPassword}")
		private String authPassword;
		
		public String getAuthUsername() {
			return authUsername;
		}

		public String getAuthPassword() {
			return authPassword;
		}


}
