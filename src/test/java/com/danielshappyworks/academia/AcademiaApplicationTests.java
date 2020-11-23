package com.danielshappyworks.academia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcademiaApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void studentAPIWorks() {
		String students = "[{\"id\":8,\"fistName\":\"A\",\"lastName\":\"A\",\"email\":\"AA@email\",\"address\":\"address\"},{\"id\":9,\"fistName\":\"B\",\"lastName\":\"B\",\"email\":\"BB@email\",\"address\":\"address\"},{\"id\":10,\"fistName\":\"C\",\"lastName\":\"C\",\"email\":\"CC@email\",\"address\":\"address\"},{\"id\":11,\"fistName\":\"D\",\"lastName\":\"D\",\"email\":\"DD@email\",\"address\":\"address\"},{\"id\":12,\"fistName\":\"E\",\"lastName\":\"E\",\"email\":\"EE@email\",\"address\":\"address\"},{\"id\":13,\"fistName\":\"F\",\"lastName\":\"F\",\"email\":\"FF@email\",\"address\":\"address\"}]";
		this.webTestClient.get().uri("/students").exchange().expectStatus().is2xxSuccessful().expectBody(String.class).isEqualTo(students);
	}

}
