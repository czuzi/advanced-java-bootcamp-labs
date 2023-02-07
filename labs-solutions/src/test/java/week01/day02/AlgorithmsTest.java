package week01.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

	@Test
	void findDuplicationTest() {
		assertEquals(1, new Algorithms().findDuplication(List.of(1,2,3,1,4,5,6)));
	}

	@Test
	void findDuplicationWhenThereIsntAnyTest() {
		assertThrows(IllegalArgumentException.class, () -> new Algorithms().findDuplication(List.of(1,2,3,4,5,6)), "There is no duplication");
	}
}