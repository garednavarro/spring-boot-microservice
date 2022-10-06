package com.appsdeveloper.estore.productservice;

//import org.junit.Assert;
import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;


//@Testcontainers
@SpringBootTest
//@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	/*@Container
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void steProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
	}*/

	@Test
	void shouldCreateProduct() throws Exception {
		/*ProductRequest productRequest =  getProductRequest();

		String productRequestString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)
		).andExpect(status().isCreated());

		Assertions.assertEquals(1, productRepository.findAll().size());*/
	}

	/*private ProductRequest getProductRequest() {
		return ProductRequest.builder().name("Iphone 13").description("iphone 13").price(BigDecimal.valueOf(1200)).build();
	}*/

}
