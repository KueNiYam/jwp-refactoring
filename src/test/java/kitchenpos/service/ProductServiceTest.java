package kitchenpos.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kitchenpos.domain.Product;
import kitchenpos.service.common.ServiceTest;

@DisplayName("ProductService 단위 테스트")
class ProductServiceTest extends ServiceTest {
	@Autowired
	private ProductService productService;

	@DisplayName("상품을 등록할 수 있다.")
	@Test
	void create() {
		Product input = new Product();
		input.setName("강정치킨");
		input.setPrice(BigDecimal.valueOf(17000));

		Product created = productService.create(input);

		assertAll(
			() -> assertThat(created.getId()).isEqualTo(7L),
			() -> assertThat(created.getName()).isEqualTo("강정치킨"),
			() -> assertThat(created.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(17000))
		);
	}

	@DisplayName("상품의 가격은 0 원 이상이어야 한다.")
	@Test
	void create_WhenPriceLessThanZero() {
		Product input = new Product();
		input.setName("강정치킨");
		input.setPrice(BigDecimal.valueOf(-1));

		assertThatThrownBy(() -> productService.create(input))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("상품의 목록을 조회할 수 있다.")
	@Test
	void list() {
		assertThat(productService.list()).hasSize(6);
	}
}