package com.kotlin.demo

import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests (@Autowired val customerRepository: CustomerRepository) {

	@Test
	fun contextLoads() {
		runBlocking {
			val customers = customerRepository.findAll()
			customerRepository.save(Customer(null, "Hadi"))
			Assertions.assertNotNull( customers.last().id)
			Assertions.assertNotNull( customers.last().name)
			Assertions.assertEquals( "Hadi", customers.last().name)
		}
	}
}
