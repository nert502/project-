package com.java.project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiLayerTests {	
	@Test
	void test()
	{
		int x = 0;
	
		Assertions.assertThat(x).isEqualTo(0);
	}
}