package com.java.project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
		boolean isTrue = true;  
		
		Assertions.assertThat(isTrue).isTrue();
		Assertions.assertThat(isTrue).isEqualTo(true);
		Assertions.assertThat(isTrue).isNotEqualTo(false);  
		
    }
   
}
